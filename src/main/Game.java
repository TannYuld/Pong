package main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Game extends GameLoop {
	
	protected GameScreen gameScreen;
	protected Input input;
	
	private boolean gameStarted = false;
	private boolean ballStop = true;
	private boolean gameOver = false;
	private boolean startTextLoop = true;
	
	private int playerOneHeight = 20;
	private int playerTwoHeight = 20;
	
	private int ballX = 1;
	private int ballY = 2;
	private int p1Score = 0;
	private int p2Score = 0;
	
	private Rectangle ballRect;
	private Rectangle p1Rect;
	private Rectangle p2Rect;
	
	
	private enum WinnerSides{
		right,
		left
	}
	
	private WinnerSides winner = null;
	
	@Override
	public void Start() {
		input = new Input();
		gameScreen = new GameScreen(this);
		
		startTextLoop();
	}
	
	@Override
	public void Update() {
		if(!gameStarted && input.isKeyPressed(KeyEvent.VK_SPACE)) {
			gameScreen.startText.setVisible(false);
			startTextLoop = false;
			gameStarted = true;
		}else if(input.isKeyPressed(KeyEvent.VK_SPACE) && ballStop) {
			ballStop = false;
			ballX = 2;
			ballY = -1;
		}
		
		if(ballStop) {
			gameScreen.Ball.setPosY( (winner == WinnerSides.left || winner == null) ? gameScreen.PlayerOneBlock.getProp(1)+(gameScreen.PLAYER_BLOCK_HEIGHT/2-5) : gameScreen.PlayerTwoBlock.getProp(1)+(gameScreen.PLAYER_BLOCK_HEIGHT/2-5));
		}else if(!gameOver) {
			gameScreen.Ball.setPosX(gameScreen.Ball.getProp(0)+ballX);
			gameScreen.Ball.setPosY(gameScreen.Ball.getProp(1)+ballY);
			
			ballRect = gameScreen.Ball.bounds(); 
			p1Rect = gameScreen.PlayerOneBlock.bounds();
			p2Rect = gameScreen.PlayerTwoBlock.bounds();
			
			if(ballRect.intersects(Collisions.BOTTOM.getRect()) || ballRect.intersects(Collisions.TOP.getRect())) {
				ballY *= -1;
				AudioPlayer.PlayAudio(SoundEffects.WALL_BOUNCE);
			}
			
			if(ballRect.intersects(p2Rect) || ballRect.intersects(p1Rect)) {
				ballX *= -1;
				AudioPlayer.PlayAudio(SoundEffects.PLAYER_BOUNCE);
			}
			
			if(ballRect.intersects(Collisions.LEFT.getRect())) {
				p2Score ++;
				gameScreen.p2ScoreText.setText(Integer.toString(p2Score));
				gameOver();
				winner = WinnerSides.left;
			}
			
			if(ballRect.intersects(Collisions.RIGHT.getRect())) {
				p1Score ++;
				gameScreen.p1ScoreText.setText(Integer.toString(p1Score));
				gameOver();
				winner = WinnerSides.right;
			}
		}
		
		if(gameOver) {
			if(input.isKeyBeingPressed(KeyEvent.VK_SPACE)) {
				gameOver = false;
				ballStop = true;
				
				startTextLoop = false;
				gameScreen.startText.setVisible(false);
				
				gameScreen.Ball.setPosX( (winner == WinnerSides.left || winner == null) ? 
						50+gameScreen.PLAYER_BLOCK_WIDHT : GameScreen.WIN_WIDHT - 80);
			}
		}
		
		if(gameStarted && !gameOver)
		{
			if(input.isKeyBeingPressed(KeyEvent.VK_S)) {
				playerOneHeight = Math.max(0, Math.min(GameScreen.WIN_HEIGHT - 130,playerOneHeight+2 ));
			}
			
			if(input.isKeyBeingPressed(KeyEvent.VK_W)) {
				playerOneHeight = Math.max(0, Math.min(GameScreen.WIN_HEIGHT - 130,playerOneHeight-2 ));
			}
			
			if(input.isKeyBeingPressed(KeyEvent.VK_DOWN)) {
				playerTwoHeight = Math.max(0, Math.min(GameScreen.WIN_HEIGHT - 130,playerTwoHeight+2 ));
			}
			
			if(input.isKeyBeingPressed(KeyEvent.VK_UP)) {
				playerTwoHeight = Math.max(0, Math.min(GameScreen.WIN_HEIGHT - 130,playerTwoHeight-2 ));
			}
		}
		
		gameScreen.PlayerOneBlock.setPosY(playerOneHeight);
		gameScreen.PlayerTwoBlock.setPosY(playerTwoHeight);
	}

	@Override
	public void RenderUpdate() {
		gameScreen.panel.repaint();
		gameScreen.frame.repaint();
	}
	
	private void gameOver() {
		AudioPlayer.PlayAudio(SoundEffects.LOSE);
		ballX = 0;
		ballY = 0;
		gameOver = true;
		gameScreen.startText.setText("Press SPACE To Restart");
		startTextLoop = true;
		startTextLoop();
	}
	
	private IDelay startTextLoop() {
		return new WaitForSecond(.3f, new Thread() {
			@Override
			public void run() {
				try {
					gameScreen.startText.setVisible(!gameScreen.startText.isVisible());
					return;
				}
				finally {
					if(startTextLoop) {
						startTextLoop();
					}else {
						gameScreen.startText.setVisible(false);
					}
				}
			}
		});
	}

}