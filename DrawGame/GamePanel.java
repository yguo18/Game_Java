package DrawGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.util.Random;

public class GamePanel extends Panel implements Runnable{
	private int x;
	private int y;
	private int diameter;
	private int width;
	private int height;
	private Image im;
	private Graphics gp;
	
	public GamePanel(){
		x = 50;
		y = 50;
		diameter = 100;
		width = 500;
		height = 500;
		setBackground(Color.pink);
		setPreferredSize(new Dimension(width, height));
	}

	public void gameStart(){
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run(){
		while(true){
			gameUpdate();
			gameRender();
			gamePaint();
		}
	}
	
	public void gamePaint(){
		Graphics g;
		try{
			g = this.getGraphics();
			if(g!=null && im!=null){
				g.drawImage(im, x, y, null);
			}
			g.dispose();
		}catch(Exception e){}
	}
	
	public void gameRender(){
		if(im ==null){
			im = createImage(width, height);
			if(im == null){
				System.out.println("im is null");
			}else{
				gp = im.getGraphics();
			}
		}
		gp.setColor(Color.pink);
		gp.fillRect(0, 0, width, height);
		gp.setColor(Color.blue);
		gp.fillOval(x, y, diameter, diameter);
	}
	
	public void gameUpdate(){
		Random rand = new Random();
		switch (rand.nextInt(4)) {
			case 0 :
				x += 10;
				break;
			case 1:
				x -= 10;
				break;
			case 2:
				y += 10;
				break;
			case 3:
				y -= 10;
				break;
		}
		if (x > width)
			x = -diameter;
		if (y > height)
			y = -diameter;
		if (x < -diameter)
			x = width;
		if (y < -diameter)
			y = height;
	}
}
