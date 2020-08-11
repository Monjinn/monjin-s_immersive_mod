package monjin.immersive_mod.tools;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemImmersiveSword extends ItemSword {
	public String type = "sword";
	public String material;
	public String toolTier;
	
	public ItemImmersiveSword(ToolMaterial p_i45356_1_, String material) {
		super(p_i45356_1_);
		this.material = material;
		
		this.toolTier = p_i45356_1_.name().replace("SwordMaterial", "");
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
