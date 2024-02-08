package com.witcher.gaming.sprites;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class KenPlayer extends CommonPlayer{
	private BufferedImage damageEffectImages[]=new BufferedImage[5];
	private BufferedImage walkImages[]=new BufferedImage[6];
	private BufferedImage kickImages[]=new BufferedImage[6];
	private BufferedImage punchImages[]=new BufferedImage[6];
	//
	public KenPlayer() throws Exception {
		x=GWIDTH-200;
		y=FLOOR-h;
		img=ImageIO.read(KenPlayer.class.getResource(OPP_PLAYER_IMG));
		speed=0;
		currentMove2=WALK;
		loadWalkImages();
		loadKickImages();
		loadPunchImages();
		loadDamageEffect();
	}
	
	private void loadDamageEffect() {
		damageEffectImages[0]=img.getSubimage(27, 521, 85, 113);
		damageEffectImages[1]=img.getSubimage(120, 519, 89, 113);
		damageEffectImages[2]=img.getSubimage(231, 521, 89, 113);
		damageEffectImages[3]=img.getSubimage(328, 540, 91, 96);
		damageEffectImages[4]=img.getSubimage(441, 545, 91, 96);
		
		
	}
	

	private void loadWalkImages() {
		walkImages[0]=img.getSubimage(0, 129, 99, 115);
		walkImages[1]=img.getSubimage(104, 129, 99,115 );
		walkImages[2]=img.getSubimage(206,128, 84, 115);
		walkImages[3]=img.getSubimage(290, 128, 64, 115);
		walkImages[4]=img.getSubimage(377, 129, 84, 115);
		walkImages[5]=img.getSubimage(464, 129, 84, 120);
		
	}

	private void loadKickImages() {
		// TODO Auto-generated method stub
		kickImages[0]=img.getSubimage(15, 646, 85, 138);
		kickImages[1]=img.getSubimage(113, 641, 85, 138);
		kickImages[2]=img.getSubimage(206, 646, 85, 138);
		kickImages[3]=img.getSubimage(291, 642, 85, 138);
		kickImages[4]=img.getSubimage(381, 645, 85, 138);
		kickImages[5]=img.getSubimage(484, 649, 85, 138);								
	}
	
	private void loadPunchImages() {
		// TODO Auto-generated method stub
		punchImages[0]=img.getSubimage(9, 277, 106, 112);
		punchImages[1]=img.getSubimage(126, 275, 113, 112);
		punchImages[2]=img.getSubimage(250, 275, 113, 112);
		punchImages[3]=img.getSubimage(375, 277, 145, 112);
		punchImages[4]=img.getSubimage(666, 275, 145, 112);
		punchImages[5]=img.getSubimage(819, 277, 134, 109);
		
	}
	
	public BufferedImage printDamageImage() {
		if(imageIndex2>damageEffectImages.length-1) {
			imageIndex2=0;
			currentMove2=WALK;
		}
		BufferedImage img=damageEffectImages[imageIndex2];
		imageIndex2++;
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
		if(imageIndex2>5) {
			imageIndex2=0;
		}
		BufferedImage img=walkImages[imageIndex2];
		imageIndex2++;
		return img;
	}
	public BufferedImage printKick() {
		if(imageIndex2>5) {
			imageIndex2=0;
			//if kick ended , make it walk again	
			currentMove2=WALK;
			isAttacking=false;
		}
		isAttacking=true;
		BufferedImage img=kickImages[imageIndex2];
		imageIndex2++;
		return img;
	}
	
	public BufferedImage printPunch() {
		if(imageIndex2>5) {
			imageIndex2=0;
			//if punch ended , make it walk again	
			currentMove2=WALK;
			isAttacking=false;
		}
		isAttacking=true;
		BufferedImage img=punchImages[imageIndex2];
		imageIndex2++;
		return img;
	}


	@Override
	public BufferedImage defaultImage() {
       if(currentMove2==KICK) {
        	return printKick();
        }
       else if(currentMove2==PUNCH) {
         	return printPunch();   
       }	
       else if(currentMove2==DAMAGE) {
    	   return printDamageImage();
       }
       else
    	   return printWalk();
	}
}