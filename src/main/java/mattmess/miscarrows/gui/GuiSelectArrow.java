package mattmess.miscarrows.gui;

import java.util.List;

import mattmess.miscarrows.item.ItemMiscBow;
import net.minecraft.client.gui.GuiButton;
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
		GuiArrowButton button = (GuiArrowButton) this.forButton(x, y);
		if(button != null){
			this.drawCreativeTabHoveringText(button.getItemStack().getDisplayName(), x, y);
		}
		
	}
	
	public GuiButton forButton(int mouseX, int mouseY){
		for(GuiButton button : (List<GuiButton>)buttonList){
			if(mouseX > button.xPosition && mouseX < button.xPosition + 16 && mouseY > button.yPosition && mouseY <= button.yPosition + 16){
				return button;
			}
		}
		return null;
		
	}
	
	@Override
	public void initGui(){
		int iconsize = 16;
		int size = arrows.size();
		int i = 0;
		int u = width/2 - size*12/2;
		int v = height/2;
		for(ItemStack item : arrows){
			this.buttonList.add(new GuiArrowButton(i, u+(20 * i), v, item));
			i++;
			if(i>5){
				i = 0;
				v += iconsize + 2;
			}
		}
		for(GuiArrowButton button : (List<GuiArrowButton>) buttonList){
			if(button.getItemStack() == ((ItemMiscBow)bow.getItem()).getSelectedArrow()){
				button.enabled = false;
			}
		}
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		GuiArrowButton arrowbutton = (GuiArrowButton)button;
		((ItemMiscBow) bow.getItem()).selectArrow(arrowbutton.getItemStack());
		for(GuiButton butt : (List<GuiButton>)buttonList){
			butt.enabled = true;
		}
		button.enabled = false;
		mc.displayGuiScreen(null);
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	
}
