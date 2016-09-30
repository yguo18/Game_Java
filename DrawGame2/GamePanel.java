package DrawGame2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.util.Random;

public class GamePanel extends Panel implements Runnable{
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int diameter;
	private int width;
	private int height;
	private Image im;
	private Graphics gp;
	private static final int FPS = 50;
	
	public GamePanel(){
		x = 50;
		y = 50;
		dx = 2;
		dy = 2;
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
		long t1, t2, dt, sleepTime;
		long period = 1000 / FPS;
		t1 = System.nanoTime();
		System.out.println(t1);
		
		while(true){
			gameUpdate();
			gameRender();
			gamePaint();
			
			t2 = System.nanoTime();
			dt = (t2 - t1) /1000000L;
			sleepTime = period - dt;
			
			if(sleepTime < 0)
				sleepTime = 2;
			try{
				Thread.sleep(sleepTime);
			}catch(InterruptedException ex){}
			
			t1 = System.nanoTime();
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
		switch (rand.nextInt(2)) {
			case 0 :
				x += dx;
				break;
			
			case 1:
				y += dy;
				break;
			
		}
		if (x > 200)
			dx = -2;
		if (y > 200)
			dy = -2;
		if (x < 0)
			dx = 2;
		if (y < 0)
			dy = 2;
	}
}
