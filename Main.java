import javax.swing.JFrame;

public class Main {
	public static void main (String[] args) {
		MainMenuFrame mainMenuFrame = new MainMenuFrame();
		mainMenuFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainMenuFrame.setSize(1024, 768);
		mainMenuFrame.setVisible(true);
	}
}
