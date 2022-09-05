package net.anvian.sculkhornid.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class SculkHornSonicBoom extends Item {

    //private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public SculkHornSonicBoom(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(!world.isClient){
            spawnSonicBoom(world, user);
            if(!user.isCreative()){
                user.getItemCooldownManager().set(this, 300);
                itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    private void spawnSonicBoom(World world, LivingEntity user){
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 3.0f, 1.0f);

        float heightOffset = 1.6f;
        int distance = 16;
        Vec3d target = user.getPos().add(user.getRotationVector().multiply(distance));
        Vec3d source = user.getPos().add(0.0, heightOffset, 0.0);
        Vec3d offsetToTarget = target.subtract(source);
        Vec3d normalized = offsetToTarget.normalize();

        // Spawn particles from the source to the target
        Set<Entity> hit = new HashSet<>();
        for (int particleIndex = 1; particleIndex < MathHelper.floor(offsetToTarget.length()) + 7; ++particleIndex) {
            Vec3d particlePos = source.add(normalized.multiply(particleIndex));
            ((ServerWorld) world).spawnParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1, 0.0, 0.0, 0.0, 0.0);

            // Locate entities around the particle location for damage
            hit.addAll(world.getEntitiesByClass(LivingEntity.class, new Box(new BlockPos(particlePos.getX(), particlePos.getY(), particlePos.getZ())).expand(2), it -> !(it instanceof WolfEntity)));
        }

        // Don't hit ourselves
        hit.remove((PlayerEntity) (Object) this);

        // Find
        for (Entity hitTarget : hit) {
            if (hitTarget instanceof LivingEntity living) {
                living.damage(DamageSource.sonicBoom((PlayerEntity) (Object) this), 10.0f);
                double vertical = 0.5 * (1.0 - living.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                double horizontal = 2.5 * (1.0 - living.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                living.addVelocity(normalized.getX() * horizontal, normalized.getY() * vertical, normalized.getZ() * horizontal);
            }
        }
    }
}
