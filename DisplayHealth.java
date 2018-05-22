
public class DisplayHealth extends Sprite {
	
	GamePanel gamePanel;
	
	public DisplayHealth(int pos, GamePanel gamePanel) {
		super("textures/HEALTH_BAR.png", pos, 700, 30, 30);
		this.gamePanel = gamePanel;
	}


}
