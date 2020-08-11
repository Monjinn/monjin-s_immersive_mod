package monjin.immersive_mod.constants;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

public class MerchantRecipes {
	
	public static List<MerchantRecipe> getRecipesForButcher() {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();

		return recipes;
	}

	public static List<MerchantRecipe> getRecipesForFarmer() {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();

		return recipes;
	}

	public static List<MerchantRecipe> getRecipesForLibrarian() {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();

		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.aquaAffinity1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.fireProtection1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.featherFalling1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.blastProtection1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.projectileProtection1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.respiration1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.thorns1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.sharpness1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.smite1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.baneOfArthropods1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.looting1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.silktouch1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.power1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.luckOfTheSea1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8), EnchantedBooks.lure1));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 16), EnchantedBooks.luckOfTheSea2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 16), EnchantedBooks.lure2));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 20), EnchantedBooks.featherFalling2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 20), EnchantedBooks.projectileProtection2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 20), EnchantedBooks.blastProtection2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 20), EnchantedBooks.thorns2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 20), EnchantedBooks.baneOfArthropods2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 20), EnchantedBooks.sharpness2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 20), EnchantedBooks.power2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 20), EnchantedBooks.protection1));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.fireAspect1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.fortune1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.unbreaking1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.efficiency1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.fireProtection2));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.respiration2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.sharpness2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.looting2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.smite2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.luckOfTheSea3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.lure3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 32), EnchantedBooks.looting2));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.knockback1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.punch1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.flame1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.fireAspect2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.fortune2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.unbreaking2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.efficiency2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.respiration3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.sharpness3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.looting3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.smite3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.baneOfArthropods3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.protection2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12), EnchantedBooks.power3));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 20), EnchantedBooks.featherFalling3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 20), EnchantedBooks.fireProtection3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 20), EnchantedBooks.blastProtection3));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 30), EnchantedBooks.protection3));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.fireProtection4));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.blastProtection4));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.protection4));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.featherFalling4));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.thorns3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.sharpness4));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.smite4));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.power4));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.baneOfArthropods4));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.knockback2));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 40), EnchantedBooks.punch2));
		
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 64), EnchantedBooks.infinity1));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 64), EnchantedBooks.fortune3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 64), EnchantedBooks.efficiency5));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 64), EnchantedBooks.unbreaking3));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 64), EnchantedBooks.power5));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 64), EnchantedBooks.sharpness5));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 64), EnchantedBooks.smite5));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 64), EnchantedBooks.baneOfArthropods5));
		
		return recipes;
	}

	public static List<MerchantRecipe> getRecipesForPriest() {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
		
		
		
		return recipes;
	}

	public static List<MerchantRecipe> getRecipesForBlacksmith() {
		List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();

		/*
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8),
				new ItemStack(Items.gold_ingot, 2), new ItemStack(
						ImmersiveMod.bronzeArmor.get(ImmersiveMod.CONST_HELM))));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8),
				new ItemStack(Items.gold_ingot, 8), new ItemStack(
						ImmersiveMod.bronzeArmor.get(ImmersiveMod.CONST_CHEST))));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8),
				new ItemStack(Items.gold_ingot, 4), new ItemStack(
						ImmersiveMod.bronzeArmor
								.get(ImmersiveMod.CONST_LEGGINGS))));
		recipes.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 8),
				new ItemStack(Items.gold_ingot, 2), new ItemStack(
						ImmersiveMod.bronzeArmor.get(ImmersiveMod.CONST_BOOTS))));

		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12),
				new ItemStack(ImmersiveMod.mithrilArmor
						.get(ImmersiveMod.CONST_HELM))));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 16),
				new ItemStack(Items.diamond, 8),
				new ItemStack(ImmersiveMod.mithrilArmor
						.get(ImmersiveMod.CONST_CHEST))));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 16),
				new ItemStack(ImmersiveMod.mithrilArmor
						.get(ImmersiveMod.CONST_LEGGINGS))));
		recipes.add(new MerchantRecipe(new ItemStack(Items.diamond, 12),
				new ItemStack(ImmersiveMod.mithrilArmor
						.get(ImmersiveMod.CONST_BOOTS))));
		*/

		return recipes;
	}
}
