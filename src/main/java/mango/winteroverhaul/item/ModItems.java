package mango.winteroverhaul.item;

import mango.winteroverhaul.WinterOverhaul;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ExplosiveSnowball = new Item(new FabricItemSettings());

    public static void registerModItems() {
        WinterOverhaul.LOGGER.debug("Registering Mod Items for " + WinterOverhaul.MOD_ID);
        Registry.register(Registries.ITEM, new Identifier("winter_overhaul", "explosive_snowball"), ExplosiveSnowball);
    }

}
