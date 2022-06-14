package net.anvian.sculkhornid.item.custom;

import net.anvian.sculkhornid.api.Helper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SculkHorn extends Item{
    public SculkHorn(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            if(user.experienceLevel >= 5 || user.isCreative()){
                if(!user.isCreative()){
                    user.addExperience(-55);
                    //itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
                }
                user.getItemCooldownManager().set(this, 300); //add a cooldown 15s
                sonicBoom(user, user, 5.0f);
                Helper.causeMagicExplosionAttack(user, user, 45, 5.0f);
            }
        }if(world.isClient){
            if(user.experienceLevel >= 5 || user.isCreative()){
                world.playSoundFromEntity(user, user, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.RECORDS, 10.0f, 1.0f);
            }
        }
         return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    public static void sonicBoom(LivingEntity attacker, LivingEntity victim, float radius){
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(victim.world, victim.getX(), victim.getY()+0.25f, victim.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setParticleType(ParticleTypes.SONIC_BOOM);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(0);
        attacker.world.spawnEntity(areaEffectCloudEntity);
    }
}