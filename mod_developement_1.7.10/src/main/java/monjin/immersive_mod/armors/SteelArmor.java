package monjin.immersive_mod.armors;

import java.util.List;

import monjin.immersive_mod.ImmersiveMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.World;

public class SteelArmor extends ItemArmor {
	private ArmorMaterial material;
	private int slot;

	public SteelArmor(ArmorMaterial material, int id, int slot) {
		super(material, id, slot);
		this.material = material;
		this.slot = slot;

		if (slot == 0) {
			this.setTextureName(ImmersiveMod.MODID + ":SteelHelm");
		} else if (slot == 1) {
			this.setTextureName(ImmersiveMod.MODID + ":SteelChest");
		} else if (slot == 2) {
			this.setTextureName(ImmersiveMod.MODID + ":SteelLeggings");
		} else if (slot == 3) {
			this.setTextureName(ImmersiveMod.MODID + ":SteelBoots");
		}
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

		list.add("�9�o" + "Protection: " + "�r�b�o"
				+ material.getDamageReductionAmount(slot) * 4 + "%");
		list.add("�9" + "Durability: " + "�r�b�o" + temp.toString() + "%");
	}

	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot,
			String type) {
		if (itemStack.getItem() == ImmersiveMod.steelArmor.get(0)
				|| itemStack.getItem() == ImmersiveMod.steelArmor.get(1)
				|| itemStack.getItem() == ImmersiveMod.steelArmor.get(3)) {
			return ImmersiveMod.MODID
					+ ":textures/models/armor/steelArmor_1.png";
		} else if (itemStack.getItem() == ImmersiveMod.steelArmor.get(2)) {
			return ImmersiveMod.MODID
					+ ":textures/models/armor/steelArmor_2.png";
		} else {
			return null;
		}
	}
}
