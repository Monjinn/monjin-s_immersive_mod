package monjin.immersive_mod.handler;

import com.ibm.icu.text.DecimalFormat;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class PlayerHealthHandler {
	public static final int CONST_BASEHEALTH = 6;
	public static final int CONST_BASEHUNGER = 17;
	public int tick = 0;
	public int newTick = 0;

	public PlayerHealthHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent
	public void playerEnters(PlayerEvent.PlayerLoggedInEvent event) {
		System.out.println("Player has logged in! NAME: "
				+ event.player.getGameProfile().getName());
	}

	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		int foodLevel = event.player.getFoodStats().getFoodLevel();
		int playerLevel = event.player.experienceLevel;
		int bonusHealth = playerLevel / 10;
		int maxHealth = CONST_BASEHEALTH + bonusHealth * 2;

		float health = event.player.getHealth();
		if (health > maxHealth) {
			event.player.setHealth(maxHealth);
			health = event.player.getHealth();
		}
		if (health < maxHealth
				&& event.player.getFoodStats().getFoodLevel() > 0) {
			if (tick >= 900 + 100 * (20 - foodLevel)) {
				event.player.heal(1.0f);
				tick = 0;
			}
		}
		tick++;
		
		if (bonusHealth == 0) {
			event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth)
			.setBaseValue(CONST_BASEHEALTH);
		}
		else {
			event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth)
			.setBaseValue(maxHealth);
		}

	}
}
