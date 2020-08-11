package monjin.immersive_mod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ImmersiveInventory extends AbstractInventory {

	/** The name for your custom inventory, possibly just "Inventory" */
	private final String name = "Custom Inventory";

	/**
	 * In case your inventory name is too generic, define a name to store the
	 * NBT tag in as well
	 */
	private final String tagName = "CustomInvTag";

	/** Define the inventory size here for easy reference */
	// This is also the place to define which slot is which if you have
	// different types,
	// for example SLOT_SHIELD = 0, SLOT_AMULET = 1;
	public static final int INV_SIZE = 2;

	/**
	 * Inventory's size must be same as number of slots you add to the Container
	 * class
	 */
	ItemStack[] inventory = new ItemStack[INV_SIZE];

	@Override
	public int getSizeInventory() {
		return inventory.length;

	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);

		if (stack != null) {

			if (stack.stackSize > amount) {
				stack = stack.splitStack(amount);

				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
			else {
				setInventorySlotContents(slot, null);
			}
			this.onInventoryChanged();
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);

		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		this.inventory[slot] = itemstack;

		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}
	
	/**
	 * 
	 * Our custom slots are similar to armor - only one item per slot
	 */
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	public void onInventoryChanged() {
		for (int i = 0; i < this.getSizeInventory(); ++i) {

			if (this.getStackInSlot(i) != null
					&& this.getStackInSlot(i).stackSize == 0)
				this.setInventorySlotContents(i, null);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer){
		return true;

	}
	
	/**
	 * 
	 * This method doesn't seem to do what it claims to do, as
	 * 
	 * items can still be left-clicked and placed in the inventory
	 * 
	 * even when this returns false
	 */

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {

		// If you have different kinds of slots, then check them here:
		// if (slot == SLOT_SHIELD && itemstack.getItem() instanceof ItemShield)
		// return true;
		// For now, only ItemUseMana items can be stored in these slots

		return false;
	}

	public void writeToNBT(NBTTagCompound tagcompound) {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i) {

			if (this.getStackInSlot(i) != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		// We're storing our items in a custom tag list using our 'tagName' from
		// above
		// to prevent potential conflicts
		tagcompound.setTag(tagName, nbttaglist);
	}

	public void readFromNBT(NBTTagCompound tagcompound) {
		NBTTagList nbttaglist = tagcompound.getTagList(tagName, 0);

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.getCompoundTagAt(i);

			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.getSizeInventory()) {
				this.setInventorySlotContents(b0,
						ItemStack.loadItemStackFromNBT(nbttagcompound1));
			}
		}
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	public boolean isInventoryNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void copy(AbstractInventory inv) {
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack stack = inv.getStackInSlot(i);
			inventory[i] = (stack == null ? null : stack.copy());
		}
		markDirty();
	}

	@Override
	protected String getNbtKey() {
		return tagName;
	}

}