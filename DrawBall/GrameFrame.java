package DrawBall;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import DrawBall.drawBall;

public class GrameFrame {
	public GrameFrame(){
		Frame f = new Frame("my app");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.setLocation(100, 100);
		drawBall dB = new drawBall();
		f.add(dB, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		dB.gameStart();
	}
	public static void main(String[] args){
		new GrameFrame();
	}
}

