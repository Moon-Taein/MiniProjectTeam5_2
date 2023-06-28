package frame;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import img.imageIcon;
import utilty.invisibility;

public class orderComplete extends JFrame {
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
					orderComplete frame = new orderComplete();
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
	public orderComplete() {
		frameSetting();
		buttonSetting();

	}
	private void frameSetting() {
		JLabel lbl = new JLabel(icon.orderComplete());
		lbl.setBounds(0, 0, 800, 900);

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(icon.getMainFrame().getIconWidth(), icon.getMainFrame().getIconHeight()));
		jlp.setLayout(null);

		jlp.add(lbl, new Integer(1));

		setContentPane(jlp);

		setUndecorated(true);
		setVisible(true);
		setSize(800, 900);
		setLocationRelativeTo(null);
	}
	private void buttonSetting() {
		JButton completeBtn = new JButton(icon.bigOrderBtn());
		completeBtn.setBounds(0, 800, 800, 100);
		jlp.add(completeBtn, new Integer(2));
		util.invisible(completeBtn);
	}

}
