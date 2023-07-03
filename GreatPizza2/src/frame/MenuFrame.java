package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.List;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import function.DetailOrder;
import function.MainOrder;
import function.MenuItem;
import function.Sql_Methods;
import img.imageIcon;
import utilty.invisibility;

public class MenuFrame extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private invisibility util = new invisibility();
	private JButton pizzaBtn;
	private JButton sideBtn;
	private JButton drinkBtn;
	private JButton makePizzaBtn;
	private static Sql_Methods sqm = new Sql_Methods();;
	private int mainOrderCount = sqm.findMainOrderCount(); // order 누르면 ++ 되게
	public int detailOrderCount = sqm.findDetailOrderCount(); // 피자 - 담기누를때 ++ 사이드,음료 - 담을때마다 ++
	private JLayeredPane menuPnl;
	private MainOrder mo;
	private DetailOrder deo;
	// parameter로 크기값 받고 return으로 해당크기의 tftfont 해주는
	private Font tftFont = getBMJUAFont(18f);
	private Font tftFont2 = getBMJUAFont(25f);
	private Font tftFont3 = getBMJUAFont(35f);
	private Font tftFont4 = getBMJUAFont(20f);
	private JLabel menuIdLabel;
	private JLabel countLabel;
	private JLabel priceLabel;
	static JLabel total_priceLabel;
	private int underListTarget = 0;
	private JLayeredPane underListPanel;
	private int deo1Price;
	private int deo2Price;
	private int deo3Price;
	private JLabel underMenu1;
	private JLabel underMenu2;
	private JLabel underMenu3;

	public int getDetailOrderCount() {
		return detailOrderCount;
	}

	public void setDetailOrderCount(int detailOrderCount) {
		this.detailOrderCount = detailOrderCount;
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

	/**
	 * Create the frame.
	 */
	public MenuFrame(MainFrame main) {

		// 오더를 눌렀을때 mainorder 객체 생성
//		mainOrderCount = sqm.findOrderCount("mainorder");
//		detailOrderCount = sqm.findOrderCount("detailorder");

		mainOrderCount++;
		mo = new MainOrder(mainOrderCount);
		FrameSetting();

		menuPnl = new JLayeredPane();
		menuPnl.setBounds(0, 196, 800, 469);
		jlp.add(menuPnl, new Integer(3));

		// 초기화면 피자탭
		pizzaTabBtn(0);

		buttonSetting(main);// 버튼 생성 메소드

		// 하단 주문목록 띄우는 메소드
		underOrderList(mo, 0);

		pizzaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 탭 전환시 지우고 다시 메뉴패널 위 버튼 생성
				menuPnl.removeAll();
				menuPnl.repaint();
				pizzaTabBtn(0);

				pizzaBtn.setIcon(icon.getwhitePizzaBtn());

				sideBtn.setIcon(icon.getdarkSideBtn());
				drinkBtn.setIcon(icon.getdarkDrinkBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());
				// 종료메소드
				exiteKey();
				jlp.requestFocusInWindow();

			}
		});

		sideBtn.addActionListener(new ActionListener() {
			// 사이드버튼 액션리스너
			@Override
			public void actionPerformed(ActionEvent e) {

				menuPnl.removeAll();
				menuPnl.repaint();
				sideTabBtn(0);

				sideBtn.setIcon(icon.getwhiteSideBtn());
				// 버튼을 눌렀을 때 해당버튼 외 버튼들을 회색으로 바꿈
				pizzaBtn.setIcon(icon.getdarkPizzaBtn());
				drinkBtn.setIcon(icon.getdarkDrinkBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());

				exiteKey();
				jlp.requestFocusInWindow();

			}
		});

		drinkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuPnl.removeAll();
				menuPnl.repaint();
				drinkTabBtn(0);

				drinkBtn.setIcon(icon.getwhiteDrinkBtn());
				pizzaBtn.setIcon(icon.getdarkPizzaBtn());
				sideBtn.setIcon(icon.getdarkSideBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());

				exiteKey();
				jlp.requestFocusInWindow();

			}
		});

		makePizzaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MakePizzaFrame newFrame = new MakePizzaFrame(MenuFrame.this);

			}
		});

		util.invisible(pizzaBtn);
		util.invisible(sideBtn);
		util.invisible(drinkBtn);
		util.invisible(makePizzaBtn);

		underListPanel = new JLayeredPane();
		underListPanel.setBounds(0, 670, 575, 180);
		underListPanel.setLayout(null);
		underListPanel.setOpaque(false);
		jlp.add(underListPanel, new Integer(3));

		exiteKey();
		jlp.requestFocusInWindow();

	}

	private void FrameSetting() {
		JLabel lbl = new JLabel(icon.getMenuFrame());
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

	// 담기할때랑, 사이드,음료 눌렀을때 갱신해주기
	// target 기준으로 mo의 deolist 띄워주기
	public void underOrderList(MainOrder mo, int target) {
		Sql_Methods sqlm = new Sql_Methods();
		List<DetailOrder> list = mo.getDeoList();

		if (list.size() > target) {
//			DetailOrder deo1 = null;
			DetailOrder deo1 = list.get(target);
			underMenu1 = new JLabel(deo1.getMenu());
			underMenu1.setBounds(30, 25, 160, 25);
			underMenu1.setFont(tftFont4);
			underMenu1.setForeground(Color.WHITE);
			underListPanel.add(underMenu1, new Integer(2));

			JLabel underCount1 = new JLabel(String.valueOf(deo1.getMenu_count()));
			underCount1.setBounds(285, 25, 50, 25);
			underCount1.setFont(tftFont4);
			underCount1.setForeground(Color.WHITE);
			underListPanel.add(underCount1, new Integer(2));

			deo1Price = Integer.valueOf(sqlm.findPriceMenuId(deo1.getMenu()));
			if (deo1.getMiList() != null) {
				for (MenuItem menu : deo1.getMiList()) {
					deo1Price = deo1Price + menu.getMenuItemPrice();
				}
			}

			JLabel underPrice1 = new JLabel(String.valueOf(deo1.getDetailOrderFullPrice()));
			underPrice1.setBounds(455, 25, 130, 25);
			underPrice1.setFont(tftFont4);
			underPrice1.setForeground(Color.WHITE);
			underListPanel.add(underPrice1, new Integer(2));

			JButton underPlus1 = new JButton(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListPlus.png")));
			underPlus1.setBounds(315, 23, 30, 30);
			underPlus1.setRolloverIcon(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListPlusRoll.png")));
			underPlus1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					deo1.setMenu_count(deo1.getMenu_count() + 1);
					deo1.setDetailOrderFullPrice(deo1.getDetailOrderFullPrice() + deo1Price);
					underCount1.setText(String.valueOf(deo1.getMenu_count()));
					underPrice1.setText(String.valueOf(deo1.getDetailOrderFullPrice()));
					underCount1.repaint();
					underPrice1.repaint();
					int mo_total_price = final_total_price(mo);
					total_priceLabel.setText(String.valueOf(mo_total_price) + "원");
				}
			});
			util.invisible(underPlus1);
			underListPanel.add(underPlus1, new Integer(2));

			JButton underMinus1 = new JButton(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListMinus.png")));
			underMinus1.setBounds(245, 23, 30, 30);
			underMinus1.setRolloverIcon(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListMinusRoll.png")));
			underMinus1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (deo1.getMenu_count() > 1) {
						deo1.setMenu_count(deo1.getMenu_count() - 1);
						deo1.setDetailOrderFullPrice(deo1.getDetailOrderFullPrice() - deo1Price);
						underCount1.setText(String.valueOf(deo1.getMenu_count()));
						underPrice1.setText(String.valueOf(deo1.getDetailOrderFullPrice()));
						underCount1.repaint();
						underPrice1.repaint();
						int mo_total_price = final_total_price(mo);
						total_priceLabel.setText(String.valueOf(mo_total_price) + "원");
					}
				}
			});
			util.invisible(underMinus1);
			underListPanel.add(underMinus1, new Integer(2));
		}

		if (list.size() > target + 1) {
//			DetailOrder deo2 = null;
			DetailOrder deo2 = list.get(target + 1);
			underMenu2 = new JLabel(deo2.getMenu());
			underMenu2.setBounds(30, 85, 160, 25);
			underMenu2.setFont(tftFont4);
			underMenu2.setForeground(Color.WHITE);
			underListPanel.add(underMenu2, new Integer(2));

			JLabel underCount2 = new JLabel(String.valueOf(deo2.getMenu_count()));
			underCount2.setBounds(285, 85, 50, 25);
			underCount2.setFont(tftFont4);
			underCount2.setForeground(Color.WHITE);
			underListPanel.add(underCount2, new Integer(2));

			deo2Price = Integer.valueOf(sqlm.findPriceMenuId(deo2.getMenu()));
			if (deo2.getMiList() != null) {
				for (MenuItem menu : deo2.getMiList()) {
					deo2Price = deo2Price + menu.getMenuItemPrice();
				}
			}
			JLabel underPrice2 = new JLabel(String.valueOf(deo2.getDetailOrderFullPrice()));
			underPrice2.setBounds(455, 85, 130, 25);
			underPrice2.setFont(tftFont4);
			underPrice2.setForeground(Color.WHITE);
			underListPanel.add(underPrice2, new Integer(2));

			JButton underPlus2 = new JButton(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListPlus.png")));
			underPlus2.setBounds(315, 83, 30, 30);
			underPlus2.setRolloverIcon(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListPlusRoll.png")));
			underPlus2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					deo2.setMenu_count(deo2.getMenu_count() + 1);
					deo2.setDetailOrderFullPrice(deo2.getDetailOrderFullPrice() + deo2Price);
					underCount2.setText(String.valueOf(deo2.getMenu_count()));
					underPrice2.setText(String.valueOf(deo2.getDetailOrderFullPrice()));
					underCount2.repaint();
					underPrice2.repaint();
					int mo_total_price = final_total_price(mo);
					total_priceLabel.setText(String.valueOf(mo_total_price) + "원");
				}
			});
			util.invisible(underPlus2);
			underListPanel.add(underPlus2, new Integer(2));

			JButton underMinus2 = new JButton(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListMinus.png")));
			underMinus2.setBounds(245, 83, 30, 30);
			underMinus2.setRolloverIcon(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListMinusRoll.png")));
			underMinus2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (deo2.getMenu_count() > 1) {
						deo2.setMenu_count(deo2.getMenu_count() - 1);
						deo2.setDetailOrderFullPrice(deo2.getDetailOrderFullPrice() - deo2Price);
						underCount2.setText(String.valueOf(deo2.getMenu_count()));
						underPrice2.setText(String.valueOf(deo2.getDetailOrderFullPrice()));
						underCount2.repaint();
						underPrice2.repaint();
						int mo_total_price = final_total_price(mo);
						total_priceLabel.setText(String.valueOf(mo_total_price) + "원");
					}
				}
			});
			util.invisible(underMinus2);
			underListPanel.add(underMinus2, new Integer(2));

		}

		if (list.size() > target + 2) {
//			DetailOrder deo3 = null;
			DetailOrder deo3 = list.get(target + 2);
			underMenu3 = new JLabel(deo3.getMenu());
			underMenu3.setBounds(30, 140, 160, 25);
			underMenu3.setFont(tftFont4);
			underMenu3.setForeground(Color.WHITE);
			underListPanel.add(underMenu3, new Integer(2));

			JLabel underCount3 = new JLabel(String.valueOf(deo3.getMenu_count()));
			underCount3.setBounds(285, 140, 50, 25);
			underCount3.setFont(tftFont4);
			underCount3.setForeground(Color.WHITE);
			underListPanel.add(underCount3, new Integer(2));

			deo3Price = Integer.valueOf(sqlm.findPriceMenuId(deo3.getMenu()));
			if (deo3.getMiList() != null) {
				for (MenuItem menu : deo3.getMiList()) {
					deo3Price = deo3Price + menu.getMenuItemPrice();
				}
			}
			JLabel underPrice3 = new JLabel(String.valueOf(deo3.getDetailOrderFullPrice()));
			underPrice3.setBounds(455, 140, 130, 25);
			underPrice3.setFont(tftFont4);
			underPrice3.setForeground(Color.WHITE);
			underListPanel.add(underPrice3, new Integer(2));

			JButton underPlus3 = new JButton(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListPlus.png")));
			underPlus3.setBounds(315, 138, 30, 30);
			underPlus3.setRolloverIcon(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListPlusRoll.png")));
			underPlus3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					deo3.setMenu_count(deo3.getMenu_count() + 1);
					deo3.setDetailOrderFullPrice(deo3.getDetailOrderFullPrice() + deo3Price);
					underCount3.setText(String.valueOf(deo3.getMenu_count()));
					underPrice3.setText(String.valueOf(deo3.getDetailOrderFullPrice()));
					underCount3.repaint();
					underPrice3.repaint();
					int mo_total_price = final_total_price(mo);
					total_priceLabel.setText(String.valueOf(mo_total_price) + "원");
				}
			});
			util.invisible(underPlus3);
			underListPanel.add(underPlus3, new Integer(2));

			JButton underMinus3 = new JButton(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListMinus.png")));
			underMinus3.setBounds(245, 138, 30, 30);
			underMinus3.setRolloverIcon(
					new ImageIcon(getClass().getClassLoader().getResource("underOrderListMinusRoll.png")));
			underMinus3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (deo3.getMenu_count() > 1) {
						deo3.setMenu_count(deo3.getMenu_count() - 1);
						deo3.setDetailOrderFullPrice(deo3.getDetailOrderFullPrice() - deo3Price);
						underCount3.setText(String.valueOf(deo3.getMenu_count()));
						underPrice3.setText(String.valueOf(deo3.getDetailOrderFullPrice()));
						underCount3.repaint();
						underPrice3.repaint();
						int mo_total_price = final_total_price(mo);
						total_priceLabel.setText(String.valueOf(mo_total_price) + "원");
					}
				}
			});
			util.invisible(underMinus3);
			underListPanel.add(underMinus3, new Integer(2));
		}

		JButton cancel1 = new JButton(new ImageIcon(getClass().getClassLoader().getResource("취소.png")));
		cancel1.setBounds(580, 690, 100, 30);
		cancel1.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("취소Roll.png")));
		cancel1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 취소하면 그냥 처음리스트로 보여줄까?
				// 아니면 해당 구역에서 사라지는걸 보여줘야 되나;
				if (underMenu1 != null) {
					System.out.println("현재 undermenu1은 " + underMenu1.getText());
					int underMenu1Index = findListIndexDetailOrderList(list, underMenu1.getText());
					System.out.println(underMenu1Index);
					removeTargetInList(list, underMenu1Index);

					underListPanel.removeAll();
					underListPanel.invalidate();
					underOrderList(mo, 0);
					underListPanel.repaint();
					System.out.println("1번째 삭제지점" + mo.getDeoList().toString());
				}
			}
		});
		util.invisible(cancel1);
		jlp.add(cancel1, new Integer(3));

		JButton cancel2 = new JButton(new ImageIcon(getClass().getClassLoader().getResource("취소.png")));
		cancel2.setBounds(580, 750, 100, 30);
		cancel2.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("취소Roll.png")));
		cancel2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (underMenu2 != null) {
					System.out.println("현재 undermenu2은 " + underMenu2.getText());
					int underMenu2Index = findListIndexDetailOrderList(list, underMenu2.getText());
					System.out.println(underMenu2Index);
					removeTargetInList(list, underMenu2Index);

					underListPanel.removeAll();
					underListPanel.invalidate();
					underOrderList(mo, 0);
					underListPanel.repaint();
					System.out.println("2번째 삭제지점" + mo.getDeoList().toString());
				}
			}
		});
		util.invisible(cancel2);
		jlp.add(cancel2, new Integer(3));

		JButton cancel3 = new JButton(new ImageIcon(getClass().getClassLoader().getResource("취소.png")));
		cancel3.setBounds(580, 810, 100, 30);
		cancel3.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("취소Roll.png")));
		cancel3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (underMenu3 != null) {
					System.out.println("현재 undermenu3은 " + underMenu3.getText());
					int underMenu3Index = findListIndexDetailOrderList(list, underMenu3.getText());
					System.out.println(underMenu3Index);
					removeTargetInList(list, underMenu3Index);

					underListPanel.removeAll();
					underListPanel.invalidate();
					underOrderList(mo, 0);
					underListPanel.repaint();

					System.out.println("3번째 삭제지점" + mo.getDeoList().toString());
				}
			}
		});
		util.invisible(cancel3);
		jlp.add(cancel3, new Integer(3));

		JButton buyListUpButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("miniUp.png")));
		buyListUpButton.setBounds(735, 685, 50, 60);
		buyListUpButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("miniUpRoll.png")));
		util.invisible(buyListUpButton);
		buyListUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (underListTarget > 0) {
					underListTarget = underListTarget - 3;
					underListPanel.removeAll();
					underListPanel.invalidate();
					underOrderList(mo, underListTarget);
					underListPanel.repaint();
				}
			}
		});
		jlp.add(buyListUpButton, new Integer(3));

		JButton buyListDownButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("miniDown.png")));
		buyListDownButton.setBounds(735, 780, 50, 60);
		buyListDownButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("miniDownRoll.png")));
		util.invisible(buyListDownButton);
		buyListDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.size() > underListTarget + 3) {
					underListTarget = underListTarget + 3;
					underListPanel.removeAll();
					underListPanel.invalidate();
					underOrderList(mo, underListTarget);
					underListPanel.repaint();
				}
			}
		});
		jlp.add(buyListDownButton, new Integer(3));
	}

	// 하단바 삭제 메소드
	public void removeTargetInList(List<DetailOrder> list, int underMenuIndex) {
		System.out.println("삭제하기전 리스트의 사이즈는: " + list.size());
		list.remove(underMenuIndex);
		detailOrderCount--;
		for (int i = underMenuIndex; i < list.size() - 1; i++) {
			if (list.get(i + 1) != null) {
				int targetNumber = list.get(i).getDetailOrderNumber();
				list.get(i + 1).setDetailOrderNumber(targetNumber);
			}
		}
	}

	// 텍스트 기준으로 해당 타겟이 리스트의 인덱스 몇번째인지 알려주는거
	public int findListIndexDetailOrderList(List<DetailOrder> list, String target) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMenu().equals(target)) {
				return i;
			}
		}
		return -1;
	}

	private void buttonSetting(MainFrame main) {

		pizzaBtn = new JButton(icon.getwhitePizzaBtn());
		pizzaBtn.setBounds(0, 154, 200, 50);
		jlp.add(pizzaBtn, new Integer(3));
		util.invisible(pizzaBtn);

		sideBtn = new JButton();
		sideBtn.setBounds(200, 154, 200, 50);
		jlp.add(sideBtn, new Integer(3));

		drinkBtn = new JButton();
		drinkBtn.setBounds(400, 154, 200, 50);
		jlp.add(drinkBtn, new Integer(3));

		makePizzaBtn = new JButton();
		makePizzaBtn.setBounds(600, 154, 200, 50);
		jlp.add(makePizzaBtn, new Integer(3));

		JButton orderBtn = new JButton(icon.getOrderBtnKor());
		orderBtn.setBounds(640, 851, 140, 45);
		orderBtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("주문하기Roll.png")));
		jlp.add(orderBtn, new Integer(3));
		orderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 이때 mainOrder db에 기록하기
				final_total_price(mo);
				orderComplete newFrame = new orderComplete(mo, MenuFrame.this, main);
				newFrame.setVisible(true);
				setVisible(false);
			}
		});
		util.invisible(orderBtn);

		JButton homeBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("홈버튼.png")));
		homeBtn.setBounds(650, 10, 100, 100);
		jlp.add(homeBtn, new Integer(3));
		homeBtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("홈버튼roll.png")));
		util.invisible(homeBtn);
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 이때 mainOrder db에 기록하기
				setVisible(false);
				main.setVisible(true);
			}
		});

		menuIdLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("메뉴.png")));
		menuIdLabel.setBounds(50, 655, 100, 30);
		jlp.add(menuIdLabel, new Integer(3));

		countLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("수량.png")));
		countLabel.setBounds(240, 655, 100, 30);
		jlp.add(countLabel, new Integer(3));

		priceLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("가격.png")));
		priceLabel.setBounds(440, 655, 100, 30);
		jlp.add(priceLabel, new Integer(3));

		total_priceLabel = new JLabel("원");
		total_priceLabel.setBounds(200, 852, 236, 36);
		total_priceLabel.setFont(tftFont2);
		total_priceLabel.setForeground(Color.WHITE);
		jlp.add(total_priceLabel, new Integer(3));

	}

	// imageIcon 을 gray로 바꿔줘서 반환
	private ImageIcon grayImageIcon(ImageIcon img) {
		Image normalImage = img.getImage();
		Image grayImage = GrayFilter.createDisabledImage(normalImage);
		ImageIcon grayImg = new ImageIcon(grayImage);
		return grayImg;
	}

	// 추가 메뉴가 있어서 다음 버튼 누르면 6,6 12,6 으로
	private void pizzaTabBtn(int target) {
		// target 기준으로 +6 after
		// target 기준으로 -6 before

		List<Object> list = sqm.findImageAndMenuIdTarget("%M", target);

		// 버튼 눌렀을시 동작하는 메소드
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton a = (JButton) e.getSource();
				String target = a.getText();
				Pizza_PopUp_Frame ppf = new Pizza_PopUp_Frame(target, mo, MenuFrame.this, detailOrderCount,
						underListPanel);
				setVisible(false);
				ppf.setVisible(true);
			}
		};

		if (list.size() > 0) {
			ImageIcon img = new ImageIcon((byte[]) list.get(1));
			JButton btn1 = new JButton(img);
			btn1.setText((String) list.get(0));
			btn1.setBounds(70, 50, 175, 150);
			btn1.setRolloverIcon(grayImageIcon(img));
			btn1.addActionListener(al);
			menuPnl.add(btn1, new Integer(3));
			util.invisible(btn1);

			String replaceString1 = (String) list.get(0);
			JLabel pizzaName1 = new JLabel();
			pizzaName1.setText(replaceString1.substring(3).replace('M', ' '));
			pizzaName1.setBounds(100, 200, 150, 30);
			pizzaName1.setForeground(new Color(103, 51, 53));
			pizzaName1.setFont(tftFont2);
			menuPnl.add(pizzaName1, new Integer(3));

		}

		if (list.size() > 3) {
			ImageIcon img = new ImageIcon((byte[]) list.get(3));
			JButton btn2 = new JButton(img);
			btn2.setText((String) list.get(2));
			btn2.setBounds(310, 50, 175, 150);
			btn2.setRolloverIcon(grayImageIcon(img));
			btn2.addActionListener(al);
			menuPnl.add(btn2, new Integer(3));
			util.invisible(btn2);

			String replaceString2 = (String) list.get(2);
			JLabel pizzaName2 = new JLabel();
			pizzaName2.setText(replaceString2.substring(3).replace('M', ' '));
			pizzaName2.setBounds(330, 200, 150, 30);
			pizzaName2.setForeground(new Color(103, 51, 53));
			pizzaName2.setFont(tftFont2);
			menuPnl.add(pizzaName2, new Integer(3));
		}

		if (list.size() > 5) {
			ImageIcon img = new ImageIcon((byte[]) list.get(5));
			JButton btn3 = new JButton(img);
			btn3.setText((String) list.get(4));
			btn3.setBounds(560, 50, 175, 150);
			btn3.setRolloverIcon(grayImageIcon(img));
			btn3.addActionListener(al);
			menuPnl.add(btn3, new Integer(3));
			util.invisible(btn3);

			String replaceString3 = (String) list.get(4);
			JLabel pizzaName3 = new JLabel();
			pizzaName3.setText(replaceString3.substring(3).replace('M', ' '));
			pizzaName3.setBounds(590, 200, 150, 30);
			pizzaName3.setForeground(new Color(103, 51, 53));
			pizzaName3.setFont(tftFont2);
			menuPnl.add(pizzaName3, new Integer(3));
		}

		if (list.size() > 7) {
			ImageIcon img = new ImageIcon((byte[]) list.get(7));
			JButton btn4 = new JButton(img);
			btn4.setText((String) list.get(6));
			btn4.setBounds(70, 240, 175, 150);
			btn4.setRolloverIcon(grayImageIcon(img));
			btn4.addActionListener(al);
			menuPnl.add(btn4, new Integer(3));
			util.invisible(btn4);

			String replaceString4 = (String) list.get(6);
			JLabel pizzaName4 = new JLabel();
			pizzaName4.setText(replaceString4.substring(3).replace('M', ' '));
			pizzaName4.setBounds(110, 390, 150, 30);
			pizzaName4.setForeground(new Color(103, 51, 53));
			pizzaName4.setFont(tftFont2);
			menuPnl.add(pizzaName4, new Integer(3));
		}

		if (list.size() > 9) {
			ImageIcon img = new ImageIcon((byte[]) list.get(9));
			JButton btn5 = new JButton(img);
			btn5.setText((String) list.get(8));
			btn5.setBounds(310, 240, 175, 150);
			btn5.setRolloverIcon(grayImageIcon(img));
			btn5.addActionListener(al);
			menuPnl.add(btn5, new Integer(3));
			util.invisible(btn5);

			String replaceString5 = (String) list.get(8);
			JLabel pizzaName5 = new JLabel();
			pizzaName5.setText(replaceString5.substring(3).replace('M', ' '));
			pizzaName5.setBounds(325, 390, 150, 30);
			pizzaName5.setForeground(new Color(103, 51, 53));
			pizzaName5.setFont(tftFont2);
			menuPnl.add(pizzaName5, new Integer(3));
		}

		if (list.size() > 11) {
			ImageIcon img = new ImageIcon((byte[]) list.get(11));
			JButton btn6 = new JButton(img);
			btn6.setText((String) list.get(10));
			btn6.setBounds(560, 240, 175, 150);
			btn6.setRolloverIcon(grayImageIcon(img));
			btn6.addActionListener(al);
			menuPnl.add(btn6, new Integer(3));
			util.invisible(btn6);

			String replaceString6 = (String) list.get(10);
			JLabel pizzaName6 = new JLabel();
			pizzaName6.setText(replaceString6.substring(3).replace('M', ' '));
			pizzaName6.setBounds(580, 390, 150, 30);
			pizzaName6.setForeground(new Color(103, 51, 53));
			pizzaName6.setFont(tftFont2);
			menuPnl.add(pizzaName6, new Integer(3));
		}

		JButton afterbtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("after.png")));
		afterbtn.setBounds(720, 190, 70, 80);
		afterbtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("afterRoll.png")));
		afterbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPnl.removeAll();
				menuPnl.repaint();
				pizzaTabBtn(target + 6);
			}
		});
		menuPnl.add(afterbtn, new Integer(3));
		util.invisible(afterbtn);

		JButton beforebtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("before.png")));
		beforebtn.setBounds(5, 190, 70, 80);
		beforebtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("beforeRoll.png")));
		beforebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (target > 0) {
					menuPnl.removeAll();
					menuPnl.repaint();
					pizzaTabBtn(target - 6);
				}
			}
		});
		menuPnl.add(beforebtn, new Integer(3));
		util.invisible(beforebtn);

	}

	// detailOrder List에 이미 있는지 확인하는 메소드
	private boolean findNameInDeoList(List<DetailOrder> deoList, String target) {
		for (DetailOrder deo : deoList) {
			if (deo.getMenu().equals(target)) {
				return true;
			}
		}
		return false;
	}

	// detailOrder List에 있는거 번호 가져오는 메소드
	private int findNumberInDeoList(List<DetailOrder> deoList, String target) {
		for (DetailOrder deo : deoList) {
			if (deo.getMenu().equals(target)) {
				return deo.getDetailOrderNumber();
			}
		}
		return 0;
	}

	private void sideTabBtn(int target) {
		Sql_Methods sqm = new Sql_Methods();
		List<Object> list = sqm.findImageAndMenuIdTarget("사이드%", target);

		// detailOrder에 넣어주는 동작 추가하기
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton target = (JButton) e.getSource();
				int price = Integer.parseInt(sqm.findPriceMenuId(target.getText()));
				List<DetailOrder> deoList1 = mo.getDeoList();

				underListPanel.removeAll();
				underListPanel.invalidate();
				// 리스트에 없을때
				if (!findNameInDeoList(deoList1, target.getText())) {
					DetailOrder deo2 = new DetailOrder(++detailOrderCount, target.getText(), 1, mo.getOrderNumber(),
							price);
					mo.getDeoList().add(deo2);
					if (deoList1.size() > 3) {
						if ((deoList1.size() - underListTarget + 3) % 3 == 1) {
							underListTarget += 3;
							underOrderList(mo, underListTarget);
							underListPanel.repaint();
						} else {
							underOrderList(mo, underListTarget);
						}
					} else {
						underOrderList(mo, 0);
					}
					underListPanel.repaint();

					// 똑같은거 두번 눌렀을때
					// 그 항목으로 이동해주는거?
					// e.getsource의
				} else {
					for (int i = 0; i < deoList1.size(); i++) {
						if (deoList1.get(i).getMenu().equals(target.getText())) {
							deoList1.get(i).setMenu_count(deoList1.get(i).getMenu_count() + 1);
							deoList1.get(i).setDetailOrderFullPrice(deoList1.get(i).getDetailOrderFullPrice() + price);

							int targetNumber = findNumberInDeoList(deoList1, target.getText());
							underListTarget = ((targetNumber - detailOrderCount + mo.getDeoList().size() - 1) / 3) * 3;
							underOrderList(mo, underListTarget);

							underListPanel.repaint();
						}
					}
				}

				int mo_total_price = final_total_price(mo);
				total_priceLabel.setText(String.valueOf(mo_total_price) + "원");

				System.out.println("사이드 버튼들 누를때" + mo.getDeoList().toString());
			}
		};

		if (list.size() > 0) {
			ImageIcon img = new ImageIcon((byte[]) list.get(1));
			JButton btn1 = new JButton(img);
			btn1.setBounds(70, 50, 175, 150);
			btn1.setText((String) list.get(0));
			btn1.setRolloverIcon(grayImageIcon(img));
			btn1.addActionListener(al);
			menuPnl.add(btn1, new Integer(3));
			util.invisible(btn1);

			String replaceString1 = (String) list.get(0);
			JLabel sideName1 = new JLabel();
			sideName1.setText(replaceString1.substring(4));
			sideName1.setBounds(100, 200, 150, 30);
			sideName1.setForeground(new Color(103, 51, 53));
			sideName1.setFont(tftFont2);
			menuPnl.add(sideName1, new Integer(3));
		}
		if (list.size() > 3) {
			ImageIcon img = new ImageIcon((byte[]) list.get(3));
			JButton btn2 = new JButton(img);
			btn2.setBounds(310, 50, 175, 150);
			btn2.setText((String) list.get(2));
			btn2.setRolloverIcon(grayImageIcon(img));
			btn2.addActionListener(al);
			menuPnl.add(btn2, new Integer(3));
			util.invisible(btn2);

			String replaceString2 = (String) list.get(2);
			JLabel sideName2 = new JLabel();
			sideName2.setText(replaceString2.substring(4));
			sideName2.setBounds(330, 200, 150, 30);
			sideName2.setForeground(new Color(103, 51, 53));
			sideName2.setFont(tftFont2);
			menuPnl.add(sideName2, new Integer(3));
		}
		if (list.size() > 5) {
			ImageIcon img = new ImageIcon((byte[]) list.get(5));
			JButton btn3 = new JButton(img);
			btn3.setBounds(560, 50, 175, 150);
			btn3.setText((String) list.get(4));
			btn3.setRolloverIcon(grayImageIcon(img));
			btn3.addActionListener(al);
			menuPnl.add(btn3, new Integer(3));
			util.invisible(btn3);

			String replaceString3 = (String) list.get(4);
			JLabel sideName3 = new JLabel();
			sideName3.setText(replaceString3.substring(4));
			sideName3.setBounds(590, 200, 150, 30);
			sideName3.setForeground(new Color(103, 51, 53));
			sideName3.setFont(tftFont2);
			menuPnl.add(sideName3, new Integer(3));
		}
		if (list.size() > 7) {
			ImageIcon img = new ImageIcon((byte[]) list.get(7));
			JButton btn4 = new JButton(img);
			btn4.setBounds(70, 240, 175, 150);
			btn4.setText((String) list.get(6));
			btn4.setRolloverIcon(grayImageIcon(img));
			btn4.addActionListener(al);
			menuPnl.add(btn4, new Integer(3));
			util.invisible(btn4);

			String replaceString4 = (String) list.get(6);
			JLabel sideName4 = new JLabel();
			sideName4.setText(replaceString4.substring(4));
			sideName4.setBounds(130, 390, 150, 30);
			sideName4.setForeground(new Color(103, 51, 53));
			sideName4.setFont(tftFont2);
			menuPnl.add(sideName4, new Integer(3));
		}
		if (list.size() > 9) {
			ImageIcon img = new ImageIcon((byte[]) list.get(9));
			JButton btn5 = new JButton(img);
			btn5.setBounds(310, 240, 175, 150);
			btn5.setText((String) list.get(8));
			btn5.setRolloverIcon(grayImageIcon(img));
			btn5.addActionListener(al);
			menuPnl.add(btn5, new Integer(3));
			util.invisible(btn5);

			String replaceString5 = (String) list.get(8);
			JLabel sideName5 = new JLabel();
			sideName5.setText(replaceString5.substring(4));
			sideName5.setBounds(340, 390, 150, 30);
			sideName5.setForeground(new Color(103, 51, 53));
			sideName5.setFont(tftFont2);
			menuPnl.add(sideName5, new Integer(3));
		}

		if (list.size() > 11) {
			ImageIcon img = new ImageIcon((byte[]) list.get(11));
			JButton btn6 = new JButton(img);
			btn6.setBounds(560, 240, 175, 150);
			btn6.setText((String) list.get(10));
			btn6.setRolloverIcon(grayImageIcon(img));
			btn6.addActionListener(al);
			menuPnl.add(btn6, new Integer(3));
			util.invisible(btn6);

			String replaceString6 = (String) list.get(10);
			JLabel sideName6 = new JLabel();
			sideName6.setText(replaceString6.substring(4));
			sideName6.setBounds(620, 390, 150, 30);
			sideName6.setForeground(new Color(103, 51, 53));
			sideName6.setFont(tftFont2);
			menuPnl.add(sideName6, new Integer(3));
		}

		JButton afterbtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("after.png")));
		afterbtn.setBounds(720, 190, 70, 80);
		afterbtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("afterRoll.png")));
		afterbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPnl.removeAll();
				menuPnl.repaint();
				sideTabBtn(target + 6);
			}
		});
		menuPnl.add(afterbtn, new Integer(3));
		util.invisible(afterbtn);

		JButton beforebtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("before.png")));
		beforebtn.setBounds(5, 190, 70, 80);
		beforebtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("beforeRoll.png")));
		beforebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (target > 0) {
					menuPnl.removeAll();
					menuPnl.repaint();
					sideTabBtn(target - 6);
				}
			}
		});
		menuPnl.add(beforebtn, new Integer(3));
		util.invisible(beforebtn);
	}

	public int getUnderListTarget() {
		return underListTarget;
	}

	public void setUnderListTarget(int underListTarget) {
		this.underListTarget = underListTarget;
	}

	private void drinkTabBtn(int target) {
		Sql_Methods sqm = new Sql_Methods();
		List<Object> list = sqm.findImageAndMenuIdTarget("음료%", target);

		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton target = (JButton) e.getSource();
				// menu_id로 price 찾아서 detailorder 만들때 생성자에 넣어주기
				int price = Integer.parseInt(sqm.findPriceMenuId(target.getText()));

				// 여기서 만약 이미 같은 이름을 가진 애가 존재하면 if문을 써서 수량이랑 가격만 플러스 해주기
				List<DetailOrder> deoList1 = mo.getDeoList();
				underListPanel.removeAll();
				underListPanel.invalidate();
				// 리스트에 없을때
				if (!findNameInDeoList(deoList1, target.getText())) {
					DetailOrder deo2 = new DetailOrder(++detailOrderCount, target.getText(), 1, mo.getOrderNumber(),
							price);
					mo.getDeoList().add(deo2);
					if (deoList1.size() > 3) {
						if ((deoList1.size() - underListTarget + 3) % 3 == 1) {
							underListTarget += 3;
							underOrderList(mo, underListTarget);
							underListPanel.repaint();
						} else {
							underOrderList(mo, underListTarget);
						}
					} else {
						underOrderList(mo, 0);
					}
					underListPanel.repaint();

					// 똑같은거 두번 눌렀을때
					// 그 항목으로 이동해주는거?
				} else {
					for (int i = 0; i < deoList1.size(); i++) {
						if (deoList1.get(i).getMenu().equals(target.getText())) {
							deoList1.get(i).setMenu_count(deoList1.get(i).getMenu_count() + 1);
							deoList1.get(i).setDetailOrderFullPrice(deoList1.get(i).getDetailOrderFullPrice() + price);
							int targetNumber = findNumberInDeoList(deoList1, target.getText());
							underListTarget = ((targetNumber - detailOrderCount + mo.getDeoList().size() - 1) / 3) * 3;
							underOrderList(mo, underListTarget);
							underListPanel.repaint();
						}
					}
				}

				// menuFrame 총금액 갱신
				int mo_total_price = final_total_price(mo);
				total_priceLabel.setText(String.valueOf(mo_total_price) + "원");

				// 하단 리스트 리페인팅
				underListPanel.removeAll();
				underListPanel.invalidate();
				underOrderList(mo, underListTarget);
				underListPanel.repaint();
			}
		};

		if (list.size() > 0) {
			ImageIcon img = new ImageIcon((byte[]) list.get(1));
			JButton btn1 = new JButton(img);
			btn1.setBounds(75, 50, 175, 150);
			btn1.setText((String) list.get(0));
			btn1.setRolloverIcon(grayImageIcon(img));
			btn1.addActionListener(al);
			menuPnl.add(btn1, new Integer(3));
			util.invisible(btn1);

			String replaceString1 = (String) list.get(0);
			JLabel sideName1 = new JLabel();
			sideName1.setText(replaceString1.substring(3));
			sideName1.setBounds(120, 200, 150, 30);
			sideName1.setForeground(new Color(103, 51, 53));
			sideName1.setFont(tftFont2);
			menuPnl.add(sideName1, new Integer(3));
		}

		if (list.size() > 3) {
			ImageIcon img = new ImageIcon((byte[]) list.get(3));
			JButton btn2 = new JButton(img);
			btn2.setBounds(310, 50, 175, 150);
			btn2.setText((String) list.get(2));
			btn2.setRolloverIcon(grayImageIcon(img));
			btn2.addActionListener(al);
			menuPnl.add(btn2, new Integer(3));
			util.invisible(btn2);

			String replaceString2 = (String) list.get(2);
			JLabel sideName2 = new JLabel();
			sideName2.setText(replaceString2.substring(3));
			sideName2.setBounds(330, 200, 150, 30);
			sideName2.setForeground(new Color(103, 51, 53));
			sideName2.setFont(tftFont2);
			menuPnl.add(sideName2, new Integer(3));
		}

		if (list.size() > 5) {
			ImageIcon img = new ImageIcon((byte[]) list.get(5));
			JButton btn3 = new JButton(img);
			btn3.setBounds(560, 50, 175, 150);
			btn3.setText((String) list.get(4));
			btn3.setRolloverIcon(grayImageIcon(img));
			btn3.addActionListener(al);
			menuPnl.add(btn3, new Integer(3));
			util.invisible(btn3);

			String replaceString3 = (String) list.get(4);
			JLabel sideName3 = new JLabel();
			sideName3.setText(replaceString3.substring(3));
			sideName3.setBounds(610, 200, 150, 30);
			sideName3.setForeground(new Color(103, 51, 53));
			sideName3.setFont(tftFont2);
			menuPnl.add(sideName3, new Integer(3));
		}

		if (list.size() > 7) {
			ImageIcon img = new ImageIcon((byte[]) list.get(7));
			JButton btn4 = new JButton(img);
			btn4.setBounds(75, 240, 175, 150);
			btn4.setText((String) list.get(6));
			btn4.setRolloverIcon(grayImageIcon(img));
			btn4.addActionListener(al);
			menuPnl.add(btn4, new Integer(3));
			util.invisible(btn4);

			String replaceString4 = (String) list.get(6);
			JLabel sideName4 = new JLabel();
			sideName4.setText(replaceString4.substring(3));
			sideName4.setBounds(110, 390, 150, 30);
			sideName4.setForeground(new Color(103, 51, 53));
			sideName4.setFont(tftFont2);
			menuPnl.add(sideName4, new Integer(3));
		}

		if (list.size() > 9) {
			ImageIcon img = new ImageIcon((byte[]) list.get(9));
			JButton btn5 = new JButton(img);
			btn5.setBounds(310, 240, 175, 150);
			btn5.setText((String) list.get(8));
			btn5.setRolloverIcon(grayImageIcon(img));
			btn5.addActionListener(al);
			menuPnl.add(btn5, new Integer(3));
			util.invisible(btn5);

			String replaceString5 = (String) list.get(8);
			JLabel sideName5 = new JLabel();
			sideName5.setText(replaceString5.substring(3));
			sideName5.setBounds(360, 390, 150, 30);
			sideName5.setForeground(new Color(103, 51, 53));
			sideName5.setFont(tftFont2);
			menuPnl.add(sideName5, new Integer(3));
		}

		if (list.size() > 11) {
			ImageIcon img = new ImageIcon((byte[]) list.get(11));
			JButton btn6 = new JButton(img);
			btn6.setBounds(560, 240, 175, 150);
			btn6.setText((String) list.get(10));
			btn6.setRolloverIcon(grayImageIcon(img));
			btn6.addActionListener(al);
			menuPnl.add(btn6, new Integer(3));
			util.invisible(btn6);

			String replaceString6 = (String) list.get(10);
			JLabel sideName6 = new JLabel();
			sideName6.setText(replaceString6.substring(3));
			sideName6.setBounds(600, 390, 150, 30);
			sideName6.setForeground(new Color(103, 51, 53));
			sideName6.setFont(tftFont2);
			menuPnl.add(sideName6, new Integer(3));
		}

		JButton afterbtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("after.png")));
		afterbtn.setBounds(720, 190, 70, 80);
		afterbtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("afterRoll.png")));
		afterbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPnl.removeAll();
				menuPnl.repaint();
				drinkTabBtn(target + 6);
			}
		});
		menuPnl.add(afterbtn, new Integer(3));
		util.invisible(afterbtn);

		JButton beforebtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("before.png")));
		beforebtn.setBounds(5, 190, 70, 80);
		beforebtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("beforeRoll.png")));
		beforebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (target > 0) {
					menuPnl.removeAll();
					menuPnl.repaint();
					drinkTabBtn(target - 6);
				}
			}
		});
		menuPnl.add(beforebtn, new Integer(3));
		util.invisible(beforebtn);
	}

	// 현재 총 주문가격을 구하는 메소드
	public static int final_total_price(MainOrder mo) {
		int full_price = 0;
		for (DetailOrder deo : mo.getDeoList()) {
			full_price += deo.getDetailOrderFullPrice();
		}
		mo.setTotal_Price(full_price);
		return full_price;
	}

	private void exiteKey() {
		jlp.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int exite = e.getKeyCode();
				System.out.println("종료");
				if (exite == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
	}

}
