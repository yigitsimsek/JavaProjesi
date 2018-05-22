import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainMenuFrame extends JFrame{
	private JButton registerButton;
	private JButton loginButton;
	private JButton startButton;
	private JButton scoreButton; 
	
	public String userName;
	public String passWord;
	
	private boolean authentication = false;
	
	public MainMenuFrame() {
		super( "Desert Call" );
		setContentPane( new JLabel(new ImageIcon( getClass().getResource( "textures/BACKGROUND.jpg" ) )) );
		
		setLayout( new FlowLayout() );
		
		Icon startIcon = new ImageIcon( getClass().getResource( "textures/START_BUTTON.png" ) );
		Icon loginIcon = new ImageIcon( getClass().getResource( "textures/LOGIN_BUTTON.png" ) );
		Icon registerIcon = new ImageIcon( getClass().getResource( "textures/REGISTER_BUTTON.png" ) );
		Icon scoreIcon = new ImageIcon( getClass().getResource( "textures/HIGH_SCORES_BUTTON.png" ) );
		
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
		
		MainHandler handler = new MainHandler(this);
		
		startButton.addActionListener( handler );
		loginButton.addActionListener( handler );
		registerButton.addActionListener( handler );
		scoreButton.addActionListener( handler );
	}
	
	private class MainHandler implements ActionListener {
		MainMenuFrame mainMenuFrame;
		
		public MainHandler( MainMenuFrame mainMenuFrame ) {
			this.mainMenuFrame = mainMenuFrame;
		}
		
		public void actionPerformed( ActionEvent event ) {
			if( event.getSource() == startButton ) {
				
				if( mainMenuFrame.authentication ) {
					
					GameFrame gameFrame = new GameFrame( mainMenuFrame );
					gameFrame.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
					gameFrame.setVisible(true);
					
				}
				
				else {
					JOptionPane.showMessageDialog(null, "You Should Login First");
				}
			
			}
			else if( event.getSource() == loginButton ) {
				String line;
				
				mainMenuFrame.userName = JOptionPane.showInputDialog( "Enter username: " );
				mainMenuFrame.passWord = JOptionPane.showInputDialog( "Enter password: " );
				
				
				try {
					FileReader fileReader = new FileReader( "registers.txt" );
					
					BufferedReader bufferreader = new BufferedReader( fileReader );
					
					try {
						while ( (line = bufferreader.readLine()) != null ) {     
						      	if( line.equals( mainMenuFrame.userName ) ) {
						      		
						      		line = bufferreader.readLine();
						      		
						      		if( line.equals( mainMenuFrame.passWord ) ) {
						      			mainMenuFrame.authentication = true;
						      		}
						      	}
						      	
						      	if ( authentication ) {
						      		break;
						      	}
							
						        line = bufferreader.readLine();
						    }
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if( event.getSource() == registerButton ) {
				FileWriter file = null;
				
				try {
					file = new FileWriter( "registers.txt", true );
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
				if( file != null ) {
					
					String username = JOptionPane.showInputDialog( "Enter username: " );
					String password = JOptionPane.showInputDialog( "Enter password: " );
					
					try {
						file.write( System.getProperty( "line.separator" ) );
						file.write( username );
						file.write( System.getProperty( "line.separator" ) );
						file.write( password );
					}
					catch (IOException e1) {
						e1.printStackTrace();
					}
					
					try {
						file.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else if( event.getSource() == scoreButton ) {
				String line;
				String totalScore = "";
				
				try {
					FileReader scoreReader = new FileReader( "scores.txt" );
					
					BufferedReader bufferreader = new BufferedReader( scoreReader );
					
					try {
						while ( (line = bufferreader.readLine()) != null ) {     
								totalScore += line + " | " + bufferreader.readLine() + "\n";
						    }
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(mainMenuFrame, totalScore, "High Scores", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public static void main (String[] args) {
		MainMenuFrame mainMenuFrame = new MainMenuFrame();
		mainMenuFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainMenuFrame.setSize(1024, 768);
		mainMenuFrame.setVisible(true);
	}
}


