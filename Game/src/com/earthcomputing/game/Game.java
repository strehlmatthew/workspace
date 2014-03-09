package com.earthcomputing.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.earthcomputing.game.Graphics.Screen;
import com.earthcomputing.game.Graphics.Sprite;
import com.earthcomputing.game.entity.mob.Player;
import com.earthcomputing.game.level.Level;
import com.earthcomputing.game.level.RandomLevel;
import com.earthcomputing.game.level.SpawnLevel;
import com.earthcomputing.game.level.TileCoordinate;
import com.earthcomputing.input.Keyboard;
import com.earthcomputing.input.Mouse;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static int width = 300;
	private static int height = width / 16 * 9; //50,400
	private static int scale = 4;
	public static String title = "Game";
	
	private Thread thread;
	private JFrame frame;  //Not android compatible.
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(108, 123); //Player Spawn (Set to 0 to see graphical bug.) 
		player = new Player(playerSpawn.x(), playerSpawn.y(), key); 
		player.init(level);
		
		addKeyListener(key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
	}
	
	public static int getWindowWidth() {
		return width * scale;
	}
	public static int getWindowheight() {
		return height * scale;
	}
			
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = true;
		try {
			thread.join();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public void update() {
		key.update();
		player.update();
		level.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy(); 
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		screen.clear();
		
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		//g.fillRect(Mouse.getX(), Mouse.getY(), 64, 64);
		//if (Mouse.getButton() != -1) g.drawString("button: " + Mouse.getButton(), 80, 80);
		bs.show();
		g.dispose();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Game.title");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
		
	}
	
}

