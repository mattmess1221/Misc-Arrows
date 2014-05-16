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
					// TODO Auto-generated method stub
					return false;
				}
			
		};
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		if(ID == 0)
			return new GuiSelectArrow(player.getHeldItem(), ItemMiscBow.getArrowStacks(player.inventory));
		return null;
	}


}
