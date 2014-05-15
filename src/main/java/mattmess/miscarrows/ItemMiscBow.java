package mattmess.miscarrows;

import java.util.ArrayList;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

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

        if (flag || player.inventory.hasItem(Items.arrow))
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

            EntityMiscArrow entityarrow = new EntityMiscArrow(world, player, f * 2.0F, selectedArrow);

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

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                player.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entityarrow);
            }
        }this.setTextureName("miscarrows:misc_bow_standby");
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack){
		return EnumAction.bow;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		if(player.isSneaking()){
			
		}
		return itemStack;
		
    }
	
	private int getArrowTypes(EntityPlayer player){
		int types = 0;
		ArrayList<Item> items = Lists.newArrayList();
		for(ItemStack item : player.inventory.mainInventory){
			if(items.contains(item.getItem()))
				continue;
			if(item.getItem().equals(Items.arrow))
				types++;
			if(item.getItem().equals(MiscArrows.normalArrow)){
				ItemMiscArrow arrow = (ItemMiscArrow) item.getItem();
				
			}
		}
		return types;
	}
}
