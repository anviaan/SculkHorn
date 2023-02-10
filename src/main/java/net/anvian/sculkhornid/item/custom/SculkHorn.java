package net.anvian.sculkhornid.item.custom;

import net.anvian.sculkhornid.SculkHornMod;
import net.anvian.sculkhornid.api.Helper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SculkHorn extends Item{
    public SculkHorn(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tootip_sculkhorn_area"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        float RADIUS = (float) SculkHornMod.CONFIG.AREA_RADIUS();//3.5
        int COOLDOWN = SculkHornMod.CONFIG.AREA_COOLDOWN();//300
        float DAMAGE= (float) SculkHornMod.CONFIG.AREA_DAMAGE();//11.5

        if (!world.isClient) {
            if(user.experienceLevel >= SculkHornMod.CONFIG.AREA_EXPERIENCE_LEVEL() || user.isCreative()){ //5
                if(!user.isCreative()){
                    user.addExperience(SculkHornMod.CONFIG.AREA_REMOVE_EXPERIENCE()); //-55
                    itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
                }
                sonicBoom(user, user, RADIUS);
                Helper.causeMagicExplosionAttack(user, user, DAMAGE, RADIUS);
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,60,0));
                user.getItemCooldownManager().set(this, COOLDOWN);
            }
        }if(world.isClient){
            if(user.experienceLevel >= SculkHornMod.CONFIG.AREA_EXPERIENCE_LEVEL() || user.isCreative()){
                world.playSoundFromEntity(user, user, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.RECORDS, 1.0f, 1.0f);
            }
        }

        if(user.experienceLevel < SculkHornMod.CONFIG.AREA_EXPERIENCE_LEVEL() && !user.isCreative()){
            return super.use(world, user, hand);
        }else{
            return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
        }

    }

    private static void sonicBoom(LivingEntity attacker, LivingEntity victim, float radius){
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(victim.world, victim.getX(), victim.getY()+0.25f, victim.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setParticleType(ParticleTypes.SONIC_BOOM);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(0);
        attacker.world.spawnEntity(areaEffectCloudEntity);
    }
}