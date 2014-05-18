package mattmess.miscarrows.gui;

import java.util.List;

import mattmess.miscarrows.MiscArrows;
import mattmess.miscarrows.item.ItemMiscBow;
import mattmess.miscarrows.network.AbstractPacket;
import mattmess.miscarrows.network.PacketSelectArrow;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class GuiSelectArrow extends GuiScreen {

	private ItemStack bow;
	private EntityPlayer player;
	private List<ItemStack> arrows;

	public GuiSelectArrow(EntityPlayer player, List<ItemStack> arrows){
		this.player = player;
		this.bow = player.getHeldItem();
		this.arrows = arrows;
	}
	
	@Override
	public void drawScreen(int x, int y, float f){
		this.drawGradientRect(width/3, height/3, this.width*2/3, this.height*2/3, -1072689136, -804253680);
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
		int u = width/2 - size*20/2;
		int v = height/2 - 8;
		for(ItemStack item : arrows){
			this.buttonList.add(new GuiArrowButton(i, u+(20 * i), v, item));
			i++;
		}
		for(GuiArrowButton button : (List<GuiArrowButton>) buttonList){
			if(button.getItemStack().getItemDamage() == ((ItemMiscBow)bow.getItem()).getSelectedArrow(bow)){
				button.enabled = false;
			}
		}
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		GuiArrowButton arrowbutton = (GuiArrowButton)button;
		((ItemMiscBow) bow.getItem()).selectArrow(player, arrowbutton.getItemStack());
		AbstractPacket packet = new PacketSelectArrow(arrowbutton.getItemStack().getItemDamage());
		MiscArrows.packetPipeline.sendToServer(packet);
		mc.displayGuiScreen(null);
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

	
	
}
