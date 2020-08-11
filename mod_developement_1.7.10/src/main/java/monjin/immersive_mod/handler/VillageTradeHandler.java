package monjin.immersive_mod.handler;

import java.util.ArrayList;
import java.util.List;

import monjin.immersive_mod.constants.MerchantRecipes;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VillageTradeHandler {
	@SubscribeEvent
	public void onTrading(EntityInteractEvent event) {
		if (event.target instanceof EntityVillager) {

			EntityVillager villager = (EntityVillager) event.target;

			switch (villager.getProfession()) {
			case 0:
				setRecipes(MerchantRecipes.getRecipesForFarmer(), villager,
						event.entityPlayer);
				break;
			case 1:
				setRecipes(MerchantRecipes.getRecipesForLibrarian(), villager,
						event.entityPlayer);
				break;
			case 2:
				setRecipes(MerchantRecipes.getRecipesForPriest(), villager,
						event.entityPlayer);
				break;
			case 3:
				setRecipes(MerchantRecipes.getRecipesForBlacksmith(), villager,
						event.entityPlayer);
				break;
			case 4:
				setRecipes(MerchantRecipes.getRecipesForButcher(), villager,
						event.entityPlayer);
				break;
			}
		}
	}

	public static void setRecipes(List<MerchantRecipe> newRecipes,
			EntityVillager villager, EntityPlayer player) {
		for (int i = 0; i < newRecipes.size(); i++) {
			villager.getRecipes(player).add(newRecipes.get(i));
		}
	}
}
