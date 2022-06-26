package net.anvian.sculkhornid.item.custom;

import net.anvian.sculkhornid.api.Helper;
import net.anvian.sculkhornid.config.ModConfigs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SculkHorn extends Item{
    public SculkHorn(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        float RADIUS = (float) ModConfigs.RADIUS;
        int COOLDOWN = ModConfigs.COOLDOWN;
        float DAMAGE_EASY = (float) ModConfigs.DAMAGE_EASY;
        float DAMAGE_NORMAL = (float) ModConfigs.DAMAGE_NORMAL;
        float DAMAGE_HARD = (float) ModConfigs.DAMAGE_HARD;

        if (!world.isClient) {
            if(user.experienceLevel >= 5 || user.isCreative()){
                if(!user.isCreative()){
                    user.addExperience(-55);
                    itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
                }
                sonicBoom(user, user, RADIUS);
                if(world.getDifficulty() == Difficulty.EASY){
                    Helper.causeMagicExplosionAttack(user, user, DAMAGE_EASY, RADIUS); //9
                }else if(world.getDifficulty() == Difficulty.HARD){
                    Helper.causeMagicExplosionAttack(user, user, DAMAGE_HARD, RADIUS); //22.5f
                }else{
                    Helper.causeMagicExplosionAttack(user, user, DAMAGE_NORMAL, RADIUS); //15
                }
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,60,0));
                user.getItemCooldownManager().set(this, COOLDOWN); //add a cooldown 15s from config
            }
        }if(world.isClient){
            if(user.experienceLevel >= 5 || user.isCreative()){
                world.playSoundFromEntity(user, user, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.RECORDS, 10.0f, 1.0f);
            }
        }
         return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip_info_item.sculkhorn_1"));
            tooltip.add(Text.translatable("tooltip_info_item.sculkhorn_2"));
        }else{
            tooltip.add(Text.translatable("tooltip_info_item.sculkhorn_shif"));
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