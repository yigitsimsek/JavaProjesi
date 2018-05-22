import javax.swing.JFrame;

public class GameFrame extends JFrame {
	MainMenuFrame mainMenuFrame;
	
	Thread gameThread;
	GamePanel gamePanel;
	
	public GameFrame( MainMenuFrame mainMenuFrame ) {
		super( "Desert Call" );
		
		this.mainMenuFrame = mainMenuFrame;
		gamePanel = new GamePanel( mainMenuFrame );
		setBounds( 200, 200, 1024, 768 );
		setResizable( false );

		add( gamePanel );
		gamePanel.setVisible( true );
		addKeyListener( gamePanel );
		
		gameThread = new Thread( gamePanel );
		gameThread.start();
	}
	
}
