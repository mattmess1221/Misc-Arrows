package mattmess.miscarrows;

import mattmess.miscarrows.item.ItemMiscArrow;
import mattmess.miscarrows.item.ItemMiscBow;
import mattmess.miscarrows.item.ItemQuiver;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "miscarrows", name= "Misc Arrows", version = "1.0")
public class MiscArrows {
	
	public static final Logger logger = LogManager.getLogger("MiscArrows");

	@Instance("miscarrows")
	public static MiscArrows instance;
	@SidedProxy(clientSide = "mattmess.miscarrows.ClientProxy", serverSide = "mattmess.miscarrows.CommonProxy")
	public static CommonProxy proxy;
	public static CreativeTabs tab = new CreativeTabs("miscarrows"){

		@Override
		public Item getTabIconItem() {
			// TODO Auto-generated method stub
			return quiver;
		}
		
	};
	public static ItemMiscBow bow = new ItemMiscBow();
	public static Item arrow = new ItemMiscArrow();
	public static ItemStack fireArrow = new ItemStack(arrow, 1, 1);
	public static ItemStack iceArrow = new ItemStack(arrow, 1, 2);
	public static ItemStack slimeArrow = new ItemStack(arrow, 1, 3);
	public static ItemStack potionArrow = new ItemStack(arrow, 1, 4);
	public static ItemStack teleportArrow = new ItemStack(arrow, 1, 5);
	public static ItemStack explosiveArrow = new ItemStack(arrow, 1, 6);
	public static ItemStack itemArrow = new ItemStack(arrow, 1, 7);
	public static ItemQuiver quiver = new ItemQuiver();
	
	@EventHandler
	public void startup(FMLPreInitializationEvent event){
		logger.info("Setting up Misc Arrows.");
		GameRegistry.registerItem(bow, "misc_bow");
		GameRegistry.registerItem(arrow, "misc_arrow");
		GameRegistry.registerItem(quiver, "quiver");
		
		GameRegistry.registerCustomItemStack("misc_arrow_fire", fireArrow);
		GameRegistry.registerCustomItemStack("misc_arrow_ice", iceArrow);
		GameRegistry.registerCustomItemStack("misc_arrow_slime", slimeArrow);
		GameRegistry.registerCustomItemStack("misc_arrow_potion", potionArrow);
		GameRegistry.registerCustomItemStack("misc_arrow_teleport", teleportArrow);
		GameRegistry.registerCustomItemStack("misc_arrow_explosive", explosiveArrow);
		GameRegistry.registerCustomItemStack("misc_arrow_item", itemArrow);
		
		GameRegistry.addRecipe(new ItemStack(bow, 1), new Object[]{"ggg", "gDg", "ggg", 'g', Items.gold_nugget, 'D', Items.bow});
		GameRegistry.addRecipe(new ItemStack(quiver, 1), new Object[]{"lll", "l l", "lll", 'l', Items.leather});
		
		GameRegistry.addShapelessRecipe(fireArrow, Items.arrow, Items.magma_cream);
		GameRegistry.addShapelessRecipe(iceArrow, Items.arrow, Items.snowball);
		GameRegistry.addShapelessRecipe(slimeArrow, Items.arrow, Items.slime_ball);
		GameRegistry.addShapelessRecipe(teleportArrow, Items.arrow, Items.ender_pearl);
		GameRegistry.addShapelessRecipe(potionArrow, Items.arrow, Items.potionitem);
		GameRegistry.addShapelessRecipe(explosiveArrow, Items.arrow, Items.gunpowder);
		GameRegistry.addShapelessRecipe(itemArrow, Items.arrow, Items.leather);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void burnEvent(LivingHurtEvent event){
		EntityLivingBase entity = event.entityLiving;
		if(event.source.isFireDamage()){
			World world = entity.worldObj;
			int x = MathHelper.floor_double(entity.posX), y = MathHelper.floor_double(entity.posY), z = MathHelper.floor_double(entity.posZ);
			Block block = world.getBlock(x, y-1, z);
			Block block1 = world.getBlock(x, y, z);
			if(Blocks.fire.getFlammability(block) > 0){
				world.setBlock(x, y, z, Blocks.fire);
			} if (Blocks.fire.getFlammability(block1) > 0)
				world.setBlock(x, y+1, z, Blocks.fire);
		}
	}
}
