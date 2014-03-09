package com.earthcomputing.game.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.earthcomputing.game.Graphics.Screen;
import com.earthcomputing.game.Graphics.Sprite;
import com.earthcomputing.game.entity.Entity;

public class Particle extends Entity {
	
	private List<Particle> particles = new ArrayList<Particle>();
	private Sprite sprite;
	
	private int life;
	
	protected double xx, yy, xa, ya;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life;
		sprite = Sprite.particle_normal;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	public Particle(int x, int y, int life, int amount) {
		this(x, y, life);
		for (int i = 0; i < amount - 1; i++) {
			particles.add(new Particle(x, y, life));
		}
		particles.add(this);
	}
	
	public void update() {
		this.xx += xa;
		this.yy += ya;
		
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}

}
