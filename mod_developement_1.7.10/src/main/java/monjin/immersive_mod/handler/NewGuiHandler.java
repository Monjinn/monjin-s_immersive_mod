package monjin.immersive_mod.handler;

import java.util.Collection;
import java.util.Iterator;

import monjin.immersive_mod.ImmersiveMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class NewGuiHandler extends Gui {
	private Minecraft mc;
	
	public NewGuiHandler(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	private static final int BUFF_ICON_SIZE = 18;
	  private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2; // 2 pixels between buff icons
	  private static final int BUFF_ICON_BASE_U_OFFSET = 0;
	  private static final int BUFF_ICON_BASE_V_OFFSET = 198;
	  private static final int BUFF_ICONS_PER_ROW = 8;
	
	@SubscribeEvent
	public void onRenderHud(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.type != ElementType.FOOD) {
			return;
		}
		
		// Starting position for the buff bar - 2 pixels from the top left corner.
//	    int xPos = 2;
//	    int yPos = 2;
//	    Collection collection = this.mc.thePlayer.getActivePotionEffects();
//	    if (!collection.isEmpty())
//	    {
//	      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//	      GL11.glDisable(GL11.GL_LIGHTING);      
//	      this.mc.renderEngine.bindTexture(new ResourceLocation(ImmersiveMod.MODID + ":" + "textures/gui/Bars.png"));      
//	      
//	      for (Iterator iterator = this.mc.thePlayer.getActivePotionEffects()
//	          .iterator(); iterator.hasNext(); xPos += BUFF_ICON_SPACING) {
//	        PotionEffect potioneffect = (PotionEffect) iterator.next();
//	        Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
//
//	        if (potion.hasStatusIcon()) {
//	          int iconIndex = potion.getStatusIconIndex();
//	          this.drawTexturedModalRect(
//	              xPos, yPos, 
//	              BUFF_ICON_BASE_U_OFFSET + iconIndex % BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE, BUFF_ICON_BASE_V_OFFSET + iconIndex / BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
//	              BUFF_ICON_SIZE, BUFF_ICON_SIZE);
//	        }       
//	      }
//	    }
	}
}
