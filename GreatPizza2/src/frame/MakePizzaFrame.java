package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Function.Sql_Methods;
import img.imageIcon;
import utilty.invisibility;

public class MakePizzaFrame extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private invisibility util = new invisibility();
	private Sql_Methods edgeSql = new Sql_Methods();

	/**
	 * Launch the application.ㅁ
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(229,206,190));
		panel.setBounds(88, 685, 170, 60);
		jlp.add(panel, new Integer(2));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("도우_M");
		lblNewLabel.setBounds(50, 10, 80, 40);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("이전");
		btnNewButton_2.setBounds(0, 15, 38, 31);
		panel.add(btnNewButton_2, new Integer(2));
		
		JButton btnNewButton_2_1 = new JButton("다음");
		btnNewButton_2_1.setBounds(120, 15, 38, 31);
		panel.add(btnNewButton_2_1);
		
		HashMap<String, byte[]> edge = edgeSql.pizzamakeSetEdgeimg(lblNewLabel.getText());
		System.out.println(edge.keySet());
		
		
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
		JButton btn1 = new JButton(icon.getBulldak());
		JButton btn2 = new JButton(icon.getHotSoy());
		JButton btn3 = new JButton(icon.getSoy());
		JButton btn4 = new JButton(icon.getCream());
		JButton btn5 = new JButton(icon.getTomato());
		
		util.invisible(btn1);
		util.invisible(btn2);
		util.invisible(btn3);
		util.invisible(btn4);
		util.invisible(btn5);
		
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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(199, 810, 195, 65);
		jlp.add(btnNewButton, new Integer(2));
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(463, 810, 195, 65);
		jlp.add(btnNewButton_1, new Integer(2));
		
		
		
		JButton backBtn = new JButton(icon.getBack());
		backBtn.setBounds(12, 10, 150, 70);
		jlp.add(backBtn, new Integer(2));
		util.invisible(backBtn);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MenuFrame backFrame = new MenuFrame();
				
			}
		});
	}
}
