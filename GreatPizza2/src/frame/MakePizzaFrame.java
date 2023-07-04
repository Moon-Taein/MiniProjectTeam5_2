package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	private JLabel edgeLbl;
	private JLabel currentEdge;
	private ImageIcon edgeIcon;
	private ImagePanel2 topingPnl;

	int toppingTarget = 0;

//	int currentPage;
//	int startIndex;
//	int endIndex;
//	int count;
//	int x;
//	int y;

	private int toppingOnAndOn;
	private HashMap<JButton, ArrayList<JLabel>> toppingMap = new HashMap<>(); // 토핑 이미지를 저장할 맵

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
		showTopping(toppingTarget);
		afterBeforeBtnSetting();
	}

	private void frameSetting() {
		jlp = new JLayeredPane();
		JLabel lbl = new JLabel(icon.makePizzaFrame());
		lbl.setBounds(0, 0, 800, 900);

		topingPnl = new ImagePanel2(icon.getMakePizzaToppingFrame().getImage());
		topingPnl.setBounds(415, 65, 360, 480);
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
		int x = 411;
		int y = 614;
		int horizontalGap = 78;

		HashMap<String, byte[]> sourceArr = sql.getOnSource("소스");

		for (Map.Entry<String, byte[]> entry : sourceArr.entrySet()) {
			String sourceName = entry.getKey();
			byte[] imageData = entry.getValue();// 정렬한 key(소스이름)과 같은 이미지 저장
			ImageIcon icon = new ImageIcon(imageData);

			JLabel sourceBtn = new JLabel(icon);
			// 왼쪽에서 오른쪽으로 버튼나열
			sourceBtn.setBounds(x, y, 52, 100);
			sourceBtn.setText(sourceName);
			sourceBtn.setFont(new Font("굴림", Font.PLAIN, 1));

			sourceBtn.setVerticalTextPosition(SwingConstants.BOTTOM); // 텍스트를 가운데에 정렬
			sourceBtn.setHorizontalTextPosition(SwingConstants.CENTER);

			jlp.add(sourceBtn, new Integer(3));
			util.invisible(sourceBtn);

			x += horizontalGap;

			sourceBtn.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {

					JLabel selectedSourceBtn = (JLabel) e.getSource();

					String str = selectedSourceBtn.getText();

					HashMap<String, byte[]> onSourceMap = sql.getOnSourceImg("소스");
					System.out.println(onSourceMap.toString());
					byte[] imgArr = onSourceMap.get(str);
					ImageIcon onSourceIcon = new ImageIcon(imgArr);

					JLabel sourceLbl = new JLabel(onSourceIcon);
					sourceLbl.setBounds(-10, 108, 410, 529);
					jlp.add(sourceLbl, new Integer(1));

				}
			});
		}

