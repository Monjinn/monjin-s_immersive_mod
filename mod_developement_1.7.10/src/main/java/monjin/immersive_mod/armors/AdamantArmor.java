package monjin.immersive_mod.armors;

import java.util.List;

import monjin.immersive_mod.ImmersiveMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class AdamantArmor extends ItemArmor {
	private ArmorMaterial material;
	private int slot;

	public AdamantArmor(ArmorMaterial material, int id, int slot) {
		super(material, id, slot);
		this.material = material;
		this.slot = slot;

		if (slot == 0) {
			this.setTextureName(ImmersiveMod.MODID + ":AdamantHelm");
		} else if (slot == 1) {
			this.setTextureName(ImmersiveMod.MODID + ":AdamantChest");
		} else if (slot == 2) {
			this.setTextureName(ImmersiveMod.MODID + ":AdamantLeggings");
		} else if (slot == 3) {
			this.setTextureName(ImmersiveMod.MODID + ":AdamantBoots");
		}
	}

	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot,
			String type) {
		/* TODO IMPLEMENT THIS
		if (itemStack.getItem() == ImmersiveMod.adamantArmor.get(0)
				|| itemStack.getItem() == ImmersiveMod.adamantArmor.get(1)
				|| itemStack.getItem() == ImmersiveMod.adamantArmor.get(3)) {
			return ImmersiveMod.MODID
					+ ":textures/models/armor/adamantArmor_1.png";
		} else if (itemStack.getItem() == ImmersiveMod.adamantArmor.get(2)) {
			return ImmersiveMod.MODID
					+ ":textures/models/armor/adamantArmor_2.png";
		} else {
			return null;
		}
		*/
		return null;
	}

	/**
	 * Adds information for armor
	 */
	public void addInformation(ItemStack itemStack, EntityPlayer player,
			List list, boolean p_77624_4_) {
		Integer temp = itemStack.getItem().getMaxDamage();
		Double maxDamage = temp.doubleValue();
		temp = itemStack.getItem().getDamage(itemStack);
		Double damage = temp.doubleValue();
		damage = (maxDamage - damage) / maxDamage * 100;
		temp = damage.intValue();

		list.add("§9§o" + "Protection: " + "§r§b§o"
				+ material.getDamageReductionAmount(slot) * 4 + "%");
		list.add("§9" + "Durability: " + "§r§b§o" + temp.toString() + "%");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10,
				10));
	}
}
