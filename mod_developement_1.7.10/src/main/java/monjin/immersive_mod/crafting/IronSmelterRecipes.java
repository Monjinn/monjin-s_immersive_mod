package monjin.immersive_mod.crafting;

import monjin.immersive_mod.ImmersiveMod;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IronSmelterRecipes {
public IronSmelterRecipes() { }
	
	public static ItemStack getSmeltingResult(Item item, Item item2) {
		return getOutput(item, item2);
	}
	
	public static ItemStack getOutput(Item item1, Item item2) {
		/*
		//steel nugget recipe
		if (item1 == Items.iron_ingot && item2 == Items.coal
				|| item2 == Items.iron_ingot && item1 == Items.coal) {
			return new ItemStack(ImmersiveMod.ingots.get(6));
		}
		//bronze nugget
		if (item1 == ImmersiveMod.ingots.get(2)
				&& item2 == ImmersiveMod.ingots.get(3)
				|| item2 == ImmersiveMod.ingots.get(2)
				&& item1 == ImmersiveMod.ingots.get(3)) {
			return new ItemStack(ImmersiveMod.ingots.get(5));
		}
		//steel nugget recipe
		if (item1 == ImmersiveMod.chunks.get(4) && item2 == Items.coal
				|| item2 == ImmersiveMod.chunks.get(4) && item1 == Items.coal) {
			return new ItemStack(ImmersiveMod.ingots.get(6));
		}
		//bronze nugget
		if (item1 == ImmersiveMod.chunks.get(2)
				&& item2 == ImmersiveMod.chunks.get(3)
				|| item2 == ImmersiveMod.chunks.get(2)
				&& item1 == ImmersiveMod.chunks.get(3)) {
			return new ItemStack(ImmersiveMod.ingots.get(5));
		}
		*/
		
		if (item1 == Items.coal) {
			if (item2 == Items.iron_ingot) {
				return new ItemStack(ImmersiveMod.steelIngot);
			}
		}
		else if (item2 == Items.iron_ingot) {
			if (item2 == Items.coal) {
				return new ItemStack(ImmersiveMod.steelIngot);
			}
		}
		
		return null;
	}
}
