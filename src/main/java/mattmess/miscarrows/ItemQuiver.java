package mattmess.miscarrows;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Maps;

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
		if(!inventories.containsKey(itemStack))
			inventories.put(itemStack, new ContainerQuiver());
		Minecraft.getMinecraft().displayGuiScreen(new GuiHopper(player.inventory, inventories.get(itemStack)));
		return itemStack;
		
	}
	
	public IInventory getInventory(ItemStack itemStack){
		if(inventories.containsKey(itemStack))
			return inventories.get(itemStack);
		return null;
	}
	
	
}
