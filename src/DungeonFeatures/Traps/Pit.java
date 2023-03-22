package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Game.Game;
import Game.MathFunctions;
import Game.Player;
import Game.Tile;

public class Pit {
	
	public int x, y, z;
	public boolean[] walls;
	//List<Tile> wallTiles;
	int[] wallLengths;
	int roomW, roomH;
	public boolean falling;
	BufferedImage CrackedFloor;
	public boolean covered;
	
	public Pit(int x, int y, boolean covered) {
		try {
			this.CrackedFloor = ImageIO.read(new File("res/CrackedFloorTile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = x;
		this.covered = covered;
		this.y = y;
		this.z = 130;
		this.wallLengths = new int[16];
		this.roomH = 1;
		this.roomW = 1;
		this.falling = false;
		this.walls = new boolean[] {true, true, true, true};
		
		for (int i = 0; i < 8; i++) {
			wallLengths[2*i] = (MathFunctions.Projection(70,70,100.25+(2.5*i))[0]-MathFunctions.Projection(-60,-60,100.25+(2.5*i))[0]);
			wallLengths[(2*i)+1] = (MathFunctions.Projection(70,70,102.25+(2.5*i))[0]-MathFunctions.Projection(-60,-60,102.25+(2.5*i))[0]);

		}

	}
	
	public void Draw(Graphics g) {
		if (this.walls[0] && Game.player.y+65 >= this.y*10) {
			DrawWall(g,-60+(this.x*10),-60+(this.y*10),10,0);
		}
		if (this.walls[1] && Game.player.x+65 <= (this.x*10)+10) {
			DrawWall(g,-50+(this.x*10),-60+(this.y*10),10,1);
		}
		if (this.walls[2] && Game.player.y+65 <= (this.y*10)+10) {
			DrawWall(g,-60+(this.x*10),-50+(this.y*10),10,0);
		}
		if (this.walls[3] && Game.player.x+65 >= this.x*10) {
			DrawWall(g,-60+(this.x*10),-60+(this.y*10),10,1);
		}
		if (this.covered) {
			int tileLength = MathFunctions.Projection(10,10, 120)[0] - MathFunctions.Projection(0,0,120)[0];
			int tilePos[] = MathFunctions.Projection(0, 0, 120);
			g.drawImage(this.CrackedFloor,MathFunctions.Projection(this.x*10-Game.player.x-65, this.y*10-Game.player.y-65, 120)[0],MathFunctions.Projection(this.x*10-Game.player.x-65, this.y*10-Game.player.y-65, 120)[1],MathFunctions.Projection(this.x*10-Game.player.x-65+10, this.y*10-Game.player.y-65+10, 120)[0],MathFunctions.Projection(this.x*10-Game.player.x-65+10, this.y*10-Game.player.y-65+10, 120)[1],0,0,60,60,null);
		}

		
		
	}
	
	private void DrawWall(Graphics g, int posX, int posY, int w, int side) {
		if (side == 0) {
			g.setColor(new Color(50,50,50));
			g.fillPolygon(new int[] {MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 120)[0], MathFunctions.Projection(posX+w-Game.player.x-5,posY-Game.player.y-5, 120)[0], MathFunctions.Projection(posX+w-Game.player.x-5,posY-Game.player.y-5, 200)[0], MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 200)[0]},
					  new int[] {MathFunctions.Projection(posX-Game.player.x-5, posY-Game.player.y-5, 120)[1], MathFunctions.Projection(posX+w-Game.player.x-5,posY-Game.player.y-5, 120)[1], MathFunctions.Projection(posX+w-Game.player.x-5,posY-Game.player.y-5, 200)[1], MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 200)[1]}, 4);
		}
		if (side == 1) {
			g.setColor(new Color(54,54,54));
			g.fillPolygon(new int[] {MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 120)[0], MathFunctions.Projection(posX-Game.player.x-5,posY+w-Game.player.y-5, 120)[0], MathFunctions.Projection(posX-Game.player.x-5,posY+w-Game.player.y-5, 200)[0], MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 200)[0]},
					  new int[] {MathFunctions.Projection(posX-Game.player.x-5, posY-Game.player.y-5, 120)[1], MathFunctions.Projection(posX-Game.player.x-5,posY+w-Game.player.y-5, 120)[1], MathFunctions.Projection(posX-Game.player.x-5,posY+w-Game.player.y-5, 200)[1], MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 200)[1]}, 4);
		}
		
		
	}
	
	public boolean update() {
		this.falling = false;
		if (Game.player.x+60 > this.x*10-5 && Game.player.x+60 < this.x*10+5 && Game.player.y+60 > this.y*10-5 && Game.player.y+60 < this.y*10+5) {
			this.falling = true;
		}
		return this.falling;
	}

}
