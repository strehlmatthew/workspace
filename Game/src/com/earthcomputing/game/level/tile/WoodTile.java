package com.earthcomputing.game.level.tile;

import com.earthcomputing.game.Graphics.Screen;
import com.earthcomputing.game.Graphics.Sprite;

public class WoodTile extends Tile {

	public WoodTile(Sprite sprite) {
		super(sprite);
		
	}
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	public boolean solid() {
		return false;
	}
	public boolean breakable() {
		return true;
	}
}
