package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;

import Game.*;

public class SpearTrap {
	
	public int x, y, facing, length;
	
	double[][][] xs, ys, zs;
	
	double[][][] dx;
	double[][][] dy;
	double[][][] dz;
	
	double[][][][] ds;
	
	double[][][][] spearCoords;
	
	boolean spearMoving;
	boolean spearOut;
	
	double spearLength;
	
	boolean disabled;
	
	int timer;
	
	public SpearTrap(int x, int y, int Facing, int length) {
		
		this.length = length;
		this.xs = new double[this.length*2][2][8];
		this.ys = new double[this.length*2][2][8];
		this.zs = new double[this.length*2][2][8];
		this.facing = Facing;
		
		this.timer = 0;
		
		this.spearCoords = new double[this.length*2][3][2][4];
		
		this.spearMoving = false;
		this.spearOut = false;
		
		this.spearLength = 0;
		
		this.disabled = false;
		
		this.x = x;
		this.y = y;
		
		for (int spikeX = 0; spikeX < this.length; spikeX++) {
			for (int spikeY = 0; spikeY < 2; spikeY++) {
				calculateSpike(5+(10*spikeX),105+(10*spikeY),spikeX+(this.length*spikeY));
			}
		}
		

		for (int xi = 0; xi < this.length; xi++) {
			for (int yi = 0; yi < 2; yi++) {
				this.spearCoords[xi+(yi*this.length)][0][0] = new double[] {5+(xi*10)-0.5,5+(xi*10)+0.5,5+(xi*10)+0.5,5+(xi*10)-0.5};
				this.spearCoords[xi+(yi*this.length)][1][0] = new double[] {0,0,40,40};
				this.spearCoords[xi+(yi*this.length)][2][0] = new double[] {105+(yi*10),105+(yi*10),105+(yi*10),105+(yi*10)};
				this.spearCoords[xi+(yi*this.length)][0][1] = new double[] {5+(xi*10)-1,5+(xi*10)+1,5+(xi*10),5+(xi*10)};
				this.spearCoords[xi+(yi*this.length)][1][1] = new double[] {40,40,42,42};
				this.spearCoords[xi+(yi*this.length)][2][1] = new double[] {105+(yi*10),105+(yi*10),105+(yi*10),105+(yi*10)};
			}
		}
		
		this.dx = new double[this.length*2][2][8];
		this.dy = new double[this.length*2][2][8];
		this.dz = new double[this.length*2][2][8];
		this.ds = new double[this.length*2][3][2][4];
		if (facing == 0) {
			this.ds = this.spearCoords;
			this.dx = this.xs;
			this.dy = this.ys;
			this.dz = this.zs;
		}
		if (this.facing == 1) {
			this.dx = this.ys;
			this.dy = this.xs;
			this.dz = this.zs;
			for (int i1 = 0; i1 < this.length*2; i1++) {
				for (int i2 = 0; i2 < 2; i2++) {
					for (int i3 = 0; i3 < 8; i3++) {
						this.dx[i1][i2][i3] += (this.length*10);
					}
				}
			}
			for (int i = 0; i < this.length*2; i++) {
				this.ds[i][0] = this.spearCoords[i][1];
				this.ds[i][1] = this.spearCoords[i][0];
				this.ds[i][2] = this.spearCoords[i][2];
			}
			for (int i = 0; i < this.length*2; i++) {
				for (int i2 = 0; i2 < 2; i2++) {
					this.ds[i][0][i2][0] = (this.length*10)-this.ds[i][0][i2][0];
					this.ds[i][0][i2][1] = (this.length*10)-this.ds[i][0][i2][1];
					this.ds[i][0][i2][2] = (this.length*10)-this.ds[i][0][i2][2];
					this.ds[i][0][i2][3] = (this.length*10)-this.ds[i][0][i2][3];
				}
			}
		} else if (this.facing == 2) {
			this.ds = this.spearCoords;
			this.dx = this.xs;
			this.dy = this.ys;
			this.dz = this.zs;
			for (int i1 = 0; i1 < this.length*2; i1++) {
				for (int i2 = 0; i2 < 2; i2++) {
					for (int i3 = 0; i3 < 8; i3++) {
						this.dy[i1][i2][i3] += (this.length*10);
					}
				}
			}
			for (int i = 0; i < this.length*2; i++) {
				for (int i2 = 0; i2 < 2; i2++) {
					this.ds[i][1][i2][0] = (this.length*10)-this.ds[i][1][i2][0];
					this.ds[i][1][i2][1] = (this.length*10)-this.ds[i][1][i2][1];
					this.ds[i][1][i2][2] = (this.length*10)-this.ds[i][1][i2][2];
					this.ds[i][1][i2][3] = (this.length*10)-this.ds[i][1][i2][3];
				}
			}
		} else if (this.facing == 3) {
			this.dx = ys;
			this.dy = xs;
			this.dz = zs;
			for (int i = 0; i < this.length*2; i++) {
				this.ds[i][0] = this.spearCoords[i][1];
				this.ds[i][1] = this.spearCoords[i][0];
				this.ds[i][2] = this.spearCoords[i][2];
			}
		}
	}
	
