package frame;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import img.imageIcon;
import utilty.invisibility;

public class MakePizzaTap extends JFrame {
	
	private invisibility util = new invisibility();
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakePizzaTap frame = new MakePizzaTap();
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
	public MakePizzaTap() {
		JLabel lbl = new JLabel(icon.getMenuTapMakePizza());

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(800, 900));

		lbl.setBounds(0, 0, 800, 900);

		jlp.add(lbl, new Integer(1));

		setContentPane(jlp);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		setSize(800,900);
		setLocationRelativeTo(null);
	}

}
