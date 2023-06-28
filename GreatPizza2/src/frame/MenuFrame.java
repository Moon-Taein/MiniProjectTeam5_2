package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Function.MainOrder;
import Function.Sql_Methods;
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
	private int mainOrderCount = 0; // order 누르면 ++ 되게
	private int detailOrderCount = 0; // 피자 - 담기누를때 ++ 사이드,음료 - 담을때마다 ++
	private JLayeredPane menuPnl;
	private MainOrder mo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
					frame.setVisible(true);
					frame.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuFrame() {

		FrameSetting();

		menuPnl = new JLayeredPane();
		menuPnl.setBounds(0, 196, 800, 469);
		jlp.add(menuPnl, new Integer(3));

		pizzaTabBtn();

		buttonSetting();// 버튼 생성 메소드

		pizzaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 탭 전환시 지우고 다시 메뉴패널 위 버튼 생성
				menuPnl.removeAll();
				menuPnl.repaint();
				pizzaTabBtn();

				pizzaBtn.setIcon(icon.getwhitePizzaBtn());

				sideBtn.setIcon(icon.getdarkSideBtn());
				drinkBtn.setIcon(icon.getdarkDrinkBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());

			}
		});

		sideBtn.addActionListener(new ActionListener() {
			// 사이드버튼 액션리스너
			@Override
			public void actionPerformed(ActionEvent e) {

				menuPnl.removeAll();
				menuPnl.repaint();
				sideTabBtn();

				sideBtn.setIcon(icon.getwhiteSideBtn());
				// 버튼을 눌렀을 때 해당버튼 외 버튼들을 회색으로 바꿈
				pizzaBtn.setIcon(icon.getdarkPizzaBtn());
				drinkBtn.setIcon(icon.getdarkDrinkBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());
				menuPnl.repaint();

			}
		});

		drinkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuPnl.removeAll();
				menuPnl.repaint();
				drinkTabBtn();

				drinkBtn.setIcon(icon.getwhiteDrinkBtn());

				pizzaBtn.setIcon(icon.getdarkPizzaBtn());
				sideBtn.setIcon(icon.getdarkSideBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());

			}
		});

		makePizzaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				menuPnl.removeAll();
				menuPnl.repaint();
				MakePizzaTabBtn();

				pizzaBtn.setIcon(icon.getdarkPizzaBtn());
				sideBtn.setIcon(icon.getdarkSideBtn());
				drinkBtn.setIcon(icon.getdarkDrinkBtn());

				makePizzaBtn.setIcon(icon.getwhiteMakePizzaBtn());
			}
		});

		util.invisible(pizzaBtn);
		util.invisible(sideBtn);
		util.invisible(drinkBtn);
		util.invisible(makePizzaBtn);

	}

	private void FrameSetting() {
		JLabel lbl = new JLabel(icon.getMenuFrame());
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

	private void buttonSetting() {

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
		util.invisible(orderBtn);

	}

	private void pizzaTabBtn() {
		Sql_Methods sqm = new Sql_Methods();

		// 버튼 눌렀을시 동작하는 메소드 (사이드, 음료용으로 바뀔듯 )
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton a = (JButton) e.getSource();
				String target = a.getText();
				Pizza_PopUp_Frame ppf = new Pizza_PopUp_Frame(target, mo);
				ppf.setVisible(true);
			}
		};

		JButton btn1 = new JButton(icon.getSmallBullgogiPizza());
		btn1.setText("불고기피자");
		btn1.setBounds(60, 70, 175, 150);
		btn1.addActionListener(al);
		menuPnl.add(btn1, new Integer(3));
		util.invisible(btn1);

		JButton btn2 = new JButton(icon.getSmallPepperoniPizza());
		btn2.setText("페퍼로니피자");
		btn2.setBounds(300, 70, 175, 150);
		btn2.addActionListener(al);
		menuPnl.add(btn2, new Integer(3));
		util.invisible(btn2);

		JButton btn3 = new JButton(icon.getSmallShrimpPizza());
		btn3.setText("쉬림프피자");
		btn3.setBounds(550, 70, 175, 150);
		btn3.addActionListener(al);
		menuPnl.add(btn3, new Integer(3));
		util.invisible(btn3);

		JButton btn4 = new JButton(icon.getSmallCheesePizza());
		btn4.setText("치즈피자");
		btn4.setBounds(60, 260, 175, 150);
		btn4.addActionListener(al);
		menuPnl.add(btn4, new Integer(3));
		util.invisible(btn4);

		JButton btn5 = new JButton(icon.getSmallCombinationPizza());
		btn5.setText("콤비네이션피자");
		btn5.setBounds(300, 260, 175, 150);
		btn5.addActionListener(al);
		menuPnl.add(btn5, new Integer(3));
		util.invisible(btn5);

		JButton btn6 = new JButton(icon.getSmallPotatoPizza());
		btn6.setText("포테이토피자");
		btn6.setBounds(550, 260, 175, 150);
		btn6.addActionListener(al);
		menuPnl.add(btn6, new Integer(3));
		util.invisible(btn6);

	}

	private void sideTabBtn() {
		JButton btn1 = new JButton(icon.getCoke1());
		btn1.setBounds(60, 70, 180, 150);
		menuPnl.add(btn1, new Integer(3));
		util.invisible(btn1);

		JButton btn2 = new JButton(icon.getCider1());
		btn2.setBounds(300, 70, 180, 150);
		menuPnl.add(btn2, new Integer(3));
		util.invisible(btn2);

		JButton btn3 = new JButton(icon.getFanta1());
		btn3.setBounds(550, 70, 180, 150);
		menuPnl.add(btn3, new Integer(3));
		util.invisible(btn3);

		JButton btn4 = new JButton(icon.getCoke2());
		btn4.setBounds(60, 260, 180, 150);
		menuPnl.add(btn4, new Integer(3));
		util.invisible(btn4);

		JButton btn5 = new JButton(icon.getCider2());
		btn5.setBounds(300, 260, 180, 150);
		menuPnl.add(btn5, new Integer(3));
		util.invisible(btn5);

		JButton btn6 = new JButton(icon.getFanta2());
		btn6.setBounds(550, 260, 180, 150);
		menuPnl.add(btn6, new Integer(3));
		util.invisible(btn6);
	}

	private void drinkTabBtn() {
		JButton btn1 = new JButton(icon.getCoke1());
		btn1.setBounds(60, 70, 180, 150);
		menuPnl.add(btn1, new Integer(3));
		util.invisible(btn1);

		JButton btn2 = new JButton(icon.getCider1());
		btn2.setBounds(300, 70, 180, 150);
		menuPnl.add(btn2, new Integer(3));
		util.invisible(btn2);

		JButton btn3 = new JButton(icon.getFanta1());
		btn3.setBounds(550, 70, 180, 150);
		menuPnl.add(btn3, new Integer(3));
		util.invisible(btn3);

		JButton btn4 = new JButton(icon.getCoke2());
		btn4.setBounds(60, 260, 180, 150);
		menuPnl.add(btn4, new Integer(3));
		util.invisible(btn4);

		JButton btn5 = new JButton(icon.getCider2());
		btn5.setBounds(300, 260, 180, 150);
		menuPnl.add(btn5, new Integer(3));
		util.invisible(btn5);

		JButton btn6 = new JButton(icon.getFanta2());
		btn6.setBounds(550, 260, 180, 150);
		menuPnl.add(btn6, new Integer(3));
		util.invisible(btn6);
	}

	private void MakePizzaTabBtn() {

		JButton btn1 = new JButton(icon.getCoke1());
		btn1.setBounds(60, 70, 180, 150);
		menuPnl.add(btn1, new Integer(3));
		util.invisible(btn1);

		JButton btn2 = new JButton(icon.getCider1());
		btn2.setBounds(300, 70, 180, 150);
		menuPnl.add(btn2, new Integer(3));
		util.invisible(btn2);

		JButton btn3 = new JButton(icon.getFanta1());
		btn3.setBounds(550, 70, 180, 150);
		menuPnl.add(btn3, new Integer(3));
		util.invisible(btn3);

		JButton btn4 = new JButton(icon.getCoke2());
		btn4.setBounds(60, 260, 180, 150);
		menuPnl.add(btn4, new Integer(3));
		util.invisible(btn4);

		JButton btn5 = new JButton(icon.getCider2());
		btn5.setBounds(300, 260, 180, 150);
		menuPnl.add(btn5, new Integer(3));
		util.invisible(btn5);

		JButton btn6 = new JButton(icon.getFanta2());
		btn6.setBounds(550, 260, 180, 150);
		menuPnl.add(btn6, new Integer(3));
		util.invisible(btn6);

	}

	private void exiteKey() {
		addKeyListener(new KeyAdapter() {

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