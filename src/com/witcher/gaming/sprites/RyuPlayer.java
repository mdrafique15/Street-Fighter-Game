package com.witcher.gaming.sprites;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class RyuPlayer extends CommonPlayer{
	private BufferedImage damageEffectImages[]=new BufferedImage[5];
	private BufferedImage walkImages[]=new BufferedImage[6];
	private BufferedImage kickImages[]=new BufferedImage[6];
	private BufferedImage punchImages[]=new BufferedImage[6];
	

	public RyuPlayer() throws Exception {
		x=100;
		y=FLOOR-h;
		speed=0;
		img=ImageIO.read(RyuPlayer.class.getResource(PLAYER_IMG));
		loadWalkImages();
		loadKickImages();
		loadPunchImages();
		loadDamageEffect();
	}
	
	private void loadDamageEffect() {
		damageEffectImages[0]=img.getSubimage(28, 2312, 83 , 96);
		damageEffectImages[1]=img.getSubimage(114, 2320, 94, 89);
		damageEffectImages[2]=img.getSubimage(216, 2337, 82, 70);
		damageEffectImages[3]=img.getSubimage(306, 2317, 84, 91);
		damageEffectImages[4]=img.getSubimage(399, 2332, 87, 81);		
	}
	


	private void loadWalkImages() {
		walkImages[0]=img.getSubimage(63, 343, 67, 98);
		walkImages[1]=img.getSubimage(140, 342, 61, 99);
		walkImages[2]=img.getSubimage(217, 343, 60, 98);
		walkImages[3]=img.getSubimage(296, 344, 59, 98);
		walkImages[4]=img.getSubimage(368, 343, 66, 102);
		walkImages[5]=img.getSubimage(450, 348, 72, 95);
		
		
	}


	private void loadKickImages() {
		// TODO Auto-generated method stub
		kickImages[0]=img.getSubimage(43, 1042, 68, 100);
		kickImages[1]=img.getSubimage(118, 1042, 73, 100);
		kickImages[2]=img.getSubimage(201, 1044, 122, 101);
		kickImages[3]=img.getSubimage(331, 1043, 64, 102);
		kickImages[4]=img.getSubimage(404, 1046, 71, 96);
		kickImages[5]=img.getSubimage(482, 1044, 95, 101);
		
	}
	
	private void loadPunchImages() {
		// TODO Auto-generated method stub
		punchImages[0]=img.getSubimage(30, 816, 65, 103);
		punchImages[1]=img.getSubimage(107, 817, 76, 102);
		punchImages[2]=img.getSubimage(189, 817, 113, 102);
		punchImages[3]=img.getSubimage(307, 818, 86, 100);
		punchImages[4]=img.getSubimage(400, 820, 110, 99);
		punchImages[5]=img.getSubimage(518, 819, 82, 98);
		
	}
	public BufferedImage printDamageImage() {
		if(imageIndex>damageEffectImages.length-1) {
			imageIndex=0;
			currentMove=WALK;
		}
		BufferedImage img=damageEffectImages[imageIndex];
		imageIndex++;
		return img;
	
	}
	
	public void jump() {
		if(!isJump) {
		isJump=true;
		force=-20;
		y=y+force;
		}
	}
	public void fall() {
		if(y>=(FLOOR-h)) {
			isJump=false;
			return;
		}
		y=y+force;
		force=force+GRAVITY;	
	}
	public BufferedImage printWalk() {
		isAttacking=false;
		if(imageIndex>5) {
			imageIndex=0;
		}
		BufferedImage img=walkImages[imageIndex];
		imageIndex++;
		return img;
	}
	public BufferedImage printKick() {
		if(imageIndex>5) {
			imageIndex=0;
			//if kick ended , make it walk again	
			currentMove=WALK;
			isAttacking=false;
		}
		isAttacking=true;
		
		BufferedImage img=kickImages[imageIndex];
		imageIndex++;
		return img;
	}
	
	public BufferedImage printPunch() {
		if(imageIndex>5) {
			imageIndex=0;
			//if punch ended , make it walk again	
			currentMove=WALK;
			isAttacking=false;
		}
		isAttacking=true;
		
		BufferedImage img=punchImages[imageIndex];
		imageIndex++;
		return img;
	}


	@Override
	public BufferedImage defaultImage() {
	       if(currentMove==KICK) {
	        	return printKick();
	        }
	       else if(currentMove==PUNCH) {
	         	return printPunch();   
	       }	
	       else if(currentMove==DAMAGE) {
	    	   return printDamageImage();
	       }
	       else
	    	   return printWalk();
		}
	
}
