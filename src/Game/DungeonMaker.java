package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DungeonFeatures.Chest;
import DungeonFeatures.Traps.ArrowTrap;
import DungeonFeatures.Traps.BladeTrap;
import DungeonFeatures.Traps.FlameTrap;
import DungeonFeatures.Traps.Pit;
import DungeonFeatures.Traps.SpearTrap;
import DungeonFeatures.Traps.SpikeTrap;
import DungeonFeatures.Traps.Spinner;

public class DungeonMaker {
	
	//arrow 0
	//blade 1
	//chest 2
	//flame 3
	//spear 4
	//spike 5
	//spinn 6
	
	public static int currentRoomNumber, w, h;
	
	public static List<ArrowTrap> ArrowTraps = new ArrayList<ArrowTrap>();
	public static List<BladeTrap> BladeTraps = new ArrayList<BladeTrap>();
	public static List<Chest> Chests = new ArrayList<Chest>();
	public static List<FlameTrap> FlameTraps = new ArrayList<FlameTrap>();
	public static List<SpearTrap> SpearTraps = new ArrayList<SpearTrap>();
	public static List<SpikeTrap> SpikeTraps = new ArrayList<SpikeTrap>();
	public static List<Spinner> Spinners = new ArrayList<Spinner>();
	
	public static DungeonFloor floor = new DungeonFloor();
	
	public static String currentDungeon;
	
	public static boolean[] walls = new boolean[] {false, false, false, false};
	
	public static void CreateNewDungeon(int w, int h, String dungeonName) throws IOException {
		Dungeon dungeon = new Dungeon(w,h);
		Path folder = Paths.get("res/data/" + dungeonName);
		if (!Files.exists(folder)) {
			Files.createDirectories(folder);
			File mazeData = new File("res/data/"+dungeonName+"/mazeData.txt");
			mazeData.createNewFile();
			FileWriter writer = new FileWriter("res/data/maze1/mazeData.txt", true);
			writer.write(w + " " + h + "\n");
			for (int i = 0; i < dungeon.Sections.size(); i++) {
				writer.write(String.valueOf(dungeon.Sections.get(i).walls[0] ? 0 : 1)+String.valueOf(dungeon.Sections.get(i).walls[1] ? 0 : 1)+String.valueOf(dungeon.Sections.get(i).walls[2] ? 0 : 1)+String.valueOf(dungeon.Sections.get(i).walls[3] ? 0 : 1) + "\n");
			}
			writer.close();
			Files.createDirectories(Paths.get("res/data/" + dungeonName + "/SectionData"));
			for (int i = 0; i < (w*h); i++) {
				File SectionData = new File("res/data/"+dungeonName+"/SectionData/Section" + i + ".txt");
				SectionData.createNewFile();
			}
			dungeon = null;
		}
		
		
	}
	
	public static void CreateRoom(String dungeon, int room, ArrowTrap[] arrowTraps, BladeTrap[] bladeTraps, Chest[] chests, FlameTrap[] flameTraps, SpearTrap[] spearTraps, SpikeTrap[] spikeTraps, Spinner[] spinners) throws IOException {
		if(!Files.exists(Paths.get("res/data/" + dungeon + "/SectionData"))) {
			Files.createDirectories(Paths.get("res/data/" + dungeon + "/SectionData"));
		}
		if(!Files.exists(Paths.get("res/data/" + dungeon + "/SectionData/Section" + room + ".txt"))) {
			File SectionsData = new File("res/data/"+dungeon+"/SectionsData/Section" + room + ".txt");
			SectionsData.createNewFile();		
		}
		FileWriter writer = new FileWriter("res/data/"+dungeon+"/SectionData/Section" + room + ".txt", true);
		for (int i = 0; i < arrowTraps.length; i++) {
			writer.write("0 " + arrowTraps[i].x + " " + arrowTraps[i].y + " " + arrowTraps[i].facing + " " + arrowTraps[i].w + "\n");
		}
		for (int i = 0; i < bladeTraps.length; i++) {
			writer.write("1 " + bladeTraps[i].x + " " + bladeTraps[i].y + " " + bladeTraps[i].facing + "\n");
		}
		for (int i = 0; i < chests.length; i++) {
			writer.write("2 " + chests[i].x + " " + chests[i].y + " " + chests[i].facing + "\n");
		}
		for (int i = 0; i < flameTraps.length; i++) {
			writer.write("3 " + flameTraps[i].x + " " + flameTraps[i].y + " " + flameTraps[i].facing + " " + flameTraps[i].w + "\n");
		}
		for (int i = 0; i < spearTraps.length; i++) {
			writer.write("4 " + spearTraps[i].x + " " + spearTraps[i].y + " " + spearTraps[i].facing + " " + spearTraps[i].length + "\n");
		}
		for (int i = 0; i < spikeTraps.length; i++) {
			writer.write("5 " + spikeTraps[i].x + " " + spikeTraps[i].y + " " + spikeTraps[i].w + " " + spikeTraps[i].h + "\n");
		}
		for (int i = 0; i < spinners.length; i++) {
			writer.write("6 " + spinners[i].x + " " + spinners[i].y + " " + spinners[i].r + "\n");
		}
		writer.close();
	}
	
