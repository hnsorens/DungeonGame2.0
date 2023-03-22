package DungeonFeatures;

import Game.Game;
import Game.MathFunctions;

import java.awt.Color;
import java.awt.Graphics;

public class Chest {
	
	public int x, y, facing;
	boolean locked;
	double rpy, rpz;
	double[] degrees, lengths;
	double[] dX, dY, dZ;
	
	boolean closed;
	boolean lidMoving;
	
	Color color1 = new Color(80,80,80); //80
	Color color2 = new Color(20,20,20); //20
	
	
	int lidDegree = 0;
	
	public Chest(int x, int y, int facing) {
		this.facing = facing;
		this.locked = false;
		this.x = x;
		this.y = y;
		this.closed = true;
		this.lidMoving = false;
	}
	
	public void Update() {
		if (this.facing==1) {
			if (this.lidMoving) {
				if (this.closed) {
					lidDegree -= 4;
					if (lidDegree == -120) {
						this.closed = false;
						this.lidMoving = false;
					}
				} else if (!this.closed) {
					lidDegree += 4;
					if (lidDegree == 0) {
						this.closed = true;
						this.lidMoving = false;
					}
				}
			}
		}else {
			if (this.lidMoving) {
				if (this.closed) {
					lidDegree += 4;
					if (lidDegree == 120) {
						this.closed = false;
						this.lidMoving = false;
					}
				} else if (!this.closed) {
					lidDegree -= 4;
					if (lidDegree == 0) {
						this.closed = true;
						this.lidMoving = false;
					}
				}
			}
		}
		if (this.locked) {
			if (Game.player.x == this.x+24-60 && Game.player.y > this.y+5-60-10 && Game.player.y < this.y+25-60) {
				Game.player.x = this.x+25-60;
			}
			if (Game.player.x == this.x+6-70 && Game.player.y > this.y+5-60-10 && Game.player.y < this.y+25-60) {
				Game.player.x = this.x+5-70;
			}
			if (Game.player.y == this.y+6-70 && Game.player.x > this.x+5-60-10 && Game.player.x < this.x+25-60) {
				Game.player.y = this.y+5-70;
			}
			if (Game.player.y == this.y+24-60 && Game.player.x > this.x+5-60-10 && Game.player.x < this.x+25-60) {
				Game.player.y = this.y+25-60;
			}
		} else if (!this.locked) {
			if (Game.player.x == this.x+19-60 && Game.player.y > this.y+10-60-10 && Game.player.y < this.y+20-60) {
				Game.player.x = this.x+20-60;
			}
			if (Game.player.x == this.x+11-70 && Game.player.y > this.y+10-60-10 && Game.player.y < this.y+20-60) {
				Game.player.x = this.x+10-70;
			}
			if (Game.player.y == this.y+11-70 && Game.player.x > this.x+10-60-10 && Game.player.x < this.x+20-60) {
				Game.player.y = this.y+10-70;
			}
			if (Game.player.y == this.y+19-60 && Game.player.x > this.x+10-60-10 && Game.player.x < this.x+20-60) {
				Game.player.y = this.y+20-60;
			}
		}
	}
	
	public void toggleOpen() {
		this.lidMoving = true;
	}
	
