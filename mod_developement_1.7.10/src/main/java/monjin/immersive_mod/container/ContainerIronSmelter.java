package monjin.immersive_mod.container;

import monjin.immersive_mod.slot.SlotCobbleSmelter;
import monjin.immersive_mod.tileentity.TileEntityIronSmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerIronSmelter extends Container {
	private TileEntityIronSmelter smelter;
	private int dualCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
	
	public ContainerIronSmelter(InventoryPlayer invPlayer, TileEntityIronSmelter tileEntity) {
		dualCookTime = 0;
		lastBurnTime = 0;
		lastItemBurnTime = 0;
		
		smelter = tileEntity;
		
		//first input slot
		this.addSlotToContainer(new Slot(tileEntity, 0, 38, 23));
		//second input slot
		this.addSlotToContainer(new Slot(tileEntity, 1, 68, 23));
		//Fuel slot
		this.addSlotToContainer(new Slot(tileEntity, 2, 53, 58));
		//output slot
		this.addSlotToContainer(new SlotCobbleSmelter(invPlayer.player, tileEntity, 3, 127, 28));
		//Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		//Hotbar
		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}

	public void addCraftingToCrafters (ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.smelter.cookTime);
		crafting.sendProgressBarUpdate(this, 1, this.smelter.burnTime);
		crafting.sendProgressBarUpdate(this, 2, this.smelter.currentItemBurnTime);
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			if (this.dualCookTime != this.smelter.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.smelter.cookTime);
			}
			if (this.lastBurnTime != this.smelter.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.smelter.burnTime);
			}
			if (this.lastItemBurnTime != this.smelter.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, this.smelter.currentItemBurnTime);
			}
		}
		
		this.dualCookTime = this.smelter.cookTime;
		this.lastBurnTime = this.smelter.burnTime;
		this.lastItemBurnTime = this.smelter.currentItemBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue) {
		if (slot == 0) {
			this.smelter.cookTime = newValue;
		}
		if (slot == 1) {
			this.smelter.burnTime = newValue;
		}
		if (slot == 2) {
			this.smelter.currentItemBurnTime = newValue;
		}
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (par2 != 1 && par2 != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityFurnace.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (par2 >= 3 && par2 < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (par2 >= 30 && par2 < 39
						&& !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return smelter.isUseableByPlayer(player);
	}
}
