package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display {
	
	Renderer renderer;
	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	Graphics g;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	int fps = 60;
	double timePerTick = 1000000000 / fps;
	double delta = 0;
	long now;
	long lastTime = System.nanoTime();
	
	public Display(String Title) {
		
		frame = new JFrame(Title);
		
		canvas = new Canvas();
		
		
	    frame.setSize(screenSize.width, screenSize.height);
	    frame.setUndecorated(true);
	    frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.addKeyListener(Game.listener);
		
		canvas.setSize(screenSize.width, screenSize.height);
		canvas.setBackground(Color.WHITE);
		canvas.setVisible(true);
		canvas.setFocusable(false);
		
		frame.add(canvas);
		
		canvas.createBufferStrategy(3);
		
	}
	
	public boolean RunFrame() {
		
		bufferStrategy = canvas.getBufferStrategy();
		g = bufferStrategy.getDrawGraphics();
		now = System.nanoTime();
		
		delta += (now-lastTime) / timePerTick;
		lastTime = now;
		
		if (delta >= 1) {
			
			delta--;
			
			return true;
			
		} else {
			
			return false;
		}
	}
	
	public void showBuffer() {
		
		bufferStrategy.show();
		g.dispose();
		
	}
	
	public void drawImages() {
		
		
		
	}
}
