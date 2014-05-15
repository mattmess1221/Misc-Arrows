package mattmess.miscarrows;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class ItemMiscArrow extends Item {
	
	public ItemMiscArrow(Effect effect){
		this.setUnlocalizedName("misc_arrow" + (effect.getName() == "" ? "" : ".") + effect.getName());
		this.setTextureName("miscarrows:misc_arrow" + (effect.getName() == "" ? "" : "_") + effect.getName());
		this.setCreativeTab(MiscArrows.tab);
		this.setEffect(effect);
	}
	
	private Effect effect;

	public void setEffect(Effect effect){
		this.effect = effect;
		if(effect.equals(Effect.ITEM)){
			this.setMaxStackSize(1);
		}
	}
	public Effect getEffect(){
		return this.effect;
	}
	public void openInventory(EntityPlayer player){
		if(effect.equals(Effect.ITEM)){
			
		}
	}
	public void onUse(){
		
	}
	
	public static enum Effect{
		NORMAL, FIRE, ICE, SLIME, POTION, TELEPORT, EXPLOSIVE, ITEM;
		
		private Potion potion = null;
		
		private Effect(){
			
		}
		
		public Potion getPotionEffect(){
			if(!this.equals(POTION))
				return null;
			
			return null;
		}
		public void setPotionEffect(Potion potion){
			if(!this.equals(POTION))
				return;	
			this.potion = potion;
		}
		public String getName(){
			if(this.equals(FIRE))
				return "fire";
			if(this.equals(ICE))
				return "ice";
			if(this.equals(SLIME))
				return "slime";
			if(this.equals(POTION))
				return "potion";
			if(this.equals(TELEPORT))
				return "teleport";
			if(this.equals(EXPLOSIVE))
				return "explosive";
			if(this.equals(ITEM))
				return "item";
			return "";
		}
	}
}
