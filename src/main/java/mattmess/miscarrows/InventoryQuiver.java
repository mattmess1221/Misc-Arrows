package mattmess.miscarrows;

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
		if(!itemstack.hasTagCompound())
			itemstack.setTagCompound(new NBTTagCompound());
		readFromNBT(itemstack.getTagCompound());
	}
	
	private void readFromNBT(NBTTagCompound tagCompound) {
		NBTTagList items = tagCompound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
		for(int i = 0; i<items.tagCount(); i++){
			NBTTagCompound item = (NBTTagCompound)items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
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
		tagCompound.setTag("ItemInventory", items);
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
		if(stack != null)
			setInventorySlotContents(slot, null);
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
		return "Quiver";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
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
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return var1 >= 4 ? true : var2.getItem() instanceof ItemMiscArrow;
	}

}
