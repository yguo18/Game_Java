package DrawGame2;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import DrawGame2.GamePanel;;

public class GameFrame {
	public GameFrame(){
		Frame f = new Frame();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.setLocation(100, 100);
		GamePanel dB = new GamePanel();
		f.add(dB, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		dB.gameStart();
	}
	public static void main(String[] args){
		new GameFrame();
	}
}
