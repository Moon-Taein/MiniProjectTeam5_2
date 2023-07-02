package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import function.DetailOrder;
import function.MainOrder;
import function.MenuItem;
import function.Sql_Methods;
import utilty.invisibility;

public class Pizza_PopUp_Frame extends JDialog {

	private ImagePanel contentPane;
	private invisibility Utility = new invisibility();
	private JButton sizeMButton;
	private JButton sizeLButton;
	private JLabel currentCount;
	private List<JCheckBox> checkList;
	private List<Integer> toppingPriceList;
	private List<JRadioButton> radioButtonList;

	// 피자_불고기피자M -> 불고기피자 라는 이름을 받아서 구현해보자
	public Pizza_PopUp_Frame(String target, MainOrder mo, MenuFrame menu, int detailOrderCount) {

		// parameter로 크기값 받고 return으로 해당크기의 tftfont 해주는
		Font tftFont = getBMJUAFont(18f);
		Font tftFont2 = getBMJUAFont(25f);
		Font tftFont3 = getBMJUAFont(35f);

		setModal(true);
		Sql_Methods sqm = new Sql_Methods();

		// 메인메뉴 넘버
		System.out.println(mo.getOrderNumber());

		// 피자토핑 리스트 가져오기
		List<String> list = sqm.findToppingPriceMenuId(target);

		setBounds(100, 100, 800, 600);
		contentPane = new ImagePanel(
				new ImageIcon(getClass().getClassLoader().getResource("popup/후보3.png")).getImage());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel imageLabel = new JLabel("image");
		imageLabel.setBounds(10, 100, 410, 380);
		// image icon 사용해서 각 피자의 이미지 사용해서 넣어줘야함
		ImageIcon ic = new ImageIcon(sqm.findPizzaImageMenuId(target));
		imageLabel.setIcon(ic);
		contentPane.add(imageLabel);

		// 토핑 이름
		JLabel toppingName1 = new JLabel(getSubStringBiggerThan3(list.get(0)));
		toppingName1.setBounds(505, 168, 100, 15);
		toppingName1.setFont(tftFont);
		toppingName1.setForeground(new Color(103, 51, 53));
		contentPane.add(toppingName1);

		JLabel toppingName2 = new JLabel(getSubStringBiggerThan3(list.get(2)));
		toppingName2.setBounds(505, 198, 100, 15);
		toppingName2.setFont(tftFont);
		toppingName2.setForeground(new Color(103, 51, 53));
		contentPane.add(toppingName2);

		JLabel toppingName3 = new JLabel(getSubStringBiggerThan3(list.get(4)));
		toppingName3.setBounds(505, 228, 100, 15);
		toppingName3.setFont(tftFont);
		toppingName3.setForeground(new Color(103, 51, 53));
		contentPane.add(toppingName3);

		JLabel toppingName4 = new JLabel(getSubStringBiggerThan3(list.get(6)));
		toppingName4.setBounds(505, 258, 100, 15);
		toppingName4.setFont(tftFont);
		toppingName4.setForeground(new Color(103, 51, 53));
		contentPane.add(toppingName4);

		toppingPriceList = new ArrayList<>();
		toppingPriceList.add(getSubStringReplaceWon(list.get(1)));
		toppingPriceList.add(getSubStringReplaceWon(list.get(3)));
		toppingPriceList.add(getSubStringReplaceWon(list.get(5)));
		toppingPriceList.add(getSubStringReplaceWon(list.get(7)));

		// 토핑 가격
		JLabel toppingPrice1 = new JLabel(list.get(1));
		toppingPrice1.setBounds(645, 168, 70, 15);
		toppingPrice1.setFont(tftFont);
		toppingPrice1.setForeground(new Color(103, 51, 53));
		contentPane.add(toppingPrice1);

		JLabel toppingPrice2 = new JLabel(list.get(3));
		toppingPrice2.setBounds(645, 198, 70, 15);
		toppingPrice2.setFont(tftFont);
		toppingPrice2.setForeground(new Color(103, 51, 53));
		contentPane.add(toppingPrice2);

		JLabel toppingPrice3 = new JLabel(list.get(5));
		toppingPrice3.setBounds(645, 228, 70, 15);
		toppingPrice3.setFont(tftFont);
		toppingPrice3.setForeground(new Color(103, 51, 53));
		contentPane.add(toppingPrice3);

		JLabel toppingPrice4 = new JLabel(list.get(7));
		toppingPrice4.setBounds(645, 258, 70, 15);
		toppingPrice4.setFont(tftFont);
		toppingPrice4.setForeground(new Color(103, 51, 53));
		contentPane.add(toppingPrice4);
		//

		JLabel toppingOptionPrice = new JLabel(
				new ImageIcon(getClass().getClassLoader().getResource("popup/토핑옵션가격.png")));
		toppingOptionPrice.setBounds(630, 165, 90, 115);
		contentPane.add(toppingOptionPrice);

		checkList = new ArrayList<>();

		// 토핑 체크 박스
		JCheckBox toppingCheckBox1 = new JCheckBox(
				new ImageIcon(getClass().getClassLoader().getResource("popup/toppingCheckFalse.png")));
		toppingCheckBox1.setBounds(460, 165, 150, 23);
		toppingCheckBox1
				.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/toppingCheckTrue.png")));
		toppingCheckBox1.setText(list.get(0));
		toppingCheckBox1.setOpaque(false);
		contentPane.add(toppingCheckBox1);

		JCheckBox toppingCheckBox2 = new JCheckBox(
				new ImageIcon(getClass().getClassLoader().getResource("popup/toppingCheckFalse.png")));
		toppingCheckBox2.setBounds(460, 195, 150, 23);
		toppingCheckBox2
				.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/toppingCheckTrue.png")));
		toppingCheckBox2.setText(list.get(2));
		toppingCheckBox2.setOpaque(false);
		contentPane.add(toppingCheckBox2);

		JCheckBox toppingCheckBox3 = new JCheckBox(
				new ImageIcon(getClass().getClassLoader().getResource("popup/toppingCheckFalse.png")));
		toppingCheckBox3.setBounds(460, 225, 150, 23);
		toppingCheckBox3
				.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/toppingCheckTrue.png")));
		toppingCheckBox3.setText(list.get(4));
		toppingCheckBox3.setOpaque(false);
		contentPane.add(toppingCheckBox3);

		JCheckBox toppingCheckBox4 = new JCheckBox(
				new ImageIcon(getClass().getClassLoader().getResource("popup/toppingCheckFalse.png")));
		toppingCheckBox4.setBounds(460, 255, 150, 23);
		toppingCheckBox4
				.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/toppingCheckTrue.png")));
		toppingCheckBox4.setText(list.get(6));
		toppingCheckBox4.setOpaque(false);
		contentPane.add(toppingCheckBox4);

		// checkList에 토핑체크박스들 넣어두기
		checkList.add(toppingCheckBox1);
		checkList.add(toppingCheckBox2);
		checkList.add(toppingCheckBox3);
		checkList.add(toppingCheckBox4);

		for (JCheckBox jcb : checkList) {
			if (jcb.getText().equals("")) {
				jcb.setEnabled(false);
			}
		}

		currentCount = new JLabel("1");
		currentCount.setBounds(212, 485, 25, 25);
		currentCount.setForeground(new Color(103, 51, 53));
		currentCount.setFont(tftFont2);
		contentPane.add(currentCount);

		// 수량 +.- Button
		JButton countMinus = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup/수량minus.png")));
		countMinus.setBounds(107, 483, 65, 30);
		countMinus.setBorderPainted(false);
		countMinus.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/수량minusBig.png")));
		countMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(currentCount.getText()) > 1) {
					currentCount.setText(String.valueOf(Integer.parseInt(currentCount.getText()) - 1));
				}
			}
		});
		contentPane.add(countMinus);

		JButton countPlus = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup/수량plus.png")));
		countPlus.setBounds(269, 483, 65, 30);
		countPlus.setBorderPainted(false);
		countPlus.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/수량plusBig.png")));
		countPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(currentCount.getText()) < 5) {
					currentCount.setText(String.valueOf(Integer.parseInt(currentCount.getText()) + 1));
				}
			}
		});
		contentPane.add(countPlus);

		JLabel dow_price = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup/도우옵션가격수정.png")));
		dow_price.setBounds(630, 345, 90, 115);
		contentPane.add(dow_price);

		JLabel sizeLabel_L = new JLabel("L");
		sizeLabel_L.setForeground(Color.WHITE);
		sizeLabel_L.setFont(tftFont2);
		sizeLabel_L.setBounds(617, 68, 57, 44);
		sizeLabel_L.setOpaque(false);
		contentPane.add(sizeLabel_L);

		JLabel sizeLabel_M = new JLabel("M");
		sizeLabel_M.setForeground(Color.WHITE);
		sizeLabel_M.setFont(tftFont2);
		sizeLabel_M.setBounds(460, 68, 25, 46);
		sizeLabel_M.setOpaque(false);
		contentPane.add(sizeLabel_M);

		JLabel sizeMPrice = new JLabel("원");
		sizeMPrice.setFont(tftFont2);
		sizeMPrice.setBounds(490, 75, 100, 30);
		sizeMPrice.setForeground(new Color(103, 51, 53));
		// 피자M의 가격
		sizeMPrice.setText(sqm.findPriceMenuId(target) + "원");
		contentPane.add(sizeMPrice);

		JLabel sizeLPrice = new JLabel("원");
		sizeLPrice.setFont(tftFont2);
		sizeLPrice.setBounds(645, 75, 100, 30);
		sizeLPrice.setForeground(new Color(103, 51, 53));
		// 피자L의 가격
		sizeLPrice.setText(sqm.findPriceMenuId(target.replace('M', 'L')) + "원");
		contentPane.add(sizeLPrice);

		JLabel menu_name_Label = new JLabel("name");
		menu_name_Label.setForeground(new Color(103, 51, 53));
		menu_name_Label.setFont(tftFont3);
		menu_name_Label.setBounds(135, 70, 210, 40);
		menu_name_Label.setText(target.substring(3).replace('M', ' '));

		contentPane.add(menu_name_Label);

		sizeMButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup/pizzaSizeFalse.png")));
		sizeMButton.setBounds(448, 67, 142, 46);
		sizeMButton.setBorderPainted(false);
		sizeMButton.setContentAreaFilled(false);
		sizeMButton.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/pizzaSizeTrue.png")));
		sizeMButton.setSelected(true);
		sizeMButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sizeMButton.setSelected(true);
				sizeLButton.setSelected(false);
			}
		});
		contentPane.add(sizeMButton);

		sizeLButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup/pizzaSizeFalse.png")));
		sizeLButton.setBounds(602, 67, 142, 46);
		sizeLButton.setBorderPainted(false);
		sizeLButton.setContentAreaFilled(false);
		sizeLButton.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/pizzaSizeTrue.png")));
		sizeLButton.setSelected(false);
		sizeLButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sizeLButton.setSelected(true);
				sizeMButton.setSelected(false);
			}
		});
		contentPane.add(sizeLButton);

		ButtonGroup edgeChoice = new ButtonGroup();
		radioButtonList = new ArrayList<>();

		// radio버튼 구성
		JRadioButton EdgeRadioButton_1 = new JRadioButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup/기본0.png")));
		EdgeRadioButton_1.setBounds(460, 345, 154, 24);
		EdgeRadioButton_1.setText("엣지_기본");
		contentPane.add(EdgeRadioButton_1, new Integer(3));
		EdgeRadioButton_1.setBorderPainted(true);
		EdgeRadioButton_1.setSelected(true);
		EdgeRadioButton_1.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/기본.png")));
		edgeChoice.add(EdgeRadioButton_1);
		Utility.invisibleRadio(EdgeRadioButton_1);

		JRadioButton EdgeRadioButton_2 = new JRadioButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup/노엣지0.png")));
		EdgeRadioButton_2.setBounds(460, 375, 154, 24);
		EdgeRadioButton_2.setText("엣지_노");
		contentPane.add(EdgeRadioButton_2);
		EdgeRadioButton_2.setBorderPainted(true);
		EdgeRadioButton_2.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/노엣지.png")));
		edgeChoice.add(EdgeRadioButton_2);
		Utility.invisibleRadio(EdgeRadioButton_2);

		JRadioButton EdgeRadioButton_3 = new JRadioButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup/치즈크러스트0.png")));
		EdgeRadioButton_3.setBounds(460, 405, 154, 24);
		EdgeRadioButton_3.setText("엣지_치즈크러스트");
		contentPane.add(EdgeRadioButton_3);
		EdgeRadioButton_3.setBorderPainted(true);
		EdgeRadioButton_3.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/치즈크러스트.png")));
		edgeChoice.add(EdgeRadioButton_3);
		Utility.invisibleRadio(EdgeRadioButton_3);

		JRadioButton EdgeRadioButton_4 = new JRadioButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup/고구마무스0.png")));
		EdgeRadioButton_4.setBounds(460, 435, 154, 24);
		EdgeRadioButton_4.setText("엣지_고구마무스");
		contentPane.add(EdgeRadioButton_4);
		EdgeRadioButton_4.setBorderPainted(true);
		EdgeRadioButton_4.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/고구마무스.png")));
		edgeChoice.add(EdgeRadioButton_4);
		Utility.invisibleRadio(EdgeRadioButton_4);

		radioButtonList.add(EdgeRadioButton_1);
		radioButtonList.add(EdgeRadioButton_2);
		radioButtonList.add(EdgeRadioButton_3);
		radioButtonList.add(EdgeRadioButton_4);

		// 취소할 경우
		JButton cancelButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup/cancel.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(450, 490, 143, 46);
		cancelButton.setBorderPainted(false);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/cancelRoll.png")));
		cancelButton.setOpaque(false);
		contentPane.add(cancelButton);

		// 담기할 경우
		JButton addButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup/담기.png")));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// menuitem 체크해서 detailOrder 안에 다 넣어줘야된다
				int total_price = 0;

				// m,l 인지
				String newTarget = "";
				if (sizeLButton.isSelected()) {
					newTarget = target.replace('M', 'L');
					total_price += Integer.valueOf(sizeLPrice.getText().replaceAll("원", ""));
				} else {
					newTarget = target;
					total_price += Integer.valueOf(sizeMPrice.getText().replaceAll("원", ""));
				}

				// detailOrder 생성
				menu.setDetailOrderCount((menu.getDetailOrderCount()) + 1);
