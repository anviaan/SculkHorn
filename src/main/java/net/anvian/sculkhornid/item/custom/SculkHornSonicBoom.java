package net.anvian.sculkhornid.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Wolf;
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

public class SculkHornSonicBoom extends Item {
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public SculkHornSonicBoom(Properties properties, float attackDamage) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        if(slot == EquipmentSlot.MAINHAND) {
            return attributeModifiers;
        }
        return super.getDefaultAttributeModifiers(slot);
    }
    @Override
    public boolean isFoil(ItemStack itemStack) {
        return true;
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tootip_sculkhorn_range"));
        super.appendHoverText(itemStack,level,list,tooltipFlag);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(player.experienceLevel>= 5 || player.isCreative()){
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
        return 10;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack itemStack, int i) {
        super.onUseTick(level, entity, itemStack, i);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        if(!level.isClientSide) {
            if(user instanceof Player player) {
                if(player.experienceLevel >= 5 || player.isCreative()){
                    if(!player.isCreative()){
                        player.giveExperienceLevels(-55);
                        stack.setCount(stack.getCount()-1);
                    }
                    player.getCooldowns().addCooldown(this,200);
                    spawnSonicBoom(level, user);
                }
            }
        }
        return super.finishUsingItem(stack, level, user);
    }

    private void spawnSonicBoom(Level level, LivingEntity user){
        level.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 1.0f,1.0f);

        Vec3 target = user.position().add(user.getLookAngle().scale(16));//distance
        Vec3 source = user.position().add(0.0,1.6f,0.0);
        Vec3 offSetToTarget = target.subtract(source);
        Vec3 normalizes = offSetToTarget.normalize();

        Set<Entity> hit = new HashSet<>();
        for(int particleIndex =1; particleIndex < Mth.floor(offSetToTarget.length()) +7; ++particleIndex){
            Vec3 particlePos = source.add(normalizes.scale(particleIndex));
            ((ServerLevel) level).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1,0.0, 0.0, 0.0, 0.0);

            hit.addAll(level.getEntitiesOfClass(LivingEntity.class,
                    new AABB(new BlockPos(particlePos.x, particlePos.y, particlePos.z)).inflate(2),
                    it -> !(it instanceof Wolf || it instanceof Villager)));

            hit.remove(user);

            for (Entity hitTarget : hit){
                if(hitTarget instanceof  LivingEntity living){
                    living.hurt(DamageSource.sonicBoom(user),10.0f);
                    living.push(normalizes.x(), normalizes.y(), normalizes.z());
                }
            }
        }
    }
}
