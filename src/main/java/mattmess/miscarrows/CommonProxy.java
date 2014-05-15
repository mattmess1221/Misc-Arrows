package mattmess.miscarrows;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == MiscArrows.GUI_QUIVER_INV)
			return new ContainerQuiver(player, player.inventory, new InventoryQuiver(player.getHeldItem()));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == MiscArrows.GUI_QUIVER_INV){
			ItemStack stack = player.getHeldItem();
			return new GuiQuiver((ContainerQuiver) new ContainerQuiver(player, player.inventory, new InventoryQuiver(player.getHeldItem())));
		}
		return null;
	}

}
