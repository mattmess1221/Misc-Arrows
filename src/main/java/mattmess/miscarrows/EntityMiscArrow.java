package mattmess.miscarrows;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityMiscArrow extends EntityArrow {

	private World world;
	private EntityPlayer shooter;
	private ItemStack arrow;
	private Type type;
	
	public EntityMiscArrow(World world, EntityPlayer player, float f, ItemStack arrow) {
		super(world, player, f);
		MinecraftForge.EVENT_BUS.register(this);
		this.world = world;
		this.shooter = player;
		this.arrow = arrow;
		this.type = Type.valueOf(arrow.getItemDamage());
		if(type.equals(Type.FIRE))
			this.setFire(100);
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		if(this.posX == this.prevPosX && this.posY == this.prevPosY && this.posZ == this.prevPosZ){
			if(type.equals(Type.EXPLOSIVE)){
				explode();
			} else if (type.equals(Type.FIRE)){
				setBlockOnFire(posX, posY, posZ);
			} else if (type.equals(Type.TELEPORT)){
				teleport(posX, posY, posZ);
			}
		}
	}
	
	private void explode(){
		world.createExplosion(this, this.posX, this.posY, this.posZ, 2F, true);
		this.setDead();
	}
	
	private void setBlockOnFire(double x, double y, double z){
		if(world.isRemote)
			return;
		int xd = MathHelper.floor_double(x), yd = MathHelper.floor_double(y), zd = MathHelper.floor_double(z);
		world.setBlock(xd, yd, zd, Blocks.fire);
		this.type = Type.NONE;
	}
	
	@SubscribeEvent
	public void onArrowHit(LivingAttackEvent event){
		if(event.source == null || event.source.getSourceOfDamage() != this)
			return;
		
		if(this.type.equals(Type.EXPLOSIVE))
			explode();
		else if(this.type.equals(Type.FIRE))
			setBlockOnFire(event.entity.posX, event.entity.posY, event.entity.posZ);
		else if(this.type.equals(Type.STICKY))
			event.setCanceled(true);
		else if(this.type.equals(Type.TELEPORT))
			teleport(event.entity.posX, event.entity.posY, event.entity.posZ);
			
	}


	private void teleport(double posX, double posY, double posZ) {
		shooter.setPositionAndUpdate(posX, posY, posZ);
		shooter.fallDistance = 0.0F;
		shooter.attackEntityFrom(DamageSource.fall, 5.0F);
		this.type = Type.NONE;
	}


	private static enum Type{
		EXPLOSIVE, FIRE, ICE, ITEM, POTION, TELEPORT, STICKY, NONE;
		
		public static Type valueOf(int id){
			switch(id){
			case 1:
				return FIRE;
			case 2: 
				return ICE;
			case 3:
				return STICKY;
			case 4:
				return POTION;
			case 5:
				return TELEPORT;
			case 6:
				return EXPLOSIVE;
			default:
				return NONE;
			}
		}
		
	}
}
