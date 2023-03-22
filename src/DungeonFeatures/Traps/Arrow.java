package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;

import Game.MathFunctions;

public class Arrow {
	
	int x, y, z, facing;
	double distance;
	
	public Arrow(int x, int y, int z, int facing) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.facing = facing;
		this.distance = 2;
	}
	
	public void Update() {
		
	}
	
	public void Draw(Graphics g) {
		if (this.facing == 0) {
			g.setColor(new Color(20,20,20));
			MathFunctions.Draw3D(g, this.x, this.y+this.distance, new double[] {-0.25,0.25,0.25,0,-0.25}, new double[] {-2,-2,0,0.5,0}, new double[] {this.z,this.z,this.z,this.z,this.z});
			//MathFunctions.Draw3D(g, this.x, this.y+this.distance, new double[] {}, new double[] {}, new double[] {this.z,this.z,this.z,this.z});
		} else if (this.facing == 1) {
			g.setColor(new Color(20,20,20));
			MathFunctions.Draw3D(g, this.x-this.distance, this.y, new double[] {2,2,0,-0.5,0}, new double[] {-0.25,0.25,0.25,0,-0.25}, new double[] {this.z,this.z,this.z,this.z,this.z});
			//MathFunctions.Draw3D(g, this.x, this.y+this.distance, new double[] {}, new double[] {}, new double[] {this.z,this.z,this.z,this.z});
		} else if (this.facing == 2) {
			g.setColor(new Color(20,20,20));
			MathFunctions.Draw3D(g, this.x, this.y-this.distance, new double[] {-0.25,0.25,0.25,0,-0.25}, new double[] {2,2,0,-0.5,0}, new double[] {this.z,this.z,this.z,this.z,this.z});
			//MathFunctions.Draw3D(g, this.x, this.y+this.distance, new double[] {}, new double[] {}, new double[] {this.z,this.z,this.z,this.z});
		} else if (this.facing == 3) {
			g.setColor(new Color(20,20,20));
			MathFunctions.Draw3D(g, this.x+this.distance, this.y, new double[] {-2,-2,0,0.5,0}, new double[] {-0.25,0.25,0.25,0,-0.25}, new double[] {this.z,this.z,this.z,this.z,this.z});
			//MathFunctions.Draw3D(g, this.x, this.y+this.distance, new double[] {}, new double[] {}, new double[] {this.z,this.z,this.z,this.z});
		}
		
	}

}
