package mattmess.miscarrows.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import mattmess.miscarrows.MiscArrows;
import mattmess.miscarrows.item.ItemMiscBow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PacketSelectArrow extends AbstractPacket {

	private int arrow;

	public PacketSelectArrow(){}
	public PacketSelectArrow(int i){
		arrow = i;
	}
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(arrow);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		arrow = buffer.readInt();
		MiscArrows.logger.info("Message Recieved, Captain!");
	}

	@Override
	public void handleClientSide(EntityPlayer player) {

		System.out.println(arrow);
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		if(player.getHeldItem().getItem() == MiscArrows.bow){
			ItemMiscBow bow = (ItemMiscBow) player.getHeldItem().getItem();
			bow.selectArrow(player, new ItemStack(Items.arrow, 1, arrow));
		}
	}

}
