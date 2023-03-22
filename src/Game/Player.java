package Game;
import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public double x;
	public double y;
	public int z;
	public boolean canMove;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 110;
		this.canMove = true;
	}
	
	public void update() {
		if (canMove) {
			if (Game.listener.up) {
				this.y -= 1;
			}
			if (Game.listener.down) {
				this.y += 1;
			}
			if (Game.listener.left) {
				this.x -= 1;
			}
			if (Game.listener.right) {
				this.x += 1;
			}
			this.x = (int)this.x;
			this.y = (int)this.y;
		}
	}
	
	public void move(int moveX, int moveY) {
		this.x += moveX;
		this.y += moveY;
		
	}
	
	public void Draw(Graphics g) {
		g.setColor(Color.green);
		int tileRenderSize = MathFunctions.Projection(this.x+10, this.y+10, this.z)[0] - MathFunctions.Projection(this.x, this.y, this.z)[0];
		g.fillRect(MathFunctions.Projection(-5,-5, this.z)[0],MathFunctions.Projection(-5,-5, this.z)[1],tileRenderSize,tileRenderSize);
	}

}
