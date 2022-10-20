package net.anvian.sculkhornid.item.custom;

import net.anvian.sculkhornid.api.Helper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SculkHorn extends Item {
    public SculkHorn(Properties properties) {super(properties);}
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tootip_sculkhorn_area"));
        super.appendHoverText(itemStack,level,list,tooltipFlag);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);

        if(!level.isClientSide){
            if(player.experienceLevel >= 5 || player.isCreative()){
                if(player.isCreative()){
                    player.giveExperienceLevels(-55);
                    itemstack.setCount(itemstack.getCount()-1);
                }
                sonicBoom(player, player, 3.5f);
                if(level.getDifficulty() == Difficulty.EASY){
                    Helper.causeMagicExplosionAttack(player, player,9,3.5f);
                }else if(level.getDifficulty() == Difficulty.HARD){
                    Helper.causeMagicExplosionAttack(player, player,22.5f,3.5f);
                }else{
                    Helper.causeMagicExplosionAttack(player, player,15,3.5f);
                }
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,6010));
                player.getCooldowns().addCooldown(this,300);
            }
        }if(level.isClientSide){
            if (player.experienceLevel >= 5 || player.isCreative()){
                level.playSound(player, player, SoundEvents.WARDEN_SONIC_BOOM, SoundSource.RECORDS,1.0f,1.0f);
            }
        }

        if(player.experienceLevel < 5 && player.isCreative()){
            return super.use(level, player,interactionHand);
        }else{
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
        }
    }

    private static void sonicBoom(LivingEntity attacker, LivingEntity victim, float radius){
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(victim.level, victim.getX(), victim.getY()+0.25f, victim.getZ());
        areaEffectCloud.setOwner(attacker);
        areaEffectCloud.setParticle(ParticleTypes.SONIC_BOOM);
        areaEffectCloud.setRadius(radius);
        areaEffectCloud.setDuration(0);
        attacker.level.addFreshEntity(areaEffectCloud);
    }
}
