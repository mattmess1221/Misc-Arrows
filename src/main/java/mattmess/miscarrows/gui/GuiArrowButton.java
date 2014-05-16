package mattmess.miscarrows.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;

public class GuiArrowButton extends GuiButton {

	private ItemStack item;

	public GuiArrowButton(int id, int x, int y, ItemStack itemstack) {
		super(id, x, y, 16, 16, "");
		this.item = itemstack;
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y){
		if(visible){
			mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			this.drawTexturedModelRectFromIcon(this.xPosition, this.yPosition, item.getIconIndex(), this.width, this.height);
		}
	}
	
	public ItemStack getItemStack(){
		return item;
	}

}