//				MenuFrame.detailOrderCount++;
				DetailOrder deo = new DetailOrder(menu.getDetailOrderCount(), newTarget,
						Integer.valueOf(currentCount.getText()), mo.getOrderNumber(), 0);

				// checkbox 확인해서 menuitem 만들어줘서 detailorder에 add
				for (int i = 0; i < 4; i++) {
					if (checkList.get(i).isSelected()) {
						// menuitem 만들어서 deatilorder에 넣기
						MenuItem mi = new MenuItem(menu.getDetailOrderCount(), checkList.get(i).getText(),
								toppingPriceList.get(i));
						total_price += toppingPriceList.get(i);
						deo.getMiList().add(mi);
					}
				}

				// radioButton 확인해서 menuitem 만들어줘서 detailorder에 add
				// menuitem 만들 menuid, 가격 가져오고 가격은 토탈에 더해주기
				for (JRadioButton jrb : radioButtonList) {
					if (jrb.isSelected()) {
						int price = sqm.findPriceIngredient(jrb.getText());
						System.out.println("이그는 왜 또 0원임" + price);
						MenuItem mi = new MenuItem(menu.getDetailOrderCount(), jrb.getText(), price);
						total_price += price;
						deo.getMiList().add(mi);
					}
				}

				// 피자가격 + 모든 옵션 가격
				total_price = total_price * Integer.valueOf(currentCount.getText());

				System.out.println("모든옵션이 합쳐진 총금액은" + total_price);
				deo.setDetailOrderFullPrice(total_price);
				mo.getDeoList().add(deo);
				System.out.println(mo.toString());
				dispose();
			}
		});
		addButton.setBounds(600, 490, 143, 46);
		addButton.setBorderPainted(false);
		addButton.setContentAreaFilled(false);
		addButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("popup/담기Roll.png")));
		addButton.setOpaque(false);
		contentPane.add(addButton);

		// 토핑 추가 부분
		JLabel toppingPlus = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup/토핑추가.png")));
		toppingPlus.setBounds(446, 130, 295, 162);
		contentPane.add(toppingPlus);

		// 엣지 변경 부분
		JLabel changeEdge = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup/엣지변경.png")));
		changeEdge.setBounds(446, 307, 298, 162);
		contentPane.add(changeEdge);

		// 수량 변경 부분
		JLabel countLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup/수량.png")));
		countLabel.setBounds(80, 475, 273, 46);
		contentPane.add(countLabel);

		setUndecorated(true);
		setLocationRelativeTo(null);

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

	private String getSubStringBiggerThan3(String string) {
		if (string.length() > 3) {
			return string.substring(3);
		}
		return string;
	}

	private int getSubStringReplaceWon(String string) {
		if (string.length() > 4) {
			return Integer.valueOf(string.replaceAll("원", ""));
		}
		return 0;
	}

}

class ImagePanel extends JPanel {
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 3, 0, null);
	}
}
