package monjin.immersive_mod.crafting;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class RecipeRemover {
	/**
	 * Method that removes all recipes below
	 */
	public static void removeRecipes() {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

		Iterator<IRecipe> remover = recipes.iterator();
		while (remover.hasNext()) {
			ItemStack is = remover.next().getRecipeOutput();
			
			if (is != null && is.getItem() == Item.getItemFromBlock(Blocks.enchanting_table))
				remover.remove();
			
			/**
			 * Removing wooden tools
			 */
			if (is != null && is.getItem() == Items.wooden_axe)
				remover.remove();
			if (is != null && is.getItem() == Items.wooden_pickaxe)
				remover.remove();
			
			/**
			 * Removing stone tools
			 */
			if (is != null && is.getItem() == Items.stone_pickaxe)
				remover.remove();
			if (is != null && is.getItem() == Items.stone_shovel)
				remover.remove();
		}
	}
}
