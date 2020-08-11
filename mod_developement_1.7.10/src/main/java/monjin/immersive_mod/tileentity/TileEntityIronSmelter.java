package monjin.immersive_mod.tileentity;

import monjin.immersive_mod.blocks.IronSmelter;
import monjin.immersive_mod.crafting.IronSmelterRecipes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityIronSmelter extends TileEntity implements ISidedInventory {

	private static final int coal = 1600;
	private static final int lava_bucket = 25000;
	private static final int coal_block = 15000;
	
	private ItemStack slots[];
	
	public static final int smeltSpeed = 400;
	/** 
	 * The number of ticks that the furnace will keep burning 
	 * */
	public int burnTime;
	/** 
	 * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for 
	 * */
	public int currentItemBurnTime;
	/** 
	 * The number of ticks that the current item has been cooking for 
	 * */
	public int cookTime;
	
	private static final int[] slots_top = new int[] {0, 1};
	private static final int[] slots_bottom = new int[] {3};
	private static final int[] slots_side = new int[] {2};
	
	private String customName;
	
	public TileEntityIronSmelter() {
		slots = new ItemStack[4];
	}
	
	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (slots[i] != null) {
			if (slots[i].stackSize <= j) {
				ItemStack itemStack = slots[i];
				slots[i] = null;
				return itemStack;
			}
			ItemStack itemStack1 = slots[i].splitStack(j);
			if(slots[i].stackSize == 0) {
				slots[i] = null;
			}
			return itemStack1;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (slots[i] != null) {
			ItemStack itemStack = slots[i];
			slots[i] = null;
			return itemStack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		slots[i] = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
		
	}

	@Override
	public String getInventoryName() {
		return "container.cobbleSmelter";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}else{
			return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64;
		}
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return i == 1 ? false : (i == 2 ? isItemFuel(itemStack) : true);
	}
	
	public static boolean isItemFuel(ItemStack itemStack) {
		return getItemBurnTime(itemStack) > 0;
	}

	private static int getItemBurnTime(ItemStack itemStack) {
		if (itemStack == null) {
			return 0;
		}
		else {
			Item item = itemStack.getItem();
			
			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);
				if (block == Blocks.coal_block) return coal_block;
			}
			if (item == Items.coal) return coal;
			if (item == Items.lava_bucket) return lava_bucket;
			
			GameRegistry.getFuelValue(itemStack);
		}
		return 0;
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int i) {
		return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack itemStack,
			int var3) {
		return this.isItemValidForSlot(var1, itemStack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack,
			int j) {
		return j != 0 || i != 1 || itemStack.getItem() == Items.bucket;
	}
	
	public int getSmelterProgressScaled(int i) {
		return (cookTime * i) / this.smeltSpeed;
	}	
	
	 public int getBurnTimeRemainingScaled(int i) {
		if (this.currentItemBurnTime == 0) {
				this.currentItemBurnTime = this.smeltSpeed;
		}
		return this.burnTime * i / this.currentItemBurnTime;
	 }
	
	public boolean canSmelt() {
		if (slots[0] == null || slots[1] == null) {
			return false;
		}
		
		ItemStack itemstack = IronSmelterRecipes.getSmeltingResult(slots[0].getItem(), slots[1].getItem());
		
		if (itemstack == null) {
			return false;
		}
		
		if (slots[3] == null) {
			return true;
		}
		
		if (!slots[3].isItemEqual(itemstack)) {
			return false;
		}
		
		if (slots[3].stackSize < getInventoryStackLimit() && slots[3].stackSize < slots[3].getMaxStackSize()) {
			return true;
		}else{
			return slots[3].stackSize < itemstack.getMaxStackSize();
		}
	}
	
	private void smeltItem() {
		if (canSmelt()) {
			ItemStack itemstack = IronSmelterRecipes.getSmeltingResult(slots[0].getItem(), slots[1].getItem());
			
			if (slots[3] == null) {
				slots[3] = itemstack.copy();
			}else if (slots[3].isItemEqual(itemstack)) {
				slots[3].stackSize += itemstack.stackSize;
			}
			
			for (int i = 0; i < 2; i++) {
				if (slots[i].stackSize <= 0) {
					slots[i] = new ItemStack(slots[i].getItem().setFull3D());
				}else{
					slots[i].stackSize--;
				}
				
				if (slots[i].stackSize <= 0){
					slots[i] = null;
				}
			}
		}
	}
	
	public void updateEntity() {
		boolean flag = this.burnTime > 0;
		boolean flag1= false;
		
		if(this.isBurning()) {
			this.burnTime--;
		}
		
		if(!worldObj.isRemote) {
			if (this.burnTime == 0 && this.canSmelt()) {
				this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[2]);
				
				if(this.slots[2] != null) {
					flag1 = true;
					
					this.slots[2].stackSize--;
					
					if(this.slots[2].stackSize == 0) {
						this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
					}
				}
			}
			
			if (isBurning() && canSmelt()) {
				cookTime++;
				
				if (this.cookTime == this.smeltSpeed) {
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}else{
				cookTime = 0;
			}
			
			if (flag != this.isBurning()) {
				flag1 = true;
				IronSmelter.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		
		if (flag1) {
			this.markDirty();
		}
    }
	
	public void readFromNBT (NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		slots = new ItemStack[getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound nbt1 = (NBTTagCompound)list.getCompoundTagAt(i);
			byte b0 = nbt1.getByte("Slot");
			
			if (b0 >= 0 && b0 < slots.length) {
				slots[b0] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
		
		burnTime = nbt.getShort("BurnTime");
		cookTime = nbt.getShort("CookTime");
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short) this.burnTime);
		nbt.setShort("CookTime", (short) cookTime);
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("Slot", (byte)i);
				slots[i].writeToNBT(nbt1);
				list.appendTag(nbt1);
			}
		}
		
		nbt.setTag("Items", list);
	}
}
