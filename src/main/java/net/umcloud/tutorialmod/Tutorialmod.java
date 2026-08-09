package net.umcloud.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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
		AttackEntityCallback.EVENT.register(((player, world, hand, entity, hitResult) -> {
			if (entity instanceof SnifferEntity snifferEntity && !world.isClient()) {
				if (player.getMainHandStack().getItem() == Items.END_ROD) {
					player.sendMessage(Text.literal("bruh"));
					player.getMainHandStack().decrement(1);
					snifferEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 6));
				}

				return ActionResult.PASS;
			}

			return ActionResult.SUCCESS;
		}));
	}
}