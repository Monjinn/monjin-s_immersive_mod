package monjin.immersive_mod.items;

import net.minecraft.item.Item;

public class ImmersiveIngot extends Item {
	private String type = "ingot";
	public String material;
	
	public ImmersiveIngot(String material) {
		this.material = material;
	}
}
