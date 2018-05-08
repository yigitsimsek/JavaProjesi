import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PlayerPlane extends JPanel implements Runnable, KeyListener {
	private static int x = 448, y = 608;
	private static int xBorder = 880, yUpBorder = 300, yDownBorder = 600;
	private	ImageIcon myPlane = new ImageIcon( getClass().getResource("textures/MY_PLANE.png") );
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(myPlane.getImage(), x, y, 128, 128, null);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if( event.getKeyCode() == KeyEvent.VK_RIGHT ) {
			if( x+8 < xBorder ) {
				x = x+8;
				repaint();
			}
		}
		else if( event.getKeyCode() == KeyEvent.VK_LEFT ) {
			if( x-8 > 0 ) {
				x = x-8;
				repaint();
			}
		}
		else if( event.getKeyCode() == KeyEvent.VK_UP ) {
			if( y-8 > yUpBorder ) {
				y = y-8;
				repaint();
			}
		}
		else if( event.getKeyCode() == KeyEvent.VK_DOWN ) {
			if( y+8 < yDownBorder ) {
				y = y+8;
				repaint();
			}
		}
		else if( event.getKeyCode() == KeyEvent.VK_SPACE ) {
			Bullet bullet= new Bullet(x, y, true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
