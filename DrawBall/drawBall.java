package DrawBall;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;


public class drawBall extends Panel implements Runnable{
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int diameter;
	private int width;
	private int height;
	private Image im;
	private Graphics dbg;
	private static final int FPS = 50;
	
	public drawBall(){    // 初始化各个变量
		x = 0;
		y = 0;
	    dx = 10;
	    dy = 10;
		diameter = 100;
		width = 500;
		height = 500;
		setBackground(Color.pink);
		setPreferredSize(new Dimension(width, height));
	}
	
	/*public void gameLoop(){
		while(true){
			x ++;
			for(int i=1; i<10000000; i++){}
			repaint();
			if(x == 400)
				x = 0;
		}
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval(x, y, diameter, diameter);
	}*/
	public void gameStart(){
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	public void run(){
		long t1, t2, dt, sleepTime;
		long period = 1000/FPS;  // 计算每一次循环需要执行的时间，单位：毫秒
		t1 = System.nanoTime();  // 保存游戏循环执行前的系统时间，单位：纳秒
		
		while(true){
			x ++;
			y ++;
			if (x >= 400)
				x = 0;
			if (y >= 400)
				y = 0;
			if (im == null){
				im = createImage(width, height);
				if (im == null){
					System.out.println("im is null");
				}else{
					dbg = im.getGraphics();
				}
			}
			dbg.setColor(Color.pink);
			dbg.fillRect(0, 0, width, height);
			dbg.setColor(Color.blue);
			dbg.fillOval(x, y, diameter, diameter);
			
			Graphics g;
			try{
				g = this.getGraphics();
				if(g!=null&&im!=null){
					g.drawImage(im, 0, 0, null);
				}
				g.dispose();
			}
			catch(Exception e){}
			
			t2 = System.nanoTime();  // 游戏循环执行后的系统时间，单位：纳秒
			dt = (t2-t1)/1000000L;   // 本次循环实际花费的时间，并转换为毫秒
			sleepTime = period - dt;  // 计算本次循环剩余时间，单位：毫秒
			if(sleepTime<=0)          // 防止sleepTime为负值
				sleepTime = 2;
			try{
				Thread.sleep(sleepTime);
			}catch(InterruptedException ex){}
			t1 = System.nanoTime();
		}
	}
}