//		JButton btn1 = new JButton(icon.getBulldak());
//		JButton btn2 = new JButton(icon.getHotSoy());
//		JButton btn3 = new JButton(icon.getSoy());
//		JButton btn4 = new JButton(icon.getCream());
//		JButton btn5 = new JButton(icon.getTomato());
//
//		ActionListener actionSource = new ActionListener() {
//			int count = 0;
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JButton selectBtn = (JButton) e.getSource();
//				if (count == 0) {
//					if (selectBtn == btn1) {
//						System.out.println("버튼1진입 " + count);
//					}
//				}
//
//			}
//		};
//
//		util.invisible(btn1);
//		util.invisible(btn2);
//		util.invisible(btn3);
//		util.invisible(btn4);
//		util.invisible(btn5);
//
//		btn1.setBounds(723, 614, 52, 98);
//		jlp.add(btn1, new Integer(1));
//		btn2.setBounds(645, 614, 52, 98);
//		jlp.add(btn2, new Integer(1));
//		btn3.setBounds(567, 614, 52, 98);
//		jlp.add(btn3, new Integer(1));
//		btn4.setBounds(489, 614, 52, 98);
//		jlp.add(btn4, new Integer(1));
//		btn5.setBounds(411, 614, 52, 98);
//		jlp.add(btn5, new Integer(1));

		// 취소,담기 버튼
		// 취소있으니까 뒤로가기 없어도될듯
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
		leftBtn.setBounds(0, 7, 50, 50);
		edgePnl.add(leftBtn, new Integer(2));
		util.invisible(leftBtn);
		leftBtn.setRolloverIcon(icon.getLeftBright());

		JButton rightBtn = new JButton(icon.getRight());
		rightBtn.setBounds(250, 7, 50, 50);
		edgePnl.add(rightBtn, new Integer(2));
		util.invisible(rightBtn);
		rightBtn.setRolloverIcon(icon.getRightBright());

		edgeLbl = new JLabel("기본");
		edgeLbl.setBounds(118, 5, 65, 50);
		edgeLbl.setForeground(new Color(138, 88, 89));
		edgeLbl.setVerticalAlignment(SwingConstants.CENTER);
		edgeLbl.setFont(new Font("함초롬바탕", Font.BOLD, 31));
		edgePnl.add(edgeLbl, new Integer(2));

		currentEdge = new JLabel(edgeIcon);
		currentEdge.setBounds(-10, 108, 410, 529);
		jlp.add(currentEdge, new Integer(6));

		rightBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				countEdge++;

				if (countEdge > 3) {
					countEdge = 0;

				}
				updateEdge();

			}

		});

		leftBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				countEdge--;
				if (countEdge < 0) {
					countEdge = 3;
				}
				updateEdge();

			}
		});
		getEdge();

	}

	private void getEdge() {
		byte[] doughArr = edgeMap.get(edgeLbl.getText());
		if (doughArr != null) {
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

	// 토핑리스트 좌우이동 버튼
	private void afterBeforeBtnSetting() {

		JButton afterButton = new JButton(icon.getMiniAfter());
		afterButton.setBounds(650, 550, 50, 50);
		afterButton.setRolloverIcon(icon.getMiniAfterRoll());
		util.invisible(afterButton);
		afterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				topingPnl.removeAll();
				topingPnl.repaint();
				setToppingTarget(getToppingTarget() + 6);
				showTopping(getToppingTarget());
			}
		});
		jlp.add(afterButton, new Integer(3));

		JButton beforeBtn = new JButton(icon.getMiniBefore());
		beforeBtn.setBounds(500, 550, 50, 50);
		beforeBtn.setRolloverIcon(icon.getMiniBeforeRoll());
		util.invisible(beforeBtn);
		beforeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getToppingTarget() > 0) {
					topingPnl.removeAll();
					topingPnl.repaint();
					setToppingTarget(getToppingTarget() - 6);
					showTopping(getToppingTarget());
				}

			}
		});
		jlp.add(beforeBtn, new Integer(3));

	}

	private void showTopping(int toppingTarget) {
		List<String> topingNames = sql.pizzamakeSetToping("토핑");
		HashMap<String, byte[]> topingArr = sql.getTopingImgInBox("토핑");

		int itemsPerPage = 6; // 한 페이지에 보여줄 이미지 수

		int currentPage = 3; // 현재 페이지
		int startIndex = (currentPage - 1) * itemsPerPage; // 시작 인덱스
		int endIndex = Math.min(startIndex + itemsPerPage, topingArr.size()); // 종료 인덱스

		int x = 23;
		int y = 17;
		int width = 135;
		int height = 100;
		int horizontalGap = 60;
		int verticalGap = 66;

		int count = 0;

		// 조건문 수정해서 size 까지만 나올수 있도록
		for (int i = 0; i < 6; i++) {

			String topingName = topingNames.get(toppingTarget);
			toppingTarget++;
			byte[] imageData = topingArr.get(topingName);
			ImageIcon icon = new ImageIcon(imageData);

			// 눌러서 상호작용 있으니 버튼으로 바꾸기
			// 누를때마다 만들고있는 피자에 이미지 겹쳐주고
			// menuitem 생성해서
			// 나만의피자 detailorder에 넣어주기

			JButton toppingBtn = new JButton(icon);
			toppingBtn.setBounds(x, y, width, height);
			toppingBtn.setText(topingName);

			topingPnl.add(toppingBtn, new Integer(2));
			util.invisible(toppingBtn);

			x += width + horizontalGap;

			if (count == 6) {
				break;
			}

			count++;

			if (count % 2 == 0) {
				x = 23;
				y += height + verticalGap;
			}

			toppingBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn1 = (JButton) e.getSource();
					ArrayList<JLabel> toppingList = toppingMap.get(btn1);
					if (toppingList == null) {
						toppingList = new ArrayList<>();
						toppingMap.put(btn1, toppingList);

					}

					String str = btn1.getText();
					System.out.println(str);
					HashMap<String, byte[]> onTopping = sql.getOnTopping(str);
					if (onTopping != null) {
						if (toppingList.isEmpty()) {
							for (Entry<String, byte[]> map : onTopping.entrySet()) {
								if (toppingOnAndOn > 4) {
									System.out.println("최대 다섯개까지 토핑추가 가능");
									break;
								}
								byte[] imageData = map.getValue();

								ImageIcon imgOnTopping = new ImageIcon(imageData);
								System.out.println(imgOnTopping);

								JLabel Jlbl = new JLabel(imgOnTopping);
								Jlbl.setBounds(41, 172, 310, 400);

								toppingList.add(Jlbl);

								toppingOnAndOn++;
								System.out.println(toppingOnAndOn);
								jlp.add(Jlbl, new Integer(toppingOnAndOn + 1));
							}
						} else {
							JLabel lastTopping = toppingList.remove(toppingList.size() - 1);
							jlp.remove(lastTopping);
							toppingOnAndOn--;
						}
						jlp.revalidate();
						jlp.repaint();
					}
				}
			});

		}

	}

	public int getToppingTarget() {
		return toppingTarget;
	}

	public void setToppingTarget(int toppingTarget) {
		this.toppingTarget = toppingTarget;
	}

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

}
