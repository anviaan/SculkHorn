package net.anvian.sculkhornid.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

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
                }
                if (!user.isCreative()){
                    itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
                }
                user.getItemCooldownManager().set(this, 500); //add a cooldown 30s
            }
        }if(world.isClient){
            if(user.experienceLevel >= 5 || user.isCreative()){
                world.playSoundFromEntity(user, user, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.RECORDS, 10.0f, 1.0f);
            }
        }
         return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}