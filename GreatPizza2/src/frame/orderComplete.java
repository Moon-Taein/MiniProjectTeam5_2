package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import function.DetailOrder;
import function.MainOrder;
import function.Sql_Methods;
import img.imageIcon;
import utilty.invisibility;

class ImagePanel2 extends JPanel {
	private Image img;

	public ImagePanel2(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 3, 0, null);
	}
}

public class orderComplete extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private invisibility util = new invisibility();
	private JPanel buyListpanel;
	private int frameChange = 0;
	private Font tftFont2 = getBMJUAFont(20f);
	private Font tftFont3 = getBMJUAFont(50f);

	// 확인하면 메인프레임으로 보내게 바꿔줘야된다
	public orderComplete(MainOrder mo, MenuFrame menu, MainFrame main) {
		System.out.println("총 주문 가격은 : " + mo.getTotal_Price());
		System.out.println("메인오더넘버 : " + mo.getOrderNumber());
		List<DetailOrder> list = mo.getDeoList();
		for (DetailOrder deo : list) {
			System.out.println("디테일오더넘버 : " + deo.getDetailOrderNumber());
		}
		frameSetting(mo);
		buttonSetting(menu, mo, main);
		orderList(mo, 0);

	}

	private void frameSetting(MainOrder mo) {

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(icon.getMainFrame().getIconWidth(), icon.getMainFrame().getIconHeight()));
		jlp.setLayout(null);

		JLabel lbl = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("order/orderCompleteFrame.png")));
		lbl.setBounds(0, 0, 800, 900);
		jlp.add(lbl, new Integer(2));

		setContentPane(jlp);

		buyListpanel = new JPanel();
		buyListpanel.setBounds(50, 290, 630, 480);
		buyListpanel.setLayout(null);
		jlp.add(buyListpanel, new Integer(3));

		setUndecorated(true);
		setSize(800, 900);
		setLocationRelativeTo(null);
	}

	// 0~5 6~11 12~17 ...
	private void orderList(MainOrder mo, int target) {
		System.out.println("타겟의 숫자" + target);

		List<DetailOrder> list = mo.getDeoList();
		System.out.println("order리스트 사이즈는 " + list.size());

		if (list.size() > target) {
			ImagePanel2 panel1 = new ImagePanel2(
					new ImageIcon(getClass().getClassLoader().getResource("order/orderListPanel.png")).getImage());
			panel1.setLayout(null);
			panel1.setBounds(2, 5, 620, 70);
			buyListpanel.add(panel1, new Integer(3));

			JLabel deoNo1 = new JLabel(String.valueOf(target + 1));
			deoNo1.setBounds(65, 25, 60, 15);
			deoNo1.setFont(tftFont2);
			deoNo1.setForeground(new Color(103, 51, 53));
			panel1.add(deoNo1);

			JLabel deoMenuId1 = new JLabel(list.get(target).getMenu());
			deoMenuId1.setBounds(165, 25, 180, 20);
			deoMenuId1.setFont(tftFont2);
			deoMenuId1.setForeground(new Color(103, 51, 53));
			panel1.add(deoMenuId1);

			JLabel deoCount1 = new JLabel(String.valueOf(list.get(target).getMenu_count()));
			deoCount1.setBounds(395, 25, 100, 15);
			deoCount1.setFont(tftFont2);
			deoCount1.setForeground(new Color(103, 51, 53));
			panel1.add(deoCount1);

			JLabel deoPrice1 = new JLabel(String.valueOf(list.get(target).getDetailOrderFullPrice()));
			deoPrice1.setBounds(530, 25, 100, 15);
			deoPrice1.setFont(tftFont2);
			deoPrice1.setForeground(new Color(103, 51, 53));
			panel1.add(deoPrice1);
		}

		if (list.size() > target + 1) {
			ImagePanel2 panel2 = new ImagePanel2(
					new ImageIcon(getClass().getClassLoader().getResource("order/orderListPanel.png")).getImage());
			panel2.setLayout(null);
			panel2.setBounds(2, 85, 620, 70);
			buyListpanel.add(panel2, new Integer(3));

			JLabel deoNo2 = new JLabel(String.valueOf(target + 2));
			deoNo2.setBounds(65, 25, 60, 15);
			deoNo2.setFont(tftFont2);
			deoNo2.setForeground(new Color(103, 51, 53));
			panel2.add(deoNo2);

			JLabel deoMenuId2 = new JLabel(list.get(target + 1).getMenu());
			deoMenuId2.setBounds(165, 25, 180, 20);
			deoMenuId2.setFont(tftFont2);
			deoMenuId2.setForeground(new Color(103, 51, 53));
			panel2.add(deoMenuId2);

			JLabel deoCount2 = new JLabel(String.valueOf(list.get(target + 1).getMenu_count()));
			deoCount2.setBounds(395, 25, 100, 15);
			deoCount2.setFont(tftFont2);
			deoCount2.setForeground(new Color(103, 51, 53));
			panel2.add(deoCount2);

			JLabel deoPrice2 = new JLabel(String.valueOf(list.get(target + 1).getDetailOrderFullPrice()));
			deoPrice2.setBounds(530, 25, 100, 15);
			deoPrice2.setFont(tftFont2);
			deoPrice2.setForeground(new Color(103, 51, 53));
			panel2.add(deoPrice2);
		}

		if (list.size() > target + 2) {
			ImagePanel2 panel3 = new ImagePanel2(
					new ImageIcon(getClass().getClassLoader().getResource("order/orderListPanel.png")).getImage());
			panel3.setLayout(null);
			panel3.setBounds(2, 165, 620, 70);
			buyListpanel.add(panel3, new Integer(3));

			JLabel deoNo3 = new JLabel(String.valueOf(target + 3));
			deoNo3.setBounds(65, 25, 60, 15);
			deoNo3.setFont(tftFont2);
			deoNo3.setForeground(new Color(103, 51, 53));
			panel3.add(deoNo3);

			JLabel deoMenuId3 = new JLabel(list.get(target + 2).getMenu());
			deoMenuId3.setBounds(165, 25, 180, 20);
			deoMenuId3.setFont(tftFont2);
			deoMenuId3.setForeground(new Color(103, 51, 53));
			panel3.add(deoMenuId3);

			JLabel deoCount3 = new JLabel(String.valueOf(list.get(target + 2).getMenu_count()));
			deoCount3.setBounds(395, 25, 100, 15);
			deoCount3.setFont(tftFont2);
			deoCount3.setForeground(new Color(103, 51, 53));
			panel3.add(deoCount3);

			JLabel deoPrice3 = new JLabel(String.valueOf(list.get(target + 2).getDetailOrderFullPrice()));
			deoPrice3.setBounds(530, 25, 100, 15);
			deoPrice3.setFont(tftFont2);
			deoPrice3.setForeground(new Color(103, 51, 53));
			panel3.add(deoPrice3);
		}

		if (list.size() > target + 3) {
			ImagePanel2 panel4 = new ImagePanel2(
					new ImageIcon(getClass().getClassLoader().getResource("order/orderListPanel.png")).getImage());
			panel4.setLayout(null);
			panel4.setBounds(2, 245, 620, 70);
			buyListpanel.add(panel4, new Integer(3));

			JLabel deoNo4 = new JLabel(String.valueOf(target + 4));
			deoNo4.setBounds(65, 25, 60, 15);
			deoNo4.setFont(tftFont2);
			deoNo4.setForeground(new Color(103, 51, 53));
			panel4.add(deoNo4);

			JLabel deoMenuId4 = new JLabel(list.get(target + 3).getMenu());
			deoMenuId4.setBounds(165, 25, 180, 20);
			deoMenuId4.setFont(tftFont2);
			deoMenuId4.setForeground(new Color(103, 51, 53));
			panel4.add(deoMenuId4);

			JLabel deoCount4 = new JLabel(String.valueOf(list.get(target + 3).getMenu_count()));
			deoCount4.setBounds(395, 25, 100, 15);
			deoCount4.setFont(tftFont2);
			deoCount4.setForeground(new Color(103, 51, 53));
			panel4.add(deoCount4);

			JLabel deoPrice4 = new JLabel(String.valueOf(list.get(target + 3).getDetailOrderFullPrice()));
			deoPrice4.setBounds(530, 25, 100, 15);
			deoPrice4.setFont(tftFont2);
			deoPrice4.setForeground(new Color(103, 51, 53));
			panel4.add(deoPrice4);
		}

		if (list.size() > target + 4) {
			ImagePanel2 panel5 = new ImagePanel2(
					new ImageIcon(getClass().getClassLoader().getResource("order/orderListPanel.png")).getImage());
			panel5.setLayout(null);
			panel5.setBounds(2, 325, 620, 70);
			buyListpanel.add(panel5, new Integer(3));

			JLabel deoNo5 = new JLabel(String.valueOf(target + 5));
			deoNo5.setBounds(65, 25, 60, 15);
			deoNo5.setFont(tftFont2);
			deoNo5.setForeground(new Color(103, 51, 53));
			panel5.add(deoNo5);

			JLabel deoMenuId5 = new JLabel(list.get(target + 4).getMenu());
			deoMenuId5.setBounds(165, 25, 180, 20);
			deoMenuId5.setFont(tftFont2);
			deoMenuId5.setForeground(new Color(103, 51, 53));
			panel5.add(deoMenuId5);

			JLabel deoCount5 = new JLabel(String.valueOf(list.get(target + 4).getMenu_count()));
			deoCount5.setBounds(395, 25, 100, 15);
			deoCount5.setFont(tftFont2);
			deoCount5.setForeground(new Color(103, 51, 53));
			panel5.add(deoCount5);

			JLabel deoPrice5 = new JLabel(String.valueOf(list.get(target + 4).getDetailOrderFullPrice()));
			deoPrice5.setBounds(530, 25, 100, 15);
			deoPrice5.setFont(tftFont2);
			deoPrice5.setForeground(new Color(103, 51, 53));
			panel5.add(deoPrice5);
		}

		if (list.size() > target + 5) {
			ImagePanel2 panel6 = new ImagePanel2(
					new ImageIcon(getClass().getClassLoader().getResource("order/orderListPanel.png")).getImage());
			panel6.setLayout(null);
			panel6.setBounds(2, 405, 620, 70);
			buyListpanel.add(panel6, new Integer(3));

			JLabel deoNo6 = new JLabel(String.valueOf(target + 6));
			deoNo6.setBounds(65, 25, 60, 15);
			deoNo6.setFont(tftFont2);
			deoNo6.setForeground(new Color(103, 51, 53));
			panel6.add(deoNo6);

			JLabel deoMenuId6 = new JLabel(list.get(target + 5).getMenu());
			deoMenuId6.setBounds(165, 25, 180, 20);
			deoMenuId6.setFont(tftFont2);
			deoMenuId6.setForeground(new Color(103, 51, 53));
			panel6.add(deoMenuId6);

			JLabel deoCount6 = new JLabel(String.valueOf(list.get(target + 5).getMenu_count()));
			deoCount6.setBounds(395, 25, 100, 15);
			deoCount6.setFont(tftFont2);
			deoCount6.setForeground(new Color(103, 51, 53));
			panel6.add(deoCount6);

			JLabel deoPrice6 = new JLabel(String.valueOf(list.get(target + 5).getDetailOrderFullPrice()));
			deoPrice6.setBounds(530, 25, 100, 15);
			deoPrice6.setFont(tftFont2);
			deoPrice6.setForeground(new Color(103, 51, 53));
			panel6.add(deoPrice6);
		}

	}

	private void buttonSetting(MenuFrame menu, MainOrder mo, MainFrame main) {

		JButton upButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("up.png")));
		upButton.setBounds(700, 310, 70, 90);
		upButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("upRoll.png")));
		upButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frameChange > 0) {
					buyListpanel.removeAll();
					buyListpanel.repaint();
					frameChange -= 6;
					orderList(mo, frameChange);
				}
			}
		});
		jlp.add(upButton, new Integer(3));
		util.invisible(upButton);

		JButton downButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("down.png")));
		downButton.setBounds(700, 670, 70, 90);
		downButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("downRoll.png")));
		downButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mo.getDeoList().size() > frameChange + 6) {
					buyListpanel.removeAll();
					buyListpanel.repaint();
					frameChange += 6;
					orderList(mo, frameChange);
				}
			}
		});
		jlp.add(downButton, new Integer(3));
		util.invisible(downButton);

		JButton completeBtn = new JButton(
				new ImageIcon(getClass().getClassLoader().getResource("order/order주문하기.png")));
		completeBtn.setBounds(400, 800, 200, 100);
		jlp.add(completeBtn, new Integer(3));
		completeBtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("order/order주문하기Roll.png")));
		completeBtn.setBorderPainted(false);

		completeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Sql_Methods sqlm = new Sql_Methods();
				// mainOrder insert
				// detailOrder insert
				// detailOrder milist.size() > 0 -> milist 전부 insert

				MainFrame main = new MainFrame();
				main.setVisible(true);
			}
		});

		JButton cancelBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("order/order취소.png")));
		cancelBtn.setBounds(600, 800, 200, 100);
		jlp.add(cancelBtn, new Integer(3));
		cancelBtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("order/order취소Roll.png")));
		cancelBtn.setBorderPainted(false);

		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				menu.setVisible(true);
			}
		});

		JLabel menuLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("order/order메뉴.png")));
		menuLabel.setBounds(219, 230, 130, 40);
		jlp.add(menuLabel, new Integer(3));

		JLabel countLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("order/order수량.png")));
		countLabel.setBounds(383, 230, 130, 40);
		jlp.add(countLabel, new Integer(3));

		JLabel priceLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("order/order가격.png")));
		priceLabel.setBounds(540, 230, 130, 40);
		jlp.add(priceLabel, new Integer(3));

		JLabel detailImageLabel = new JLabel(
				new ImageIcon(getClass().getClassLoader().getResource("order/order넘버.png")));
		detailImageLabel.setBounds(60, 230, 130, 40);
		jlp.add(detailImageLabel, new Integer(3));

		JLabel total_main_price = new JLabel(String.valueOf(mo.getTotal_Price()));
		total_main_price.setBounds(140, 820, 210, 50);
		total_main_price.setFont(tftFont3);
		total_main_price.setForeground(Color.WHITE);
		jlp.add(total_main_price, new Integer(4));

	}

	private Font getBMJUAFont(float f) {
		// TFT 폰트 파일 로드
		InputStream fontStream = Pizza_PopUp_Frame.class.getResourceAsStream("/popup/BMJUA_ttf.ttf");
		Font tftFont;
		try {
			tftFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			tftFont = tftFont.deriveFont(f);
			return tftFont;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
