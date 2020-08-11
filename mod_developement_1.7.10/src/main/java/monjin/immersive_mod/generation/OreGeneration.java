package monjin.immersive_mod.generation;

import java.util.List;
import java.util.Random;

import monjin.immersive_mod.ImmersiveMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * Ore generation class for the mod.
 */
public class OreGeneration implements IWorldGenerator {

	/**
	 * Default generate method
	 */
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case 1:
			generateEnd(world, random, chunkX, chunkZ);
			break;
		case 0:
			generateOverworld(world, random, chunkX, chunkZ);
			break;
		case -1:
			generateNether(world, random, chunkX, chunkZ);
			break;
		}
	}

	/**
	 * Generates end ores
	 * 
	 * @param world
	 * @param rand
	 * @param x
	 * @param z
	 */
	public void generateEnd(World world, Random rand, int x, int z) {

	}

	/**
	 * Generates overWorld ores
	 * 
	 * @param world
	 * @param rand
	 * @param x
	 * @param z
	 */
	public void generateOverworld(World world, Random rand, int x, int z) {
		/**
		 * Generate ore: (ore, world, random, x, z, minveinsize, maxveinsize,
		 * chance, minY, maxY, generateIn(block))
		 */

		generateOre(Blocks.emerald_ore, world, rand, x, z, 2, 5, 3, 10, 45,
				Blocks.stone);

		generateOre(Blocks.coal_ore, world, rand, x, z, 10, 25, 20, 20, 60,
				Blocks.stone);

		generateOre(Blocks.gold_ore, world, rand, x, z, 3, 5, 17, 10, 25,
				Blocks.stone);

		generateOre(Blocks.iron_ore, world, rand, x, z, 8, 20, 20, 20, 55,
				Blocks.stone);

		generateOre(Blocks.diamond_ore, world, rand, x, z, 3, 7, 10, 7, 23,
				Blocks.stone);
		
		generateOre(Blocks.lapis_ore, world, rand, x, z, 2, 5, 15, 8, 25,
				Blocks.stone);
		
		generateOre(Blocks.redstone_ore, world, rand, x, z, 4, 7, 19, 10, 30,
				Blocks.stone);
	}

	/**
	 * Generates nether ores
	 * 
	 * @param world
	 * @param rand
	 * @param x
	 * @param z
	 */
	public void generateNether(World world, Random rand, int x, int z) {
		
	}

	/**
	 * Default method generate ore
	 */
	public void generateOre(Block block, World world, Random rand, int chunkX,
			int chunkZ, int minVeinSize, int maxVeinSize, int chance, int minY,
			int maxY, Block generateIn) {
		int veinSize = minVeinSize + rand.nextInt(maxVeinSize - minVeinSize);
		int heightRange = maxY - minY;
		WorldGenMinable gen = new WorldGenMinable(block, veinSize, generateIn);
		for (int i = 0; i < chance; i++) {
			int xRand = chunkX * 16 + rand.nextInt(16);
			int yRand = rand.nextInt(heightRange) + minY;
			int zRand = chunkZ * 16 + rand.nextInt(16);
			gen.generate(world, rand, xRand, yRand, zRand);
		}
	}
}
