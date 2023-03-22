package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import DungeonFeatures.Traps.Pit;

public class DungeonFloor {
	
	Pit[] pits;
	List<Pit> pitsToDraw;
	
	boolean onPit;
	int pitFalling;
	
	int tileSize;
	BufferedImage floorTile;
	
	public DungeonFloor() {
		System.out.println(tileSize);
		this.onPit = false;
		this.pitFalling = 0;
		this.pits = new Pit[13*13];
		this.pitsToDraw = new ArrayList<Pit>();
		for (int i = 0; i < pits.length; i++) {
			this.pits[i] = null;
		}
		try {
			this.floorTile = ImageIO.read(new File("res/FloorTile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPit(int posX, int posY) {
		pits[posX+(posY*13)] = new Pit(posX, posY, true);
	}
	
	public void init() {
		for (int posY = 0; posY < 13; posY++) {
			for (int posX = 0; posX < 13;posX++) {
				if (this.pits[posX+(posY*13)] != null) {
					if (posY != 12) {
						if (pits[posX+(posY*13)+13] != null) {
							pits[posX+(posY*13)].walls[2] = false;
						}
					}
					if (posY != 0) {
						if (pits[posX+(posY*13)-13] != null) {
							pits[posX+(posY*13)].walls[0] = false;
						}
					}
					if (posX != 12) {
						if (pits[posX+(posY*13)+1] != null) {
							pits[posX+(posY*13)].walls[1] = false;
						}
					}
					if (posX != 0) {
						if (pits[posX+(posY*13)-1] != null) {
							pits[posX+(posY*13)].walls[3] = false;
						}
					}

				}
			}
			for (int i = 0; i < (13*13); i++) {
				if (this.pits[i] != null) {
					this.pitsToDraw.add(this.pits[i]);
				}
			}
		}
	}
	
	public void Draw(Graphics g) {
		int[] floorPos = MathFunctions.Projection(-Game.player.x-65, -Game.player.y-65, 120);
		for (int i = 0; i < this.pitsToDraw.size(); i++) {
			this.pitsToDraw.get(i).Draw(g);
		}
		tileSize = 80;
		for (int posY = 0; posY < 13; posY++) {
			for (int posX = 0; posX < 13; posX++) {
				if (this.pits[posX+(posY*13)] == null) {
					g.drawImage(floorTile ,floorPos[0]+(posX*tileSize),floorPos[1]+(posY*tileSize),floorPos[0]+(posX*tileSize)+tileSize,floorPos[1]+(posY*tileSize)+tileSize,0,0,20,20, null);
					//g.drawImage(this.floorTile, floorPos[0]+(posX*tileSize), floorPos[1]+(posY*tileSize), 40, 40, 0, 0, 20,20, null);
				}
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < this.pitsToDraw.size(); i++) {
			if (this.pitsToDraw.get(i).update() == true) {
				this.onPit = true;
				this.pitFalling = i;
			}
		}
		if (this.onPit == true) {
			Game.player.canMove = false;
			this.pitsToDraw.get(this.pitFalling).covered = false;
			if (Game.player.x+60 > this.pitsToDraw.get(this.pitFalling).x*10) {
				Game.player.x-=0.5;
			}
			if (Game.player.y+60 > this.pitsToDraw.get(this.pitFalling).y*10) {
				Game.player.y-=0.5;
			}
			if (Game.player.x+60 < this.pitsToDraw.get(this.pitFalling).x*10) {
				Game.player.x+=0.5;
			}
			if (Game.player.y+60 < this.pitsToDraw.get(this.pitFalling).y*10) {
				Game.player.y+=0.5;
			}
			if (Game.player.x+60 == this.pitsToDraw.get(this.pitFalling).x*10 && Game.player.y+60 == this.pitsToDraw.get(this.pitFalling).y*10 && Game.player.z < 200) {
				Game.player.z += 2;
			}
		}
	}

}
