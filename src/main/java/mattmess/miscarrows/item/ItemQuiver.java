package mattmess.miscarrows.item;

import java.util.Map;

import mattmess.miscarrows.ContainerQuiver;
import mattmess.miscarrows.InventoryQuiver;
import mattmess.miscarrows.MiscArrows;
import mattmess.miscarrows.gui.GuiQuiver;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemQuiver extends Item{
	public ItemQuiver(){
		this.setTextureName("minecraft:quiver");
		this.setUnlocalizedName("quiver");
		this.setCreativeTab(MiscArrows.tab);
		this.setMaxStackSize(1);
	}

	private Map<ItemStack, IInventory> inventories = Maps.newHashMap();
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
		openInv(itemStack, world, player);
		return itemStack;
		
	}

	@SideOnly(Side.CLIENT)
	private void openInv(ItemStack itemStack, World world, EntityPlayer player){
		if(world.isRemote)
			Minecraft.getMinecraft().displayGuiScreen(new GuiQuiver(new ContainerQuiver(player.inventory, new InventoryQuiver(itemStack))));
	}
	
	public InventoryQuiver getInventory(ItemStack itemstack){
		return new InventoryQuiver(itemstack);
	}
		
	
}
