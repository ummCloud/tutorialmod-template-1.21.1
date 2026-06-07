package net.umcloud.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.umcloud.tutorialmod.block.ModBlocks;
import net.umcloud.tutorialmod.component.ModDataComponentTypes;
import net.umcloud.tutorialmod.item.ModItemGroups;
import net.umcloud.tutorialmod.item.ModItems;
import net.umcloud.tutorialmod.loot.ModLootTables;
import net.umcloud.tutorialmod.util.HammerUsageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tutorialmod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

		ModDataComponentTypes.registerDataComponentTypes();

		ModLootTables.registerLootTables();

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
	}
}