	public void Draw(Graphics g) {

		for (int i = 0; i < this.length*2; i++) {
			g.setColor(new Color(20,20,20));
			MathFunctions.Draw3D(g, this.x, this.y, this.dx[i][0], this.dy[i][0], this.dz[i][0]);
			g.setColor(new Color(0,0,0));
			MathFunctions.Draw3D(g, this.x, this.y, this.dx[i][1], this.dy[i][1], this.dz[i][1]);
		}
		for (int i1 = 0; i1 < this.length*2; i1++) {
			for (int i = 0; i < 2; i++) {
				g.setColor(new Color(20,20,20));
				MathFunctions.Draw3D(g, this.x, this.y, this.ds[i1][0][i],this.ds[i1][1][i],this.ds[i1][2][i]);
			}
		}
		
	}
	
	private double[] gcp(double degree, double r) {
		
		return new double[] {Math.cos(Math.toRadians(degree)),Math.sin(Math.toRadians(degree))};
	}
	
	private void calculateSpike(int posX, int posY, int spike) {
		for (int i = 0; i < xs[0][1].length; i++) {
			this.xs[spike][0][i] = 2*Math.cos(Math.toRadians((360/xs[0][1].length)*i))+posX;
			this.zs[spike][0][i] = 2*Math.sin(Math.toRadians((360/xs[0][1].length)*i))+posY;
			this.ys[spike][0][i] = 0;
			this.xs[spike][1][i] = 1.25*Math.cos(Math.toRadians((360/xs[0][1].length)*i))+posX;
			this.zs[spike][1][i] = 1.25*Math.sin(Math.toRadians((360/xs[0][1].length)*i))+posY;
			this.ys[spike][1][i] = 0;
		}
	}
	
	public void Update() {
		if (!this.disabled) {
			if (Game.player.x+60 >= this.x && Game.player.x+60 <= this.x+(this.length*10) && Game.player.y+60 >= this.y && Game.player.y+60 <= this.y+(this.length*10) && !this.spearOut) {
				this.timer += 1;
				if (this.timer > 40) {
					this.spearMoving = true;
					this.timer = 0;
				}
			}
			if (this.spearMoving && !this.spearOut) {
				this.spearLength += 3;
				if (this.spearLength > this.length*10) {
					this.spearOut = true;
					this.spearMoving = false;
				}
			}
			if (!this.spearMoving && this.spearOut) {
				this.timer += 1;
				if (this.timer > 60) {
					this.spearMoving = true;
					this.timer = 0;
				}
			}
			if (this.spearMoving && this.spearOut) {
				this.spearLength -= 1;
				if (this.spearLength == 0) {
					this.spearOut = false;
					this.spearMoving = false;
				}
			}
		}
		changeSpearLength((int)this.spearLength);
		if (this.disabled) {
			if (this.spearLength > 0) {
				this.spearLength -= 1;
			}
		}
	}
	
	private void changeSpearLength(int posY) {
		for (int i = 0; i < this.length*2; i++) {
			if (this.facing == 0) {
				this.ds[i][1][0][2] = posY;
				this.ds[i][1][0][3] = posY;
				this.ds[i][1][1][0] = posY;
				this.ds[i][1][1][1] = posY;
				this.ds[i][1][1][2] = posY+2;
				this.ds[i][1][1][3] = posY+2;
			} else if (this.facing == 1) {
				this.ds[i][0][0][2] = (this.length*10)-posY;
				this.ds[i][0][0][3] = (this.length*10)-posY;
				this.ds[i][0][1][0] = (this.length*10)-posY;
				this.ds[i][0][1][1] = (this.length*10)-posY;
				this.ds[i][0][1][2] = (this.length*10)-posY-2;
				this.ds[i][0][1][3] = (this.length*10)-posY-2;
			} else if (this.facing == 2) {
				this.ds[i][1][0][2] = (this.length*10)-posY;
				this.ds[i][1][0][3] = (this.length*10)-posY;
				this.ds[i][1][1][0] = (this.length*10)-posY;
				this.ds[i][1][1][1] = (this.length*10)-posY;
				this.ds[i][1][1][2] = (this.length*10)-posY-2;
				this.ds[i][1][1][3] = (this.length*10)-posY-2;
			} else if (this.facing == 3) {
				this.ds[i][0][0][2] = posY;
				this.ds[i][0][0][3] = posY;
				this.ds[i][0][1][0] = posY;
				this.ds[i][0][1][1] = posY;
				this.ds[i][0][1][2] = posY+2;
				this.ds[i][0][1][3] = posY+2;
			}
		}
	}

}
