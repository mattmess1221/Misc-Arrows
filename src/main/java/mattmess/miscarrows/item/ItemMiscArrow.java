package mattmess.miscarrows.item;

import java.util.List;

import mattmess.miscarrows.MiscArrows;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemMiscArrow extends Item {

	private IIcon fire, ice, slime, potion, teleport, explosive, item;

	public ItemMiscArrow() {
		this.setUnlocalizedName("misc_arrow");
		this.setTextureName("miscarrows:misc_arrow");
		this.setCreativeTab(MiscArrows.tab);
		this.hasSubtypes = true;
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		switch (damage) {
		case 1:
			return fire;
		case 2:
			return ice;
		case 3:
			return slime;
		case 4:
			return potion;
		case 5:
			return teleport;
		case 6:
			return explosive;
		case 7:
			return item;
		}
		return super.getIconFromDamage(damage);
	}

	public void registerIcons(IIconRegister icon) {
		super.registerIcons(icon);
		fire = icon.registerIcon("miscarrows:misc_arrow_fire");
		ice = icon.registerIcon("miscarrows:misc_arrow_ice");
		slime = icon.registerIcon("miscarrows:misc_arrow_slime");
		potion = icon.registerIcon("miscarrows:misc_arrow_potion");
		teleport = icon.registerIcon("miscarrows:misc_arrow_teleport");
		explosive = icon.registerIcon("miscarrows:misc_arrow_explosive");
		item = icon.registerIcon("miscarrows:misc_arrow_item");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		String name = getUnlocalizedName();
		switch(itemStack.getItemDamage()){
		case 1:
			return name + ".fire";
		case 2:
			return name + ".ice";
		case 3:
			return name + ".slime";
		case 4:
			return name + ".potion";
		case 5:
			return name + ".teleport";
		case 6:
			return name + ".explosive";
		case 7:
			return name + ".item";
		}
		return name + ".normal";
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(MiscArrows.fireArrow);
		list.add(MiscArrows.iceArrow);
		list.add(MiscArrows.slimeArrow);
		//list.add(MiscArrows.itemArrow);
		//list.add(MiscArrows.potionArrow);
		list.add(MiscArrows.teleportArrow);
		list.add(MiscArrows.explosiveArrow);
	}


}
