package mattmess.miscarrows;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Maps;

public class ItemQuiver extends Item{
	private Minecraft mc = Minecraft.getMinecraft();
	public ItemQuiver(){
		this.setTextureName("minecraft:quiver");
		this.setUnlocalizedName("quiver");
		this.setCreativeTab(MiscArrows.tab);
		this.setMaxStackSize(1);
	}

	private Map<ItemStack, IInventory> inventories = Maps.newHashMap();
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
		if(!world.isRemote)
			player.openGui(MiscArrows.instance, MiscArrows.GUI_QUIVER_INV, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		return itemStack;
		
	}
	
	public InventoryQuiver getInventory(ItemStack itemstack){
		return new InventoryQuiver(itemstack);
	}
		
	
}
