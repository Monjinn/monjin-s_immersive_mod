package monjin.immersive_mod.tools;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemImmersivePickaxe extends ItemPickaxe {
	public String type = "pickaxe";
	public String material;
	public String toolTier;

	public ItemImmersivePickaxe(ToolMaterial p_i45347_1_, String material) {
		super(p_i45347_1_);
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

	public void addInformation(ItemStack itemStack, List list) {
	}
}
