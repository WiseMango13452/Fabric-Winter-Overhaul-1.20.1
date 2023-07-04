package mango.winteroverhaul.item;

import mango.winteroverhaul.WinterOverhaul;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ExplosiveSnowball = registerItem("Explosive Snowball",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(WinterOverhaul.MOD_ID, name), item);
    }

    public static void registerModItems() {
        WinterOverhaul.LOGGER.debug("Registering Mod Items for " + WinterOverhaul.MOD_ID);
    }
}
