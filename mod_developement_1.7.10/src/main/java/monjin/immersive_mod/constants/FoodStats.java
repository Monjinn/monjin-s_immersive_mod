package monjin.immersive_mod.constants;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FoodStats {
	public static void setCustomFoodStats() {
		ItemFood foodItem = (ItemFood) Items.bread;
	}
}
