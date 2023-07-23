package mango.winteroverhaul.registery;

import mango.winteroverhaul.WinterOverhaul;
import mango.winteroverhaul.item.ExplosiveSnowballItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SNOW_GEM = new Item(new FabricItemSettings());
    public static final ExplosiveSnowballItem EXPLOSIVE_SNOWBALL = new ExplosiveSnowballItem();

    public static void registerModItems() {
        WinterOverhaul.LOGGER.debug("Registering Mod Items for " + WinterOverhaul.MOD_ID);

        Registry.register(Registries.ITEM, new Identifier(WinterOverhaul.MOD_ID, "snow_gem"), SNOW_GEM);
        Registry.register(Registries.ITEM, new Identifier(WinterOverhaul.MOD_ID, "explosive_snowball"), EXPLOSIVE_SNOWBALL);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.WINTER_OVERHAUL).register(content -> {
            content.add(SNOW_GEM);
            content.add(EXPLOSIVE_SNOWBALL);
        });
    }
}
