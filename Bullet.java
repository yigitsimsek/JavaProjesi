import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bullet extends JPanel implements Runnable {
	private int xCoordinate, yCoordinate;
	private boolean isPlayers;
	private Thread thread;
	private ImageIcon bullet;
	
	public Bullet(int x, int y, boolean isPlyrs) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.isPlayers = isPlyrs;
		
		if(isPlayers) {
			bullet = new ImageIcon( getClass().getResource("textures/PLAYER_BULLET.png") );
		}
		else if(isPlayers) {
			bullet = new ImageIcon( getClass().getResource("textures/ENEMY_BULLET.png") );
		}
		
		thread=new Thread(this);
		thread.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(isPlayers) {
			g.drawImage( bullet.getImage(), xCoordinate+52, yCoordinate-30, 25, 89, null );
		}
	}
	
	public int getxCoordinate() {
		return xCoordinate;	
	}
	
	public int getyCoordinate() {
		return yCoordinate;	
	}

	@Override
	public void run() {
		if(isPlayers) {
			repaint();
			this.yCoordinate -= 10;
		}
	}
}
