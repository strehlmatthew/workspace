package com.earthcomputing.game.level.tile;

import com.earthcomputing.game.Graphics.Screen;
import com.earthcomputing.game.Graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile rock_solid = new RockSolidTile(Sprite.rock_solid);
	public static Tile wall = new WallTile(Sprite.wall);
	public static Tile wall_blue = new WallTile(Sprite.wall_blue);
	public static Tile wall_red = new WallTile(Sprite.wall_red);
	public static Tile plank = new WoodTile(Sprite.plank);
	public static Tile water = new WaterTile(Sprite.water);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	

	public static final int col_grass = 0xff00ff00;
	public static final int col_flower = 0xffffff00;
	public static final int col_rock = 0xFF7F7F00;
	public static final int col_rock_solid = 0;
	public static final int col_wall = 0xff808080;
	public static final int col_wall_blue = 0;
	public static final int col_wall_red = 0;
	public static final int col_plank = 0xffA05D30;
	public static final int col_water = 0xff0000ff;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}

}
