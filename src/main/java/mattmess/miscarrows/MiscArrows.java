package mattmess.miscarrows;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "miscarrows", name= "Misc Arrows", version = "1.0")
public class MiscArrows {
	public static CreativeTabs tab = new CreativeTabs("miscarrows"){

		@Override
		public Item getTabIconItem() {
			// TODO Auto-generated method stub
			return normalArrow;
		}
		
	};
	public static ItemMiscBow bow = new ItemMiscBow();
	public static Item normalArrow = new ItemMiscArrow(ItemMiscArrow.Effect.NORMAL);
	public static Item fireArrow = new ItemMiscArrow(ItemMiscArrow.Effect.FIRE);
	public static Item iceArrow = new ItemMiscArrow(ItemMiscArrow.Effect.ICE);
	public static Item slimeArrow = new ItemMiscArrow(ItemMiscArrow.Effect.SLIME);
	public static Item potionArrow = new ItemMiscArrow(ItemMiscArrow.Effect.POTION);
	public static Item teleportArrow = new ItemMiscArrow(ItemMiscArrow.Effect.TELEPORT);
	public static Item explosiveArrow = new ItemMiscArrow(ItemMiscArrow.Effect.EXPLOSIVE);
	public static Item itemArrow = new ItemMiscArrow(ItemMiscArrow.Effect.ITEM);
	
	@EventHandler
	public void startup(FMLPreInitializationEvent event){
		GameRegistry.registerItem(bow, "misc_bow");
		GameRegistry.registerItem(normalArrow, "misc_arrow");
		GameRegistry.addShapelessRecipe(new ItemStack(explosiveArrow), Items.arrow, Items.gunpowder);
		GameRegistry.addShapelessRecipe(new ItemStack(fireArrow), Items.arrow, Items.magma_cream);
		GameRegistry.addShapelessRecipe(new ItemStack(iceArrow), Items.arrow, Items.snowball);
		GameRegistry.addShapelessRecipe(new ItemStack(slimeArrow), Items.arrow, Items.slime_ball);
		GameRegistry.addShapelessRecipe(new ItemStack(potionArrow), Items.arrow, Items.potionitem);
		GameRegistry.addShapelessRecipe(new ItemStack(itemArrow), Items.arrow, Items.leather);
	}
}
