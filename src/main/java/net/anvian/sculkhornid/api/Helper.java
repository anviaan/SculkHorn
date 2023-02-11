package net.anvian.sculkhornid.api;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Helper {
    public static float ticksToSeconds(int cooldown){
        return (float)cooldown / 20;
    }
    public static void causeMagicExplosionAttack(LivingEntity user, LivingEntity victim, float damageAmount, float distance){
        DamageSource magicExplosion = DamageSource.explosion(user, user).setExplosion();
        for(LivingEntity nearbyEntity : getAoeTargets(victim, user, distance)){
            nearbyEntity.hurt(magicExplosion, damageAmount);
        }
    }
    private static List<LivingEntity> getAoeTargets(LivingEntity center, LivingEntity attacker, float distance){
        return center.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class,
                new AABB(center.blockPosition()).inflate(distance),
                (nearbyEntity) -> isAoeTarget(nearbyEntity, attacker, center)
                );
    }
    private static boolean isAoeTarget(LivingEntity self, LivingEntity attacker, LivingEntity center){
        return self != attacker
                && self.isAlive()
                && !isAllOf(attacker, self)
                && !isUnaffected(self)
                && center.hasLineOfSight(self);
    }
    private static boolean isAllOf(LivingEntity self, LivingEntity other){
        return self.isAlliedTo(other) ||
                excludeFromDamge(other);
    }
    private static boolean excludeFromDamge(LivingEntity nearbyEntity){
        return (nearbyEntity instanceof Wolf) ||
                (nearbyEntity instanceof Villager) ||
                (nearbyEntity instanceof Allay);
    }
    private static boolean isUnaffected(LivingEntity entity){
        if (entity instanceof Player)
            return ((Player) entity).isCreative();
        return false;
    }
}
