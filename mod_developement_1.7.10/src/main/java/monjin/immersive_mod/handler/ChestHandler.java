package monjin.immersive_mod.handler;

import java.util.ArrayList;
import java.util.List;

import akka.actor.FSM.Timer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChestHandler {
	/**
	 * Catches the event where player interacts with something. In case it is a
	 * chest we remove forbidden items. Forbidden items are vanilla items that
	 * are not meant to be used by the player in this mod.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public void onInteract(PlayerInteractEvent event) {
		if (event.action == event.action.RIGHT_CLICK_BLOCK) {
			Block block = event.world.getBlock(event.x, event.y, event.z);
			if (block instanceof BlockChest) {
				boolean isInCreative = false;
				if (event.entity instanceof EntityPlayerMP) {
					EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
					isInCreative = player.theItemInWorldManager.isCreative();
				}
				if (!isInCreative) {
					//event.entityPlayer.openContainer.putStackInSlot(26,
							//new ItemStack(Items.bread, 8));
				}
			}
		}
	}
}
