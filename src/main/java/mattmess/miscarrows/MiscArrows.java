package mattmess.miscarrows;

import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
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
	public static Item quiver = new ItemQuiver();
	
	@EventHandler
	public void startup(FMLPreInitializationEvent event){
		GameRegistry.registerItem(bow, "misc_bow");
		GameRegistry.registerItem(normalArrow, "misc_arrow");
		GameRegistry.registerItem(fireArrow, "misc_arrow_fire");
		GameRegistry.registerItem(iceArrow, "misc_arrow_ice");
		GameRegistry.registerItem(slimeArrow, "misc_arrow_slime");
		GameRegistry.registerItem(potionArrow, "misc_arrow_potion");
		GameRegistry.registerItem(teleportArrow, "misc_arrow_teleport");
		GameRegistry.registerItem(explosiveArrow, "misc_arrow_explosive");
		GameRegistry.registerItem(itemArrow, "misc_arrow_item");
		GameRegistry.registerItem(quiver, "quiver");
		GameRegistry.addRecipe(new ItemStack(this.bow, 1), new Object[]{"ggg", "gDg", "ggg", 'g', Items.gold_nugget, 'D', Items.bow});
		GameRegistry.addRecipe(new ItemStack(quiver, 1), new Object[]{"lll", "l l", "lll", 'l', Items.leather});
		
		GameRegistry.addShapelessRecipe(new ItemStack(explosiveArrow), Items.arrow, Items.gunpowder);
		GameRegistry.addShapelessRecipe(new ItemStack(fireArrow), Items.arrow, Items.magma_cream);
		GameRegistry.addShapelessRecipe(new ItemStack(iceArrow), Items.arrow, Items.snowball);
		GameRegistry.addShapelessRecipe(new ItemStack(slimeArrow), Items.arrow, Items.slime_ball);
		GameRegistry.addShapelessRecipe(new ItemStack(teleportArrow), Items.arrow, Items.ender_pearl);
		GameRegistry.addShapelessRecipe(new ItemStack(potionArrow), Items.arrow, Items.potionitem);
		GameRegistry.addShapelessRecipe(new ItemStack(itemArrow), Items.arrow, Items.leather);
		
	}
}
