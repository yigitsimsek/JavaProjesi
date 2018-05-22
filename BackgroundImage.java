public class BackgroundImage extends Sprite {
	
	public BackgroundImage(int x, int y) {
		super("textures/DUNES.jpg", x, y, 1024, 768);
	}
	
	@Override
	public void update() {
		this.y += 1;
		
		if (this.y > 768)
			this.y -= (768 * 2);
	}
}
