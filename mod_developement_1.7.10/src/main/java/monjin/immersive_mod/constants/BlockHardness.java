package monjin.immersive_mod.constants;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BlockHardness {
	public static void setCustomHardness() {
		Blocks.stone.setHardness(22.0F);
		Blocks.cobblestone.setHardness(4.0F);
		Blocks.stone.setResistance(10.0F);
		Blocks.netherrack.setHardness(12.0F);
		Blocks.nether_brick.setHardness(8.0F);
		Blocks.sandstone.setHardness(5.0F);
	}
}