	public static void LoadRoom(String dungeon, int room) throws IOException {
		currentDungeon = dungeon;
		ArrowTraps.clear();
		BladeTraps.clear();
		Chests.clear();
		FlameTraps.clear();
		SpearTraps.clear();
		SpikeTraps.clear();
		Spinners.clear();
		Scanner reader = new Scanner(new File("res/data/"+dungeon+"/SectionData/Section" + room + ".txt"));
		while (reader.hasNextLine()) {
			String[] featureData = reader.nextLine().split(" ", 5);
			if (Integer.parseInt(featureData[0]) == 0) {
				ArrowTraps.add(new ArrowTrap(Integer.parseInt(featureData[1]), Integer.parseInt(featureData[2]), Integer.parseInt(featureData[3]), Integer.parseInt(featureData[4])));
			} else if (Integer.parseInt(featureData[0]) == 1) {
				BladeTraps.add(new BladeTrap(Integer.parseInt(featureData[1]), Integer.parseInt(featureData[2]), Integer.parseInt(featureData[3])));
			} else if (Integer.parseInt(featureData[0]) == 2) {
				Chests.add(new Chest(Integer.parseInt(featureData[1]), Integer.parseInt(featureData[2]), Integer.parseInt(featureData[3])));
			} else if (Integer.parseInt(featureData[0]) == 3) {
				FlameTraps.add(new FlameTrap(Integer.parseInt(featureData[1]), Integer.parseInt(featureData[2]), Integer.parseInt(featureData[3]), Integer.parseInt(featureData[4])));
			} else if (Integer.parseInt(featureData[0]) == 4) {
				SpearTraps.add(new SpearTrap(Integer.parseInt(featureData[1]), Integer.parseInt(featureData[2]), Integer.parseInt(featureData[3]), Integer.parseInt(featureData[4])));
			} else if (Integer.parseInt(featureData[0]) == 5) {
				SpikeTraps.add(new SpikeTrap(Integer.parseInt(featureData[1]), Integer.parseInt(featureData[2]), Integer.parseInt(featureData[3]), Integer.parseInt(featureData[4])));
			} else if (Integer.parseInt(featureData[0]) == 6) {
				Spinners.add(new Spinner(Integer.parseInt(featureData[1]), Integer.parseInt(featureData[2]), Integer.parseInt(featureData[3])));
			}
		}
		currentRoomNumber = room;
		String SectionData = "";
		reader = new Scanner(new File("res/data/"+dungeon+"/mazeData.txt"));
		String[] DungeonData = reader.nextLine().split(" ", 2);
		System.out.println(DungeonData[0]);
		System.out.println(DungeonData[1]);
		w = Integer.parseInt(DungeonData[0]);
		h = Integer.parseInt(DungeonData[1]);
		for (int posY = 0; posY < w; posY++) {
			for (int posX = 0; posX < h; posX++) {
				if (((posY*w)+posX) == room) {
					SectionData = reader.nextLine();
				} else {
					reader.nextLine();
				}
			}
		}
		walls[0] = (0 == Integer.parseInt(String.valueOf(SectionData.charAt(0))));
		walls[1] = (0 == Integer.parseInt(String.valueOf(SectionData.charAt(1))));
		walls[2] = (0 == Integer.parseInt(String.valueOf(SectionData.charAt(2))));
		walls[3] = (0 == Integer.parseInt(String.valueOf(SectionData.charAt(3))));
		floor.init();
	}
	
