package monjin.immersive_mod.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

/**
 * Handles player movement speed when wearing armor
 * @author Monjin
 * 9.6.2015
 */
public class PlayerArmorHandler {
	/**
	 * Register evenhandler
	 */
	public PlayerArmorHandler() {
		FMLCommonHandler.instance().bus().register(this);
	}

	/**
	 * Handles the movement speed of player
	 * @param event
	 */
	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		int armor = event.player.getTotalArmorValue();
		int i = 0;
		/* Movement slowdown disabled
		if (event.player.getEquipmentInSlot(1) != null) {
				event.player.motionX *= 0.99;
				event.player.motionZ *= 0.99;
		}
		if (event.player.getEquipmentInSlot(2) != null) {
				event.player.motionX *= 0.94;
				event.player.motionZ *= 0.94;
		}
		if (event.player.getEquipmentInSlot(3) != null) {
				event.player.motionX *= 0.96;
				event.player.motionZ *= 0.96;
		}
		if (event.player.getEquipmentInSlot(4) != null) {
				event.player.motionX *= 0.97;
				event.player.motionZ *= 0.97;
		}
		*/
	}
}
