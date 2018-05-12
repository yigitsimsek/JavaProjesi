import java.util.Random;

public class EnemyJet extends Sprite {
	private GamePanel gamePanel;
	private final int XBORDER = 880, YBORDER = 100, speed = 3;
	private int direction = 1;
	
	private long lastBullet = 0;
	
	public EnemyJet( GamePanel gamePanel ) {
		super("textures/ENEMY_PLANE.png", (new Random()).nextInt(1024), -100, 100, 100);
		
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void update() {
		Random rand = new Random();
		int a = rand.nextInt(90);
		
		if( a == 0 ) {
			shoot();
		}
		
		y += speed;
		if (y > YBORDER) {
			y = YBORDER;
		}
		
		x += direction * speed;
		if (x > XBORDER) {
			x = XBORDER;
			direction = -1;
		}
		else if (x < 0) {
			x = 0;
			direction = 1;
		}
	}
	
	public void shoot() {
		if( lastBullet + 150 < System.currentTimeMillis() ) {
			gamePanel.getBulletList().add( new Bullet(x+width/2, y+height/4, false) );
			lastBullet = System.currentTimeMillis();
		}
	}
}
