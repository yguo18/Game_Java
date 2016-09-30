package DrawGame4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

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
	private static long  period = 1000/FPS;
	
	public GamePanel(){
		x = 0;
		y = 0;
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
		long t;
		//t = currentTime();
		while(true){
			t = currentTime();
			while(x == 0 && y != 200){
				//gameUpdate();
				y ++;
				gameRender();
				gamePaint();
				t = computeTime(t);
			}
			while(x != 200 && y == 200){
				//gameUpdate();
				x ++;
				gameRender();
				gamePaint();
				t = computeTime(t);
			}
			while(x == 200 && y != 0){
				//gameUpdate();
				y --;
				gameRender();
				gamePaint();
				t = computeTime(t);
			}
			while(x != 0 && y == 0){
				//gameUpdate();
				x --;
				gameRender();
				gamePaint();
				t = computeTime(t);
			}
		}
	
	}
	
	public long currentTime(){
		long t = System.nanoTime();  // ������Ϸѭ��ִ��ǰ��ϵͳʱ�䣬��λ������
		return t;
	}
	
	public long computeTime(long t1){   // t1 ��Ϸ��ʼʱ��ʱ��
		long t2, dt, sleepTime;
		t2 = System.nanoTime();  // ��Ϸѭ��ִ�к��ϵͳʱ�䣬��λ������
		dt = (t2-t1)/1000000L;   // ����ѭ��ʵ�ʻ��ѵ�ʱ�䣬��ת��Ϊ����
		sleepTime = period - dt;  // ���㱾��ѭ��ʣ��ʱ�䣬��λ������
		if(sleepTime<=0)          // ��ֹsleepTimeΪ��ֵ
			sleepTime = 2;
		try{
			Thread.sleep(sleepTime);
		}catch(InterruptedException ex){}
		t1 = currentTime();
		return t1;
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
	
	public void gameUpdateX(){
		x += dx;
	}
	
	public void gameUpdateY(){
		y += dy;
	}
}
