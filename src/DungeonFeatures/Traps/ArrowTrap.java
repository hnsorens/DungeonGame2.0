package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Game.Game;
import Game.MathFunctions;

public class ArrowTrap {
	
	public int x, y, w, facing;
	
	double[][][][] holes;
	List<Arrow> arrows;
	
	int timer;
	
	public ArrowTrap(int x, int y, int facing, int w) {
		this.arrows = new ArrayList<Arrow>();
		this.x = x;
		this.y = y;
		this.w = w;
		this.timer = 0;
		this.facing = facing;
		this.holes = new double[3][this.w*2][2][8];
		this.holes = new double[3][this.w*2][2][8];
		for (int i = 0; i < w; i++) {
			for (int i1 = 0; i1 < 2; i1++) {
				calculateHole(i+(w*i1), 5+(i*10), 105+(i1*10));
			}
		}
	}
	
	
	public void Draw(Graphics g) {
		for (int i = 0; i < w*2; i++) {
			g.setColor(new Color(20,20,20));
			MathFunctions.Draw3D(g, this.x, this.y, this.holes[0][i][0], this.holes[1][i][0], this.holes[2][i][0]);
			g.setColor(new Color(0,0,0));
			MathFunctions.Draw3D(g, this.x, this.y, this.holes[0][i][1], this.holes[1][i][1], this.holes[2][i][1]);
		}
		for (int i = 0; i < this.arrows.size(); i++) {
			this.arrows.get(i).Draw(g);
		}
	}
	
	public void Update() {
		for (int i = 0; i < this.arrows.size(); i++) {
			if (this.arrows.get(i).distance > 127) {
				this.arrows.remove(i);
			}
		}
		this.timer += 1;
		for (int i = 0; i < this.arrows.size(); i++) {
			this.arrows.get(i).distance+=1;
		}
		if (Game.player.x >= this.x-60 && Game.player.x <= this.x+(10*w)-60 && Game.player.y >= this.y-60 && Game.player.y <= this.y+(10*w)-60) {
			if (this.timer > 60) {
				this.timer = 0;
				if (this.facing == 0) {
					for (int i = 0; i < w; i++) {
						for (int i1 = 0; i1 < 2; i1++) {
							this.arrows.add(new Arrow(5+(i*10), 0, 105+(i1*10),0));
						}
					}
				} else if (this.facing == 1) {
					for (int i = 0; i < w; i++) {
						for (int i1 = 0; i1 < 2; i1++) {
							this.arrows.add(new Arrow(this.w*10, 5+(i*10), 105+(i1*10),1));
						}
					}
				} else if (this.facing == 2) {
					for (int i = 0; i < w; i++) {
						for (int i1 = 0; i1 < 2; i1++) {
							this.arrows.add(new Arrow(5+(i*10), this.w*10, 105+(i1*10),2));
						}
					}
				} else if (this.facing == 3) {
					for (int i = 0; i < w; i++) {
						for (int i1 = 0; i1 < 2; i1++) {
							this.arrows.add(new Arrow(0, 5+(i*10), 105+(i1*10),3));
						}
					}
				}
			}
		}
	}
	
	
	
	private void calculateHole(int hole, int posX, int posY) {
		for (int i = 0; i < 8; i++) {
			if (this.facing == 0) {
				this.holes[0][hole][0][i] = 2*Math.cos(Math.toRadians((360/8)*i))+posX;
				this.holes[2][hole][0][i] = 2*Math.sin(Math.toRadians((360/8)*i))+posY;
				this.holes[1][hole][0][i] = 0;
				this.holes[0][hole][1][i] = 1.25*Math.cos(Math.toRadians((360/8)*i))+posX;
				this.holes[2][hole][1][i] = 1.25*Math.sin(Math.toRadians((360/8)*i))+posY;
				this.holes[1][hole][1][i] = 0;
			} else if (this.facing == 1) {
				this.holes[1][hole][0][i] = 2*Math.cos(Math.toRadians((360/8)*i))+posX;
				this.holes[2][hole][0][i] = 2*Math.sin(Math.toRadians((360/8)*i))+posY;
				this.holes[0][hole][0][i] = this.w*10;
				this.holes[1][hole][1][i] = 1.25*Math.cos(Math.toRadians((360/8)*i))+posX;
				this.holes[2][hole][1][i] = 1.25*Math.sin(Math.toRadians((360/8)*i))+posY;
				this.holes[0][hole][1][i] = this.w*10;
			} else if (this.facing == 2) {
				this.holes[0][hole][0][i] = 2*Math.cos(Math.toRadians((360/8)*i))+posX;
				this.holes[2][hole][0][i] = 2*Math.sin(Math.toRadians((360/8)*i))+posY;
				this.holes[1][hole][0][i] = this.w*10;
				this.holes[0][hole][1][i] = 1.25*Math.cos(Math.toRadians((360/8)*i))+posX;
				this.holes[2][hole][1][i] = 1.25*Math.sin(Math.toRadians((360/8)*i))+posY;
				this.holes[1][hole][1][i] = this.w*10;
			} else if (this.facing == 3) {
				this.holes[1][hole][0][i] = 2*Math.cos(Math.toRadians((360/8)*i))+posX;
				this.holes[2][hole][0][i] = 2*Math.sin(Math.toRadians((360/8)*i))+posY;
				this.holes[0][hole][0][i] = 0;
				this.holes[1][hole][1][i] = 1.25*Math.cos(Math.toRadians((360/8)*i))+posX;
				this.holes[2][hole][1][i] = 1.25*Math.sin(Math.toRadians((360/8)*i))+posY;
				this.holes[0][hole][1][i] = 0;
			}
		}
	}

}
