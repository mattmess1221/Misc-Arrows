package mattmess.miscarrows.gui;

import mattmess.miscarrows.quiver.ContainerQuiver;
import mattmess.miscarrows.quiver.InventoryQuiver;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiQuiver extends GuiContainer {

	private float xSize_lo;
	private float ySize_lo;
	
	private static final ResourceLocation guiLoc = new ResourceLocation("minecraft", "textures/gui/container/hopper.png");
	public static final int GUI_ID = 0;
	private final InventoryQuiver inventory;
	
	public GuiQuiver(ContainerQuiver container) {
		super(container);
		this.inventory = container.inventory;
	}
	
	public void drawScreen(int x, int y, int tick){
		String s = this.inventory.getInventoryName();
		super.drawScreen(x, y, tick);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y){
		super.drawGuiContainerForegroundLayer(x, y);
		this.fontRendererObj.drawString(inventory.getInventoryName(), 8, 5, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, 39, 4210752);
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
