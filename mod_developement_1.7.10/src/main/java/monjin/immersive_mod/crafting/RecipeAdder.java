package monjin.immersive_mod.crafting;

import java.util.List;

import monjin.immersive_mod.ImmersiveMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeAdder {

	public enum ToolType {
		PICKAXE, AXE, SHOVEL, SWORD, SHEARS, HOE
	}

	public static void AddCustomRecipes(List<String> toolTypes) {

		//Brewing stand from stick
		//Cobblestone from gavel

		//Flint Tools
		GameRegistry.addRecipe(new ItemStack(ImmersiveMod.flintHatchet),
						new Object[] { "SF", "S ", 'S', Items.stick, 'F',
								Items.flint });
		GameRegistry.addRecipe(new ItemStack(ImmersiveMod.flintPickaxe),
				new Object[] { "FFF", " S ", " S ", 'S', Items.stick, 'F',
						Items.flint });

		 //Saplings for sticks recipe
		GameRegistry.addRecipe(new ItemStack(Items.stick, 2), new Object[] {
				"F", 'F', Blocks.sapling });

		//Gravel to cobblestone
		GameRegistry.addRecipe(new ItemStack(Blocks.cobblestone), new Object[] {
				"FF", "FF", 'F', Blocks.gravel });
		
		GameRegistry.addRecipe(new ItemStack(Items.flint), new Object[] {
			"F", 'F', Blocks.gravel });
		
		GameRegistry.addRecipe(new ItemStack(Items.gunpowder), new Object[] {
			"FS", 'S', Items.coal, 'F', Items.clay_ball });
		

	}

	public static void createArmorRecipes(Item material, List<Item> armors) {
		GameRegistry.addRecipe(new ItemStack(armors.get(0)), new Object[] {
				"FFF", "F F", 'F', material });
		GameRegistry.addRecipe(new ItemStack(armors.get(1)), new Object[] {
				"F F", "FFF", "FFF", 'F', material });
		GameRegistry.addRecipe(new ItemStack(armors.get(2)), new Object[] {
				"FFF", "F F", "F F", 'F', material });
		GameRegistry.addRecipe(new ItemStack(armors.get(3)), new Object[] {
				"F F", "F F", 'F', material });
	}

	/**
	 * Methood for creating single tool recipe
	 * 
	 * @param toolType
	 * @param material
	 */
	public static void createToolRecipe(ToolType toolType, Item material,
			Item output) {
		switch (toolType) {
		case PICKAXE:
			GameRegistry.addRecipe(new ItemStack(output), new Object[] { "FFF",
					" S ", " S ", 'S', Items.stick, 'F', material });
			break;
		case AXE:
			GameRegistry.addRecipe(new ItemStack(output), new Object[] { "FF",
					"SF", "S ", 'S', Items.stick, 'F', material });
			break;
		case HOE:
			GameRegistry.addRecipe(new ItemStack(output), new Object[] { "FF",
					"S ", "S ", 'S', Items.stick, 'F', material });
			break;
		case SWORD:
			GameRegistry.addRecipe(new ItemStack(output), new Object[] { "F",
					"F", "S", 'S', Items.stick, 'F', material });
			break;
		case SHOVEL:
			GameRegistry.addRecipe(new ItemStack(output), new Object[] { "F",
					"S", "S", 'S', Items.stick, 'F', material });
			break;
		case SHEARS:
			GameRegistry.addRecipe(new ItemStack(output), new Object[] { " F",
					"F ", 'F', material });
			break;
		}
	}
}
