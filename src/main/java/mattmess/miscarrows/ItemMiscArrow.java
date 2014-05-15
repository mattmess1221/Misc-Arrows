package mattmess.miscarrows;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class ItemMiscArrow extends Item {
	
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
		Effect(){
			
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
	}
}
