package com.earthcomputing.game.entity.mob;


import java.util.ArrayList;
import java.util.List;

import com.earthcomputing.game.Graphics.Sprite;
import com.earthcomputing.game.entity.Entity;
import com.earthcomputing.game.entity.particle.Particle;
import com.earthcomputing.game.entity.projectile.PlayerProjectile;
import com.earthcomputing.game.entity.projectile.Projectile;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0; //0 = north and so on.
	protected boolean moving = false;
	protected boolean walking = false;
	
	
	public void move(int xa, int ya) {
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		} 
	}
	
	public void update() {
		
	}
	
	protected void shoot (int x, int y, double dir) {
		Projectile p = new PlayerProjectile(x, y, dir);
		level.addProjectile(p);
		
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 9 - 5) / 16; //collision
			int yt = ((y + ya) + c / 2 * 14 + 1) / 16; //collision
			if(level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render() {
	}
}
