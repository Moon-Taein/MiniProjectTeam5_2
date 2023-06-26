package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import Util.invisibility;
import img.imageIcon;

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

	}

	private void frameSetting() {

		lbl = new JLabel(icon.getMenuTapPizza());

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(800, 900));

		lbl.setBounds(0, 0, 800, 900);

		jlp.add(lbl, new Integer(1));

		setContentPane(jlp);
		

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 900);
		setLocationRelativeTo(null);
	}

	private void BtnSetting() {
		orderBtn = new JButton(icon.getOrderBtn());
		orderBtn.setBounds(250, 648, 300, 100);

		util.invisible(orderBtn);

		jlp.add(orderBtn, new Integer(2));

	}

	
	public void name() {
		Image origin = icon.getMenuTapPizza().getImage();
		Image resize = origin.getScaledInstance(800, 900, Image.SCALE_SMOOTH);
		ImageIcon change = new ImageIcon(resize);
		JLabel lbl = new JLabel(change);

		add(lbl);
		setSize(800, 900);
		setVisible(true);

	}
}
