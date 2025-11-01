package net.anvian.sculkhornid.core.item.custom;

import net.anvian.anvianslib.util.TimeUtil;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class SculkHorn extends Item {
    protected final ModConfigs.SculkHornConfig config;

    protected final float DAMAGE;
    protected final int COOLDOWN;
    protected final int EXPERIENCE_LEVEL;
    protected final int REMOVE_EXPERIENCE;

    public SculkHorn(ModConfigs.SculkHornConfig config, Properties properties, float damage, int cooldown, int experienceLevel, int removeExperience) {
        super(properties);
        this.config = config;
        this.DAMAGE = damage;
        this.COOLDOWN = cooldown;
        this.EXPERIENCE_LEVEL = experienceLevel;
        this.REMOVE_EXPERIENCE = removeExperience;
    }

    @Override
    public abstract void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag);

    @Override
    public abstract InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand);

    protected void applyCooldownToBothHorns(Player player) {
        player.getCooldowns().addCooldown(ModItems.SCULKHORN.asItem(), TimeUtil.secondsToTicks((float) this.config.areaCooldown));
        player.getCooldowns().addCooldown(ModItems.SCULKHORN_SONICBOOM.asItem(), TimeUtil.secondsToTicks((float) this.config.distanceCooldown));
    }
}
