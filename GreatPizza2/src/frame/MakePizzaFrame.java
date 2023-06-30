package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
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
	private Sql_Methods sql = new Sql_Methods();
	private int countEdge;
	
	private HashMap<String, byte[]> edgeMap = sql.pizzamakeSetEdgeimg("엣지");
	
	private JLabel edgeLbl;
	private JLabel currentEdge;
	private ImageIcon edgeIcon;
	

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
		getToping();



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


		JButton leftBtn = new JButton(icon.getLeft());
		leftBtn.setBounds(0, 30, 45, 45);
		edgePnl.add(leftBtn, new Integer(3));
		util.invisible(leftBtn);

		JButton rightBtn = new JButton(icon.getRight());
		rightBtn.setBounds(210, 30, 45, 45);
		edgePnl.add(rightBtn, new Integer(3));
		util.invisible(rightBtn);
		
		edgeLbl = new JLabel("기본");
		edgeLbl.setBounds(108, 20, 178, 50);
		edgeLbl.setFont(new Font("함초롬바탕", Font.BOLD, 20));
		edgePnl.add(edgeLbl, new Integer(2));
		
		currentEdge = new JLabel(edgeIcon);
		currentEdge.setBounds(-10, 200, 410, 529);
		jlp.add(currentEdge, new Integer(3));
		
		
		rightBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				countEdge++;
				
				if(countEdge > 3) {
					countEdge = 0;
					
				}
				updateEdge();

			}
			
			
		});
		
		
		leftBtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				countEdge--;
				if(countEdge < 0) {
					countEdge = 3;
				}
				updateEdge();
				
			}
		});
		getEdge();
		
	}
	private void getEdge() {
			byte[] doughArr = edgeMap.get(edgeLbl.getText());
			if(doughArr != null) {
			edgeIcon = new ImageIcon(doughArr);
			currentEdge.setIcon(edgeIcon);
				
			} else {
				System.out.println("이미지가 null참조이다");
			}			
			

	}
	private void updateEdge() {
		
		if (countEdge == 0) {
	        edgeLbl.setText("기본");
	        edgeLbl.setBounds(108, 20, 178, 50);
	    } else if (countEdge == 1) {
	        edgeLbl.setText("노엣지");
	        edgeLbl.setBounds(102, 20, 178, 50);
	        currentEdge.setIcon(null);
	    } else if (countEdge == 2) {
	        edgeLbl.setText("고구마무스");
	        edgeLbl.setBounds(83, 20, 139, 50);
	    } else if (countEdge == 3) {
	        edgeLbl.setText("치즈크러스트");
	        edgeLbl.setBounds(78, 20, 139, 50);
	    }
		getEdge();
	}
	
	private void getToping() {
		HashMap<String, byte[]> topingMap = sql.getTopingImgInBox("토핑");
		byte[] cheese = topingMap.get("캐비어");
		ImageIcon cheeseIcon = new ImageIcon(cheese);
		JLabel cheeseLbl = new JLabel(cheeseIcon);
		cheeseLbl.setBounds(60, 10, 100, 100);
		jlp.add(cheeseLbl, new Integer(2));
		
		
		
	}
}
