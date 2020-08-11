package monjin.immersive_mod.handler;

import java.util.Iterator;
import java.util.Map;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class AnvilHandler {

	@SubscribeEvent
	public void onRepair(AnvilUpdateEvent event) {

		ItemStack stackRight = event.right;
		ItemStack stackLeft = event.left.copy();
		// stackRight.setRepairCost(0);
		// stackLeft.setRepairCost(0);
		ItemStack output = null;
		event.cost = 1;

		/*
		 * ===
		 * Case when adding an enchatment on item
		 * ===
		 */
		if (stackRight.getItem() instanceof ItemEnchantedBook) {
			Map map = EnchantmentHelper.getEnchantments(stackRight);

			Iterator iterator1 = map.keySet().iterator();
			int l;

			while (iterator1.hasNext()) {
				l = ((Integer) iterator1.next()).intValue();
				Enchantment enchantment = Enchantment.enchantmentsList[l];

				if (enchantment != null) {

					boolean flag8 = enchantment.canApply(stackLeft);

					Iterator iterator = map.keySet().iterator();

					while (iterator.hasNext()) {
						int l1 = ((Integer) iterator.next()).intValue();

						Enchantment e2 = Enchantment.enchantmentsList[l1];
						if (l1 != l
								&& !(enchantment.canApplyTogether(e2) && e2
										.canApplyTogether(enchantment))) {
							flag8 = false;
						}
						
						// When the books enchantment is the one currently in iterator
						if (flag8) {
							event.output = stackLeft;
							Map itemEnchant = EnchantmentHelper.getEnchantments(stackLeft);
							map.putAll(itemEnchant);
							
							// Increase cost for every enchantment that the item has
							for (int i = 0; i < itemEnchant.size(); i++) {
								event.cost++;
							}
							
							// Set enchantments
							EnchantmentHelper
									.setEnchantments(map, event.output);
							break;
						} else {
							event.output = null;
						}
					}
				}
			}
		/*
		 * ===
		 * Case when item is to be repaired 
		 * ===
		 */
		} else if (stackLeft.isItemStackDamageable() 
				&& stackLeft.getItem().getIsRepairable(event.left, stackRight)) {
			int j = Math.min(stackLeft.getItemDamage(),
					stackLeft.getMaxDamage() / 4);
			int l, k;

			if (j <= 0) {
				event.output = null;
				event.setCanceled(true);
				return;
			}

			for (k = 0; j > 0 && k < stackRight.stackSize; k++) {
				l = stackLeft.getItemDamage() - j;
				stackLeft.setItemDamage(l);
				output = event.output;
				j = Math.min(stackLeft.getItemDamage(),
						stackLeft.getMaxDamage() / 4);
			}
			event.output = stackLeft;
		}
		event.setResult(Result.ALLOW);
	}
}
