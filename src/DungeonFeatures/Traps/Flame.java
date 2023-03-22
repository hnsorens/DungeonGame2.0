package DungeonFeatures.Traps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Game.MathFunctions;

public class Flame {
	
	double x, y;
	double offx, offy;
	
	List<double[]> particles;
	
	double length;
	double degree;
	
	double unitLength;
	
	boolean disabled;
	
	public Flame(int offx, int offy, int x, int y) {
		this.disabled = true;
		this.particles = new ArrayList<double[]>();
		this.x = x;
		this.y = y;
		this.offx = offx;
		this.offy = offy;
		this.length = Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2));
		this.degree = Math.toDegrees(Math.atan2(this.y, this.x));
	}
	
	private void createParticle() {
		int particleDegree = 0;
		if (Math.random()*2 > 1) {
			particleDegree = 90;
		} else {
			particleDegree = -90;
		}
		double placementFactor = Math.random()*7;
		this.particles.add(new double[] {placementFactor*Math.cos(Math.toRadians(this.degree+particleDegree)),placementFactor*Math.sin(Math.toRadians(this.degree+particleDegree)),8});
	}
	
	public void Draw(Graphics g) {
		for (int i = 0; i < this.particles.size(); i++) {
			g.setColor(new Color(200,(int)(Math.random()*150),12));
			g.fillRect((int)(this.offx+(Math.random()*8)-4)+(int)this.particles.get(i)[0],(int)(this.offy+(Math.random()*8)-4)+(int) this.particles.get(i)[1],(int) this.particles.get(i)[2],(int) this.particles.get(i)[2]);
			//System.out.println(this.particles.get(i)[0] + " " + this.particles.get(i)[1]);
		}
	}
	
	public void Update() {
		this.length = Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2));
		this.degree = Math.toDegrees(Math.atan2(this.y, this.x));
		if (!disabled) {
			createParticle();
			createParticle();
			createParticle();
		}
		for (int i = 0; i < this.particles.size(); i++) {
			this.particles.get(i)[0] += Math.cos(Math.toRadians(this.degree))*5;
			this.particles.get(i)[1] += Math.sin(Math.toRadians(this.degree))*5;
			if (Math.sqrt(Math.pow(this.particles.get(i)[0], 2)+Math.pow(this.particles.get(i)[1], 2)) > this.length) {
				if (Math.random()*4>3) {
					this.particles.remove(i);
				}
			}
		}
		for (int i = 0; i < this.particles.size(); i++) {
			if (Math.sqrt(Math.pow(this.particles.get(i)[0], 2)+Math.pow(this.particles.get(i)[1], 2)) > this.length/2) {
				if (Math.random()*25>24) {
					this.particles.remove(i);
				}
			}
		}
	}

}
