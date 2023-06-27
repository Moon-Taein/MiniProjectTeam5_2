package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import img.imageIcon;
import utilty.invisibility;

public class MenuFrame extends JFrame{
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private JLabel lbl;
	private invisibility util = new invisibility();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
					frame.setVisible(true);
					frame.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		FrameSetting();
		
		pizzaTapBtn();
		
		

	}
	private void FrameSetting() {
		lbl = new JLabel(icon.getMenuTapPizza());
		lbl.setBounds(0, 0, 800,900);
		
		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(icon.getMainFrame().getIconWidth(), icon.getMainFrame().getIconHeight()));
		
		jlp.add(lbl, new Integer(1));		
		
		setContentPane(jlp);
		
		exiteKey(); //프로그램 종료ㅁ
		
		setUndecorated(true);
		setVisible(true);
		setSize(800,900);
		setLocationRelativeTo(null);
	}
	
	private void pizzaTapBtn() {
		JButton pizzaTap = new JButton();
		pizzaTap.setBounds(0, 150, 200, 50);
		
		util.invisible(pizzaTap);
		
		jlp.add(pizzaTap, new Integer(2));
		
		sideTapBtn();
		
		JButton btn1 = new JButton("1");
		btn1.setBounds(50, 300, 200, 50);
		
		jlp.add(btn1, new Integer(2));
		
		JButton btn2 = new JButton("2");
		btn2.setBounds(300, 300, 200, 50);
		
		jlp.add(btn2, new Integer(2));
		
		JButton btn3 = new JButton("3");
		btn3.setBounds(600, 300, 200, 50);
		
		jlp.add(btn3, new Integer(2));
		
		JButton btn4 = new JButton("4");
		btn4.setBounds(50, 400, 200, 50);
		
		jlp.add(btn4, new Integer(2));
		
		JButton btn5 = new JButton("5");
		btn5.setBounds(300, 400, 200, 50);
		
		jlp.add(btn5, new Integer(2));
		
		JButton btn6 = new JButton("6");
		btn6.setBounds(600, 400, 200, 50);
		
		jlp.add(btn6, new Integer(2));
		
	}
	
	private void sideTapBtn() {
		JButton sideTap = new JButton();
		sideTap.setBounds(200, 150, 200, 50);
		
		util.invisible(sideTap);
		
		jlp.add(sideTap, new Integer(2));
		
		
		
		sideTap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				SideTapFrame sideTap = new SideTapFrame();		
				
			}
		});
	}
	
	private void exiteKey() {
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				int exite = e.getKeyCode();
				System.out.println("종료");
				if(exite == KeyEvent.VK_ESCAPE) {
				System.exit(0);
				
				}
				
			}
		});
	}
		
	
	
}