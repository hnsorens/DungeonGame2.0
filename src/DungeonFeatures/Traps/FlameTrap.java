package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;

import Game.MathFunctions;

public class FlameTrap {
	
	public int x, y, facing, w;
	
	Flame[][] flames;
	
	double[][][][] holes;
	
	boolean disabled;
	boolean fire;
	
	public FlameTrap(int x, int y, int facing, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.facing = facing;
		this.fire = false;
		this.disabled = false;
		flames = new Flame[2][w];
		for (int i = 0; i < w; i++) {
			if (this.facing == 0) {
				flames[0][i] = new Flame(MathFunctions.Projection(this.x-60+(i*10), this.y-65, 115)[0],MathFunctions.Projection(this.x-60+(i*10), this.y-70, 115)[1],0,320);
				flames[1][i] = new Flame(MathFunctions.Projection(this.x-60+(i*10), this.y-65, 105)[0],MathFunctions.Projection(this.x-60+(i*10), this.y-70, 105)[1],0,320);
			} else if (this.facing == 1) {
				flames[0][i] = new Flame(MathFunctions.Projection( this.y-65,this.x-60+(i*10)+(w*10), 115)[0],MathFunctions.Projection(this.y+70, this.x-60+(i*10)+(w*10), 115)[1],-320,0);
				flames[1][i] = new Flame(MathFunctions.Projection( this.y-65,this.x-60+(i*10)+(w*10), 105)[0],MathFunctions.Projection(this.y+70, this.x-60+(i*10)+(w*10), 105)[1],-320,0);
			} else if (this.facing == 2) {
				flames[0][i] = new Flame(MathFunctions.Projection(this.x-60+(i*10), this.y-65, 115)[0],MathFunctions.Projection(this.x-60+(i*10), this.y-70, 115)[1],0,-320);
				flames[1][i] = new Flame(MathFunctions.Projection(this.x-60+(i*10), this.y-65, 105)[0],MathFunctions.Projection(this.x-60+(i*10), this.y-70, 105)[1],0,-320);
			} else if (this.facing == 3) {
				flames[0][i] = new Flame(MathFunctions.Projection( this.y-65,this.x-60+(i*10), 115)[0],MathFunctions.Projection(this.y+70, this.x-60+(i*10), 115)[1],320,0);
				flames[1][i] = new Flame(MathFunctions.Projection( this.y-65,this.x-60+(i*10), 105)[0],MathFunctions.Projection(this.y+70, this.x-60+(i*10), 105)[1],320,0);
			}
		}
		this.holes = new double[3][this.w*2][2][8];
		for (int i = 0; i < w; i++) {
			for (int i1 = 0; i1 < 2; i1++) {
				calculateHole(i+(w*i1), 5+(i*10), 105+(i1*10));
			}
		}
	}
	
	public void Update() {
		if (!disabled) {
			for (int i = 0; i < w; i++) {
				flames[0][i].Update();
				flames[1][i].Update();
			}
			for (int i = 0; i < w; i++) {
				if (facing == 0) {
					flames[0][i].offx = MathFunctions.Projection(this.x-60+(i*10)-Game.player.x, this.y-65-Game.player.y, 115)[0];
					flames[0][i].offy = MathFunctions.Projection(this.x-60+(i*10)-Game.player.x, this.y-65-Game.player.y, 115)[1];
					flames[1][i].offx = MathFunctions.Projection(this.x-60+(i*10)-Game.player.x, this.y-65-Game.player.y, 105)[0];
					flames[1][i].offy = MathFunctions.Projection(this.x-60+(i*10)-Game.player.x, this.y-65-Game.player.y, 105)[1];
				} else if (facing == 1) {
					flames[0][i].offx = MathFunctions.Projection(this.x-65-Game.player.x+(w*10), this.y-60-Game.player.y+(i*10), 115)[0];
					flames[0][i].offy = MathFunctions.Projection(this.x-65-Game.player.x+(w*10), this.y-60-Game.player.y+(i*10), 115)[1];
					flames[1][i].offx = MathFunctions.Projection(this.x-65-Game.player.x+(w*10), this.y-60-Game.player.y+(i*10), 105)[0];
					flames[1][i].offy = MathFunctions.Projection(this.x-65-Game.player.x+(w*10), this.y-60-Game.player.y+(i*10), 105)[1];
				} else if (facing == 2) {
					flames[0][i].offx = MathFunctions.Projection(this.x-60+(i*10)-Game.player.x, this.y-65-Game.player.y+(w*10), 115)[0];
					flames[0][i].offy = MathFunctions.Projection(this.x-60+(i*10)-Game.player.x, this.y-65-Game.player.y+(w*10), 115)[1];
					flames[1][i].offx = MathFunctions.Projection(this.x-60+(i*10)-Game.player.x, this.y-65-Game.player.y+(w*10), 105)[0];
					flames[1][i].offy = MathFunctions.Projection(this.x-60+(i*10)-Game.player.x, this.y-65-Game.player.y+(w*10), 105)[1];
				} else if (facing == 3) {
					flames[0][i].offx = MathFunctions.Projection(this.x-65-Game.player.x, this.y-60-Game.player.y+(i*10), 115)[0];
					flames[0][i].offy = MathFunctions.Projection(this.x-65-Game.player.x, this.y-60-Game.player.y+(i*10), 115)[1];
					flames[1][i].offx = MathFunctions.Projection(this.x-65-Game.player.x, this.y-60-Game.player.y+(i*10), 105)[0];
					flames[1][i].offy = MathFunctions.Projection(this.x-65-Game.player.x, this.y-60-Game.player.y+(i*10), 105)[1];
				}
			}
			
			if (Game.player.x >= this.x-60 && Game.player.x <= this.x+(10*w)-60 && Game.player.y >= this.y-60 && Game.player.y <= this.y+(10*w)-60) {
				for (int i = 0; i < w; i++) {
					this.flames[0][i].disabled = false;
					this.flames[1][i].disabled = false;
				}
			} else {
				for (int i = 0; i < w; i++) {
					this.flames[0][i].disabled = true;
					this.flames[1][i].disabled = true;
				}
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
		for (int i = 0; i < w; i++) {
			flames[0][i].Draw(g);
			flames[1][i].Draw(g);
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
