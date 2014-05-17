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
		player.openGui(MiscArrows.instance, 1, world, 0, 0, 0);	
		return super.onItemRightClick(itemStack, world, player);
		
	}
	
	public InventoryQuiver getInventory(ItemStack itemstack){
		return new InventoryQuiver(itemstack);
	}
		
	
}
