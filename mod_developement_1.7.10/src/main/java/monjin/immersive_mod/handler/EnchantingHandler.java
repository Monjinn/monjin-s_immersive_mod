package monjin.immersive_mod.handler;

import net.minecraft.block.Block;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class EnchantingHandler {

	@SubscribeEvent
	public void onEnchant(PlayerInteractEvent event) {
		
		/*
		Block block = (Block)event.world.getBlock(event.x, event.y, event.z);
		// TileEntityEnchantmentTable te = (TileEntityEnchantmentTable)event.world.getTileEntity(event.x,event.y, event.z);
		ContainerPlayer openContainer = (ContainerPlayer)event.entityPlayer.openContainer;
		System.out.println(openContainer.windowId);
		
		
		if (openContainer instanceof ContainerEnchantment) {
			ContainerEnchantment container = (ContainerEnchantment) event.entityPlayer.openContainer;
			for (int i = 0; i < container.enchantLevels.length; i++) {
				container.enchantLevels[i] = 1;
			}
		}
		*/
		// block.

	}
}
