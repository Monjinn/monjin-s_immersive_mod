package monjin.immersive_mod.items;

import net.minecraft.item.Item;

public class ImmersiveNugget extends Item {
	private String type = "nugget";
	public String material;
	
	public ImmersiveNugget(String material) {
		this.material = material;
	}
}
