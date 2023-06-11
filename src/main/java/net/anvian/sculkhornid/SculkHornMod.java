package net.anvian.sculkhornid;

import net.anvian.sculkhornid.config.SculkConfig;
import net.anvian.sculkhornid.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SculkHornMod implements ModInitializer {
	public static final SculkConfig CONFIG = SculkConfig.createAndLoad();
	public static final String MOD_ID = "sculkhornid";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final RegistryKey<ItemGroup> SCULKHORNGROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "sculkhorngroup"));

	@Override
	public void onInitialize() {

		Registry.register(Registries.ITEM_GROUP, SCULKHORNGROUP, FabricItemGroup.builder()
				.icon(() -> new ItemStack(ModItems.SCULKHORN))
				.displayName(Text.translatable("itemGroup.sculkhornid.sculkhorngroup"))
				.build());

		ModItems.registerModItems();

		LOGGER.info("Hello from SculkHorn Mod!");
	}
}
