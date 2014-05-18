package mattmess.miscarrows.gui;

import mattmess.miscarrows.MiscArrows;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class GuiArrowButton extends GuiButton {

	private ItemStack item;

	public GuiArrowButton(int id, int x, int y, ItemStack itemstack) {
		super(id, x, y, 16, 16, "");
		this.item = itemstack;
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y){
		if(visible){
			if(!enabled){
				renderSelected(mc,x,y);
			}
			int xPos = xPosition + width - mc.fontRenderer.getStringWidth(String.valueOf(item.stackSize));
			mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			this.drawTexturedModelRectFromIcon(this.xPosition, this.yPosition, item.getIconIndex(), this.width, this.height);
			
			this.drawString(mc.fontRenderer, String.valueOf(item.stackSize), xPos, yPosition+height-5, 0xffffff);
			
		}
	}
	
	private void renderSelected(Minecraft mc, int x, int y) {
		mc.renderEngine.bindTexture(super.buttonTextures);
		this.drawTexturedModalRect(xPosition-3, yPosition-3, 1, 23, 22, 22);
		
	}

	public ItemStack getItemStack(){
		return item;
	}
	

}
