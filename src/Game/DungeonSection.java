package Game;
import javax.imageio.ImageIO;


import javax.swing.*;

import DungeonFeatures.Chest;
import DungeonFeatures.Traps.Arrow;
import DungeonFeatures.Traps.ArrowTrap;
import DungeonFeatures.Traps.BladeTrap;
import DungeonFeatures.Traps.Flame;
import DungeonFeatures.Traps.FlameTrap;
import DungeonFeatures.Traps.Pit;
import DungeonFeatures.Traps.SpearTrap;
import DungeonFeatures.Traps.SpikeTrap;
import DungeonFeatures.Traps.Spinner;

import java.util.List;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DungeonSection {
	
	public boolean left, right, up, down;
	public int x, y, w, h;
	public int tileD;
	public boolean[] walls;
	boolean visited;
	

	
	DungeonFloor Floor;
	
	
	
	int roomW = 15, roomH = 15;
	
	public DungeonSection(int x, int y, int w, int h, int tileD) {
		this.Floor = new DungeonFloor();
		
		this.Floor.addPit(0,0);
		
		this.Floor.init();

		this.tileD = tileD;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.walls = new boolean[] {true,true,true,true}; //top right bottom left
		this.visited = false;
	}
	
	public DungeonSection checkNeigbors(ArrayList<DungeonSection> sections) {
		ArrayList<DungeonSection> neigbors = new ArrayList<DungeonSection>();
		
		DungeonSection top = null;
		DungeonSection left = null;
		DungeonSection right = null;
		DungeonSection bottom = null;
		
		if (index(x, y-1) != -1) top = sections.get(index(x, y-1));
		if (index(x-1, y) != -1) left = sections.get(index(x-1, y));
		if (index(x+1, y) != -1) right = sections.get(index(x+1, y));
		if (index(x, y+1) != -1) bottom = sections.get(index(x, y+1));
		
		if (top != null) if (!top.visited) neigbors.add(top);
		if (left != null) if (!left.visited) neigbors.add(left);
		if (right != null) if (!right.visited) neigbors.add(right);
		if (bottom != null) if (!bottom.visited) neigbors.add(bottom);
		
		if (neigbors.size() > 0) {
			int r = (int)(Math.random()*neigbors.size());
			return neigbors.get(r);
		} else {
			return null;
		}
		
	}
	
	public int index(int x, int y) {
		if (x < 0 || y < 0 || x > w-1 || y > h-1) {
			return -1;
		}
		return x + (y * w);
	}
}
