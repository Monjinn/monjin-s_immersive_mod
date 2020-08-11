package monjin.immersive_mod.handler;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

import org.lwjgl.Sys;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.gameevent.TickEvent;

public class SpawnHandler {
	private Random rand = new Random();

	@SubscribeEvent
	public void onMobTick(LivingUpdateEvent event) {
		if (event.entity instanceof EntityMob && !(event.entity instanceof EntityEnderman)) {
			EntityMob mob = (EntityMob)event.entity;
			EntityPlayer player = event.entity.worldObj.getClosestVulnerablePlayerToEntity(event.entity, 300.0D);
		}
	}
	
	/**
	 * Sets settings for mobs that spawn
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public void onSpawn(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityMob) {
			//EntityLivingBase entity = (EntityLivingBase)event.entity;
			EntityMob mob = (EntityMob)event.entity;
			EntityPlayer player = event.entity.worldObj.getClosestVulnerablePlayerToEntity(event.entity, 300.0D);
			
			if  (!(event.entity instanceof EntityPigZombie)) {
				if (player == null) {
					event.setCanceled(true);
					event.setResult(Result.DENY);
				}
				else {
					double heightDifference = Math.abs(mob.posY - player.posY);
					if (heightDifference > 30) {
						event.setCanceled(true);
						event.setResult(Result.DENY);
					}
				}
			}
			else {
				mob.setTarget(player);
			}
		}
		if (event.entity instanceof EntityZombie) {
			if (rand.nextInt(7) < 2)
				event.entity.setSprinting(true);
		}
		if (event.entity instanceof EntityVillager) {
			// event.entity.setDead();
		}
	}
}
