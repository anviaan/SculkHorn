package net.anvian.sculkhornid.core.item.custom;

import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class SculkHornArea extends SculkHorn {
    public SculkHornArea(ModConfigs.SculkHornConfig config, Properties properties) {
        super(config, properties, (float) config.areaDamage, (float) config.areaCooldown, config.areaExperienceLevel, config.areaRemoveExperience);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            componentConsumer.accept(CommonComponents.space().append(Component.empty().append(String.valueOf(Math.abs(REMOVE_EXPERIENCE))).append(" ").append(Component.translatable("tooltip.experience")).withStyle(ChatFormatting.DARK_GREEN)));
            componentConsumer.accept(CommonComponents.space().append(Component.empty().append(String.valueOf(config.areaRadius)).append(" ").append(Component.translatable("tooltip.radius")).withStyle(ChatFormatting.DARK_GREEN)));
            componentConsumer.accept(CommonComponents.space().append(String.valueOf(COOLDOWN)).append(" ").append(Component.translatable("tooltip.cooldown")).withStyle(ChatFormatting.DARK_GREEN));
            componentConsumer.accept(Component.empty().append(String.valueOf(DAMAGE)).append(" ").append(Component.translatable("tooltip.damage")).withStyle(ChatFormatting.DARK_GREEN));
        } else {
            componentConsumer.accept(Component.translatable("tooltip_info_item.sculkhorn_shif"));
        }
        componentConsumer.accept(Component.empty());
        componentConsumer.accept(Component.translatable("tootip_sculkhorn_area"));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            UseCooldown usecooldown = itemstack.get(DataComponents.USE_COOLDOWN);
            if (player.experienceLevel >= EXPERIENCE_LEVEL || player.isCreative()) {
                if (!player.isCreative()) {
                    player.giveExperiencePoints(REMOVE_EXPERIENCE);
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
                }
                sonicBoom(player, player, (float) config.areaRadius);
                Helper.causeMagicExplosionAttack(level, player, player, DAMAGE, (float) config.areaRadius);
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, config.areaSpeedDuration, config.areaSpeedAmplifier));
                if (config.bothInCooldown) {
                    applyCooldownToBothHorns(player);
                } else {
                    if (usecooldown != null) {
                        usecooldown.apply(itemstack, player);
                    }
                }
            }
        }
        if (level.isClientSide) {
            if (player.experienceLevel >= EXPERIENCE_LEVEL || player.isCreative()) {
                level.playSound(player, player, SoundEvents.WARDEN_SONIC_BOOM, SoundSource.RECORDS, 1.0f, 1.0f);
            }
        }

        if (player.experienceLevel < EXPERIENCE_LEVEL && !player.isCreative()) {
            return InteractionResult.FAIL;
        } else {
            return InteractionResult.SUCCESS;
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
