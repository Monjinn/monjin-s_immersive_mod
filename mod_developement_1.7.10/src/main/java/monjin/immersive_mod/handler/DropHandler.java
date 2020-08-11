package monjin.immersive_mod.handler;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DropHandler {
	private static Random random;
	private boolean holdingRightTool;
	
	/**
	 * Catches the event where player breaks a block.
	 * When a log, it checks whether player is holding
	 * axe.
	 * @param event
	 */
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event) {
        holdingRightTool = false;
        
		if (event.block instanceof BlockLog) {
			System.out.println("Held item: " + event.getPlayer().getHeldItem());			
			if (event.getPlayer().getHeldItem() == null) {
				System.out.println("Trying to punch wood with fists, eh? Not dropping wood!");
				return;
			}
			/**
			 * Goes through all tools to check if the held item matches an axe
			 * or a hatchet. If the held item is an axe(or hatchet) the drop will be default.
			 */
			String asd = event.getPlayer().getHeldItem().getUnlocalizedName();
			if (event.getPlayer().getHeldItem().getUnlocalizedName()
				.contains("hatchet") || event.getPlayer().getHeldItem().getUnlocalizedName()
				.contains("Axe") 
				|| event.getPlayer().getHeldItem().getUnlocalizedName()
				.contains("Hatchet") ) {
				System.out.println("Right tool was held! Dropping wood!");
				holdingRightTool = true; // a right tool for harvesting was held on hand
			} 
		}

        /*
         * TODO THIS WORKS! MAKE IT BETTER! :)
         */
		if (event.getPlayer().getHeldItem() == null) {
			return;
		}
		Item item = event.getPlayer().getHeldItem().getItem();
        if (item instanceof ItemPickaxe) {
        	ItemPickaxe pickaxe = (ItemPickaxe)event.getPlayer().getHeldItem().getItem();
        	int damage = pickaxe.getDamage(event.getPlayer().getHeldItem());
        	damage += 2;
        	pickaxe.setDamage(event.getPlayer().getHeldItem(), damage);
        }
	}
	
	/**
	 * Catches the event where block is harvested, and
	 * must decide what to drop(harvestdropevent)
	 * @param event
	 */
	@SubscribeEvent
	public void onBlockHarvest(HarvestDropsEvent event) {
		if (event.block == null) return;
		if (event.harvester == null) return;
		/**
		 * Here we check if block harvested is a log, it drops nothing
		 * if tool held in hand wasn't an axe(or a hatchet).
		 */
		if (event.block instanceof BlockLog) {
			if (!this.holdingRightTool) {
				event.harvester.addChatMessage(new ChatComponentText(
						"Trying to punch wood, eh? Think again!"));
				event.drops.clear();
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		random = new Random();
		int amount = 0;
		int dropped = random.nextInt(2) + 1;

		if (event.entityLiving instanceof EntityCow) {
			int drop = random.nextInt(4);
			if (drop == 3) { // chance to drop an apple is 1 of 4
				amount = random.nextInt(3) + 1; // if it happens, drops at least one and in addition randomly from 0 to 2
				event.entityLiving.entityDropItem(new ItemStack(Items.apple, amount), dropped);
			}
		}
		if (event.entityLiving instanceof EntityChicken) {
			amount = random.nextInt(3) + 1; // if it happens, drops at least one
											// and in addition randomly from 0
											// to 2
			event.entityLiving.entityDropItem(new ItemStack(Items.feather,
					amount), dropped);
		}
		if (event.entityLiving instanceof EntitySheep) {
			event.entityLiving.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1), dropped);
		}
	}
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		EntityLivingBase el = event.entityLiving;
		double x = el.posX;
		double y = el.posY;
		double z = el.posZ;
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entity;
			int dropAmount = (int)((int)player.experienceTotal*0.95f);
			if (dropAmount > 0) {
				int orbs = 5;
				for (int i = 0; i < orbs; i++) {
					el.worldObj.spawnEntityInWorld(new EntityXPOrb(el.worldObj, x, y, z, (int)dropAmount/5));
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onChestOpen(PlayerOpenContainerEvent event) {
		
	}
}
