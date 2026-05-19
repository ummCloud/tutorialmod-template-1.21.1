package net.umcloud.tutorialmod.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.umcloud.tutorialmod.Tutorialmod;
import net.umcloud.tutorialmod.item.ModItems;

public class ModLootTables {

    public static final Identifier SNIFFER_DIGGING_LOOT_TABLE_ID = LootTables.SNIFFER_DIGGING_GAMEPLAY.getValue();

    public static void registerLootTables() {
        Tutorialmod.LOGGER.info("Registering loot tables for " + Tutorialmod.MOD_ID);

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {

            if (source.isBuiltin() && SNIFFER_DIGGING_LOOT_TABLE_ID.equals(key.getValue())) {

                tableBuilder.modifyPools(poolBuilder -> {
                    poolBuilder.with(ItemEntry.builder(ModItems.PINK_GARNET));

                });
            }
        });

    }
}
