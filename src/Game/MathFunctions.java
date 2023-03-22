package Game;
import java.awt.Graphics;

public class MathFunctions {
	
	public static int[] Projection(double x, double y, double z) {

		double fpx = Math.tan(Math.toRadians(45))*z*2;
		
		double rx = x/fpx;
		double ry = y/fpx;
		
		double sx = ScaleNumberLine(x,fpx,Game.Width);
		double sy = ScaleNumberLine(y,fpx,Game.Width);
		
		return new int[] {(int)Math.round(sx+(Game.Width/2)), (int)Math.round(sy+Game.Height/2)};
	}
	public static double[] ProjectionDouble(double x, double y, double z) {

		double fpx = Math.tan(Math.toRadians(45))*z*2;
		
		double rx = x/fpx;
		double ry = y/fpx;
		
		double sx = ScaleNumberLine(x,fpx,Game.Width);
		double sy = ScaleNumberLine(y,fpx,Game.Width);
		
		return new double[] {Math.round(sx+(Game.Width/2)), Math.round(sy+Game.Height/2)};
	}
	
	public static double ScaleNumberLine(double number, double line1, double line2) {
		return (number/line1)*line2;
	}
	
	public static void Draw3D(Graphics g, double posX, double posY, double[] x, double[] y, double[] z) {
		int[] dx = new int[x.length];
		int[] dy = new int[x.length];
		for (int i = 0; i < x.length; i++) {
			dx[i] = (int)ProjectionDouble(x[i]-60-Game.player.x-5+posX,y[i]-60-Game.player.y-5+posY, z[i])[0];
			dy[i] = (int)ProjectionDouble(x[i]-60-Game.player.x-5+posX,y[i]-60-Game.player.y-5+posY, z[i])[1];
		}
		g.fillPolygon(dx,dy,dx.length);
	}
	
	public static double[] rotate(double x, double y, double mx, double my, double degree) {
		double length = Math.sqrt(Math.pow(mx-x, 2)+Math.pow(my-y, 2));
		double initialDegree = Math.toDegrees(Math.atan2(y-my,x-mx));
		return new double[] {length*Math.cos(Math.toRadians(initialDegree+degree))+mx,length*Math.sin(Math.toRadians(initialDegree+degree))+my};
	
	}
	
	public static double getDegree(double x1, double y1, double x2, double y2) {
		return Math.toDegrees(Math.atan2(y2-y1, x2-x1));
	}
}
