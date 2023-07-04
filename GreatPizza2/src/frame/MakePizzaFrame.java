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

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import function.DetailOrder;
import function.MainOrder;
import function.MenuItem;
import function.Sql_Methods;
import img.imageIcon;
import utilty.invisibility;

public class MakePizzaFrame extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private invisibility util = new invisibility();
	private Sql_Methods sql = new Sql_Methods();
	private int countEdge;
	private String topingStr;

	private HashMap<String, byte[]> edgeMap = sql.pizzamakeSetEdgeimg("엣지");
	private HashMap<String, byte[]> topingMap;

	private JLabel edgeLbl;
	private JLabel currentEdge;
	private ImageIcon edgeIcon;
	private ImagePanel2 topingPnl;
	private ImageIcon sourceIcon;

	private String currentSourceTarget;
	private ArrayList<JLabel> toppingList;

	int toppingTarget = 0;
	int topingCount = 3;

	private JButton previousButton = null;
	private JLabel toppingLbl;

//	int currentPage;
//	int startIndex;
//	int endIndex;
//	int count;
//	int x;
//	int y;

	private int toppingOnAndOn = 1;

	private JLayeredPane topingJP;
	private List<JLabel> plusToppingList = new ArrayList<>();

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
	public MakePizzaFrame(MenuFrame menu, MainOrder mo, JLayeredPane underListPanel) {
		frameSetting();
		sourceBtnSetting(menu, mo, underListPanel);
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
		jlp.add(topingPnl, new Integer(1));

		topingJP = new JLayeredPane();
		topingJP.setBounds(41, 172, 310, 400);
		jlp.add(topingJP, new Integer(3));

		setContentPane(jlp);

		setUndecorated(true);
		setVisible(true);
		setSize(800, 900);
		setLocationRelativeTo(null);
	}

	private void sourceBtnSetting(MenuFrame menu, MainOrder mo, JLayeredPane underListPanel) {
		int x = 411;
		int y = 614;
		int horizontalGap = 78;

		HashMap<String, byte[]> sourceArr = sql.getOnSource("소스");
		HashMap<String, byte[]> onSourceMap = sql.getOnSourceImg("소스");

		JLayeredPane sourceJlp = new JLayeredPane();
		sourceJlp.setBounds(-10, 108, 410, 529);
		jlp.add(sourceJlp, new Integer(2));

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

			jlp.add(sourceBtn, new Integer(2));

			x += horizontalGap;

			sourceBtn.addMouseListener(new MouseAdapter() {
				private JLabel selectedSourceBtn;
				private JLabel sourceLbl;

				@Override
				public void mouseClicked(MouseEvent e) {
					selectedSourceBtn = (JLabel) e.getSource();
					String str = selectedSourceBtn.getText();
					if (currentSourceTarget != null && currentSourceTarget.equals(str)) {
						sourceJlp.remove(sourceLbl);
						sourceJlp.revalidate();
						sourceJlp.repaint();
						currentSourceTarget = null;

					} else {

						currentSourceTarget = str;

						byte[] imgArr = onSourceMap.get(str);
						ImageIcon onSourceIcon = new ImageIcon(imgArr);

						sourceLbl = new JLabel(onSourceIcon);

						sourceLbl.setBounds(0, 0, 410, 529);
						sourceJlp.removeAll();
						sourceJlp.add(sourceLbl, new Integer(2));
						sourceJlp.revalidate();
						sourceJlp.repaint();
					}
				}
			});
		}

		// 취소,담기 버튼
		// 취소있으니까 뒤로가기 없어도될듯
		JButton cancle = new JButton(icon.getCancle());
		cancle.setBounds(90, 775, 280, 70);
		jlp.add(cancle, new Integer(2));
		cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				menu.setVisible(true);
			}
		});
		util.invisible(cancle);
		cancle.setRolloverIcon(icon.getCancleBright());

		JButton add = new JButton(icon.getAdd());
		add.setBounds(430, 775, 280, 70);
		jlp.add(add, new Integer(2));
		util.invisible(add);
		add.setRolloverIcon(icon.getAddBright());
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int total_price = 0;
				// detailorder 생성
				menu.setDetailOrderCount((menu.getDetailOrderCount()) + 1);
				DetailOrder deo = new DetailOrder(menu.getDetailOrderCount(), "피자_나만의피자", 1, mo.getOrderNumber(), 0);
				// menuItem 추가해주고
				MenuItem miSource = new MenuItem(menu.getDetailOrderCount(), "소스_" + currentSourceTarget,
						sql.findPriceIngredient("소스_" + currentSourceTarget)); // 소스찾았고
				total_price += miSource.getMenuItemPrice();
				deo.getMiList().add(miSource);
				System.out.println("엣지_" + edgeLbl.getText()); // 엣지고
				MenuItem miEdge = new MenuItem(menu.getDetailOrderCount(), "엣지_" + edgeLbl.getText(),
						sql.findPriceIngredient("엣지_" + edgeLbl.getText()));
				total_price += miEdge.getMenuItemPrice();
				deo.getMiList().add(miEdge);
				if (plusToppingList.size() > 1) {
					for (JLabel lbl : plusToppingList) {
						MenuItem miTopping = new MenuItem(menu.getDetailOrderCount(), lbl.getText(),
								sql.findPriceIngredient(lbl.getText()));
						deo.getMiList().add(miTopping);
						total_price += miTopping.getMenuItemPrice();
					}
				}

				// 토탈 코스트 넣어주고
				total_price += 9900;
				deo.setDetailOrderFullPrice(total_price);

				// mo에 넣어주고
				mo.getDeoList().add(deo);

				// 하단바 새로고쳐주고
				List<DetailOrder> deoList1 = mo.getDeoList();
				underListPanel.removeAll();
				underListPanel.invalidate();
				if (deoList1.size() > 3) {
					if ((deoList1.size()) % 3 == 1) {
//						System.out.println("413줄에서 숫자확인" + (deoList1.size() / 3));
						menu.setUnderListTarget(((deoList1.size() / 3)) * 3);
						menu.underOrderList(mo, ((deoList1.size() / 3)) * 3);
					} else {
						menu.setUnderListTarget((((deoList1.size() - 1) / 3)) * 3);
						menu.underOrderList(mo, (((deoList1.size() - 1) / 3)) * 3);
					}
				} else {
					menu.setUnderListTarget((((deoList1.size() - 1) / 3)) * 3);
					menu.underOrderList(mo, (((deoList1.size() - 1) / 3)) * 3);
				}
				underListPanel.repaint();

				int mo_total_price = MenuFrame.final_total_price(mo);
				MenuFrame.total_priceLabel.setText(String.valueOf(mo_total_price) + "원");

