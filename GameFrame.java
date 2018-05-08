import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private boolean running;
	private PlayerPlane playerPlane;
	static BackgroundPanel backgroundPanel = new BackgroundPanel();
	
	public GameFrame() {
		super("Arabian Nights");
		
		setBounds(200, 200, 1024, 768);
		setResizable( false );
		
		playerPlane= new PlayerPlane();
		add(playerPlane);
		playerPlane.setVisible(true);
		addKeyListener( playerPlane );
		playerPlane.repaint();	 
	}

}
