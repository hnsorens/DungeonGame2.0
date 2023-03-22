package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import DungeonFeatures.Chest;
import DungeonFeatures.Traps.ArrowTrap;
import DungeonFeatures.Traps.BladeTrap;
import DungeonFeatures.Traps.FlameTrap;
import DungeonFeatures.Traps.SpearTrap;
import DungeonFeatures.Traps.SpikeTrap;
import DungeonFeatures.Traps.Spinner;


public class Game {
	
	DungeonSection room;
	
	Display display;
	//Renderer renderer;
	
	ImageIcon ii = new ImageIcon("res/20210608_150203.jpg");
	Image image = ii.getImage();
	
	Dungeon dungeon;
	
	public static Player player = new Player(0,0);
	
	public static KeyboardListener listener = new KeyboardListener();
	
	public static int Width, Height;
	
	public Game() throws InterruptedException, IOException {
		
		DungeonMaker.CreateNewDungeon(10,10,"maze1");
		
		DungeonMaker.CreateRoom("maze1", 0, new ArrowTrap[] {new ArrowTrap(0,0,0,4)}, new BladeTrap[] {new BladeTrap(10,10,0)}, new Chest[] {new Chest(50,50,0)}, new FlameTrap[] {new FlameTrap(90,90,1,4)}, new SpearTrap[] {new SpearTrap(90,0,0,4)}, new SpikeTrap[] {new SpikeTrap(100,100,1,1)}, new Spinner[] {new Spinner(6,6,4)});
		
		DungeonMaker.LoadRoom("maze1", 0);
		
		display = new Display("Dungeon Game");
		//renderer = new Renderer(display);
		Width = display.screenSize.width;
		Height = display.screenSize.height;
		dungeon = new Dungeon(1,1);
		room = dungeon.Sections.get(0);
		
		boolean Running = true;
		
		while (Running) {
			
			listener.UpdateKeys();
			if (display.RunFrame()) {
				player.update();
				
				display.g.clearRect(0, 0, display.screenSize.width, display.screenSize.height);
				
				display.g.setColor(Color.BLACK);
				display.g.fillRect(0,0,Width, Height);
				
				//display.g.drawImage(image,100,100,1000,1000,0,0,3000,4000,null);
				
				//dungeon.Update();
				//dungeon.show(display.g);
				
				DungeonMaker.Update();
				DungeonMaker.Draw(display.g);
			
				
				//showImage(display.g);
				
				display.showBuffer();
			
			}
			
		}
		
	}
	
	public static void drawImage(Graphics g, String url, double[] x, double[] y) {
		double posX = x[0];
		double posY = y[0];
		for (int i = 0; i < x.length; i++) {
			x[i] -= posX;
			y[i] -= posY;
		}
		try {
			BufferedImage original = ImageIO.read(new File("res/" + url));
            BufferedImage skew = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
            
            AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
            at.shear((x[2]-x[1])/(y[2]-y[1]), (y[2]-y[3])/(x[2]-x[3]));
            
            AffineTransformOp op = new AffineTransformOp(at,new RenderingHints(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC));
            skew = op.filter(original, null);
            
            g.drawImage(skew,(int)posX,(int)posY,(int)x[2],(int)y[2],0,0,skew.getWidth(),skew.getHeight(), null);
            
		} catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}
