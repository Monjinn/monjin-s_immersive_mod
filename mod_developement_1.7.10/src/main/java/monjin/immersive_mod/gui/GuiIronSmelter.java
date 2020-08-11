package monjin.immersive_mod.gui;

import monjin.immersive_mod.ImmersiveMod;
import monjin.immersive_mod.container.ContainerIronSmelter;
import monjin.immersive_mod.tileentity.TileEntityIronSmelter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiIronSmelter extends GuiContainer {
	public ResourceLocation texture = new ResourceLocation(ImmersiveMod.MODID + ":" + "textures/gui/smelterGui.png");
	public TileEntityIronSmelter smelter;
	
	public GuiIronSmelter(InventoryPlayer inventory, TileEntityIronSmelter tileEntity) {
		super(new ContainerIronSmelter(inventory, tileEntity));
		smelter = tileEntity;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	public void drawGuiContainerForegroundLayer(int i, int j) {
		String name = "Iron Smelter";
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 5, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f,
			int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        
        if (this.smelter.isBurning()) {
			int k = this.smelter.getBurnTimeRemainingScaled(13);
			// System.out.println(k);
			int p = 13 - k;
			// System.out.println(p);
			drawTexturedModalRect(guiLeft + 53, guiTop + 42 + p, 176, 0 + p, 18, 13 - p);
		}
		int k = this.smelter.getSmelterProgressScaled(24);
		drawTexturedModalRect(guiLeft + 92, guiTop + 28, 176, 14, k + 1, 16);
	}
}
