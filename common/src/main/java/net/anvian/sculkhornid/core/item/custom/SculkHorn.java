package net.anvian.sculkhornid.core.item.custom;

import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public abstract class SculkHorn extends Item {
    protected float DAMAGE;
    protected float COOLDOWN;
    protected int EXPERIENCE_LEVEL;
    protected int REMOVE_EXPERIENCE;

    public SculkHorn(Properties properties, float damage, float cooldown, int experienceLevel, int removeExperience) {
        super(properties);
        this.DAMAGE = damage;
        this.COOLDOWN = cooldown;
        this.EXPERIENCE_LEVEL = experienceLevel;
        this.REMOVE_EXPERIENCE = removeExperience;
    }

    @Override
    public abstract void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag tooltipFlag);

    @Override
    public abstract InteractionResult use(Level level, Player player, InteractionHand hand);

    protected void applyCooldownToBothHorns(Player player) {
        ItemStack sculkHorn = ModItems.SCULKHORN.getDefaultInstance();
        ItemStack sculkHornSonicBoom = ModItems.SCULKHORN_SONICBOOM.getDefaultInstance();

        UseCooldown sculkHornCooldown = sculkHorn.get(DataComponents.USE_COOLDOWN);
        if (sculkHornCooldown != null) {
            sculkHornCooldown.apply(sculkHorn, player);
        }

        UseCooldown sculkHornSonicBoomCooldown = sculkHornSonicBoom.get(DataComponents.USE_COOLDOWN);
        if (sculkHornSonicBoomCooldown != null) {
            sculkHornSonicBoomCooldown.apply(sculkHornSonicBoom, player);
        }
    }
}
