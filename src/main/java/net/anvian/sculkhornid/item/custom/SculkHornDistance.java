package net.anvian.sculkhornid.item.custom;

import net.anvian.sculkhornid.SculkHornMod;
import net.anvian.sculkhornid.api.Helper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SculkHornDistance extends Item{
    public SculkHornDistance(Settings settings) {
        super(settings);
    }

    float DAMAGE = (float) SculkHornMod.CONFIG.DISTANCE_DAMAGE();//7.75
    int DISTANCE = SculkHornMod.CONFIG.DISTANCE_DISTANCE();//16
    int COOLDOWN =  SculkHornMod.CONFIG.DISTANCE_COOLDOWN();//200

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()){
            tooltip.add(Math.min(1, tooltip.size()), Text.of(I18n.translate("tooltip.distance", DISTANCE)));
            tooltip.add(Math.min(1, tooltip.size()), Text.of(I18n.translate("tooltip.cooldown.distance", Helper.ticksToSeconds(COOLDOWN))));
            tooltip.add(Math.min(1, tooltip.size()), Text.of(I18n.translate("tooltip.damage.distance", DAMAGE)));
        }else {
            tooltip.add(Math.min(1, tooltip.size()), Text.of(I18n.translate("tooltip_info_item.sculkhorn_shif")));
        }
        tooltip.add(Math.min(1, tooltip.size()), Text.of(I18n.translate("null")));
        tooltip.add(Math.min(1, tooltip.size()), Text.of(I18n.translate("tootip_sculkhorn_distance")));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.experienceLevel >= SculkHornMod.CONFIG.DISTANCE_EXPERIENCE_LEVEL() || user.isCreative()) {
            user.setCurrentHand(hand);
        }
        return super.use(world, user, hand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) { //x!= 0
        if (SculkHornMod.CONFIG.DISTANCE_USE_TIME() <= 0){
            return 1;
        }
        return SculkHornMod.CONFIG.DISTANCE_USE_TIME();//10
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(!world.isClient) {
            if(user instanceof PlayerEntity player) {
                if(player.experienceLevel >= SculkHornMod.CONFIG.DISTANCE_EXPERIENCE_LEVEL() || player.isCreative()){//5
                    if(!player.isCreative()){
                        player.addExperience(SculkHornMod.CONFIG.DISTANCE_REMOVE_EXPERIENCE());//-55
                        stack.damage(1, user, x -> x.sendToolBreakStatus(Hand.MAIN_HAND));
                    }
                    player.getItemCooldownManager().set(this,COOLDOWN);//200
                    spawnSonicBoom(world, user);
                }
            }
        }
        return super.finishUsing(stack, world, user);
    }

    private void spawnSonicBoom(World world, LivingEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 1.0f, 1.0f);

        Vec3d target = user.getPos().add(user.getRotationVector().multiply(DISTANCE)); //DISTANCE = 16
        Vec3d source = user.getPos().add(0.0, 1.6f, 0.0);
        Vec3d offsetToTarget = target.subtract(source);
        Vec3d normalized = offsetToTarget.normalize();

        // Spawn particles from the source to the target
        Set<Entity> hit = new HashSet<>();
        for (int particleIndex = 1; particleIndex < MathHelper.floor(offsetToTarget.length()) + 7; ++particleIndex) {
            Vec3d particlePos = source.add(normalized.multiply(particleIndex));
            ((ServerWorld) world).spawnParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1, 0.0, 0.0, 0.0, 0.0);

            // Locate entities around the particle location for damage
            hit.addAll(world.getEntitiesByClass(LivingEntity.class,
                    new Box(new BlockPos(particlePos.getX(), particlePos.getY(), particlePos.getZ())).expand(2),
                    it -> !(it instanceof WolfEntity || it instanceof VillagerEntity || it instanceof AllayEntity)));
        }

        // Don't hit ourselves
        hit.remove(user);

        // Find
        for (Entity hitTarget : hit) {
            if(hitTarget instanceof LivingEntity living) {
                living.damage(DamageSource.sonicBoom(user), DAMAGE);//7.75
                double vertical = 0.5 * (1.0 - living.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                double horizontal = 2.5 * (1.0 - living.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                living.addVelocity(normalized.getX()*horizontal, normalized.getY()*vertical, normalized.getZ()*horizontal);

            }
        }
    }
}
