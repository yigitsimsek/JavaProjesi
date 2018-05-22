public class Bullet extends Sprite {
	public boolean isPlayers;
	private final int MY_SPEED = 25;
	private final int ENEMY_SPEED = 10;
	
	public Bullet(int x, int y, boolean isPlyrs) {
		super((isPlyrs ? "textures/PLAYER_BULLET.png" : "textures/ENEMY_BULLET.png")
				, (isPlyrs ? x-13 : x-13)
				, (isPlyrs ? y-25 : y-25)
				, (isPlyrs ? 26 : 15)
				, (isPlyrs ? 90 : 45));
		
		this.isPlayers = isPlyrs;
	}
	
	@Override
	public void update() {
		if (isPlayers) {
			y -= MY_SPEED;
		}
		else {
			y += ENEMY_SPEED;
		}
	}
}
