package monjin.immersive_mod.tools;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemImmersiveAxe extends ItemAxe {
	public String type = "axe";
	public String material;
	public String toolTier;
	
	public ItemImmersiveAxe(ToolMaterial toolMaterial, String material) {
		super(toolMaterial);
		this.material = material;
		
		int harvestLevel = this.toolMaterial.getHarvestLevel();
		
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
