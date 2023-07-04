package mango.winteroverhaul;

import mango.winteroverhaul.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WinterOverhaul implements ModInitializer {

	public static final String MOD_ID = "winteroverhaul";
    public static final Logger LOGGER = LoggerFactory.getLogger("winter-overhaul");

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}