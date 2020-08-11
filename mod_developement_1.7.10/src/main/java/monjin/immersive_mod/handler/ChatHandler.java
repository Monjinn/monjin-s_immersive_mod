package monjin.immersive_mod.handler;

import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ChatHandler {
	private int timer = 0;
	
	public ChatHandler() {
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		timer++;
		if (timer % 12000 == 0) {
			int foodLevel = event.player.getFoodStats().getFoodLevel();
			//event.player.addChatMessage(new ChatComponentText("Perhaps you should try to get some flint"));
			if (foodLevel <= 12 && foodLevel > 7) event.player.addChatMessage(new ChatComponentText("Maybe I should eat something.."));
			if (foodLevel <= 6 & foodLevel > 2) event.player.addChatMessage(new ChatComponentText("I feel ... hungry."));
			if (foodLevel <= 1) event.player.addChatMessage(new ChatComponentText("...So... hungry..."));
		}
		if (timer > 100000) timer = 0;
	}
}
