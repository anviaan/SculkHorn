package net.anvian.sculkhornid.core.item.custom;

import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class SculkHorn extends Item {
    protected float DAMAGE;
    protected int COOLDOWN;
    protected int EXPERIENCE_LEVEL;
    protected int REMOVE_EXPERIENCE;

    public SculkHorn(Properties properties, float damage, int cooldown, int experienceLevel, int removeExperience) {
        super(properties);
        this.DAMAGE = damage;
        this.COOLDOWN = cooldown;
        this.EXPERIENCE_LEVEL = experienceLevel;
        this.REMOVE_EXPERIENCE = removeExperience;
    }

    @Override
    public abstract void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag);

    @Override
    public abstract InteractionResult use(Level level, Player player, InteractionHand interactionHand);

    protected void applyCooldownToBothHorns(Player player) {
        player.getCooldowns().addCooldown(ModItems.SCULKHORN.getDefaultInstance(), ModConfigs.areaCooldown);
        player.getCooldowns().addCooldown(ModItems.SCULKHORN_SONICBOOM.getDefaultInstance(), ModConfigs.distanceCooldown);
    }
}
