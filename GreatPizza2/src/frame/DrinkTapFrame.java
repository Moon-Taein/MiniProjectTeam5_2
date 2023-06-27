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

public class DrinkTapFrame extends JFrame {
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
					DrinkTapFrame frame = new DrinkTapFrame();
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
	public DrinkTapFrame() {
		JLabel lbl = new JLabel(icon.getMenuTapDrink());

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(800, 900));

		lbl.setBounds(0, 0, 800, 900);

		jlp.add(lbl, new Integer(1));

		setContentPane(jlp);
		
		JButton btn = new JButton();
		btn.setBounds(600, 150, 200, 50);
		jlp.add(btn, new Integer(2));
		util.invisible(btn);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MakePizzaTap makePizza = new MakePizzaTap();
				
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		setSize(800,900);
		setLocationRelativeTo(null);
	}

}
