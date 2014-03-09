package com.earthcomputing.game.level;

import java.util.ArrayList;
import java.util.List;

import com.earthcomputing.game.Graphics.Screen;
import com.earthcomputing.game.entity.Entity;
import com.earthcomputing.game.entity.projectile.Projectile;
import com.earthcomputing.game.level.tile.Tile;


public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public static Level spawn = new SpawnLevel("/levels/level.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	protected Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		for (int y = 0; y <64; y++) {
			for (int x = 0; x < 64; x++) {
				getTile(x, y);
			}
		}
		tile_size = 16;
	}

	protected void loadLevel(String path) {
	}

	private void time() {
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			
		}
	}
	
	public boolean tileCollision(double x, double y, double xa, double ya, int size) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (((int)x + (int)xa) + c % 2 * size) / 16; //collision
			int yt = (((int)y + (int)ya) + c / 2 * size) / 16; //collision
			if(getTile( xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public List<Projectile>getProjectiles() {
		return projectiles;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen); 
		}	
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
			
		}
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void addProjectile(Projectile p) {
		p.init(this);
		projectiles.add(p);
		
	}
	
	
	// Grass = 0xFF00FF00
	//Flower = 0xFFFF00
	//Rock= 0xFF7F7F00
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass;
		if (tiles[x + y * width] == Tile.col_flower) return Tile.flower;
		if (tiles[x + y * width] == Tile.col_rock) return Tile.rock;
		if (tiles[x + y * width] == Tile.col_rock_solid) return Tile.rock_solid;
		if (tiles[x + y * width] == Tile.col_wall) return Tile.wall;
		if (tiles[x + y * width] == Tile.col_wall_blue) return Tile.wall_blue;
		if (tiles[x + y * width] == Tile.col_wall_red) return Tile.wall_red;
		if (tiles[x + y * width] == Tile.col_plank) return Tile.plank;
		if (tiles[x + y * width] == Tile.col_water) return Tile.water;
		return Tile.voidTile;
	}

}
