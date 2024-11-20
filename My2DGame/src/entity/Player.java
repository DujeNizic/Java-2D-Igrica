package entity;

import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	
	public Player( GamePanel gp,KeyHandler keyH ) {
		
		this.gp=gp;
		this.keyH=keyH;
		setDefaultValues();
		getPlayerImage();
		
	}
	
	
	
	public void setDefaultValues() {
		
		x=100;
		y=100;
		speed=4;
		direction="down";
	}
	
	public void getPlayerImage() {
		try {
			up1=ImageIO.read(getClass().getResourceAsStream("/player/doli1-5.png.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/player/doli1-6.png.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/player/doli1-1.png.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/player/doli1-2.png.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/player/doli1-3.png.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/player/doli1-4.png.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/player/doli1-7.png.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/player/doli1-8.png.png"));
			
			
		}catch(IOException e){
			
			e.printStackTrace();
		}
	}
	
	public void update() {
		//ovo se izvršava 60 puta u jednoj sekundi
	//System.out.println("Updating player position...");
		
		//sve smo stavili u veliki if jer ako nepritisnemo nista
		//sprite nan se nece minjat i objekt ce stat na mistu
		if(keyH.upPressed == true || keyH.downPressed == true 
				|| keyH.rightPressed == true || keyH.leftPressed == true) {
			if(keyH.upPressed== true) {
				y-=speed;
				direction="up";
			}
			else if(keyH.downPressed== true) {
				y+=speed;
				direction="down";
			}
			else if(keyH.leftPressed== true) {
				x-=speed;
				direction="left";
			}
			else if(keyH.rightPressed== true) {
				x+=speed;
				direction="right";
			}
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		}
			
		
	}
	
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		
		//g2.fillRect(x,y,gp.tileSize,gp.tileSize);		// crtamo kocku i punimo je gore navedenon bojon
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image=up1;
			}
			if(spriteNum == 2) {
				image=up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image=down1;
			}
			if(spriteNum == 2) {
				image=down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image=left1;
			}
			if(spriteNum == 2) {
				image=left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image=right1;
			}
			if(spriteNum == 2) {
				image=right2;
			}
			break;
		}
		g2.drawImage(image, x, y,gp.tileSize,gp.tileSize,null);
		
	}
}
