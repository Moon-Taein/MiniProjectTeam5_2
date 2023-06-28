package frame;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import img.imageIcon;
import utilty.invisibility;

public class MakePizzaFrame extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private invisibility util = new invisibility();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakePizzaFrame frame = new MakePizzaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MakePizzaFrame() {
		frameSetting();
		sourceBtnSetting();
		
	}
	private void frameSetting() {
		JLabel lbl = new JLabel(icon.makePizzaFrame());
		lbl.setBounds(0, 0, 800, 900);

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(icon.getMainFrame().getIconWidth(), icon.getMainFrame().getIconHeight()));
		jlp.setLayout(null);

		jlp.add(lbl);

		setContentPane(jlp);

		setUndecorated(true);
		setVisible(true);
		setSize(800, 900);
		setLocationRelativeTo(null);
	}
	private void sourceBtnSetting() {
		JButton btn1 = new JButton();
		JButton btn2 = new JButton();
		JButton btn3 = new JButton();
		JButton btn4 = new JButton();
		JButton btn5 = new JButton();
		
		btn1.setBounds(345, 614, 70, 130);
		jlp.add(btn1, new Integer(1));
		btn2.setBounds(435, 614, 70, 130);
		jlp.add(btn2, new Integer(1));
		btn3.setBounds(525, 614, 70, 130);
		jlp.add(btn3, new Integer(1));
		btn4.setBounds(615, 614, 70, 130);
		jlp.add(btn4, new Integer(1));
		btn5.setBounds(705, 614, 70, 130);
		jlp.add(btn5, new Integer(1));
	}

}
