package mattmess.miscarrows;

import mattmess.miscarrows.gui.GuiQuiver;
import mattmess.miscarrows.gui.GuiSelectArrow;
import mattmess.miscarrows.item.ItemMiscBow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 0)
			return new Container(){

				@Override
				public boolean canInteractWith(EntityPlayer var1) {
					return true;
				}
			
		};
		if(ID == 1){
			return new ContainerQuiver(player.inventory, new InventoryQuiver(player.getHeldItem()));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 0)
			return new GuiSelectArrow(player.getHeldItem(), ItemMiscBow.getArrowStacks(player.inventory));
		if(ID == 1){
			return new GuiQuiver((ContainerQuiver) getServerGuiElement(ID, player, world, x, y, z));
		}
		return null;
	}


}