	public static void Update() throws IOException {
		UpdateRoomNavigation();
		
		floor.update();
		updateCollisions();
		
		UpdateFeatures();
	}
	
	public static void Draw(Graphics g) throws IOException {
		floor.Draw(g);
		DrawWalls(g);
		
		DrawFeatures(g);
	}
	
	public static void UpdateFeatures() {
		for (int i = 0; i < ArrowTraps.size(); i++) {
			ArrowTraps.get(i).Update();
		}
		for (int i = 0; i < BladeTraps.size(); i++) {
			BladeTraps.get(i).Update();
		}
		for (int i = 0; i < Chests.size(); i++) {
			Chests.get(i).Update();
		}
		for (int i = 0; i < FlameTraps.size(); i++) {
			FlameTraps.get(i).Update();
		}
		for (int i = 0; i < SpearTraps.size(); i++) {
			SpearTraps.get(i).Update();
		}
		for (int i = 0; i < SpikeTraps.size(); i++) {
			SpikeTraps.get(i).Update();
		}
		for (int i = 0; i < Spinners.size(); i++) {
			Spinners.get(i).Update();
		}
 	}
	
	public static void DrawFeatures(Graphics g) {
		for (int i = 0; i < BladeTraps.size(); i++) {
			BladeTraps.get(i).Draw(g);
		}
		for (int i = 0; i < SpikeTraps.size(); i++) {
			SpikeTraps.get(i).Draw(g);
		}
		for (int i = 0; i < Chests.size(); i++) {
			Chests.get(i).Draw(g);
		}
		for (int i = 0; i < Spinners.size(); i++) {
			Spinners.get(i).Draw(g);
		}
		Game.player.Draw(g);
		for (int i = 0; i < SpearTraps.size(); i++) {
			SpearTraps.get(i).Draw(g);
		}
		for (int i = 0; i < FlameTraps.size(); i++) {
			FlameTraps.get(i).Draw(g);
		}
		for (int i = 0; i < ArrowTraps.size(); i++) {
			ArrowTraps.get(i).Draw(g);
		}
	}
	
	public static void DrawWalls(Graphics g) {
		if (!walls[0]) {
			DrawWall(g,-60,-60,50,0);
			DrawWall(g,20,-60,50,0);
			if (Game.player.x > -10) {
				DrawWall(g,-10,-70,10,1);
			}
			if (Game.player.x < 20) {
				DrawWall(g,20,-70,10,1);
			}
		} else {
			DrawWall(g,-60,-60,130,0);
		}
		if (!walls[1]) {
			DrawWall(g,70,-60,50,1);
			DrawWall(g,70,20,50,1);
			if (Game.player.y > -11) {
				DrawWall(g,70,-10,10,0);
			}
			if (Game.player.y < 21) {
				DrawWall(g,70,20,10,0);
			}
		} else {
			DrawWall(g,70,-60,130,1);
		}
		if (!walls[2]) {
			DrawWall(g,-60,70,50,0);
			DrawWall(g,20,70,50,0);
			if (Game.player.x > -11) {
				DrawWall(g,-10,70,10,1);
			}
			if (Game.player.x < 21) {
				DrawWall(g,20,70,10,1);
			}
		} else {
			DrawWall(g,-60,70,130,0);
		}
		if (!walls[3]) {
			DrawWall(g,-60,-60,50,1);
			DrawWall(g,-60,20,50,1);
			if (Game.player.y > -11) {
				DrawWall(g,-70,-10,10,0);
			}
			if (Game.player.y < 21) {
				DrawWall(g,-70,20,10,0);
			}
		} else {
			DrawWall(g,-60,-60,130,1);
		}
	}
	
