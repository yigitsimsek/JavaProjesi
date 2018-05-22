import java.awt.Graphics;
import java.util.Vector;
import javax.swing.ImageIcon;

public class Explosion extends Sprite {
	
	int x, y;
	
	private Vector<ImageIcon> explosionSet = new Vector<>();
	
	protected ImageIcon exp_1 = new ImageIcon( getClass().getResource("textures/exp_1.png") );
	protected ImageIcon exp_2 = new ImageIcon( getClass().getResource("textures/exp_2.png") );
	protected ImageIcon exp_3 = new ImageIcon( getClass().getResource("textures/exp_3.png") );
	protected ImageIcon exp_4 = new ImageIcon( getClass().getResource("textures/exp_4.png") );
	protected ImageIcon exp_5 = new ImageIcon( getClass().getResource("textures/exp_5.png") );
	protected ImageIcon exp_6 = new ImageIcon( getClass().getResource("textures/exp_6.png") );
	protected ImageIcon exp_7 = new ImageIcon( getClass().getResource("textures/exp_7.png") );
	protected ImageIcon exp_8 = new ImageIcon( getClass().getResource("textures/exp_8.png") );
	protected ImageIcon exp_9 = new ImageIcon( getClass().getResource("textures/exp_9.png") );
	protected ImageIcon exp_10 = new ImageIcon( getClass().getResource("textures/exp_10.png") );
	
	private boolean didExplode = false;
	int expIndex = 0;
	
	public Explosion( int x, int y ) {
		super( "textures/exp_1.png", x, y, 30, 30 );
		
		this.x = x;
		this.y = y;
		
		explosionSet.add( exp_1 );
		explosionSet.add( exp_2 );
		explosionSet.add( exp_3 );
		explosionSet.add( exp_4 );
		explosionSet.add( exp_5 );
		explosionSet.add( exp_6 );
		explosionSet.add( exp_7 );
		explosionSet.add( exp_8 );
		explosionSet.add( exp_9 );
		explosionSet.add( exp_10 );
	}
	
	@Override
	public void draw(Graphics g) {
		if( expIndex < explosionSet.size() ) {
			g.drawImage( explosionSet.get(expIndex).getImage(), x, y, 120, 120, null );
			expIndex++;
		}
		else {
			didExplode = true;
		}
	}
	
	public boolean getDidExplode() {
		return didExplode;
	}
	
	
}
