package mattmess.miscarrows;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sun.org.apache.xml.internal.security.utils.I18n;

public class GuiQuiver extends GuiContainer {

	private float xSize_lo;
	private float ySize_lo;
	
	private static final ResourceLocation guiLoc = new ResourceLocation("miscarrows", "textures/gui/quiver.png");
	public static final int GUI_ID = 0;
	private final InventoryQuiver inventory;
	
	public GuiQuiver(ContainerQuiver container) {
		super(container);
		this.inventory = container.inventory;
		// TODO Auto-generated constructor stub
	}
	
	public void drawScreen(int x, int y, int tick){
		super.drawScreen(x, y, tick);
		this.xSize_lo = x;
		this.ySize_lo = y;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y){
		String s = this.inventory.getInventoryName();
		this.fontRendererObj.drawString(s,  (int) (this.xSize/2 - this.fontRendererObj.getStringWidth(s)/2), 0, 4210752);
		this.fontRendererObj.drawString(I18n.translate("container.inventory"), 26, (int) (this.xSize-92), 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiLoc);
		int k = ((this.width - this.xSize) / 2);
		int l = ((this.height - this.ySize) / 2);
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		
	}
	

}
