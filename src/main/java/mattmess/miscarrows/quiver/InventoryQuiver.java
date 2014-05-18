package mattmess.miscarrows.quiver;

import mattmess.miscarrows.MiscArrows;
import mattmess.miscarrows.item.ItemMiscArrow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class InventoryQuiver implements IInventory {

	public ItemStack[] inventory = new ItemStack[INV_SIZE];
	private final ItemStack invItem;
	public static final int INV_SIZE = 5;
	public static final int GUI_INV = 0;
	
	public InventoryQuiver(ItemStack itemstack){
		this.invItem = itemstack;
		if(itemstack.stackTagCompound == null)
			itemstack.stackTagCompound = new NBTTagCompound();
	}
	
	private void readFromNBT(NBTTagCompound tagCompound) {
		NBTTagList items = tagCompound.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
		for(int i = 0; i<items.tagCount(); i++){
			NBTTagCompound item = (NBTTagCompound)items.getCompoundTagAt(i);
			int slot = item.getInteger("Slot");
			if(slot>=0 && slot < getSizeInventory())
				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
		}
	}
	private void writeToNBT(NBTTagCompound tagCompound) {
		NBTTagList items = new NBTTagList();
		for(int i = 0; i < getSizeInventory(); i++){
			if(getStackInSlot(i) != null){
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}
		tagCompound.setTag("Inventory", items);
	}
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null)
			if(stack.stackSize > amount){
				stack = stack.splitStack(amount);
				markDirty();
			} else {
				setInventorySlotContents(slot, null);
			}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		this.inventory[slot] = itemstack;
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
			itemstack.stackSize = this.getInventoryStackLimit();
		markDirty();
	}

	@Override
	public String getInventoryName() {
		return invItem.getDisplayName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return invItem.hasDisplayName();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
				inventory[i] = null;
		}
		writeToNBT(invItem.getTagCompound());

	}


	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getHeldItem().equals(invItem);
	}

	@Override
	public void openInventory() {
		readFromNBT(invItem.stackTagCompound);
	}

	@Override
	public void closeInventory() {
		//writeToNBT(invItem.stackTagCompound);
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return var2.getItem().equals(MiscArrows.arrow);
	}

}
