package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private HashMap<String, byte[]> topingMap = sql.getTopingImgInBox("토핑");
	
	private JLabel edgeLbl;
	private JLabel currentEdge;
	private ImageIcon edgeIcon;

//	/**
//	 * Launch the application.ㅁ
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MakePizzaFrame frame = new MakePizzaFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MakePizzaFrame(MenuFrame menu) {
		frameSetting();
		sourceBtnSetting(menu);
		edgeSetting();
		showTopping();
	}

	private void frameSetting() {
		jlp = new JLayeredPane();
		JLabel lbl = new JLabel(icon.makePizzaFrame());
		JLayeredPane topingPnl = new JLayeredPane();
		lbl.setBounds(0, 0, 800, 900);
		topingPnl.setBounds(407, 114, 358, 430);
		topingPnl.setOpaque(true);

		jlp.setPreferredSize(new Dimension(icon.getMainFrame().getIconWidth(), icon.getMainFrame().getIconHeight()));
		jlp.setLayout(null);
		

		jlp.add(lbl);
		jlp.add(topingPnl, new Integer(2));

		setContentPane(jlp);

		setUndecorated(true);
		setVisible(true);
		setSize(800, 900);
		setLocationRelativeTo(null);
	}

	private void sourceBtnSetting(MenuFrame menu) {
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

		btn1.setBounds(713, 614, 52, 98);
		jlp.add(btn1, new Integer(1));
		btn2.setBounds(639, 614, 52, 98);
		jlp.add(btn2, new Integer(1));
		btn3.setBounds(562, 614, 52, 98);
		jlp.add(btn3, new Integer(1));
		btn4.setBounds(485, 614, 52, 98);
		jlp.add(btn4, new Integer(1));
		btn5.setBounds(407, 614, 52, 98);
		jlp.add(btn5, new Integer(1));
		
		//취소,담기 버튼
		JButton cancle = new JButton(icon.getCancle());
		cancle.setBounds(90, 775, 280, 70);
		jlp.add(cancle, new Integer(2));
		util.invisible(cancle);
		cancle.setRolloverIcon(icon.getCancleBright());

		JButton add = new JButton(icon.getAdd());
		add.setBounds(430, 775, 280, 70);
		jlp.add(add, new Integer(2));
		util.invisible(add);
		add.setRolloverIcon(icon.getAddBright());

		JButton backBtn = new JButton(icon.getBack());
		backBtn.setBounds(12, 10, 150, 70);
		jlp.add(backBtn, new Integer(2));
		util.invisible(backBtn);

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				menu.setVisible(true);

			}
		});
	}
	private void edgeSetting() {
		

		JLayeredPane edgePnl = new JLayeredPane();
		edgePnl.setBackground(new Color(229, 206, 190));
		edgePnl.setBounds(54, 657, 300, 60);
		jlp.add(edgePnl, new Integer(1));
		edgePnl.setOpaque(true);
		


		JButton leftBtn = new JButton(icon.getLeft());
		leftBtn.setBounds(0, 5, 50, 50);
		edgePnl.add(leftBtn, new Integer(2));
		util.invisible(leftBtn);
		leftBtn.setRolloverIcon(icon.getLeftBright());

		JButton rightBtn = new JButton(icon.getRight());
		rightBtn.setBounds(250, 5, 50, 50);
		edgePnl.add(rightBtn, new Integer(2));
		util.invisible(rightBtn);
		rightBtn.setRolloverIcon(icon.getRightBright());
		
		edgeLbl = new JLabel("기본");
		edgeLbl.setBounds(118, 5, 65, 50);
		edgeLbl.setForeground(new Color(138, 88, 89));
		edgeLbl.setVerticalAlignment(SwingConstants.CENTER);
		edgeLbl.setFont(new Font("함초롬바탕", Font.BOLD, 30));
		edgePnl.add(edgeLbl, new Integer(2));
		
		currentEdge = new JLabel(edgeIcon);
		currentEdge.setBounds(-10, 108, 410, 529);
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
	        edgeLbl.setBounds(118, 5, 65, 50);
	    } else if (countEdge == 1) {
	        edgeLbl.setText("노엣지");
	        edgeLbl.setBounds(101, 5, 99, 50);
	        currentEdge.setIcon(null);
	    } else if (countEdge == 2) {
	        edgeLbl.setText("고구마무스");
	        edgeLbl.setBounds(70, 5, 160, 50);
	    } else if (countEdge == 3) {
	        edgeLbl.setText("치즈크러스트");
	        edgeLbl.setBounds(55, 5, 190, 50);
	    }
		getEdge();
	}
	
	private void getToping() {
//		HashMap<String, byte[]> topingMap = sql.getTopingImgInBox("토핑");
//		byte[] cheese = topingMap.get("치즈");
//		ImageIcon cheeseIcon = new ImageIcon(cheese);
//		JLabel cheeseLbl = new JLabel(cheeseIcon);
//		cheeseLbl.setBounds(415, 88, 150, 130);
//		jlp.add(cheeseLbl, new Integer(2));
//		
//		byte[] bullgogiArr = topingMap.get("불고기");
//		ImageIcon bullgogiIcon = new ImageIcon(bullgogiArr);
//		JLabel bullgogiLbl = new JLabel(bullgogiIcon);
//		bullgogiLbl.setBounds(595, 88, 150, 130);
//		jlp.add(bullgogiLbl, new Integer(2));
//		
//		byte[] friedArr = topingMap.get("뻥튀기");
//		ImageIcon friedIcon = new ImageIcon(friedArr);
//		JLabel friedLbl = new JLabel(friedIcon);
//		friedLbl.setBounds(415, 200, 150, 130);
//		jlp.add(friedLbl, new Integer(2));
//		
//		byte[] cheese = topingMap.get("아스파라거스");
//		ImageIcon cheeseIcon = new ImageIcon(cheese);
//		JLabel cheeseLbl = new JLabel(cheeseIcon);
//		cheeseLbl.setBounds(55, 88, 150, 130);
//		jlp.add(cheeseLbl, new Integer(2));
//		
//		byte[] cheese = topingMap.get("양파");
//		ImageIcon cheeseIcon = new ImageIcon(cheese);
//		JLabel cheeseLbl = new JLabel(cheeseIcon);
//		cheeseLbl.setBounds(55, 88, 150, 130);
//		jlp.add(cheeseLbl, new Integer(2));
//		
//		byte[] cheese = topingMap.get("초콜릿");
//		ImageIcon cheeseIcon = new ImageIcon(cheese);
//		JLabel cheeseLbl = new JLabel(cheeseIcon);
//		cheeseLbl.setBounds(55, 88, 150, 130);
//		jlp.add(cheeseLbl, new Integer(2));
		
		
		
	}
