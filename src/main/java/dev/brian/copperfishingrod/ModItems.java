package dev.brian.copperfishingrod;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class ModItems {
	private static final ResourceKey<Item> COPPER_FISHING_ROD_KEY =
		ResourceKey.create(Registries.ITEM, Copperfishingrod.id("copper_fishing_rod"));

	// Same durability and enchantability as the vanilla rod.
	public static final CopperFishingRodItem COPPER_FISHING_ROD = Registry.register(BuiltInRegistries.ITEM,
		COPPER_FISHING_ROD_KEY, new CopperFishingRodItem(new Item.Properties()
			.setId(COPPER_FISHING_ROD_KEY)
			.durability(64)
			.enchantable(1)
			.repairable(Items.COPPER_INGOT)));

	private ModItems() {
	}

	public static void initialize() {
		ResourceKey<CreativeModeTab> tools = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
			Identifier.withDefaultNamespace("tools_and_utilities"));
		CreativeModeTabEvents.modifyOutputEvent(tools).register(output -> output.insertAfter(
			Items.FISHING_ROD, COPPER_FISHING_ROD));
	}
}
