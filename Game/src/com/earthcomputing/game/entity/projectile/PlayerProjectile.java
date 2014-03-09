package com.earthcomputing.game.entity.projectile;

import com.earthcomputing.game.Graphics.Screen;
import com.earthcomputing.game.Graphics.Sprite;
import com.earthcomputing.game.entity.particle.Particle;

public class PlayerProjectile extends Projectile {
	
	public static final int FIRE_RATE = 15; //Higher is slower!

	public PlayerProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = random.nextInt(150) + 200;
		speed = 3; //Speed of the Bullet.
		damage = 15;
		sprite = Sprite.projectile_Player;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		
	}

	public void update() {
		if (level.tileCollision(x, y, nx, ny, 2)) {
			Particle p = new Particle((int)x, (int)y, 50, 500);
			level.add(p);
			remove();
		}
		move();
		
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance()>range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin - y)));
		return dist;
		
		
	}

	public void render(Screen screen) {
		screen.renderProjectile((int)x - 8, (int)y - 6, this);
	}
}
