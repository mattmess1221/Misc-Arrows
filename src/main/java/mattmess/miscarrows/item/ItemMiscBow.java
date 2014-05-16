package mattmess.miscarrows.item;

import java.util.ArrayList;

import mattmess.miscarrows.EntityMiscArrow;
import mattmess.miscarrows.InventoryQuiver;
import mattmess.miscarrows.MiscArrows;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import com.google.common.collect.Lists;

public class ItemMiscBow extends ItemBow {
	private ItemStack selectedArrow;
	private int power;
	public ItemMiscBow(){
		super();
		this.setUnlocalizedName("misc_bow");
		this.setTextureName("miscarrows:misc_bow");
		this.setCreativeTab(MiscArrows.tab);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int i){

		
		int j = this.getMaxItemUseDuration(stack) - i;

        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (flag || (player.inventory.hasItemStack(selectedArrow) && stack.getTagCompound().getInteger("arrow") != 0))
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityMiscArrow entityarrow = new EntityMiscArrow(world, player, f * 2.0F, stack.getTagCompound().getInteger("arrow"));

            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
            {
                entityarrow.setFire(100);
            }

            stack.damageItem(1, player);
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if(flag){
            	entityarrow.canBePickedUp = 2;
            }else{
            	selectedArrow.stackSize--;
            }

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entityarrow);
            }
        }
	}

	private void openSelectArrowGui(ItemStack itemstack, EntityPlayer player) {
		ArrayList<ItemStack> arrows = getArrowStacks(player.inventory);
		if(arrows.size() == 0)
			return;
		//if(player.worldObj.isRemote)
			player.openGui(MiscArrows.instance, 0, player.worldObj, 0, 0, 0);
		
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
		if(itemstack.stackTagCompound == null)
			itemstack.stackTagCompound = new NBTTagCompound();
		if(selectedArrow == null || !player.inventory.hasItemStack(this.selectedArrow) || (player.capabilities.isCreativeMode && selectedArrow != null)){
			selectFirstArrow(player.inventory);
		}
		if(player.isSneaking() && world.isRemote){
			openSelectArrowGui(itemstack, player);
			return itemstack;
		}
        ArrowNockEvent event = new ArrowNockEvent(player, itemstack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if(selectedArrow == null)
        	return itemstack;
        if (player.capabilities.isCreativeMode || player.inventory.hasItemStack(selectedArrow))
        {
            player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }

        return itemstack;
    }
	
	private void selectFirstArrow(InventoryPlayer inventory) {
		for(ItemStack stack : inventory.mainInventory){
			if(stack == null || stack.getItem() == null)
				continue;
			if(stack.getItem().equals(MiscArrows.arrow) || stack.getItem().equals(Items.arrow)){
				selectArrow(inventory.getCurrentItem(),stack);
				return;
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack){
		return EnumAction.bow;
	}
	public void selectArrow(ItemStack bow, ItemStack arrow){
		NBTTagCompound tag = bow.getTagCompound();
		if(tag == null)
			tag = new NBTTagCompound();
		if(arrow == null)
			tag.removeTag("arrow");
		else
			tag.setInteger("arrow", arrow.getItemDamage());
		bow.writeToNBT(tag);
		this.selectedArrow = arrow;
		System.out.println(bow.getTagCompound());
	}
	
	public int getSelectedArrow(ItemStack bow){
		if(bow.getTagCompound() == null)
			bow.setTagCompound(new NBTTagCompound());
		return bow.getTagCompound().getInteger("arrow");
	}
	
	public static ArrayList<ItemStack> getArrowStacks(InventoryPlayer inventory){
		ArrayList<ItemStack> types = Lists.newArrayList();
		for(ItemStack item : inventory.mainInventory){
			if(item == null) continue;
			if(item.getItem().equals(MiscArrows.arrow))
				types.add(item);
			if(item.getItem().equals(Items.arrow))
				types.add(item);
			if(item.getItem().equals(MiscArrows.quiver)){
				ItemQuiver quiver = (ItemQuiver) item.getItem();
				InventoryQuiver quivInv = quiver.getInventory(item);
				for(ItemStack itemstack : quivInv.inventory){
					if(itemstack != null)
						types.add(itemstack);
				}
			}
		}
		if(inventory.player.capabilities.isCreativeMode){
			if(!types.contains(MiscArrows.explosiveArrow))
				types.add(MiscArrows.explosiveArrow);
			if(!types.contains(MiscArrows.fireArrow))
				types.add(MiscArrows.fireArrow);
			if(!types.contains(MiscArrows.iceArrow))
				types.add(MiscArrows.iceArrow);
			if(!types.contains(MiscArrows.slimeArrow))
				types.add(MiscArrows.slimeArrow);
			if(!types.contains(MiscArrows.teleportArrow))
				types.add(MiscArrows.teleportArrow);
			return types;
		}
		
		
		return types;
	}
}
