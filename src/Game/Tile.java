package Game;
import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	
	int x, y, z, type;
	
	int Size = 10;
	
	public Tile(int x, int y, int z, int type) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}
	
	public void Show(Graphics g) {
		g.setColor(Color.black);
		double tileRenderSize = MathFunctions.Projection(x*Size+10-Game.player.x-5, y*Size,this.z)[0]-MathFunctions.Projection((x*Size)-Game.player.x-5, y*Size,this.z)[0];
		g.fillRect(MathFunctions.Projection(x*Size-Game.player.x-5, y*Size-Game.player.y-5,this.z)[0], (int)MathFunctions.Projection(x*Size-Game.player.x-5, y*Size-Game.player.y-5,this.z)[1], (int)tileRenderSize,(int)tileRenderSize);
	}

}
