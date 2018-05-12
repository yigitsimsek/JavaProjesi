import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private BackgroundImage background = new BackgroundImage(0, 0);
	private BackgroundImage background2 = new BackgroundImage(0, -768);
	
	private ArrayList<Bullet> bulletList = new ArrayList<>();
	private ArrayList<EnemyJet> enemyList = new ArrayList<>();
	
	private PlayerJet playerJet = new PlayerJet( this );
	
	private boolean up, down, left, right, space;
	private boolean isWaveDone = true;
	private int enemyPerWave = 5;

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isSpace() {
		return space;
	}

	public ArrayList<Bullet> getBulletList() {
		return bulletList;
	}
	
	private void updateAll() {
		background.update();
		background2.update();
		
		for ( Bullet b : bulletList ) {
			b.update();
		}
		
		for ( EnemyJet e : enemyList ) {
			e.update();
		}
		
		playerJet.update();
	}
	
	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		
		background.draw(g);
		background2.draw(g);
		
		for ( Bullet b : bulletList ) {
			b.draw(g);
		}
		
		for ( EnemyJet e : enemyList ) {
			e.draw(g);
		}
		
		playerJet.draw(g);

	}

	@Override
	public void keyPressed(KeyEvent event) {		
		if( event.getKeyCode() == KeyEvent.VK_RIGHT ) {
			right = true;
		}
		else if( event.getKeyCode() == KeyEvent.VK_LEFT ) {
			left = true;
		}
		else if( event.getKeyCode() == KeyEvent.VK_UP ) {
			up = true;
		}
		else if( event.getKeyCode() == KeyEvent.VK_DOWN ) {
			down = true;
		}
		else if( event.getKeyCode() == KeyEvent.VK_SPACE ) {
			space = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if( event.getKeyCode() == KeyEvent.VK_RIGHT ) {
			right = false;
		}
		else if( event.getKeyCode() == KeyEvent.VK_LEFT ) {
			left = false;
		}
		else if( event.getKeyCode() == KeyEvent.VK_UP ) {
			up = false;
		}
		else if( event.getKeyCode() == KeyEvent.VK_DOWN ) {
			down = false;
		}
		else if( event.getKeyCode() == KeyEvent.VK_SPACE ) {
			space = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void run() {
		while (true) {
			if( enemyList.size() == 0) {
				isWaveDone = true;
				enemyPerWave++;
			}
			
			if( isWaveDone ) {
				for (int i = 0; i < enemyPerWave; i++) {
					enemyList.add( new EnemyJet(this) );
				}
				isWaveDone = false;
			}
			
			for(int i = enemyList.size()-1; i >= 0; i--) {
				for(int j = bulletList.size()-1; j >= 0; j--) {
					if( bulletList.get(j).isPlayers ) {
						if( collisionCheck( enemyList.get(i), bulletList.get(j) ) ) {
							bulletList.remove(j);
							enemyList.remove(i);
						}
					}
				}
			}
			
			this.updateAll();
			this.repaint();
			
			try {
				Thread.sleep(16);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean collisionCheck( Sprite jet, Sprite bullet ) {
		if( jet.getRect().intersects( bullet.getRect() ) ) {
			return true;
		}
		
		return false;
	}
	
	
}
