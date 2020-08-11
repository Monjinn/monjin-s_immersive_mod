package monjin.immersive_mod.items;

import net.minecraft.item.Item;

public class ImmersiveShard extends Item {
	private String type = "shard";
	public String material;
	
	public ImmersiveShard(String material) {
		this.material = material;
	}
}
