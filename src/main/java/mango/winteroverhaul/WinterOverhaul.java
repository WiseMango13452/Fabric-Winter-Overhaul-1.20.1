package mango.winteroverhaul;

import mango.winteroverhaul.entities.custom.ExplosiveSnowmanEntity;
import mango.winteroverhaul.entities.custom.SawmanEntity;
import mango.winteroverhaul.registery.ModEntities;
import mango.winteroverhaul.registery.ModItemGroups;

import mango.winteroverhaul.registery.ModBlocks;
import mango.winteroverhaul.registery.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WinterOverhaul implements ModInitializer {

	public static final String MOD_ID = "winter_overhaul";
    public static final Logger LOGGER = LoggerFactory.getLogger("winter_overhaul");

	@Override
	public void onInitialize() {
		ModItemGroups.registerModGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FabricDefaultAttributeRegistry.register(ModEntities.SAWMAN, SawmanEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.EXPLOSIVE_SNOWMAN, ExplosiveSnowmanEntity.setAttributes());
	}
}