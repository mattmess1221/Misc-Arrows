package mattmess.miscarrows;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityMiscArrow extends EntityArrow {

	private World world;
	private EntityPlayer shooter;
	private Type type;
	
	public EntityMiscArrow(World world, EntityPlayer player, float f, int i) {
		super(world, player, f);
		MinecraftForge.EVENT_BUS.register(this);
		this.world = world;
		this.shooter = player;
		this.type = Type.valueOf(i);
		if(type.equals(Type.FIRE))
			this.setFire(100);
	}
	public EntityMiscArrow(World world, EntityPlayer player, float f, int arrow, Container inventory){
		this(world, player, f, arrow);
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		if (type.equals(Type.FIRE)){
			setBlockOnFire(posX, posY, posZ);
		} else if (type.equals(Type.ICE)){
			freeze();
		}
		if(this.posX == this.prevPosX && this.posY == this.prevPosY && this.posZ == this.prevPosZ){
			if(type.equals(Type.EXPLOSIVE)){
				explode();
			} else if (type.equals(Type.TELEPORT)){
				teleport(posX, posY, posZ);
			}
			this.type = Type.NONE;
		}
	}
	private void freeze(){
		int x = MathHelper.floor_double(posX), y = MathHelper.floor_double(posY), z = MathHelper.floor_double(posZ);
		Block block = world.getBlock(x, y, z);
		Block block1 = world.getBlock(x, y-1, z);
		if(block.getMaterial().equals(Material.air) && Blocks.snow_layer.canPlaceBlockAt(world, x, y, z)){
			world.setBlock(x, y, z, Blocks.snow_layer);
		} else if (block.getMaterial().equals(Material.water))
			world.setBlock(x, y, z, Blocks.ice);
		else if (block.equals(Blocks.fire))
			world.setBlock(x, y, z, Blocks.air);
	}
	private void freeze(EntityLivingBase entity){
		if(entity != null)
			entity.addPotionEffect(new PotionEffect(2,500, 5000));
	}
	
	private void explode(){
		world.createExplosion(this, this.posX, this.posY, this.posZ, 2F, true);
		this.setDead();
	}
	
	private void setBlockOnFire(double x, double y, double z){
		if(world.isRemote)
			return;
		int xd = MathHelper.floor_double(x), yd = MathHelper.floor_double(y), zd = MathHelper.floor_double(z);
		if(world.getBlock(xd, yd, zd).getMaterial().equals(Material.air))
			world.setBlock(xd, yd, zd, Blocks.fire);
		//this.type = Type.NONE;
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
		else if (this.type.equals(Type.ICE))
			freeze(event.entityLiving);
			
	}

	private void teleport(double posX, double posY, double posZ) {
		shooter.setPositionAndUpdate(posX, posY, posZ);
		shooter.fallDistance = 0.0F;
		shooter.attackEntityFrom(DamageSource.fall, 5.0F);
		this.type = Type.NONE;
	}


	public static enum Type{
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
