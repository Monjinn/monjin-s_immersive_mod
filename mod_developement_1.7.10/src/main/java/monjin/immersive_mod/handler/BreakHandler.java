package monjin.immersive_mod.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;

public class BreakHandler {
	@SubscribeEvent
	public void breakSpeed(BreakSpeed event) {
		int i = event.entityPlayer.getFoodStats().getFoodLevel();
		float x = event.originalSpeed;
		float f = event.originalSpeed;
		
		if (i == 20) {
			f = f * 1.1f;
		}
		else if (i >= 16) {
			f = f * 1.0f;
		}
		else {
			f = f * ((float)i / 20.0f) * 1.25f;
		}
		
		event.newSpeed = f;
	}
}
