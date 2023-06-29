package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import function.Sql_Methods;
import img.imageIcon;
import utilty.invisibility;

public class MakePizzaFrame extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private invisibility util = new invisibility();
	private Sql_Methods edgeSql = new Sql_Methods();
	private int countEdge = 0;

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
		edgeSetting();



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
	private void edgeSetting() {

		JPanel edgePnl = new JPanel();
		edgePnl.setBackground(new Color(229, 206, 190));
		edgePnl.setBounds(40, 685, 300, 100);
		jlp.add(edgePnl, new Integer(2));
		edgePnl.setLayout(null);

		JLabel edgeLbl = new JLabel();
		edgeLbl.setIcon(icon.getBasic());
		edgeLbl.setBounds(55, 25, 139, 50);
		
		edgePnl.add(edgeLbl, new Integer(2));

		JButton leftBtn = new JButton(icon.getLeft());
		leftBtn.setBounds(0, 30, 45, 45);
		edgePnl.add(leftBtn, new Integer(3));
		util.invisible(leftBtn);

		JButton rightBtn = new JButton(icon.getRight());
		rightBtn.setBounds(210, 30, 45, 45);
		edgePnl.add(rightBtn, new Integer(3));
		util.invisible(rightBtn);
		
		
		rightBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(countEdge == 0) {
					String sweetPotato = "엣지_고구마무스";
					edgeLbl.setIcon(icon.getSweetPotato());
					edgeLbl.setBounds(42, 25, 178, 50);
					countEdge = 1;
					
				} else if (countEdge == 1){
					String cheeseBite = "엣지_크러스트";
					edgeLbl.setIcon(icon.getCheeseBite());
					edgeLbl.setBounds(42, 25, 178, 50);
					countEdge = 2;
					
				} else if (countEdge == 2) {
					String no = "엣지_노";
					edgeLbl.setIcon(icon.getNoEdge());
					edgeLbl.setBounds(55, 25, 139, 50);
					countEdge = 3;
				}
				else if (countEdge == 3) {
					String basic = "엣지_기본";
					edgeLbl.setIcon(icon.getBasic());
					edgeLbl.setBounds(55, 25, 139, 50);
					countEdge = 0;
					
				} 
				
			}
		});
		
		countEdge = 0;
		
		leftBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(countEdge == 0) {
					String basic = "엣지_기본";
					edgeLbl.setIcon(icon.getBasic());
					edgeLbl.setBounds(55, 25, 139, 50);
					
					countEdge = 3;
					
				}else if(countEdge == 1) {
					String sweetPotato = "엣지_고구마무스";
					edgeLbl.setBounds(42, 25, 178, 50);
					edgeLbl.setIcon(icon.getSweetPotato());
					
					
					countEdge = 0;
					
				} else if (countEdge == 2){
					String cheeseBite = "엣지_치즈바이트";
					edgeLbl.setIcon(icon.getCheeseBite());
					edgeLbl.setBounds(42, 25, 178, 50);
					
					
					countEdge = 1;
					
				} else if (countEdge == 3) {
					String no = "엣지_노";
					edgeLbl.setIcon(icon.getNoEdge());
					edgeLbl.setBounds(55, 25, 139, 50);
					
					countEdge = 2;
				}
				
			}
		});
		
	}
}
