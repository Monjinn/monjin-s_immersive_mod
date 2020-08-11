package monjin.immersive_mod.handler;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CombatHandler {
	private double direction;
	private Random rand = new Random();
	private boolean hurtByPlayer;
	private int hunger;

	/**
	 * onAttack event handler
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public void onAttack(AttackEntityEvent event) {
		/* Gets the section(angle) of the attacking player facing. */
		direction = event.entityPlayer.rotationYaw;
		/* Sets if enemy was actually hurt by player(what happends
		 * next is up to living hurt event) */
		this.hurtByPlayer = true;
		
		/* Decreases saturiation when attacking */
		this.hunger = event.entityPlayer.getFoodStats().getFoodLevel();
		/* NOT ANYMORE
		if (event.entityPlayer.getFoodStats().getSaturationLevel() > 0.2f) {
			event.entityPlayer.getFoodStats()
					.setFoodSaturationLevel(
							event.entityPlayer.getFoodStats()
									.getSaturationLevel() - 0.1f);
		}
		*/
	}

	/**
	 * Sets what happens on entity being hurt event.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		float f = event.ammount;
		if (hurtByPlayer) {
			if (hunger == 20) {
				f = f * 1.0f;
			}
			else if (hunger >= 16) {
				f = f * 1.0f;
			}
			else {
				f = f * ((float)hunger / 20.0f) * 1.25f;
			}
			System.out.println("Damage amout: " + f);
		}
		event.ammount = f;
		if (this.hurtByPlayer) {
			if (hunger <= 6) {
				event.entityLiving.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0f);
			}
			else {
				event.entityLiving.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0f);
			}
		}
		if (!(event.entity instanceof EntityPlayer) && this.hurtByPlayer
				&& !(event.entity.isBurning())) {
			this.hurtByPlayer = false;
			/*
			 * Decreases the knockback by setting velocity on the direction
			 * where the player is attacking from.
			 */
			double x = calculateX(direction);
			double z = calculateZ(direction);
			//event.entity.setVelocity(x, -0.75D, z);
		}

		if (event.entity instanceof EntityPig) {
			event.entity.setSprinting(true);
		}
	}

	public double calculateX(double angle) {
		return Math.sin(Math.toRadians(angle)) * 0.5D;

	}

	public double calculateZ(double angle) {
		return -1 * Math.cos(Math.toRadians(angle)) * 0.5D;
	}
}
