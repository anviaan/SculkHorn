package net.anvian.sculkhornid.item.custom;

import net.minecraft.entity.ai.brain.task.SonicBoomTask;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class SculkHorn extends Item {
    public SculkHorn(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient){
            PlayerEntity player = context.getPlayer();
            //Hand hand = context.getHand();
            //ItemStack stack = player.getStackInHand(hand);
            World world = context.getWorld();

            if(player.isCreative()){
                SonicBoomTask.cooldown(player, 40);
                world.playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.AMBIENT, 10.0f, 1.0f);
                player.getItemCooldownManager().set(this, 600); //add a cooldown 30s

            }else{
                if(player.experienceLevel >= 10){
                    SonicBoomTask.cooldown(player, 40);
                    world.playSound(player, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.AMBIENT, 10.0f, 1.0f);
                    player.experienceLevel -= 10;
                    player.getItemCooldownManager().set(this, 600); //add a cooldown 30s

                }else{
                    player.sendMessage(Text.of("Necesitas 10 niveles o más"), false);
                }
            }
        }



        return super.useOnBlock(context);
    }
}
