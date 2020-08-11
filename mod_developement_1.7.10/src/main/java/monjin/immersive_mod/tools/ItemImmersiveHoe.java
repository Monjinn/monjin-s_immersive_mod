package monjin.immersive_mod.tools;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemImmersiveHoe extends ItemHoe {
	public String material;
	public String toolTier;
	
	public ItemImmersiveHoe(ToolMaterial p_i45343_1_, String material) {
		super(p_i45343_1_);
		this.material = material;
		
		int harvestLevel = p_i45343_1_.getHarvestLevel();
		
		this.toolTier = "-";
		switch(harvestLevel) {
		case 0:
			this.toolTier = "Poor";
			break;
		case 1:
			this.toolTier = "Good";
			break;
		case 2:
			this.toolTier = "Excellent";
			break;
		case 3:
			this.toolTier = "Legendary";
			break;
		}
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
				+ "§r§b§o" + toolTier);
		
		list.add("§9" + "Durability: "
				+ "§r§b§o" + temp.toString()
				+ "%");
	}
}