//				System.out.println("최종 나만의피자로 들어가는지 " + mo.getDeoList().toString());
//				System.out.println("잘들어가니" + deo.getMiList().toString());

				dispose();
				menu.setVisible(true);

				//

			}
		});

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
		jlp.add(currentEdge, new Integer(8));

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

	public int getTopingCount() {
		return topingCount;
	}

	public void setTopingCount(int topingCount) {
		this.topingCount = topingCount;
	}

	private void showTopping(int toppingTarget) {

		List<Object> list = sql.findImageAndIngredient_IdTarget("토핑%", toppingTarget);

		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btnTarget = (JButton) e.getSource();
				String str = btnTarget.getText();
				System.out.println(str);
				byte[] targetImage = sql.findImageIngredientTarget(str);
				System.out.println(str + " " + targetImage);

				// 화면에 뿌려주기
				ImageIcon icon = new ImageIcon(targetImage);
				toppingLbl = new JLabel(icon);
				toppingLbl.setText(str);
				toppingLbl.setBounds(0, 0, 310, 400);

				if (topingCount < 8) {
					topingJP.add(toppingLbl, new Integer(topingCount));
					plusToppingList.add(toppingLbl);
					System.out.println("라벨리스트사이즈는" + plusToppingList.size());
					System.out.println(plusToppingList.toString());
					topingCount++;
					System.out.println(topingCount);
				} else {
					System.out.println(topingCount + "5개초과함");
					topingJP.remove(plusToppingList.get(4));
					topingJP.remove(plusToppingList.get(3));
					topingJP.remove(plusToppingList.get(2));
					topingJP.remove(plusToppingList.get(1));
					topingJP.remove(plusToppingList.get(0));
					plusToppingList.remove(4);
					plusToppingList.remove(3);
					plusToppingList.remove(2);
					plusToppingList.remove(1);
					plusToppingList.remove(0);
					topingCount = 3;
					topingJP.repaint();
				}
			}
		};

		int width = 135;
		int height = 100;

		// 가져와서 뿌려주기;
		if (list.size() > 0) {
			ImageIcon img = new ImageIcon((byte[]) list.get(1));
			JButton btn1 = new JButton(img);
			btn1.setText((String) list.get(0));
			btn1.setBounds(19, 0, 150, 130);
			btn1.setRolloverIcon(grayImageIcon(img));
			btn1.addActionListener(al);
			topingPnl.add(btn1, new Integer(3));
			util.invisible(btn1);

		}

		if (list.size() > 3) {
			ImageIcon img = new ImageIcon((byte[]) list.get(3));
			JButton btn2 = new JButton(img);
			btn2.setText((String) list.get(2));
			btn2.setBounds(210, 0, 150, 130);
			btn2.setRolloverIcon(grayImageIcon(img));
			btn2.addActionListener(al);
			topingPnl.add(btn2, new Integer(3));
			util.invisible(btn2);

		}

		if (list.size() > 5) {
			ImageIcon img = new ImageIcon((byte[]) list.get(5));
			JButton btn3 = new JButton(img);
			btn3.setText((String) list.get(4));
			btn3.setBounds(19, 160, 150, 130);
			btn3.setRolloverIcon(grayImageIcon(img));
			btn3.addActionListener(al);
			topingPnl.add(btn3, new Integer(3));
			util.invisible(btn3);
		}

		if (list.size() > 7) {
			ImageIcon img = new ImageIcon((byte[]) list.get(7));
			JButton btn4 = new JButton(img);
			btn4.setText((String) list.get(6));
			btn4.setBounds(210, 160, 150, 130);
			btn4.setRolloverIcon(grayImageIcon(img));
			btn4.addActionListener(al);
			topingPnl.add(btn4, new Integer(3));
			util.invisible(btn4);

		}

		if (list.size() > 9) {
			ImageIcon img = new ImageIcon((byte[]) list.get(9));
			JButton btn5 = new JButton(img);
			btn5.setText((String) list.get(8));
			btn5.setBounds(19, 320, 150, 130);
			btn5.setRolloverIcon(grayImageIcon(img));
			btn5.addActionListener(al);
			topingPnl.add(btn5, new Integer(3));
			util.invisible(btn5);

		}

		if (list.size() > 11) {
			ImageIcon img = new ImageIcon((byte[]) list.get(11));
			JButton btn6 = new JButton(img);
			btn6.setText((String) list.get(10));
			btn6.setBounds(210, 320, 150, 130);
			btn6.setRolloverIcon(grayImageIcon(img));
			btn6.addActionListener(al);
			topingPnl.add(btn6, new Integer(3));
			util.invisible(btn6);

		}

		JButton afterButton = new JButton(icon.getMiniAfter());
		afterButton.setBounds(650, 550, 50, 50);
		afterButton.setRolloverIcon(icon.getMiniAfterRoll());
		util.invisible(afterButton);
		afterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				topingPnl.removeAll();
				setToppingTarget(getToppingTarget() + 6);
				showTopping(getToppingTarget());
				topingPnl.repaint();
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
					setToppingTarget(getToppingTarget() - 6);
					showTopping(getToppingTarget());
					topingPnl.repaint();
				}

			}
		});
		jlp.add(beforeBtn, new Integer(3));

	}

	// imageIcon 을 gray로 바꿔줘서 반환
	private ImageIcon grayImageIcon(ImageIcon img) {
		Image normalImage = img.getImage();
		Image grayImage = GrayFilter.createDisabledImage(normalImage);
		ImageIcon grayImg = new ImageIcon(grayImage);
		return grayImg;
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
