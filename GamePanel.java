import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	private MainMenuFrame mainMenuFrame;
	
	public GamePanel(MainMenuFrame mainMenuFrame) {
		this.mainMenuFrame = mainMenuFrame;
	}

	private BackgroundImage background = new BackgroundImage(0, 0);
	private BackgroundImage background2 = new BackgroundImage(0, -768);
	
	private Vector<Bullet> myBulletList = new Vector<>();
	private Vector<Bullet> enemyBulletList = new Vector<>();
	private Vector<EnemyJet> enemyList = new Vector<>();
	private Vector<DisplayHealth> healthBar = new Vector<>();
	private Vector<Explosion> explosions = new Vector<>();

	private PlayerJet playerJet = new PlayerJet( this );
	
	private boolean up, down, left, right, space;
	
	private boolean isWaveDone = true;
	private int enemyPerWave = 4;
	private int score = 0, health = 6;

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

	public Vector<Bullet> getMyBulletList() {
		return myBulletList;
	}
	public Vector<Bullet> getEnemyBulletList() {
		return enemyBulletList;
	}
	public Vector<DisplayHealth> getHealthBar() {
		return healthBar;
	}
	
	private void updateAll() {
		background.update();
		background2.update();
		
		for( int i = 0; i < enemyList.size(); i++) {
			
			for( int j = 0; j < myBulletList.size(); j++ ) {
				
				if ( collisionCheck(enemyList.get(i), myBulletList.get(j)) ) {
					
					explosions.add(	new Explosion( enemyList.get(i).x, enemyList.get(i).y ) );
					
					myBulletList.remove(j);
					j--;
					enemyList.remove(i);
					i--;
					score += 50;
				}
			}
		}
		
		for( int j = 0; j < enemyBulletList.size(); j++ ) {
			
			if (collisionCheck( playerJet, enemyBulletList.get(j)) ) {
				
				enemyBulletList.remove(j);
				j--;
				health--;
			}
		}
		
		for( Bullet b : enemyBulletList ) { b.update(); }
		for( Bullet m : myBulletList ) { m.update(); }
		for( EnemyJet e : enemyList ) { e.update(); }
		for(int i = 0; i < explosions.size(); i++) {
			if( explosions.get(i).getDidExplode() ) {
				explosions.remove(i);
				i--;
			}
		}
		
		
		playerJet.update();
	}
	
	@Override
	public void paintComponent( Graphics g ) {
		
		super.paintComponent(g);
		
		background.draw(g);
		background2.draw(g);
		
		for ( Bullet eb : enemyBulletList ) { eb.draw(g); }
		for ( Bullet b : myBulletList ) { b.draw(g); }
		for ( EnemyJet e : enemyList ) { e.draw(g); }
		for( DisplayHealth h : healthBar ) { h.draw(g); }
		
		playerJet.draw(g);
		
		for( Explosion e : explosions) { e.draw(g); }
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
		while ( true ) {
			if( enemyList.size() == 0 ) {
				isWaveDone = true;
				enemyPerWave++;
				health = ( (health<=3) ? health+2 : 5 );
			}
			
			if( isWaveDone ) {
				
				for ( int i = 0; i < enemyPerWave; i++ ) {
					enemyList.add( new EnemyJet( this ) );
				}
				isWaveDone = false;
			}
			
			Iterator<DisplayHealth> iter = healthBar.iterator();

			while ( iter.hasNext() ) {
			    DisplayHealth h = iter.next();
			    iter.remove();
			}
			
			for(int i = 0; i < getHealth(); i++) {
				getHealthBar().add( new DisplayHealth( 980-(30*i) , this ));
			}
			
			this.updateAll();
			this.repaint();
			
			try {
				Thread.sleep(16);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			if( healthBar.size() == 0 ) {
				break;
			}
		}
		
		JOptionPane.showMessageDialog( null, "Your score is: " + score, "GAME OVER", JOptionPane.PLAIN_MESSAGE );
		
		try {
			FileWriter scoreWriter = new FileWriter("scores.txt", true);
			
			if (scoreWriter != null) {
				scoreWriter.write( mainMenuFrame.userName );
				scoreWriter.write( System.getProperty( "line.separator" ) );
				
				scoreWriter.write( String.format("%d", score) );
				scoreWriter.write( System.getProperty( "line.separator" ) );
				
				scoreWriter.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean collisionCheck( Sprite jet, Sprite bullet ) {
		if(jet.getRect().intersects( bullet.getRect() )) {
			return true;
		}
		
		return false;
	}
	
	public int getHealth() {
		return this.health;
	}
	
}
