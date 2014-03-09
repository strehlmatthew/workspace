package com.earthcomputing.game.Graphics;

import java.util.Random;

import com.earthcomputing.game.entity.mob.Player;
import com.earthcomputing.game.entity.projectile.Projectile;
import com.earthcomputing.game.level.tile.Tile;

public class Screen {
	
	public int width;
	public int height;
	public int[] pixels;
	public final int MAP_SIZE = 64; //<---Map Size Variable insert here. Can't be bigger than tiles.
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int xOffset, yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; 
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width *height];//50,400
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			
		}
		
		
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels [i] = 0;		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
		xp -= xOffset;
		yp -= yOffset;
		}
		for(int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + yp;
				if (xa < 0 || xa >= width || ya <0 || ya >= height) continue;
				pixels[x + y * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) { // ya, xa are Y/X actual value.
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; //stop render when it goes off screen.
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	public void renderProjectile(int xp, int yp, Projectile p) { // ya, xa are Y/X actual value.
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break; //stop render when it goes off screen.
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if (col != 0xffff00ff)
				pixels[xa + ya * width]= col;
			}
		}
	}
	
	
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break; //stop render when it goes off screen.
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * 32];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;//Transparency
			}
			
		}
	}
	
	
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
}
