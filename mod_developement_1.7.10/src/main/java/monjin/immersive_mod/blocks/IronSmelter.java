package monjin.immersive_mod.blocks;

import java.util.Random;

import monjin.immersive_mod.ImmersiveMod;
import monjin.immersive_mod.tileentity.TileEntityIronSmelter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IronSmelter extends BlockContainer {
	private final boolean isActive;
	private Random rand;
	private static boolean keepInventory = true;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public IronSmelter(boolean isActive) {
		super(Material.rock);
		
		this.isActive = isActive;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister
				.registerIcon("immersivemod:blockIronSmelterSide");
		this.iconFront = iconRegister
				.registerIcon(this.isActive ? "immersivemod:blockIronSmelterFrontActive"
						: "immersivemod:blockIronSmelterFrontIdle");
		this.iconTop = iconRegister
				.registerIcon("immersivemod:blockIronSmelterTop");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return metadata == 0 && side == 3 ? this.iconFront
				: side == 1 ? this.iconTop : (side == 0 ? this.iconTop
						: (side == metadata ? this.iconFront : this.blockIcon));
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	public Item getItemDropped(int i, Random random, int j) {
		return Item.getItemFromBlock(ImmersiveMod.ironSmelterIdle);
	}
	
	public void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			Block b1 = world.getBlock(x, y, z - 1);
			Block b2 = world.getBlock(x, y, z + 1);
			Block b3 = world.getBlock(x - 1, y, z);
			Block b4 = world.getBlock(x + 1, y, z);
			
			byte b0 = 3;
			
			if (b1.func_149730_j() && !b2.func_149730_j()) {
				b0 = 3;
			}
			if (b2.func_149730_j() && !b1.func_149730_j()) {
				b0 = 2;
			}
			if (b3.func_149730_j() && !b4.func_149730_j()) {
				b0 = 5;
			}
			if (b4.func_149730_j() && !b3.func_149730_j()) {
				b0 = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	if (world.isRemote) {
    		return true;
    	}else if (!player.isSneaking()) {
    		TileEntityIronSmelter entity = (TileEntityIronSmelter) world.getTileEntity(x, y, z);
    		if (entity != null) {
    			FMLNetworkHandler.openGui(player, ImmersiveMod.instance, ImmersiveMod.guiIDIronSmelter, world, x, y, z);
    		}
    		return true;
    	}else{
    		return false;
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityIronSmelter();
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (this.isActive) {
			int direction = world.getBlockMetadata(x, y, z);
			float x1 = (float)x + 0.5F;
			float y1 = (float)y + random.nextFloat() * 0.3F + 0.1F;
			float z1 = (float)z + 0.5F;
			
			float f = 0.52F;
			float f1 = random.nextFloat() * 0.4F - 0.2F;
			
			if (direction == 4) {
				world.spawnParticle("smoke", (double) (x1 - f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 - f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
			}
			if (direction == 5) {
				world.spawnParticle("smoke", (double) (x1 + f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
			}
			if (direction == 2) {
				world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1),
						(double) (z1 - f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f1), (double) (y1),
						(double) (z1 - f), 0D, 0D, 0D);
			}
			if (direction == 3) {
				world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1),
						(double) (z1 + f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f1), (double) (y1),
						(double) (z1 + f), 0D, 0D, 0D);
			}
		}
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entityPlayer, ItemStack itemStack) {
		int l = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360F) + 0.5D) & 3;
		
		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		
		if (itemStack.hasDisplayName()) {
			//((TileEntityIronSmelter)world.getTileEntity(x, y, z)).setGuiDisplayName(itemStack.getDisplayName());
		}
	}
	
	public static void updateBlockState(boolean isSmelting, World world, int xCoord, int yCoord, int zCoord) {
		
		int i = world.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity entity = world.getTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;
		
		if (isSmelting) {
			world.setBlock(xCoord, yCoord, zCoord, ImmersiveMod.ironSmelterActive);
		} else{
			world.setBlock(xCoord, yCoord, zCoord, ImmersiveMod.ironSmelterIdle);
		}
		
		keepInventory = false;
		world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		
		if (entity != null) {
			entity.validate();
			world.setTileEntity(xCoord, yCoord, zCoord, entity);
		}
	}
	
	public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata) {
		if (!keepInventory) {
			TileEntityIronSmelter tileEntity = (TileEntityIronSmelter) world.getTileEntity(x, y, z);
			if (tileEntity != null) {
				for (int i = 0; i < tileEntity.getSizeInventory(); i++) {
					ItemStack itemStack = tileEntity.getStackInSlot(i);
					if (itemStack != null) {
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
						
						while (itemStack.stackSize > 0) {
							int j = this.rand.nextInt(21) + 10;
							if (j > itemStack.stackSize) {
								j = itemStack.stackSize;
							}
							itemStack.stackSize -= j;
							EntityItem item = new EntityItem(world,
									(double) ((float) x + f),
									(double) ((float) y + f),
									(double) ((float) z + f2), new ItemStack(
											itemStack.getItem(), j,
											itemStack.getItemDamage()));
							if (itemStack.hasTagCompound()) {
								item.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
							}
							world.spawnEntityInWorld(item);
						}
					}
				}
				world.func_147453_f(x, y, z, oldBlock);
			}
		}
		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
	}
}
