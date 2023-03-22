package Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener{
	
	private boolean[] Keys = new boolean[256];
	
	public static boolean up, down, left, right;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Keys[e.getKeyCode()] = false;
	}
	
	public void UpdateKeys() {
		this.up = Keys[KeyEvent.VK_W];
		this.down = Keys[KeyEvent.VK_S];
		this.left = Keys[KeyEvent.VK_A];
		this.right = Keys[KeyEvent.VK_D];
	}


}