	public void Draw(Graphics g) {
		g.setColor(Color.yellow);
		//top bottom left right
		
		if (Game.player.y>this.y-60+18) {
			g.setColor(color1);
			MathFunctions.Draw3D(g,this.x, this.y,  new double[] {11,19,19,11}, new double[] {18,18,18,18}, new double[] {120,120,114,114});
		}
		if (Game.player.y<this.y-60+12) {
			g.setColor(color1);
			MathFunctions.Draw3D(g,this.x, this.y,  new double[] {11,19,19,11}, new double[] {12,12,12,12}, new double[] {120,120,114,114});
		}
		if (Game.player.x<this.x-60+11) {
			g.setColor(color1);
			MathFunctions.Draw3D(g,this.x, this.y,  new double[] {11,11,11,11}, new double[] {12,18,18,12}, new double[] {120,120,114,114});
		}
		if (Game.player.x>this.x-60+19) {
			g.setColor(color1);
			MathFunctions.Draw3D(g,this.x, this.y,  new double[] {19,19,19,19}, new double[] {12,18,18,12}, new double[] {120,120,114,114});
		}
		
		if (Game.player.y>this.y-60+18) {
			
			g.setColor(color2);
			if (Game.player.x > this.x-60+11) {
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {11,11,11,11} ,new double[] {18,19,19,18}, new double[] {114,114,120,120});
			}
			if (Game.player.x < this.x-60+19) {
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {19,19,19,19} ,new double[] {18,19,19,18}, new double[] {114,114,120,120});
			}
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,20,20,10}, new double[] {19,19,19,19}, new double[] {114,114,115,115});
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,11,11,10}, new double[] {19,19,19,19}, new double[] {114,114,120,120});
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {19,20,20,19}, new double[] {19,19,19,19}, new double[] {114,114,120,120});
		}
		if (Game.player.y<this.y-60+12) {
			
			g.setColor(color2);
			if (Game.player.x > this.x-60+11) {
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {11,11,11,11} ,new double[] {11,12,12,11}, new double[] {114,114,120,120});
			}
			if (Game.player.x < this.x-60+19) {
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {19,19,19,19} ,new double[] {11,12,12,11}, new double[] {114,114,120,120});
			}
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,20,20,10}, new double[] {11,11,11,11}, new double[] {114,114,115,115});
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,11,11,10}, new double[] {11,11,11,11}, new double[] {114,114,120,120});
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {19,20,20,19}, new double[] {11,11,11,11}, new double[] {114,114,120,120});
		}
		if (Game.player.x<this.x-60+11) {
			
			g.setColor(color2);
			if (Game.player.y > this.y-60+12) {
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,11,11,10} ,new double[] {12,12,12,12}, new double[] {114,114,120,120});
			}
			if (Game.player.y < this.y-60+18) {
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,11,11,10} ,new double[] {18,18,18,18}, new double[] {114,114,120,120});
			}
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,10,10,10}, new double[] {11,11,19,19}, new double[] {114,114,115,115});
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,10,10,10}, new double[] {11,12,12,11}, new double[] {114,114,120,120});
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,10,10,10}, new double[] {18,19,19,18}, new double[] {114,114,120,120});
		}
		if (Game.player.x>this.x-60+19) {
			
			g.setColor(color2);
			if (Game.player.y > this.y-60+12) {
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {19,20,20,19} ,new double[] {12,12,12,12}, new double[] {114,114,120,120});
			}
			if (Game.player.y < this.y-60+18) {
				MathFunctions.Draw3D(g, this.x, this.y, new double[] {19,20,20,19} ,new double[] {18,18,18,18}, new double[] {114,114,120,120});
			}
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {20,20,20,20}, new double[] {11,11,19,19}, new double[] {114,114,115,115});
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {20,20,20,20}, new double[] {11,12,12,11}, new double[] {114,114,120,120});
			MathFunctions.Draw3D(g, this.x, this.y, new double[] {20,20,20,20}, new double[] {18,19,19,18}, new double[] {114,114,120,120});
		}
		g.setColor(color2);
		
		MathFunctions.Draw3D(g, this.x, this.y, new double[] {11,19,20,10}, new double[] {18,18,19,19}, new double[] {114,114,114,114});
		MathFunctions.Draw3D(g, this.x, this.y, new double[] {10,11,11,10}, new double[] {11,12,18,19}, new double[] {114,114,114,114});
		MathFunctions.Draw3D(g, this.x, this.y, new double[] {20,19,19,20}, new double[] {11,12,18,19}, new double[] {114,114,114,114});
		MathFunctions.Draw3D(g, this.x, this.y, new double[] {11,19,20,10}, new double[] {12,12,11,11}, new double[] {114,114,114,114});
		g.setColor(new Color(0,0,0));
		MathFunctions.Draw3D(g, this.x, this.y, new double[] {11,19,19,11}, new double[] {12,12,18,18}, new double[] {114,114,114,114});
		if (locked) {
			g.setColor(color2);
			for (int i = 0; i < 20; i+=4) {
				MathFunctions.Draw3D(g,this.x, this.y, new double[] {6+i,7+i,7+i,6+i}, new double[] {25,25,25,25}, new double[] {120,120,105,105});
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {6+i,7+i,7+i,6+i}, new double[] {5,5,5,5}, new double[] {120,120,105,105});
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {5,5,5,5}, new double[] {6+i,7+i,7+i,6+i}, new double[] {120,120,105,105});
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {25,25,25,25}, new double[] {6+i,7+i,7+i,6+i}, new double[] {120,120,105,105});
				
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {5,5,25,25}, new double[] {6+i,7+i,7+i,6+i}, new double[] {105,105,105,105});
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {6+i,7+i,7+i,6+i}, new double[] {5,5,25,25}, new double[] {105,105,105,105});
			}
			for (int i = 0; i < 15; i+=4) {
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {5,25,25,5}, new double[] {25,25,25,25}, new double[] {105+i,105+i,106+i,106+i});
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {5,25,25,5}, new double[] {5,5,5,5}, new double[] {105+i,105+i,106+i,106+i});
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {5,5,5,5}, new double[] {5,25,25,5}, new double[] {105+i,105+i,106+i,106+i});
				MathFunctions.Draw3D(g,this.x, this.y,  new double[] {25,25,25,25}, new double[] {5,25,25,5}, new double[] {105+i,105+i,106+i,106+i});
			}
		}
		
		if (Game.player.x > this.x-60+14) {
			LidLeft(g);
			LidLMiddle(g);
			LidMiddle(g);
			LidRMiddle(g);
			LidRight(g);
		}
		if (Game.player.x < this.x-60+15) {
			LidRight(g);
			LidRMiddle(g);
			LidMiddle(g);
			LidLMiddle(g);
			LidLeft(g);
		}
		
	}
	
	private void LidLMiddle(Graphics g) {
		g.setColor(color1);
		DrawLidFace(g, new double[] {11,11,11,11}, new double[] {11.5,18.5,17,13}, new double[] {114,114,111.5,111.5}, lidDegree);
	}
	
	private void LidRMiddle(Graphics g) {
		g.setColor(color1);
		DrawLidFace(g, new double[] {19,19,19,19}, new double[] {11.5,18.5,17,13}, new double[] {114,114,111.5,111.5}, lidDegree);
	}
	
	private void LidMiddle(Graphics g) {
		g.setColor(color1);
		DrawLidFace(g, new double[] {11,19,19,11}, new double[] {11.5,11.5,13,13}, new double[] {114,114,111.5,111.5}, lidDegree);
		DrawLidFace(g, new double[] {11,19,19,11}, new double[] {18.5,18.5,17,17}, new double[] {114,114,111.5,111.5}, lidDegree);
		DrawLidFace(g, new double[] {11,19,19,11}, new double[] {13,13,17,17}, new double[] {111.5,111.5,111.5,111.5}, lidDegree);
	}
	
	private void LidLeft(Graphics g) {
		g.setColor(color2);
		DrawLidFace(g, new double[] {10,10,10,10}, new double[] {11,12,13,12.5}, new double[] {114,114,112,111}, lidDegree);
		DrawLidFace(g, new double[] {10,10,10,10}, new double[] {19,18,17,17.5}, new double[] {114,114,112,111}, lidDegree);
		DrawLidFace(g, new double[] {10,10,10,10}, new double[] {13,17,17.5,12.5}, new double[] {112,112,111,111}, lidDegree);
		
		DrawLidFace(g, new double[] {10,10,11,11}, new double[] {11,12.5,12.5,11}, new double[] {114,111,111,114}, lidDegree);
		DrawLidFace(g, new double[] {10,10,11,11}, new double[] {19,17.5,17.5,19}, new double[] {114,111,111,114}, lidDegree);
		DrawLidFace(g, new double[] {10,10,11,11}, new double[] {17.5,12.5,12.5,17.5}, new double[] {111,111,111,111}, lidDegree);
		
		DrawLidFace(g, new double[] {11,11,11,11}, new double[] {11,11.5,13,12.5}, new double[] {114,114,111.5,111}, lidDegree);
		DrawLidFace(g, new double[] {11,11,11,11}, new double[] {19,18.5,17,17.5}, new double[] {114,114,111.5,111}, lidDegree);
		DrawLidFace(g, new double[] {11,11,11,11}, new double[] {13,17,17.5,12.5}, new double[] {111.5,111.5,111,111}, lidDegree);
		
		DrawLidFace(g, new double[] {10,11,11,10}, new double[] {11,11,12,12}, new double[] {114,114,114,114}, lidDegree);
		DrawLidFace(g, new double[] {10,11,11,10}, new double[] {19,19,18,18}, new double[] {114,114,114,114}, lidDegree);
	}
	
	private void LidRight(Graphics g) {
		g.setColor(color2);
		DrawLidFace(g, new double[] {20,20,20,20}, new double[] {11,12,13,12.5}, new double[] {114,114,112,111}, lidDegree);
		DrawLidFace(g, new double[] {20,20,20,20}, new double[] {19,18,17,17.5}, new double[] {114,114,112,111}, lidDegree);
		DrawLidFace(g, new double[] {20,20,20,20}, new double[] {13,17,17.5,12.5}, new double[] {112,112,111,111}, lidDegree);
		
		DrawLidFace(g, new double[] {20,20,19,19}, new double[] {11,12.5,12.5,11}, new double[] {114,111,111,114}, lidDegree);
		DrawLidFace(g, new double[] {20,20,19,19}, new double[] {19,17.5,17.5,19}, new double[] {114,111,111,114}, lidDegree);
		DrawLidFace(g, new double[] {20,20,19,19}, new double[] {17.5,12.5,12.5,17.5}, new double[] {111,111,111,111}, lidDegree);
		
		DrawLidFace(g, new double[] {19,19,19,19}, new double[] {11,11.5,13,12.5}, new double[] {114,114,111.5,111}, lidDegree);
		DrawLidFace(g, new double[] {19,19,19,19}, new double[] {19,18.5,17,17.5}, new double[] {114,114,111.5,111}, lidDegree);
		DrawLidFace(g, new double[] {19,19,19,19}, new double[] {13,17,17.5,12.5}, new double[] {111.5,111.5,111,111}, lidDegree);
		
		DrawLidFace(g, new double[] {20,19,19,20}, new double[] {11,11,12,12}, new double[] {114,114,114,114}, lidDegree);
		DrawLidFace(g, new double[] {20,19,19,20}, new double[] {19,19,18,18}, new double[] {114,114,114,114}, lidDegree);
	}
	
	public void DrawLidFace(Graphics g, double[] x, double[] y, double[] z, int degree) {
		if (this.facing==1) {
			rpy = 11.5;
			rpz = 113.5;
		} else {
			rpy = 18.5;
			rpz = 113.5;
		}
		
		degrees = new double[x.length];
		lengths = new double[x.length];
		dY = new double[x.length];
		dZ = new double[x.length];
		
		for (int i = 0; i < x.length; i++) {
			lengths[i] = Math.sqrt(Math.pow(z[i]-rpz, 2)+Math.pow(y[i]-rpy,2));
			degrees[i] = Math.toDegrees(Math.atan2((z[i]-rpz),(y[i]-rpy)))+degree;
			dZ[i] = (lengths[i]*Math.sin(Math.toRadians(degrees[i])))+rpz;
			dY[i] = (lengths[i]*Math.cos(Math.toRadians(degrees[i])))+rpy;
		}
		
		MathFunctions.Draw3D(g, this.x, this.y, x, dY, dZ);
		
	}

}
