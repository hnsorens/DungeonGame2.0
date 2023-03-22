package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;
import Game.MathFunctions;

public class Spinner {
	
	public int x, y, r;
	
	double[][][] middleCoords;
	
	double[][][] blades;
	double[][][] dBlades;
	
	int degree;
	int speed;
	
	public Spinner(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		
		this.degree = 0;
		
		this.speed = 1;
		
		this.middleCoords = new double[3][8][4];
		
		for (int i = 0; i < 8; i++) {
			this.middleCoords[0][i][0] = Math.cos(Math.toRadians(i*45))+(r*10);
			this.middleCoords[1][i][0] = Math.sin(Math.toRadians(i*45))+(r*10);
			this.middleCoords[2][i][0] = 115;
			this.middleCoords[0][i][1] = Math.cos(Math.toRadians(i*45))+(r*10);
			this.middleCoords[1][i][1] = Math.sin(Math.toRadians(i*45))+(r*10);
			this.middleCoords[2][i][1] = 120;
			this.middleCoords[0][i][2] = Math.cos(Math.toRadians(45+(i*45)))+(r*10);
			this.middleCoords[1][i][2] = Math.sin(Math.toRadians(45+(i*45)))+(r*10);
			this.middleCoords[2][i][2] = 120;
			this.middleCoords[0][i][3] = Math.cos(Math.toRadians(45+(i*45)))+(r*10);
			this.middleCoords[1][i][3] = Math.sin(Math.toRadians(45+(i*45)))+(r*10);
			this.middleCoords[2][i][3] = 115;
		}
		this.blades = new double[][][] {{{0,3,r*10,r*10,3},{r*20,(r*20)-3,r*10,r*10,(r*20)-3},{r*10,(r*10)-1,(r*10)-1,(r*10)+1,(r*10)+1},{r*10,(r*10)-1,(r*10)-1,(r*10)+1,(r*10)+1}},{{r*10,(r*10)-1,(r*10)-1,(r*10)+1,(r*10)+1},{r*10,(r*10)-1,(r*10)-1,(r*10)+1,(r*10)+1},{0,2,r*10,r*10,2},{r*20,(r*20)-3,r*10,r*10,(r*20)-3}},{{115,115,115,115,115},{115,115,115,115,115},{115,115,115,115,115},{115,115,115,115,115}}};
		this.dBlades = new double[3][4][5];
	}
	
	public void Draw(Graphics g) {
		g.setColor(new Color(20,20,20));
		for (int i = 0; i < 8; i++) {
			MathFunctions.Draw3D(g, this.x, this.y, this.middleCoords[0][i], this.middleCoords[1][i], this.middleCoords[2][i]);
		}
		g.setColor(new Color(30,30,30));
//		MathFunctions.Draw3D(g, this.x, this.y, this.blade[0], this.blade[1], this.blade[2]);
		for (int i = 0; i < 4; i++) {
			MathFunctions.Draw3D(g, this.x, this.y, this.dBlades[0][i], this.dBlades[1][i], this.dBlades[2][i]);
		}
	}
	
	public void Update() {
		degree += this.speed;
		for (int i = 0; i < 4; i++) {
			for (int i1 = 0; i1 < 5; i1++) {
				this.dBlades[0][i][i1] = MathFunctions.rotate((int)(this.blades[0][i][i1]), (int)(this.blades[1][i][i1]), r*10, r*10, this.degree)[0];
				this.dBlades[1][i][i1] = MathFunctions.rotate((int)(this.blades[0][i][i1]), (int)(this.blades[1][i][i1]), r*10, r*10, this.degree)[1];
				this.dBlades[2][i][i1] = 115;		
			}	
		}
		
		if (Game.player.x > r*10-60-12+this.x && Game.player.x < r*10-60+2+this.x) {
			if (Game.player.y == r*10-60-10+this.y) {
				Game.player.y = r*10-60-11+this.y;
			}
			if (Game.player.y == r*10-60+this.y) {
				Game.player.y = r*10-60+1+this.y;
			}
		}
		if (Game.player.y > r*10-60-12+this.y && Game.player.y < r*10-60+2+this.y) {
			if (Game.player.x == r*10-60-10+this.x) {
				Game.player.x = r*10-60-11+this.x;
			}
			if (Game.player.x == r*10-60+this.x) {
				Game.player.x = r*10-60+1+this.x;
			}
		}
	}
}
