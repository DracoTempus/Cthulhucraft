package minicraft.entity.mob.infected;

import minicraft.core.io.Settings;
import minicraft.entity.mob.Player;
import minicraft.gfx.MobSprite;
import minicraft.item.Items;

public class Mooshroom extends InfectedMob {
	private static MobSprite[][] sprites = MobSprite.compileMobSpriteAnimations(0, 22);
	
	/**
	 * Creates the Mooshroom with the right sprites and color.
	 */
	public Mooshroom() {
		super(sprites, 10);
	}
	
	public void tick() {
		super.tick();
		
		Player player = getClosestPlayer();
		if (player != null && player.activeItem != null && player.activeItem.name.equals("Wheat")){ //This function will make the entity follow the player directly
			int xd = player.x - x;
			int yd = player.y - y;
				/// if player is less than 6.25 tiles away, then set move dir towards player
				int sig0 = 1; // this prevents too precise estimates, preventing mobs from bobbing up and down.
				xa = ya = 0;
				if (xd < sig0) xa = -1;
				if (xd > sig0) xa = +1;
				if (yd < sig0) ya = -1;
				if (yd > sig0) ya = +1;
			} else {
				// if the Pet was following the player, but has now lost it, it stops moving.
					//*that would be nice, but I'll just make it move randomly instead.
				randomizeWalkDir(false);
			}
		}
	
	public void die() {
		int min = 0, max = 0;
		if (Settings.get("diff").equals("Easy")) {min = 1; max = 2;}
		if (Settings.get("diff").equals("Normal")) {min = 1; max = 1;}
		if (Settings.get("diff").equals("Hard")) {min = 0; max = 1;}
		
		dropItem(min, max, Items.get("leather"), Items.get("Cordyceps"));
		
		super.die();
	}
}

