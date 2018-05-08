import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ImageIcon background = new ImageIcon( getClass().getResource("textures/DUNES.jpg") );

		g.drawImage(background.getImage(), 0, 0, 1024, 768, null);
	}
}
