package net.anvian.sculkhornid.item.custom;

import net.anvian.sculkhornid.api.Helper;
import net.anvian.sculkhornid.config.ModConfigDistance;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SculkHornDistance extends Item {
    public SculkHornDistance(Properties properties) {
        super(properties);
    }
    float DAMAGE = ModConfigDistance.DISTANCE_DAMAGE.get().floatValue();
    int DISTANCE = ModConfigDistance.DISTANCE_DISTANCE.get();
    int COOLDOWN =  ModConfigDistance.DISTANCE_COOLDOWN.get();

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            list.add(Math.min(1, list.size()), Component.nullToEmpty(I18n.get("tooltip.distance", DISTANCE)));
            list.add(Math.min(1, list.size()),Component.nullToEmpty(I18n.get("tooltip.cooldown.distance", Helper.ticksToSeconds(COOLDOWN))));
            list.add(Math.min(1, list.size()),Component.nullToEmpty(I18n.get("tooltip.damage.distance", DAMAGE)));
        }else {
            list.add(Math.min(1, list.size()),Component.nullToEmpty(I18n.get("tooltip_info_item.sculkhorn_shif")));
        }
        list.add(Math.min(1, list.size()),Component.nullToEmpty(I18n.get("null")));
        list.add(Math.min(1, list.size()),Component.nullToEmpty(I18n.get("tootip_sculkhorn_distance")));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(player.experienceLevel>= ModConfigDistance.DISTANCE_EXPERIENCE_LEVEL.get() || player.isCreative()){
            player.startUsingItem(hand);
        }
        return super.use(level, player, hand);
    }
    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return ModConfigDistance.DISTANCE_USE_TIME.get();
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack itemStack, int i) {
        super.onUseTick(level, entity, itemStack, i);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        InteractionHand interactionHand = user.getUsedItemHand();

        if(!level.isClientSide) {
            if(user instanceof Player player) {
                if(player.experienceLevel >= ModConfigDistance.DISTANCE_EXPERIENCE_LEVEL.get() || player.isCreative()){
                    if(!player.isCreative()){
                        player.giveExperiencePoints(ModConfigDistance.DISTANCE_REMOVE_EXPERIENCE.get());
                        stack.hurtAndBreak(1, player, (entity) -> {
                            entity.broadcastBreakEvent(interactionHand);
                        });
                    }
                    player.getCooldowns().addCooldown(this,COOLDOWN);
                    spawnSonicBoom(level, user);
                }
            }
        }
        return super.finishUsingItem(stack, level, user);
    }

    private void spawnSonicBoom(Level level, LivingEntity user){
        level.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 1.0f,1.0f);

        Vec3 target = user.position().add(user.getLookAngle().scale(DISTANCE));//distance
        Vec3 source = user.position().add(0.0,1.6f,0.0);
        Vec3 offSetToTarget = target.subtract(source);
        Vec3 normalizes = offSetToTarget.normalize();

        Set<Entity> hit = new HashSet<>();
        for(int particleIndex =1; particleIndex < Mth.floor(offSetToTarget.length()) +7; ++particleIndex){
            Vec3 particlePos = source.add(normalizes.scale(particleIndex));
            ((ServerLevel) level).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1,0.0, 0.0, 0.0, 0.0);

            hit.addAll(level.getEntitiesOfClass(LivingEntity.class,
                    new AABB(new BlockPos(particlePos.x, particlePos.y, particlePos.z)).inflate(2),
                    it -> !(it instanceof Wolf || it instanceof Villager || it instanceof Allay)));

            hit.remove(user);

            for (Entity hitTarget : hit){
                if(hitTarget instanceof  LivingEntity living){
                    living.hurt(DamageSource.sonicBoom(user),DAMAGE);
                    double vertical = 0.5 * (1.0 - living.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    double horizontal = 2.5 * (1.0 - living.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    living.push(normalizes.x()/horizontal, normalizes.y()/vertical, normalizes.z()/horizontal);
                }
            }
        }
    }
}
