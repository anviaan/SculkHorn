package net.anvian.sculkhornid.core.item.custom;

import net.anvian.anvianslib.util.TimeUtil;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SculkHornArea extends SculkHorn {
    public SculkHornArea(ModConfigs.SculkHornConfig config, Properties properties) {
        super(config, properties, (float) config.areaDamage, TimeUtil.secondsToTicks((float) config.areaCooldown), config.areaExperienceLevel, config.areaRemoveExperience);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(Math.abs(REMOVE_EXPERIENCE))).append(" ").append(Component.translatable("tooltip.experience")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(this.config.areaRadius)).append(" ").append(Component.translatable("tooltip.radius")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(TimeUtil.ticksToSeconds(this.COOLDOWN))).append(" ").append(Component.translatable("tooltip.cooldown")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(this.DAMAGE)).append(" ").append(Component.translatable("tooltip.damage")).withStyle(ChatFormatting.DARK_GREEN));
        } else {
            list.add(Math.min(1, list.size()), Component.translatable("tooltip_info_item.sculkhorn_shif"));
        }
        list.add(Math.min(1, list.size()), Component.empty());
        list.add(Math.min(1, list.size()), Component.translatable("tootip_sculkhorn_area"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);

        if (!level.isClientSide) {
            if (player.experienceLevel >= EXPERIENCE_LEVEL || player.isCreative()) {
                if (!player.isCreative()) {
                    player.giveExperiencePoints(REMOVE_EXPERIENCE);
                    itemstack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(interactionHand));
                }
                sonicBoom(player, player, (float) config.areaRadius);
                Helper.causeMagicExplosionAttack(level, player, player, DAMAGE, (float) config.areaRadius);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, config.areaSpeedDuration, config.areaSpeedAmplifier));
                if (config.bothInCooldown) {
                    applyCooldownToBothHorns(player);
                } else {
                    player.getCooldowns().addCooldown(this, this.COOLDOWN);
                }
            }
        }
        if (level.isClientSide) {
            if (player.experienceLevel >= EXPERIENCE_LEVEL || player.isCreative()) {
                level.playSound(player, player, SoundEvents.WARDEN_SONIC_BOOM, SoundSource.RECORDS, 1.0f, 1.0f);
            }
        }

        if (player.experienceLevel < EXPERIENCE_LEVEL && !player.isCreative()) {
            return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
        } else {
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
        }
    }

    private static void sonicBoom(LivingEntity attacker, LivingEntity victim, float radius) {
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(victim.level(), victim.getX(), victim.getY() + 0.25f, victim.getZ());
        areaEffectCloud.setOwner(attacker);
        areaEffectCloud.setParticle(ParticleTypes.SONIC_BOOM);
        areaEffectCloud.setRadius(radius);
        areaEffectCloud.setDuration(0);
        attacker.level().addFreshEntity(areaEffectCloud);
    }
}
