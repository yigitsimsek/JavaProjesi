import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class MainMenuFrame extends JFrame{
	private JButton registerButton;
	private JButton loginButton;
	private JButton startButton;
	private JButton scoreButton; 
	
	public MainMenuFrame() {
		super( "The Sleeper Must Awaken" );
		setContentPane(new JLabel(new ImageIcon( getClass().getResource("textures/BACKGROUND.jpg") )));
		
		setLayout( new FlowLayout() );
		
		Icon startIcon = new ImageIcon( getClass().getResource("textures/START_BUTTON.png") );
		Icon loginIcon = new ImageIcon( getClass().getResource("textures/LOGIN_BUTTON.png") );
		Icon registerIcon = new ImageIcon( getClass().getResource("textures/REGISTER_BUTTON.png") );
		Icon scoreIcon = new ImageIcon( getClass().getResource("textures/HIGH_SCORES_BUTTON.png") );
		
		startButton = new JButton( startIcon );
		loginButton = new JButton( loginIcon );
		registerButton = new JButton( registerIcon );
		scoreButton = new JButton( scoreIcon );
		
		startButton.setBorder( null );
		loginButton.setBorder( null );
		registerButton.setBorder( null );
		scoreButton.setBorder( null );
		
		setResizable( false );

		add( startButton );
		add( loginButton );
		add( registerButton );
		add( scoreButton );
		
		MainHandler handler = new MainHandler();
		
		startButton.addActionListener( handler );
		loginButton.addActionListener( handler );
		registerButton.addActionListener( handler );
		scoreButton.addActionListener( handler );
	}
	
	private class MainHandler implements ActionListener{
		public void actionPerformed( ActionEvent event ) {
			if( event.getSource() == startButton ) {
				GameFrame gameFrame = new GameFrame();
				gameFrame.setDefaultCloseOperation( EXIT_ON_CLOSE );
				gameFrame.setVisible(true);
			}
			else if( event.getSource() == loginButton ) {
							
			}
			else if( event.getSource() == registerButton ) {
				
			}
			else if( event.getSource() == scoreButton ) {
				
			}
		}
	}
}
