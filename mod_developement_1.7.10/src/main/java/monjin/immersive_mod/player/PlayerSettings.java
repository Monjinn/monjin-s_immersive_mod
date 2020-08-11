package monjin.immersive_mod.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerSettings {
	@SubscribeEvent
	public void setCustomGameRules(WorldEvent.Load event) {
		event.world.getGameRules().setOrCreateGameRule("naturalRegeneration", "false");
	}
	

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.isPlayerFullyAsleep()) {
				player.addChatMessage(new ChatComponentText("After a full night's rest, you feel refreshed!"));
			}
		}
	}
}
