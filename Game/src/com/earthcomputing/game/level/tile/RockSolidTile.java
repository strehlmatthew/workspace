package com.earthcomputing.game.level.tile;

import com.earthcomputing.game.Graphics.Screen;
import com.earthcomputing.game.Graphics.Sprite;

public class RockSolidTile extends Tile {

	public RockSolidTile(Sprite sprite) {
		super(sprite);
		
	}
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	public boolean solid() {
		return true;
	}
	
	public boolean breakable() {
		return true;
		
	}

}
