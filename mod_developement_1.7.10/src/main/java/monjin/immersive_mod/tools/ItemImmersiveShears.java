package monjin.immersive_mod.tools;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;

public class ItemImmersiveShears extends ItemShears {
	public String material;
	
	public ItemImmersiveShears(String material) {
		this.material = material;
	}
	
	/**
	 * Adds information for tool
	 */
	public void addInformation(ItemStack itemStack, EntityPlayer player,
			List list, boolean p_77624_4_) {
		Integer temp = itemStack.getItem().getMaxDamage();
		Double maxDamage = temp.doubleValue();
		temp = itemStack.getItem().getDamage(itemStack);
		Double damage = temp.doubleValue();
		damage = (maxDamage - damage) / maxDamage * 100;
		temp = damage.intValue();

		list.add("§9" + "Tooltier: "
				+ "§r§b§o" + "-");
		
		list.add("§9" + "Durability: "
				+ "§r§b§o" + temp.toString()
				+ "%");
	}
}
