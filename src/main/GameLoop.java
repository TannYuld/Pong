package main;

public abstract class GameLoop implements Runnable {
	
	protected Thread loopThread;
	
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	protected int FPS;
	protected int UPS;
	
	abstract public void Start();
	abstract public void Update();
	abstract public void RenderUpdate();
	
	public GameLoop() {
		Start();
		loopThread = new Thread(this);
		loopThread.start();
	}
	
	@Override
	public void run() {
		double frameTime = 1000000000 / FPS_SET;
		double tickTime = 1000000000 / UPS_SET;
		
		double tickDelta = 0;
		double frameDelta = 0;
		
		long lastCheck = System.currentTimeMillis();
		long prevTime = System.nanoTime();
		
		int ticks = 0;
		int frames = 0;
		
		while(true) {
			long currentTime = System.nanoTime();
			
			tickDelta += (currentTime - prevTime) / tickTime;
			frameDelta += (currentTime - prevTime) / frameTime;
			prevTime = currentTime;
			
			if(tickDelta >= 1) {
				Update();
				tickDelta--;
				ticks++;
			}
			
			if(frameDelta >= 1) {
				RenderUpdate();
				frameDelta--;
				frames++;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				
				FPS = frames;
				UPS = ticks;
				
				ticks = 0;
				frames = 0;
			}
		}
	}
}
