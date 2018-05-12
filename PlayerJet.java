public class PlayerJet extends Sprite {
	public int xBorder = 880, yUpBorder = 450, yDownBorder = 600;
	private final int SPEED = 8;
	private GamePanel gamePanel;
	
	private long lastBullet = 0;
	
	public PlayerJet( GamePanel gamePanel ) {
		super("textures/MY_PLANE.png", 448, 608, 128, 128);
		
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void update() {
		if ( gamePanel.isRight() ) {
			
			if( x+SPEED < xBorder ) {
				x = x+SPEED;
			}
		}
		else if ( gamePanel.isLeft() ) {
			
			if( x-SPEED > 0 ) {
				x = x-SPEED;
			}
		}
		
		if ( gamePanel.isUp() ) {
			
			if( y-SPEED > yUpBorder ) {
				y = y-SPEED;
			}
		}
		else if ( gamePanel.isDown() ) {
			
			if( y+SPEED < yDownBorder ) {
				y = y+SPEED;
			}
		}
		
		if( gamePanel.isSpace()) {
			
			if( lastBullet + 150 < System.currentTimeMillis() ) {
				gamePanel.getBulletList().add( new Bullet(x+width/2, y-height/4, true) );
				lastBullet = System.currentTimeMillis();
			}
		}
	}

}
