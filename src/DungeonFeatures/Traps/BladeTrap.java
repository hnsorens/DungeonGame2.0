package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;

import Game.MathFunctions;

import Game.*;

public class BladeTrap {
	
	public int x, y, facing;
	
	double[][] BladeCoords;
	
	int teethCount;
	
	boolean disabled;
	
	public BladeTrap(int x, int y, int facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
		this.teethCount = 16;
		this.disabled = false;
		BladeCoords = new double[3][this.teethCount*2];
		if (this.facing == 0) {
			for (int i = 0; i < this.teethCount; i++) {
				this.BladeCoords[0][i*2] = 3*Math.cos(Math.toRadians(i*(360/this.teethCount)))+5;
				this.BladeCoords[1][i*2] = 5;
				this.BladeCoords[2][i*2] = 3*Math.sin(Math.toRadians(i*(360/this.teethCount)))+120;
				this.BladeCoords[0][1+(i*2)] = 4*Math.cos(Math.toRadians(i*(360/this.teethCount)+(180/this.teethCount)))+5;
				this.BladeCoords[1][1+(i*2)] = 5;
				this.BladeCoords[2][1+(i*2)] = 4*Math.sin(Math.toRadians(i*(360/this.teethCount)+(180/this.teethCount)))+120;
			}
		} else {
			for (int i = 0; i < this.teethCount; i++) {
				this.BladeCoords[1][i*2] = 3*Math.cos(Math.toRadians(i*(360/this.teethCount)))+5;
				this.BladeCoords[0][i*2] = 5;
				this.BladeCoords[2][i*2] = 3*Math.sin(Math.toRadians(i*(360/this.teethCount)))+120;
				this.BladeCoords[1][1+(i*2)] = 4*Math.cos(Math.toRadians(i*(360/this.teethCount)+(180/this.teethCount)))+5;
				this.BladeCoords[0][1+(i*2)] = 5;
				this.BladeCoords[2][1+(i*2)] = 4*Math.sin(Math.toRadians(i*(360/this.teethCount)+(180/this.teethCount)))+120;
			}
		}
	}
	
	public void Update() {
		if (!this.disabled) {
			if (this.facing == 0) {
				for (int i = 0; i < this.teethCount*2; i++) {
					double[] coordPos = MathFunctions.rotate(this.BladeCoords[0][i], this.BladeCoords[2][i], 5, 120, 1);
					this.BladeCoords[0][i] = coordPos[0];
					this.BladeCoords[2][i] = coordPos[1];
				}
			} else {
				for (int i = 0; i < this.teethCount*2; i++) {
					double[] coordPos = MathFunctions.rotate(this.BladeCoords[1][i], this.BladeCoords[2][i], 5, 120, 1);
					this.BladeCoords[1][i] = coordPos[0];
					this.BladeCoords[2][i] = coordPos[1];
				}
			}
		}
		
		
	}
	
	public void Draw(Graphics g) {
		if (this.facing == 0) {
			if (Game.player.y > this.y+5-60) {
				g.setColor(new Color(50,50,50));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {0.5,9.5,9.5,0.5}, new double[] {0.5,0.5,5,5}, new double[] {120,120,120,120});
				g.setColor(new Color(0,0,0));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {1,9,9,1}, new double[] {5,5,4.75,4.75}, new double[] {120,120,120,120});
				if (!this.disabled) {
					g.setColor(new Color(20,20,20));
					MathFunctions.Draw3D(g,this.x,this.y, this.BladeCoords[0], this.BladeCoords[1], this.BladeCoords[2]);
				}
				g.setColor(new Color(50,50,50));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {0.5,9.5,9.5,0.5}, new double[] {5,5,9.5,9.5}, new double[] {120,120,120,120});
				g.setColor(new Color(0,0,0));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {1,9,9,1}, new double[] {5,5,5.25,5.25}, new double[] {120,120,120,120});
			} else if (Game.player.y < this.y+5-60) {
				g.setColor(new Color(50,50,50));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {0.5,9.5,9.5,0.5}, new double[] {5,5,9.5,9.5}, new double[] {120,120,120,120});
				g.setColor(new Color(0,0,0));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {1,9,9,1}, new double[] {5,5,5.25,5.25}, new double[] {120,120,120,120});
				if (!this.disabled) {
					g.setColor(new Color(20,20,20));
					MathFunctions.Draw3D(g,this.x,this.y, this.BladeCoords[0], this.BladeCoords[1], this.BladeCoords[2]);
				}
				g.setColor(new Color(50,50,50));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {0.5,9.5,9.5,0.5}, new double[] {0.5,0.5,5,5}, new double[] {120,120,120,120});
				g.setColor(new Color(0,0,0));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {1,9,9,1}, new double[] {5,5,4.75,4.75}, new double[] {120,120,120,120});
			}
		} else {
			if (Game.player.x > this.x+5-60) {
				g.setColor(new Color(50,50,50));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {0.5,0.5,5,5}, new double[] {0.5,9.5,9.5,0.5}, new double[] {120,120,120,120});
				g.setColor(new Color(0,0,0));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {5,5,4.75,4.75}, new double[] {1,9,9,1}, new double[] {120,120,120,120});
				if (!this.disabled) {
					g.setColor(new Color(20,20,20));
					MathFunctions.Draw3D(g,this.x,this.y, this.BladeCoords[0], this.BladeCoords[1], this.BladeCoords[2]);
				}
				g.setColor(new Color(50,50,50));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {5,5,9.5,9.5}, new double[] {0.5,9.5,9.5,0.5}, new double[] {120,120,120,120});
				g.setColor(new Color(0,0,0));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {5,5,5.25,5.25}, new double[] {1,9,9,1}, new double[] {120,120,120,120});
			} else if (Game.player.x < this.x+5-60) {
				g.setColor(new Color(50,50,50));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {5,5,9.5,9.5}, new double[] {0.5,9.5,9.5,0.5}, new double[] {120,120,120,120});
				g.setColor(new Color(0,0,0));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {5,5,5.25,5.25}, new double[] {1,9,9,1}, new double[] {120,120,120,120});
				if (!this.disabled) {
					g.setColor(new Color(20,20,20));
					MathFunctions.Draw3D(g,this.x,this.y, this.BladeCoords[0], this.BladeCoords[1], this.BladeCoords[2]);
				}
				g.setColor(new Color(50,50,50));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {0.5,0.5,5,5}, new double[] {0.5,9.5,9.5,0.5}, new double[] {120,120,120,120});
				g.setColor(new Color(0,0,0));
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {5,5,4.75,4.75}, new double[] {1,9,9,1}, new double[] {120,120,120,120});
			}
		}
	}

}
