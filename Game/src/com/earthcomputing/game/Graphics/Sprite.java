package com.earthcomputing.game.Graphics;

public class Sprite {
	
	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite rock_solid = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite wall = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite wall_blue= new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite wall_red = new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite plank = new Sprite(16, 7, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 8, 0, SpriteSheet.tiles);
	
	public static Sprite  voidSprite = new Sprite(16, 0);
	
	//PLAYER SPRITES
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 3, 5, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32, 1, 5, SpriteSheet.tiles);

	
	public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	
	public static Sprite player_right_1 = new Sprite(32, 3, 6, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 3, 7, SpriteSheet.tiles);
	
	public static Sprite player_left_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_left_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	
	public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	
	//PROJECTILE SPRITES
		public static Sprite projectile_Player = new Sprite(16, 15, 15, SpriteSheet.tiles);
	
	// Particles
		public static Sprite particle_normal = new Sprite(3, 0x00ff00);
		
		

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		setColor(color);
		
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
		
	}
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
 
	private void load()  {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
	
}
