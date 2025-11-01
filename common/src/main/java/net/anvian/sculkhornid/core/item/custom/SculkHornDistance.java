package net.anvian.sculkhornid.core.item.custom;

import net.anvian.anvianslib.util.TimeUtil;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SculkHornDistance extends SculkHorn {
    public SculkHornDistance(ModConfigs.SculkHornConfig config, Properties properties) {
        super(
                config,
                properties,
                (float) config.distanceDamage,
                TimeUtil.secondsToTicks((float) config.distanceCooldown),
                config.distanceExperienceLevel,
                config.distanceRemoveExperience
        );
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(Math.abs(REMOVE_EXPERIENCE))).append(" ").append(Component.translatable("tooltip.experience")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(this.config.distanceDistance)).append(" ").append(Component.translatable("tooltip.distance")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(TimeUtil.ticksToSeconds(this.COOLDOWN))).append(" ").append(Component.translatable("tooltip.cooldown")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(this.DAMAGE)).append(" ").append(Component.translatable("tooltip.damage")).withStyle(ChatFormatting.DARK_GREEN));
        } else {
            list.add(Math.min(1, list.size()), Component.translatable("tooltip_info_item.sculkhorn_shif"));
        }
        list.add(Math.min(1, list.size()), Component.empty());
        list.add(Math.min(1, list.size()), Component.translatable("tootip_sculkhorn_distance"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (player.experienceLevel >= EXPERIENCE_LEVEL || player.isCreative()) {
            player.startUsingItem(interactionHand);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return config.distanceUseTime;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack itemStack, int i) {
        super.onUseTick(level, entity, itemStack, i);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        InteractionHand interactionHand = user.getUsedItemHand();

        if (!level.isClientSide) {
            if (user instanceof Player player) {
                if (player.experienceLevel >= EXPERIENCE_LEVEL || player.isCreative()) {
                    if (!player.isCreative()) {
                        player.giveExperiencePoints(REMOVE_EXPERIENCE);
                        stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(interactionHand));
                    }
                    if (config.bothInCooldown) {
                        applyCooldownToBothHorns(player);
                    } else {
                        player.getCooldowns().addCooldown(this, COOLDOWN);
                    }
                    spawnSonicBoom(level, user);
                }
            }
        }
        return super.finishUsingItem(stack, level, user);
    }

    private void spawnSonicBoom(Level level, LivingEntity user) {
        level.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 3.0f, 1.0f);

        Vec3 target = user.position().add(user.getLookAngle().scale(config.distanceDistance));
        Vec3 source = user.position().add(0.0, 1.6f, 0.0);
        Vec3 offSetToTarget = target.subtract(source);
        Vec3 normalized = offSetToTarget.normalize();

        Set<Entity> hit = new HashSet<>();
        for (int particleIndex = 1; particleIndex < Mth.floor(offSetToTarget.length()) + 7; particleIndex++) {
            Vec3 particlePos = source.add(normalized.scale(particleIndex));
            ((ServerLevel) level).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1, 0.0, 0.0, 0.0, 0.0);

            hit.addAll(level.getEntitiesOfClass(LivingEntity.class,
                    new AABB(new BlockPos((int) particlePos.x, (int) particlePos.y, (int) particlePos.z)).inflate(2),
                    it -> !(Helper.isAllOf(user, it))));

            hit.remove(user);

            for (Entity hitTarget : hit) {
                if (hitTarget instanceof LivingEntity living) {
                    living.hurt(level.damageSources().sonicBoom(user), DAMAGE);
                }
            }
        }
    }
}