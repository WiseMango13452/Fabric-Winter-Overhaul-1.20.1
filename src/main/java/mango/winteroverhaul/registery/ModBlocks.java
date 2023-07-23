package mango.winteroverhaul.registery;

import mango.winteroverhaul.WinterOverhaul;
import mango.winteroverhaul.blocks.custom.WinterCore;
import mango.winteroverhaul.registery.ModItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModBlocks {
    public static final Block WINTER_CORE_BLOCK = new WinterCore(FabricBlockSettings.create().hardness(30f).resistance(1200f).nonOpaque().luminance(15));

    public static void registerModBlocks() {
        WinterOverhaul.LOGGER.debug("Registering Mod Blocks for " + WinterOverhaul.MOD_ID);

        //blocks

        Registry.register(Registries.BLOCK, new Identifier("winter_overhaul", "winter_core_block"), WINTER_CORE_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("winter_overhaul", "winter_core_block"), new BlockItem(WINTER_CORE_BLOCK, new FabricItemSettings().rarity(Rarity.RARE)));

        //add to group

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.WINTER_OVERHAUL).register(content -> {
            content.add(WINTER_CORE_BLOCK);
        });
    }
}
