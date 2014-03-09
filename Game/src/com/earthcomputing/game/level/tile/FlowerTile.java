package com.earthcomputing.game.level.tile;

import com.earthcomputing.game.Graphics.Screen;
import com.earthcomputing.game.Graphics.Sprite;

public class FlowerTile extends Tile {

	public FlowerTile(Sprite sprite) {
		super(sprite);
	}
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
