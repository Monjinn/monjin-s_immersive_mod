package monjin.immersive_mod.proxy;

import monjin.immersive_mod.ImmersiveMod;
import monjin.immersive_mod.container.ContainerIronSmelter;
import monjin.immersive_mod.container.ImmersivePlayerContainer;
import monjin.immersive_mod.gui.GuiIronSmelter;
import monjin.immersive_mod.tileentity.TileEntityIronSmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if(entity != null) {
			switch(ID) {
			case ImmersiveMod.guiIDIronSmelter:
				if (entity instanceof TileEntityIronSmelter) {
					return new ContainerIronSmelter(player.inventory, (TileEntityIronSmelter) entity);
				}
				return null;
				
			}
			
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if(entity != null) {
			switch(ID) {

			case ImmersiveMod.guiIDIronSmelter:
				if (entity instanceof TileEntityIronSmelter) {
					return new GuiIronSmelter(player.inventory, (TileEntityIronSmelter) entity);
				}
				return null;
				
			}
		}
		return null;
	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}
	
	public void registerRenderers() {   }

}
