package mattmess.miscarrows;

import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMiscArrow extends EntityArrow implements IProjectile{

	private World world;
	private EntityPlayer shooter;
	private ItemStack arrow;
	private Type type;
	
	public EntityMiscArrow(World world, EntityPlayer player, float f, ItemStack arrow) {
		super(world, player, f);
		this.world = world;
		this.shooter = player;
		this.arrow = arrow;
		this.type = Type.valueOf(arrow.getItemDamage());
		System.out.println(type.toString());
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
