package mattmess.miscarrows;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;

public class GuiSelectArrow extends GuiScreen {

	private ItemStack bow;
	private List<ItemStack> arrows;

	public GuiSelectArrow(ItemStack bow, List<ItemStack> arrows){
		this.bow = bow;
		this.arrows = arrows;
	}
	
	@Override
	public void drawScreen(int x, int y, float f){
		super.drawScreen(x, y, f);
		int size = arrows.size();
		int u = width/2 - size*12/2;
		int v = height/2;
		mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
		for(ItemStack item : arrows){
			this.drawTexturedModelRectFromIcon(u, v, item.getIconIndex(), 12, 12);
			//this.drawCenteredString(fontRendererObj,item.getDisplayName() + " (" + item.stackSize + ")", width/2, pos, 0xffffff);
			u += 15;
			if(u>350){
				u = width/2-size*6;
				v += 15;
			}
		}
	}
	
	@Override
	public void initGui(){
		
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	
}
