package net.anvian.sculkhornid.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class Helper {

    public static void causeMagicExplosionAttack(LivingEntity user, LivingEntity victim, float damageAmount, float distance){
        DamageSource magicExplosion = DamageSource.explosion(user, user).setExplosive();
        for (LivingEntity nearbyEntity : getAoeTargets(victim, user, distance)) {
            nearbyEntity.damage(magicExplosion, damageAmount);
        }
    }

    private static List<LivingEntity> getAoeTargets(LivingEntity center, LivingEntity attacker, float distance) {
        return center.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(center.getBlockPos()).expand(distance),
                (nearbyEntity) -> isAoeTarget(nearbyEntity, attacker, center)
        );
    }

    private static boolean isAoeTarget(LivingEntity self, LivingEntity attacker, LivingEntity center) {
        return self != attacker
                && self.isAlive()
                && !isAllyOf(attacker, self)
                && !isUnaffected(self)
                && center.canSee(self);
    }
    private static boolean isAllyOf(LivingEntity self, LivingEntity other) {
        return self.isTeammate(other)
                || exludeFromDamage(other);
    }
    private static boolean exludeFromDamage(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof WolfEntity) ||
                (nearbyEntity instanceof VillagerEntity);
    }
    private static boolean isUnaffected(LivingEntity entity) {
        if (entity instanceof PlayerEntity)
            return ((PlayerEntity) entity).isCreative();
        return false;
    }
}
