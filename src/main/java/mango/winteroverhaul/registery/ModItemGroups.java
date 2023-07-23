package mango.winteroverhaul.registery;

import mango.winteroverhaul.WinterOverhaul;
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

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> WINTER_OVERHAUL = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(WinterOverhaul.MOD_ID, "mod_group"));

    public static void registerModGroups() {
        Registry.register(Registries.ITEM_GROUP, WINTER_OVERHAUL, FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.winteroverhaul"))
                .icon(() -> new ItemStack(Items.SPRUCE_SAPLING))
                .build());
    }
}
