package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import Function.MainOrder;
import Function.Sql_Methods;
import utilty.invisibility;

public class Pizza_PopUp_Frame extends JDialog {

	private ImagePanel contentPane;
	private invisibility Utility = new invisibility();
	private JButton sizeMButton;
	private JButton sizeLButton;

	// 피자_불고기피자M -> 불고기피자 라는 이름을 받아서 구현해보자
	public Pizza_PopUp_Frame(String target, MainOrder mo, int detailOrderCount) {
		System.out.println(mo.getOrderNumber());

//		Font font = loadFont("BMJUA_ttf.ttf", Font.BOLD, 18);

		setModal(true);
		Sql_Methods sqm = new Sql_Methods();

		setBounds(100, 100, 800, 600);
//		contentPane = new JPanel();
		contentPane = new ImagePanel(new ImageIcon("배경화면1.png").getImage());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel imageLabel = new JLabel("image");
		imageLabel.setBounds(15, 80, 410, 380);
		// image icon 사용해서 각 피자의 이미지 사용해서 넣어줘야함
		ImageIcon ic = new ImageIcon(sqm.findPizzaImageMenuId("피자_" + target + "M"));

		JLabel sizeLabel_L = new JLabel("L");
		sizeLabel_L.setForeground(Color.WHITE);
		sizeLabel_L.setFont(new Font("굴림", Font.PLAIN, 24));
		sizeLabel_L.setBounds(617, 68, 57, 44);
		sizeLabel_L.setOpaque(false);
		contentPane.add(sizeLabel_L);

		JLabel sizeLabel_M = new JLabel("M");
		sizeLabel_M.setForeground(Color.WHITE);
		sizeLabel_M.setFont(new Font("굴림", Font.PLAIN, 24));
		sizeLabel_M.setBounds(460, 68, 25, 46);
		sizeLabel_M.setOpaque(false);
		contentPane.add(sizeLabel_M);

		JLabel currentCount = new JLabel("1");
		currentCount.setBounds(212, 491, 25, 15);
		contentPane.add(currentCount);

		JLabel sizeMPrice = new JLabel("원");
		sizeMPrice.setFont(new Font("굴림", Font.PLAIN, 22));
		sizeMPrice.setBounds(500, 75, 100, 30);
		// 피자M의 가격
		sizeMPrice.setText(sqm.findPriceMenuId(target + "M") + "원");
		contentPane.add(sizeMPrice);

		JLabel sizeLPrice = new JLabel("원");
		sizeLPrice.setFont(new Font("굴림", Font.PLAIN, 22));
		sizeLPrice.setBounds(655, 75, 100, 30);
		// 피자L의 가격
		sizeLPrice.setText(sqm.findPriceMenuId(target + "L") + "원");
		contentPane.add(sizeLPrice);
		imageLabel.setIcon(ic);
		contentPane.add(imageLabel);

		JLabel menu_name_Label = new JLabel("name");
		menu_name_Label.setFont(new Font("굴림", Font.PLAIN, 17));
		menu_name_Label.setBounds(175, 40, 150, 30);
		menu_name_Label.setText(target);
		contentPane.add(menu_name_Label);

		sizeMButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup\\pizzaSizeFalse.png")));
		sizeMButton.setBounds(448, 67, 142, 46);
		sizeMButton.setBorderPainted(false);
		sizeMButton.setContentAreaFilled(false);
		sizeMButton.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup\\pizzaSizeTrue.png")));
		sizeMButton.setSelected(true);
		sizeMButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sizeMButton.setSelected(true);
				sizeLButton.setSelected(false);
			}
		});
		contentPane.add(sizeMButton);

		sizeLButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup\\pizzaSizeFalse.png")));
		sizeLButton.setBounds(602, 67, 142, 46);
		sizeLButton.setBorderPainted(false);
		sizeLButton.setContentAreaFilled(false);
		sizeLButton.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup\\pizzaSizeTrue.png")));
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

		// radio버튼 구성
		JRadioButton EdgeRadioButton_1 = new JRadioButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup\\\\기본0.png")));
		EdgeRadioButton_1.setBounds(530, 335, 154, 24);
		EdgeRadioButton_1.setText("도우_기본");
		contentPane.add(EdgeRadioButton_1, new Integer(3));
		EdgeRadioButton_1.setBorderPainted(true);
		EdgeRadioButton_1.setSelected(true);
		EdgeRadioButton_1.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup\\\\기본.png")));
		edgeChoice.add(EdgeRadioButton_1);
		Utility.invisibleRadio(EdgeRadioButton_1);

		JRadioButton EdgeRadioButton_2 = new JRadioButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup\\\\노엣지0.png")));
		EdgeRadioButton_2.setBounds(460, 375, 154, 24);
		contentPane.add(EdgeRadioButton_2);
		EdgeRadioButton_2.setBorderPainted(true);
		EdgeRadioButton_2.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup\\\\노엣지.png")));
		edgeChoice.add(EdgeRadioButton_2);
		Utility.invisibleRadio(EdgeRadioButton_2);

		JRadioButton EdgeRadioButton_3 = new JRadioButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup\\\\치즈크러스트0.png")));
		EdgeRadioButton_3.setBounds(460, 405, 154, 24);
		contentPane.add(EdgeRadioButton_3);
		EdgeRadioButton_3.setBorderPainted(true);
		EdgeRadioButton_3
				.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup\\\\치즈크러스트.png")));
		edgeChoice.add(EdgeRadioButton_3);
		Utility.invisibleRadio(EdgeRadioButton_3);

		JRadioButton EdgeRadioButton_4 = new JRadioButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup\\\\고구마무스0.png")));
		EdgeRadioButton_4.setBounds(460, 435, 154, 24);
		contentPane.add(EdgeRadioButton_4);
		EdgeRadioButton_4.setBorderPainted(true);
		EdgeRadioButton_4.setSelectedIcon(new ImageIcon(getClass().getClassLoader().getResource("popup\\\\고구마무스.png")));
		edgeChoice.add(EdgeRadioButton_4);
		Utility.invisibleRadio(EdgeRadioButton_4);

		// 취소할 경우
		JButton cancelButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup\\cancel.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(450, 490, 143, 46);
		cancelButton.setBorderPainted(false);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("popup\\cancelRoll.png")));
		contentPane.add(cancelButton);

		// 담기할 경우
		JButton addButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup\\선택.png")));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		addButton.setBounds(600, 490, 143, 46);
		addButton.setBorderPainted(false);
		addButton.setContentAreaFilled(false);
		contentPane.add(addButton);

		// 토핑 추가 부분
		JLabel toppingPlus = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup\\토핑추가.png")));
		toppingPlus.setBounds(446, 130, 295, 162);
		contentPane.add(toppingPlus);

		// 엣지 변경 부분
		JLabel changeEdge = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup\\엣지변경.png")));
		changeEdge.setBounds(446, 307, 298, 162);
		contentPane.add(changeEdge);

		// 수량 변경 부분
		JLabel countLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup\\수량.png")));
		countLabel.setBounds(80, 475, 273, 46);
		contentPane.add(countLabel);

		setLocationRelativeTo(null);

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
