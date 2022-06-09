package net.anvian.sculkhornid.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SculkHorn extends Item {
    public SculkHorn(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            if (user.isCreative()) {
                world.playSound(user, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.AMBIENT, 10.0f, 1.0f);
                user.getItemCooldownManager().set(this, 600); //add a cooldown 30s

            } else {
                if (user.experienceLevel >= 10) {
                    world.playSound(user, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.AMBIENT, 10.0f, 1.0f);
                    user.experienceLevel -= 10;
                    user.getItemCooldownManager().set(this, 600); //add a cooldown 30s
                }
            }
        }
        return super.use(world, user, hand);
    }
}
