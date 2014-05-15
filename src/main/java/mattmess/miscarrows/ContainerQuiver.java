package mattmess.miscarrows;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerQuiver extends Container implements IInventory {

	private boolean inventoryChanged;
	
	public static void openContainer(EntityPlayer entity, ItemStack itemStack){
		ContainerQuiver container;
	}
	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		// TODO Auto-generated method stub
		return (ItemStack) inventoryItemStacks.get(var1);
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		this.markDirty();
		if(inventoryItemStacks.get(var1) == null)
			return null;
		ItemStack itemstack;
		if (((ItemStack)this.inventoryItemStacks.get(var1)).stackSize <= var2)
        {
            itemstack = (ItemStack) this.inventoryItemStacks.get(var1);
            this.inventoryItemStacks.set(var1, null);
            return itemstack;
        }
        else
        {
            itemstack = ((ItemStack) this.inventoryItemStacks.get(var1)).splitStack(var2);

            if (((ItemStack)this.inventoryItemStacks.get(var1)).stackSize == 0)
            {
                this.inventoryItemStacks.set(var1, null);
            }

            return itemstack;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		if(inventoryItemStacks.get(var1) == null)
			return null;
		ItemStack itemstack = (ItemStack) inventoryItemStacks.get(var1);
		inventoryItemStacks.set(var1, null);
		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		inventoryItemStacks.set(var1, var2);
        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return "Quiver";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public void markDirty() {
		this.inventoryChanged = true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return var1.getItemInUse().getItem() instanceof ItemQuiver;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		if(var1 > 5)
			return true;
		if(var2.getItem() instanceof ItemMiscArrow)
			return true;
		return false;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return true;
	}

}
