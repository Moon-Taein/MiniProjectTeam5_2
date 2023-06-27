package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import img.imageIcon;
import utilty.invisibility;

public class MainFrame extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private JLabel lbl;
	private invisibility util = new invisibility();
	private JButton orderBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {

		frameSetting();
		

		BtnSetting();
		
		orderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MenuFrame menu = new MenuFrame();
				setVisible(false);

			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 900);
		setLocationRelativeTo(null);

	}

	private void frameSetting() {

		lbl = new JLabel(icon.getMainFrame());

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(800, 900));

		lbl.setBounds(0, 0, 800, 900);

		jlp.add(lbl, new Integer(1));

		setContentPane(jlp);
		
		setUndecorated(true);

		
	}

	private void BtnSetting() {
		orderBtn = new JButton(icon.getOrderBtn());
		orderBtn.setBounds(250, 648, 300, 100);

		util.invisible(orderBtn);

		jlp.add(orderBtn, new Integer(2));

	}

}
