import javax.swing.JFrame;

public class GameFrame extends JFrame {
	Thread gameThread;
	GamePanel gamePanel = new GamePanel();
	
	public GameFrame() {
		super("Desert Contact");
		
		setBounds(200, 200, 1024, 768);
		setResizable( false );

		add( gamePanel );
		gamePanel.setVisible(true);
		addKeyListener( gamePanel );
		
		gameThread = new Thread(gamePanel);
		gameThread.start();
	}
}
