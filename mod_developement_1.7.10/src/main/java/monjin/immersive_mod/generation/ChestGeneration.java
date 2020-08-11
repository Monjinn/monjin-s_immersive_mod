package monjin.immersive_mod.generation;

import java.util.ArrayList;
import java.util.List;

import monjin.immersive_mod.ImmersiveMod;
import monjin.immersive_mod.constants.Indexes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ChestGeneration {
	public static void setCustomChestGeneration() {
		
		List<ChestGenHooks> categories = new ArrayList<ChestGenHooks>();
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY));
		categories.add(ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH));
		
		for (ChestGenHooks category : categories) {
			
			//TODO Fix chest generation to get more diamonds and other awesome stuff
		}
	}
}
