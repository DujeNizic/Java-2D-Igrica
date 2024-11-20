package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;



public class GamePanel extends JPanel implements Runnable {

	// postavke za zaslon
	final int originalTileSize = 16;// 16*16 tile

	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48 tile prikazivat se ovako na ekranu
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; // ratio je 4 x3
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixela
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixela
	
	
	
	
	int FPS=60;
	
	
	TileManager tileM = new TileManager(this);
	
	
	KeyHandler keyH=new KeyHandler(); //bavi nan 
	// citanjen tipkovnice odnosno pustanjen i diranjen nasih tipki za micanje
	
	Thread gameThread; // sat
	
	Player player = new Player(this,keyH);
	
	
	//postavljanje pozicije plejera
	
	//int playerX=100;
	//int playerY=100;
	//int playerSpeed=4;
	
	
	
	

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black); // minjamo boju pozadine nase konzole
		this.setDoubleBuffered(true); // bolji game rendering
		this.addKeyListener(keyH);
		this.setFocusable(true); //s ovin GamePanel more bit fokusiran da prima inpute
		
		
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
	    double drawInterval = 1000000000 / FPS; //kolikocemo cest crtat na ekranu u ovon slucaju svako 0.016 crtamo;
	    double delta=0;
	    long lastTime=System.nanoTime();
	    long currentTime;
	    long timer=0;
	    int drawCount=0;
	    

	    while (gameThread != null) {
	    	
	    	currentTime=System.nanoTime();
	    	
	    	delta+=(currentTime - lastTime)/ drawInterval;
	    	timer+= (currentTime - lastTime);
	    	lastTime=currentTime;
	    	
	    	
	    	if(delta>=1) {   //delta > 1: This means that at least one full frame interval has passed (16.67 ms for 60 FPS).
	    		update();  // Update player position
	  	        repaint();  // Repaint the screen
	  	        delta--;
	  	        drawCount++;
	    	}
	    	
	    	
	    	if(timer >= 1000000000) {
	    		System.out.println("FPS:"+ drawCount);
	    		drawCount=0;
	    		timer=0;
	    	}
	    	
	    }
	}
	
	
	public void update() {
		
		player.update();
		
		
	}
	
	public void paintComponent(Graphics g) {
		//pomocu ove metode crtamo nas objekt na consoli
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;  // pretvaramo u graphics 2d jer nan daj vise mogucnosti i kontrole nad crtanjen
		tileM.draw(g2); // mora bi isprid igraca jer je kao lejer mora bit isprid
		
		player.draw(g2);
		g2.dispose();
		
	}
}
