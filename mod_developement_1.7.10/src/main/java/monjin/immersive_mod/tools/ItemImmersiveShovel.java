package monjin.immersive_mod.tools;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemImmersiveShovel extends ItemSpade {
	public String material;
	public String toolTier;
	
	public ItemImmersiveShovel(ToolMaterial p_i45353_1_, String material) {
		super(p_i45353_1_);
		this.material = material;
		this.toolTier = p_i45353_1_.name().replace("ShovelMaterial", "");
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
