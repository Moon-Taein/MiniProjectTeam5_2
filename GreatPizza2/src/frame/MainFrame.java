package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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

	public MainFrame() {

		frameSetting();

		BtnSetting();

		orderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				MenuFrame menu = new MenuFrame(MainFrame.this);

			}
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 900);
		setLocationRelativeTo(null);

	}

	private void frameSetting() {

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(800, 900));
		setContentPane(jlp);

		lbl = new JLabel(icon.getMainFrame());
		lbl.setBounds(0, 0, 800, 900);
		jlp.add(lbl, new Integer(1));

		setUndecorated(true);

	}

	private void BtnSetting() {
		orderBtn = new JButton(icon.getOrderBtn());
		orderBtn.setBounds(250, 760, 300, 120);
		orderBtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("OrderBtnRoll.png")));
		util.invisible(orderBtn);
		jlp.add(orderBtn, new Integer(2));

	}
}