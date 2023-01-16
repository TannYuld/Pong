package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GameScreen{
	
	protected final JPanel panel;
	protected final JFrame frame;
	
	protected final JLabel startText = new JLabel("Press SPACE to Play");
	protected final JLabel p1ScoreText = new JLabel("0");
	protected final JLabel p2ScoreText = new JLabel("0");
	
	protected static final int WIN_HEIGHT = 500;
	protected static int WIN_WIDHT = 700;
	
	protected final int PLAYER_BLOCK_HEIGHT = 90;
	protected final int PLAYER_BLOCK_WIDHT = 5;
	
	protected final Block Ball = new Block();
	protected final Block PlayerOneBlock = new Block();
	protected final Block PlayerTwoBlock = new Block();
	
	private Font PixelFont;
	private Font BigPixelFont;
	
	public GameScreen(Game game) {
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				rePositionGameObjects(g);
			}
		};
		frame = new JFrame();
		panel.setLayout(null);
		panel.setBackground(Color.black);
		
		try {
			PixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PixelFont.ttf")).deriveFont(30f);
			BigPixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PixelFont.ttf")).deriveFont(70f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(PixelFont);
			ge.registerFont(BigPixelFont);
		}catch(FontFormatException | IOException e) {
			System.out.println("There are a problem occurred about Font \n"+e);
		}
		
		initializeGameObjects();
		initializeComponents();
		buildGameWindow();

		panel.requestFocusInWindow();
		panel.addKeyListener(game.input);
	}
	
	private void buildGameWindow() {
		frame.setSize(WIN_WIDHT, WIN_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
	
	private void initializeComponents() {
		startText.setBounds(0,WIN_HEIGHT-100, WIN_WIDHT-25,60);
		startText.setFont(PixelFont);
		startText.setForeground(Color.white);
		//startText.setBorder(new LineBorder(Color.BLACK));
		startText.setHorizontalAlignment(SwingConstants.CENTER);
		
		p1ScoreText.setBounds(150,-70,250,250);
		p1ScoreText.setFont(BigPixelFont);
		p1ScoreText.setForeground(Color.white);
		
		p2ScoreText.setBounds(500,-70,250,250);
		p2ScoreText.setFont(BigPixelFont);
		p2ScoreText.setForeground(Color.white);
		
		panel.add(p1ScoreText);
		panel.add(p2ScoreText);
		panel.add(startText);
	}
	
	private void initializeGameObjects() {
		PlayerOneBlock.initializeProp(50, 20, PLAYER_BLOCK_WIDHT, PLAYER_BLOCK_HEIGHT);
		PlayerTwoBlock.initializeProp(630, 20, PLAYER_BLOCK_WIDHT, PLAYER_BLOCK_HEIGHT);
		Ball.initializeProp(50+(PLAYER_BLOCK_WIDHT), 20+(PLAYER_BLOCK_HEIGHT/2-5), 10, 10);
	}
	
	private void rePositionGameObjects(Graphics g) {
		g.setColor(Color.white);
		
		g.fillRect(PlayerOneBlock.getProp(0), PlayerOneBlock.getProp(1), PlayerOneBlock.getProp(2), PlayerOneBlock.getProp(3));
		g.fillRect(PlayerTwoBlock.getProp(0), PlayerTwoBlock.getProp(1), PlayerTwoBlock.getProp(2), PlayerTwoBlock.getProp(3));
		g.fillRect(Ball.getProp(0),Ball.getProp(1),Ball.getProp(2),Ball.getProp(3));
	}
	
}
