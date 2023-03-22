package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;

import Game.*;

public class SpikeTrap {
	
	public int x, y, w, h;
	
	double[][][][] holes;
	double[][][] spikes;
	double[][][] drawSpikes;
	
	Color holeColor;
	
	boolean spikeOut;
	int timer;
	
	public SpikeTrap(int x, int y, int w, int h) {
		this.spikeOut = false;
		this.timer = 0;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.holes = new double[3][w*w*4][2][16];
		this.holeColor = new Color(0,0,0);
		
		this.spikes = new double[3][w*w*4][3];
		this.drawSpikes = new double[3][w*w*4][3];
		
		for (int posX = 0; posX < this.w*2; posX++) {
			for (int posY = 0; posY < this.h*2; posY++) {
				for (int i = 0; i < holes[0][0][0].length; i++) {
					this.holes[0][(posX*w*2)+posY][0][i] = 2*Math.cos(Math.toRadians(22.5*i))+(posX*5)+2.5;
					this.holes[1][(posX*w*2)+posY][0][i] = 2*Math.sin(Math.toRadians(22.5*i))+(posY*5)+2.5;
					this.holes[2][(posX*w*2)+posY][0][i] = 120;
					this.holes[0][(posX*w*2)+posY][1][i] = 1.25*Math.cos(Math.toRadians(22.5*i))+(posX*5)+2.5;
					this.holes[1][(posX*w*2)+posY][1][i] = 1.25*Math.sin(Math.toRadians(22.5*i))+(posY*5)+2.5;
					this.holes[2][(posX*w*2)+posY][1][i] = 120;
				}
				this.spikes[0][(posX*w*2)+posY][0] = 1.25+(posX*5)+2.5;
				this.spikes[1][(posX*w*2)+posY][0] = (posY*5)+2.5;
				this.spikes[2][(posX*w*2)+posY][0] = 120;
				this.drawSpikes[2][(posX*w*2)+posY][0] = 120;
				this.spikes[0][(posX*w*2)+posY][1] = (posX*5)+2.5;
				this.drawSpikes[0][(posX*w*2)+posY][1] = (posX*5)+2.5;
				this.spikes[1][(posX*w*2)+posY][1] = (posY*5)+2.5;
				this.drawSpikes[1][(posX*w*2)+posY][1] = (posY*5)+2.5;
				this.spikes[2][(posX*w*2)+posY][1] = 115;
				this.drawSpikes[2][(posX*w*2)+posY][1] = 115;
				this.spikes[0][(posX*w*2)+posY][2] = -1.25+(posX*5)+2.5;
				this.spikes[1][(posX*w*2)+posY][2] = (posY*5)+2.5;
				this.spikes[2][(posX*w*2)+posY][2] = 120;
				this.drawSpikes[2][(posX*w*2)+posY][2] = 120;
			}
		}
		
	}
	
	public void Update() {
		this.timer += 1;
		for (int posX = 0; posX < this.w*2; posX++) {
			for (int posY = 0; posY < this.h*2; posY++) {
				double degree = MathFunctions.getDegree(this.x+(posX*5)+2.5, this.y+(posY*5)+2.5, Game.player.x+65, Game.player.y+65)+90;
				System.out.println(degree + " " + (Game.player.x) + " " + (Game.player.y));
				this.drawSpikes[0][(posX*w*2)+posY][0] = MathFunctions.rotate(this.spikes[0][(posX*w*2)+posY][0], this.spikes[1][(posX*w*2)+posY][0], (posX*5)+2.5, (posY*5)+2.5, degree)[0];
				this.drawSpikes[1][(posX*w*2)+posY][0] = MathFunctions.rotate(this.spikes[0][(posX*w*2)+posY][0], this.spikes[1][(posX*w*2)+posY][0], (posX*5)+2.5, (posY*5)+2.5, degree)[1];
				this.drawSpikes[0][(posX*w*2)+posY][2] = MathFunctions.rotate(this.spikes[0][(posX*w*2)+posY][2], this.spikes[1][(posX*w*2)+posY][2], (posX*5)+2.5, (posY*5)+2.5, degree)[0];
				this.drawSpikes[1][(posX*w*2)+posY][2] = MathFunctions.rotate(this.spikes[0][(posX*w*2)+posY][2], this.spikes[1][(posX*w*2)+posY][2], (posX*5)+2.5, (posY*5)+2.5, degree)[1];
			}
		}
		
		if (!this.spikeOut && this.timer > 60) {
			this.spikeOut = true;
			this.holeColor = new Color(100,100,100);
			this.timer = 0;
		}
		
		if (this.spikeOut && this.timer > 60) {
			this.spikeOut = false;
			this.holeColor = new Color(0,0,0);
			this.timer = 0;
		}
	}
	
	public void Draw(Graphics g) {
		g.setColor(new Color(50,50,50));
		MathFunctions.Draw3D(g, this.x, this.y, new double[] {0,this.w*10,this.w*10,0}, new double[] {0,0,(this.h*10),(this.h*10)}, new double[] {120,120,120,120});
		for (int i = 0; i < this.holes[0].length; i++) {
			g.setColor(new Color(20,20,20));
			MathFunctions.Draw3D(g, this.x, this.y, this.holes[0][i][0], this.holes[1][i][0], this.holes[2][i][0]);
			g.setColor(this.holeColor);
			MathFunctions.Draw3D(g, this.x, this.y, this.holes[0][i][1], this.holes[1][i][1], this.holes[2][i][1]);
			if (this.spikeOut) {
				g.setColor(new Color(100,100,100));
				MathFunctions.Draw3D(g, this.x, this.y, this.drawSpikes[0][i], this.drawSpikes[1][i], this.drawSpikes[2][i]);
			}
		}
	}

}
