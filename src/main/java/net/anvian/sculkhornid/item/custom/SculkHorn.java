package net.anvian.sculkhornid.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class SculkHorn extends Item{
    public SculkHorn(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (world.isClient) {
            if (user.isCreative()) {
                playsound(world, user);
                user.getItemCooldownManager().set(this, 600); //add a cooldown 30s
            } else {
                if (user.experienceLevel >= 10) {
                    playsound(world, user);
                    user.experienceLevel -= 10;
                    user.getItemCooldownManager().set(this, 600); //add a cooldown 30s
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    private static void playsound(World world, PlayerEntity user){
        world.playSoundFromEntity(user, user, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.RECORDS, 10.0f, 1.0f);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, user.getPos(), GameEvent.Emitter.of(user));
    }


}