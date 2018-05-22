import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {
	
	protected int x, y;
	protected int width, height;

	private	Image image;
	
	public Sprite(String file, int x, int y, int width, int height) {
		ImageIcon imageIcon = new ImageIcon( getClass().getResource(file) );
		image = imageIcon.getImage();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {}
	
	public void draw( Graphics g ) {
		g.drawImage( image, x, y, width, height, null );
	}
	
	public Rectangle getRect() {
		return new Rectangle( x, y, width, height );
	}
}