//	private void addToppingLabel(HashMap<String, byte[]> topingMap, String toppingName, int x, int y, int width, int height, int layer) {
//	    byte[] toppingImage = topingMap.get(toppingName);
//	    ImageIcon toppingIcon = new ImageIcon(toppingImage);
//	    JLabel toppingLbl = new JLabel(toppingIcon);
//	    toppingLbl.setBounds(x, y, width, height);
//	    jlp.add(toppingLbl, new Integer(layer));
//	}
//
//	private void addTopping() {
//		addToppingLabel(topingMap, "치즈", 415, 88, 150, 130, 2);
//		addToppingLabel(topingMap, "불고기", 415, 263, 150, 130, 2);
//		addToppingLabel(topingMap, "뻥튀기", 415, 445, 150, 130, 2);
//		
//	}
	private void showTopping() {
		
		List<String> topingNames = sql.pizzamakeSetToping("토핑");
		HashMap<String, byte[]> topingArr = sql.getTopingImgInBox("토핑");
		
		for(String topingName : topingArr.keySet()) {
			
			byte[] imageData = topingArr.get(topingName);
			ImageIcon icon = new ImageIcon(imageData);
			
			JLabel lbl = new JLabel(topingName, icon, JLabel.CENTER);
			lbl.setBounds(400, 450, 600, 700);
			jlp.add(lbl, new Integer(3));
			
		}
	}
	

}
