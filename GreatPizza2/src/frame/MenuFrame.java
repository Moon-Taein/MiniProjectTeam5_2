package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import function.DetailOrder;
import function.MainOrder;
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
	static int detailOrderCount = sqm.findDetailOrderCount(); // 피자 - 담기누를때 ++ 사이드,음료 - 담을때마다 ++
	private JLayeredPane menuPnl;
	private MainOrder mo;
	private DetailOrder deo;
	// parameter로 크기값 받고 return으로 해당크기의 tftfont 해주는
	private Font tftFont = getBMJUAFont(18f);
	private Font tftFont2 = getBMJUAFont(25f);
	private Font tftFont3 = getBMJUAFont(35f);

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
		jlp.add(orderBtn, new Integer(3));
		orderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 이때 mainOrder db에 기록하기
				orderComplete newFrame = new orderComplete(mo, MenuFrame.this);
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
		
		
		

	}

	// 추가 메뉴가 있어서 다음 버튼 누르면 6,6 12,6 으로
	private void pizzaTabBtn(int target) {
		//target 기준으로 +6 after
		//target 기준으로 -6 before

		List<Object> list = sqm.findImageAndMenuIdTarget("%M", target);

		// 버튼 눌렀을시 동작하는 메소드
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton a = (JButton) e.getSource();
				String target = a.getText();
				Pizza_PopUp_Frame ppf = new Pizza_PopUp_Frame(target, mo, detailOrderCount);
				ppf.setVisible(true);
			}
		};
		
		// image랑 이름 db에서 불러오게 만들기
		ImageIcon img = new ImageIcon((byte[]) list.get(1));
		JButton btn1 = new JButton(img);
//		Image img2 = img.getImage();
//		Image changedImg = img2.getScaledInstance(210, 160, java.awt.Image.SCALE_SMOOTH);
//		ImageIcon changedImageIcon = new ImageIcon(changedImg);
//		btn1.setRolloverIcon(changedImageIcon);
		btn1.setText((String) list.get(0));
		btn1.setBounds(70, 50, 175, 150);
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

		JButton btn2 = new JButton(new ImageIcon((byte[]) list.get(3)));
		btn2.setText((String) list.get(2));
		btn2.setBounds(310, 50, 175, 150);
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

		JButton btn3 = new JButton(new ImageIcon((byte[]) list.get(5)));
		btn3.setText((String) list.get(4));
		btn3.setBounds(560, 50, 175, 150);
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

		JButton btn4 = new JButton(new ImageIcon((byte[]) list.get(7)));
		btn4.setText((String) list.get(6));
		btn4.setBounds(70, 240, 175, 150);
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

		JButton btn5 = new JButton(new ImageIcon((byte[]) list.get(9)));
		btn5.setText((String) list.get(8));
		btn5.setBounds(310, 240, 175, 150);
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

		JButton btn6 = new JButton(new ImageIcon((byte[]) list.get(11)));
		btn6.setText((String) list.get(10));
		btn6.setBounds(560, 240, 175, 150);
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
		
		JButton afterbtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("after.png")));
		afterbtn.setBounds(720, 190, 70, 80);
		afterbtn.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("afterRoll.png")));
		afterbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPnl.removeAll();
				menuPnl.repaint();
				pizzaTabBtn(target+6);
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
				if(target > 0) {
					menuPnl.removeAll();
					menuPnl.repaint();
					pizzaTabBtn(target-6);
				}
			}
		});
		menuPnl.add(beforebtn, new Integer(3));
		util.invisible(beforebtn);

	}

	private void sideTabBtn(int target) {
		Sql_Methods sqm = new Sql_Methods();
		List<Object> list = sqm.findImageAndMenuIdTarget("사이드%", target);

		// detailOrder에 넣어주는 동작 추가하기
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton target = (JButton) e.getSource();
				detailOrderCount++;
				DetailOrder deo = new DetailOrder(detailOrderCount, target.getText(), 1, mo.getOrderNumber());
				mo.getDeoList().add(deo);
			}
		};

		JButton btn1 = new JButton(new ImageIcon((byte[]) list.get(1)));
		btn1.setBounds(70, 50, 180, 150);
		btn1.setText((String) list.get(0));
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

		JButton btn2 = new JButton(new ImageIcon((byte[]) list.get(3)));
		btn2.setBounds(310, 50, 180, 150);
		btn2.setText((String) list.get(2));
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

		JButton btn3 = new JButton(new ImageIcon((byte[]) list.get(5)));
		btn3.setBounds(560, 50, 180, 150);
		btn3.setText((String) list.get(4));
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

		JButton btn4 = new JButton(new ImageIcon((byte[]) list.get(7)));
		btn4.setBounds(70, 240, 180, 150);
		btn4.setText((String) list.get(6));
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

		JButton btn5 = new JButton(new ImageIcon((byte[]) list.get(9)));
		btn5.setBounds(310, 240, 180, 150);
		btn5.setText((String) list.get(8));
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

		JButton btn6 = new JButton(new ImageIcon((byte[]) list.get(11)));
		btn6.setBounds(560, 240, 180, 150);
		btn6.setText((String) list.get(10));
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

	private void drinkTabBtn(int target) {
		Sql_Methods sqm = new Sql_Methods();
		List<Object> list = sqm.findImageAndMenuIdTarget("음료%", target);

		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton target = (JButton) e.getSource();
				detailOrderCount++;
				DetailOrder deo = new DetailOrder(detailOrderCount, target.getText(), 1, mo.getOrderNumber());
				mo.getDeoList().add(deo);
			}
		};

		JButton btn1 = new JButton(new ImageIcon((byte[]) list.get(1)));
		btn1.setBounds(75, 50, 180, 150);
		btn1.setText((String) list.get(0));
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

		JButton btn2 = new JButton(new ImageIcon((byte[]) list.get(3)));
		btn2.setBounds(310, 50, 180, 150);
		btn2.setText((String) list.get(2));
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

		JButton btn3 = new JButton(new ImageIcon((byte[]) list.get(5)));
		btn3.setBounds(560, 50, 180, 150);
		btn3.setText((String) list.get(4));
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

		JButton btn4 = new JButton(new ImageIcon((byte[]) list.get(7)));
		btn4.setBounds(75, 240, 180, 150);
		btn4.setText((String) list.get(6));
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

		JButton btn5 = new JButton(new ImageIcon((byte[]) list.get(9)));
		btn5.setBounds(310, 240, 180, 150);
		btn5.setText((String) list.get(8));
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

		JButton btn6 = new JButton(new ImageIcon((byte[]) list.get(11)));
		btn6.setBounds(560, 240, 180, 150);
		btn6.setText((String) list.get(10));
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

	public int final_total_price(MainOrder mo) {
		int full_price = 0;
		for (DetailOrder deo : mo.getDeoList()) {
			full_price += deo.getDetailOrderFullPrice();
		}
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