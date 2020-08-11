package monjin.immersive_mod.items;

import net.minecraft.item.Item;

public class ImmersiveChunk extends Item {
	private String type = "chunk";
	public String material;
	
	public ImmersiveChunk (String material) {
		this.material = material;
	}
}
