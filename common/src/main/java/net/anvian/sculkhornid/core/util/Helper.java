package net.anvian.sculkhornid.core.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Helper {
    public static void causeMagicExplosionAttack(ServerLevel serverLevel, LivingEntity user, LivingEntity victim, float damageAmount, float distance) {
        DamageSource magicExplosion = serverLevel.damageSources().explosion(user, user);
        for (LivingEntity nearbyEntity : getAoeTargets(serverLevel, victim, user, distance)) {
            nearbyEntity.hurtServer(serverLevel, magicExplosion, damageAmount);
        }
    }

    private static List<LivingEntity> getAoeTargets(ServerLevel serverLevel, LivingEntity center, LivingEntity attacker, float distance) {
        AABB area = new AABB(center.blockPosition()).inflate(distance);
        return serverLevel.getEntitiesOfClass(LivingEntity.class, area, (nearbyEntity) -> isAoeTarget(nearbyEntity, attacker, center));
    }

    private static boolean isAoeTarget(LivingEntity self, LivingEntity attacker, LivingEntity center) {
        return self != attacker
                && self.isAlive()
                && !isAllOf(attacker, self)
                && !isUnaffected(self)
                && center.hasLineOfSight(self);
    }

    public static boolean isAllOf(LivingEntity self, LivingEntity other) {
        return self.isAlliedTo(other) ||
                excludeFromDamge(other);
    }

    public static boolean excludeFromDamge(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof Villager) ||
                (nearbyEntity instanceof Allay) ||
                (nearbyEntity instanceof Wolf && ((Wolf) nearbyEntity).isTame()) ||
                (nearbyEntity instanceof AbstractHorse && ((AbstractHorse) nearbyEntity).isTamed()) ||
                (nearbyEntity instanceof Parrot && ((Parrot) nearbyEntity).isTame()) ||
                (nearbyEntity instanceof Cat && ((Cat) nearbyEntity).isTame());
    }

    private static boolean isUnaffected(LivingEntity entity) {
        if (entity instanceof Player)
            return ((Player) entity).isCreative();
        return false;
    }
}
