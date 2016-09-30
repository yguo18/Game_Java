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
	
	public drawBall(){    // ��ʼ����������
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
		long period = 1000/FPS;  // ����ÿһ��ѭ����Ҫִ�е�ʱ�䣬��λ������
		t1 = System.nanoTime();  // ������Ϸѭ��ִ��ǰ��ϵͳʱ�䣬��λ������
		
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
			
			t2 = System.nanoTime();  // ��Ϸѭ��ִ�к��ϵͳʱ�䣬��λ������
			dt = (t2-t1)/1000000L;   // ����ѭ��ʵ�ʻ��ѵ�ʱ�䣬��ת��Ϊ����
			sleepTime = period - dt;  // ���㱾��ѭ��ʣ��ʱ�䣬��λ������
			if(sleepTime<=0)          // ��ֹsleepTimeΪ��ֵ
				sleepTime = 2;
			try{
				Thread.sleep(sleepTime);
			}catch(InterruptedException ex){}
			t1 = System.nanoTime();
		}
	}
}
