package mattmess.miscarrows;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "miscarrows", name= "Misc Arrows", version = "1.0")
public class MiscArrows {

	public static ItemMiscBow bow = new ItemMiscBow();
	public static ItemMiscArrow arrow = new ItemMiscArrow();
	
	@EventHandler
	public void startup(FMLPreInitializationEvent event){
		GameRegistry.registerItem(bow, "misc_bow");
		GameRegistry.registerItem(arrow, "misc_arrow");
		
	}
}
