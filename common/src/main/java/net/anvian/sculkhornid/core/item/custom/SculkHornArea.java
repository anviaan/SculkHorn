package net.anvian.sculkhornid.core.item.custom;

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
    float RADIUS = (float) ModConfigs.areaRadius;
    int SPEED_DURATION = ModConfigs.areaSpeedDuration;
    int SPEED_AMPLIFIER = ModConfigs.areaSpeedAmplifier;

    public SculkHornArea(Properties properties) {
        super(properties, (float) ModConfigs.areaDamage, ModConfigs.areaCooldown, ModConfigs.areaExperienceLevel, ModConfigs.areaRemoveExperience);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(Math.abs(REMOVE_EXPERIENCE))).append(" ").append(Component.translatable("tooltip.experience")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(RADIUS)).append(" ").append(Component.translatable("tooltip.radius")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(Helper.ticksToSeconds(COOLDOWN))).append(" ").append(Component.translatable("tooltip.cooldown")).withStyle(ChatFormatting.DARK_GREEN));
            list.add(Math.min(1, list.size()), Component.empty().append(String.valueOf(DAMAGE)).append(" ").append(Component.translatable("tooltip.damage")).withStyle(ChatFormatting.DARK_GREEN));
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
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
                }
                sonicBoom(player, player, RADIUS);
                Helper.causeMagicExplosionAttack(level, player, player, DAMAGE, RADIUS);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, SPEED_DURATION, SPEED_AMPLIFIER));
                if (ModConfigs.bothInCooldown) {
                    applyCooldownToBothHorns(player);
                } else {
                    player.getCooldowns().addCooldown(this, COOLDOWN);
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