	public static void UpdateRoomNavigation() throws IOException {
		System.out.println(currentRoomNumber);
		if (Game.player.x > 70) {
			ChangeRoom(currentRoomNumber+1);
			Game.player.x = -70;
		} else if (Game.player.y > 70) {
			ChangeRoom(currentRoomNumber+w);
			Game.player.y = -70;
		} else if (Game.player.x < -70) {
			ChangeRoom(currentRoomNumber-1);
			Game.player.x = 70;
		} else if (Game.player.y < -70) {
			ChangeRoom(currentRoomNumber-w);
			Game.player.y = 70;
		}
	}
	
	public static void ChangeRoom(int room) throws IOException {
		LoadRoom(currentDungeon, room);
	}
	
	
	
	private static void DrawWall(Graphics g, int posX, int posY, int w, int side) {
		int[] wallLengths = new int[16];
		for (int i = 0; i < 8; i++) {
			wallLengths[2*i] = (MathFunctions.Projection(70,70,100.25+(2.5*i))[0]-MathFunctions.Projection(-60,-60,100.25+(2.5*i))[0]);
			wallLengths[(2*i)+1] = (MathFunctions.Projection(70,70,102.25+(2.5*i))[0]-MathFunctions.Projection(-60,-60,102.25+(2.5*i))[0]);

		}
		int brickTop1;
		int brickBottom1;
		int brickTop2;
		int brickBottom2;
		if (side == 0) {
			
			g.setColor(new Color(70,70,70));
			g.fillPolygon(new int[] {MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100)[0], MathFunctions.Projection(posX+w-Game.player.x-5,posY-Game.player.y-5, 100)[0], MathFunctions.Projection(posX+w-Game.player.x-5,posY-Game.player.y-5, 120)[0], MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 120)[0]},
					  new int[] {MathFunctions.Projection(posX-Game.player.x-5, posY-Game.player.y-5, 100)[1], MathFunctions.Projection(posX+w-Game.player.x-5,posY-Game.player.y-5, 100)[1], MathFunctions.Projection(posX+w-Game.player.x-5,posY-Game.player.y-5, 120)[1], MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 120)[1]}, 4);
			for (double y = 0; y < 20; y+=5) {
				g.setColor(new Color(50,50,50));
				brickTop1 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[1];
				brickBottom1 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y)[1];
				brickTop2 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1];
				brickBottom2 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[1];
				
				for (double x = 0; x < w; x+=10) {
					g.fillPolygon(new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[0]+MathFunctions.ScaleNumberLine(0.25+x, 130, wallLengths[(int) ((y/5)*4)])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[0]+MathFunctions.ScaleNumberLine(9.75+x, 130, wallLengths[(int) ((y/5)*4)])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y)[0]+MathFunctions.ScaleNumberLine(9.75+x, 130, wallLengths[(int) ((y/5)*4)+1])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y)[0]+MathFunctions.ScaleNumberLine(0.25+x, 130, wallLengths[(int) ((y/5)*4)+1]))},
							 new int[] {brickTop1,brickTop1,brickBottom1,brickBottom1},4);
				}
				for (double x = 5; x < w-10; x+=10) {
					g.fillPolygon(new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(0.25+x, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(9.75+x, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(9.75+x, 130, wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(0.25+x, 130, wallLengths[(int) ((y/5)*4)+3]))},
							      new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2}, 4);
				}
				g.fillPolygon(new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(0.25, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(4.75, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(4.75, 130, wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(0.25, 130, wallLengths[(int) ((y/5)*4)+3]))},
					      new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2}, 4);
				g.fillPolygon(new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(w-4.75, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(w-0.25, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(w-0.25, 130, wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(w-4.75, 130, wallLengths[(int) ((y/5)*4)+3]))},
					      new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2}, 4);
				
			}
		}
		if (side == 1) {
			g.setColor(new Color(70,70,70));
			g.fillPolygon(new int[] {MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100)[0], MathFunctions.Projection(posX-Game.player.x-5,posY+w-Game.player.y-5, 100)[0], MathFunctions.Projection(posX-Game.player.x-5,posY+w-Game.player.y-5, 120)[0], MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 120)[0]},
					  new int[] {MathFunctions.Projection(posX-Game.player.x-5, posY-Game.player.y-5, 100)[1], MathFunctions.Projection(posX-Game.player.x-5,posY+w-Game.player.y-5, 100)[1], MathFunctions.Projection(posX-Game.player.x-5,posY+w-Game.player.y-5, 120)[1], MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 120)[1]}, 4);
			for (double y = 0; y < 20; y+=5) {
				brickTop1 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[0];
				brickBottom1 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y)[0];
				brickTop2 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0];
				brickBottom2 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[0];
				for (double x = 0; x < w; x+=10) {
					g.setColor(new Color(50,50,50));
					g.fillPolygon(new int[] {brickTop1,brickTop1,brickBottom1,brickBottom1},
							      new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[1]+MathFunctions.ScaleNumberLine(0.25+x, 130, wallLengths[(int) ((y/5)*4)])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[1]+MathFunctions.ScaleNumberLine(9.75+x, 130, wallLengths[(int) ((y/5)*4)])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y)[1]+MathFunctions.ScaleNumberLine(9.75+x, 130, wallLengths[(int) ((y/5)*4)+1])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y)[1]+MathFunctions.ScaleNumberLine(0.25+x, 130, wallLengths[(int) ((y/5)*4)+1]))},
							      4);
				}
				for (double x = 5; x < w-10; x+=10) {
					g.fillPolygon(new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2},
							      new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(0.25+x, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(9.75+x, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(9.75+x, 130, wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(0.25+x, 130, wallLengths[(int) ((y/5)*4)+3]))},
							      4);
				}
				g.fillPolygon(new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2},
							  new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(0.25, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(4.75, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(4.75, 130, wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(0.25, 130, wallLengths[(int) ((y/5)*4)+3]))},
					          4);
				g.fillPolygon(new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2},
							  new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(w-4.75, 130,wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(w-0.25, 130, wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(w-0.25, 130, wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(w-4.75, 130, wallLengths[(int) ((y/5)*4)+3]))},
					          4);
				
			}
		}
		
		
	}
	
	public static void updateCollisions() {
		if (!walls[0]) {
			if (Game.player.y < -60) {
				if (Game.player.x == -11) {
					Game.player.x = -10;
				} else if (Game.player.x == 11) {
					Game.player.x = 10;
				} else if (Game.player.y == -61 && (Game.player.x < -10 || Game.player.x > 10)) {
					Game.player.y = -60;
				}
			}
		} else if (Game.player.y == -61) {
			Game.player.y = -60;
		}
		
		if (!walls[1]) {
			if (Game.player.x > 60) {
				if (Game.player.y == -11) {
					Game.player.y = -10;
				} else if (Game.player.y == 11) {
					Game.player.y = 10;
				} else if (Game.player.x == 61 && (Game.player.y < -10 || Game.player.y > 10)) {
					Game.player.x = 60;
				}
			}
		} else if (Game.player.x == 61) {
			Game.player.x = 60;
		}
		
		if (!walls[2]) {
			if (Game.player.y > 60) {
				if (Game.player.x == -11) {
					Game.player.x = -10;
				} else if (Game.player.x == 11) {
					Game.player.x = 10;
				} else if (Game.player.y == 61 && (Game.player.x < -10 || Game.player.x > 10)) {
					Game.player.y = 60;
				}
			}
		} else if (Game.player.y == 61) {
			Game.player.y = 60;
		}
		
		if (!walls[3]) {
			if (Game.player.x < -60) {
				if (Game.player.y == -11) {
					Game.player.y = -10;
				} else if (Game.player.y == 11) {
					Game.player.y = 10;
				} else if (Game.player.x == -61 && (Game.player.y < -10 || Game.player.y > 10)) {
					Game.player.x = -60;
				}
			}
		} else if (Game.player.x == -61) {
			Game.player.x = -60;
		}
		

	}
	
	

}
