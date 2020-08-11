package monjin.immersive_mod.handler;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class LoginHandler {
	private Random rand = new Random();
	private static final String[] messages = {
		", try not to die!",
		", and have a nice day!",
		", watch out for zombies!",
		", go punch that wood!",
		", have you found adamant yet?"
	};
	
	public LoginHandler() {
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void onPlayerEnter(PlayerEvent.PlayerLoggedInEvent event) {
		int a = rand.nextInt(4);
		event.player
		.addChatMessage(new ChatComponentText("Welcome "
				+ event.player.getGameProfile().getName()
				+ " to Monjin's ImmersiveMod" + messages[a]));
	}
}
