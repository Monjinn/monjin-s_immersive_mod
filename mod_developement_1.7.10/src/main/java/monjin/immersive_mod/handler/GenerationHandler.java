package monjin.immersive_mod.handler;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.OreGenEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GenerationHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
	public void onGenerate(OreGenEvent.GenerateMinable event) {
		/*
		if (event.type == event.type.IRON || event.type == event.type.GOLD
				|| event.type == event.type.DIAMOND) {
			event.setResult(Event.Result.DENY);
		}
		*/
	}
}
