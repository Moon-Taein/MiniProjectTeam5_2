package frame;

import java.awt.Dimension;
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

	// 피자_불고기피자M -> 불고기피자 라는 이름을 받아서 구현해보자
	public Pizza_PopUp_Frame(String target, MainOrder mo) {

		setModal(true);
		Sql_Methods sqm = new Sql_Methods();

		setBounds(100, 100, 800, 600);
//		contentPane = new JPanel();
		contentPane = new ImagePanel(new ImageIcon("배경화면1.png").getImage());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel imageLabel = new JLabel("image");
		imageLabel.setBounds(10, 80, 410, 380);
		// image icon 사용해서 각 피자의 이미지 사용해서 넣어줘야함
		ImageIcon ic = new ImageIcon(sqm.findPizzaImageMenuId("피자_" + target + "M"));

		JLabel currentCount = new JLabel("1");
		currentCount.setBounds(212, 491, 25, 15);
		contentPane.add(currentCount);

		JLabel sizeMPrice = new JLabel("원");
		sizeMPrice.setBounds(522, 81, 57, 15);
		// 피자M의 가격
		sizeMPrice.setText(sqm.findPriceMenuId(target + "M") + "원");
		contentPane.add(sizeMPrice);

		JLabel sizeLPrice = new JLabel("원");
		sizeLPrice.setBounds(679, 81, 57, 15);
		// 피자L의 가격
		sizeLPrice.setText(sqm.findPriceMenuId(target + "L") + "원");
		contentPane.add(sizeLPrice);
		imageLabel.setIcon(ic);
		contentPane.add(imageLabel);

		JLabel menu_name_Label = new JLabel("name");
		menu_name_Label.setBounds(180, 30, 100, 30);
		menu_name_Label.setText(target);
		contentPane.add(menu_name_Label);

		JButton sizeMButton = new JButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup\\pizzaSizeTrue.png")));
		sizeMButton.setBounds(448, 67, 142, 46);
		sizeMButton.setBorderPainted(false);
		sizeMButton.setContentAreaFilled(false);
		contentPane.add(sizeMButton);

		JButton sizeLButton = new JButton(
				new ImageIcon(getClass().getClassLoader().getResource("popup\\pizzaSizeFalse.png")));
		sizeLButton.setBounds(602, 67, 142, 46);
		sizeLButton.setBorderPainted(false);
		sizeLButton.setContentAreaFilled(false);
		contentPane.add(sizeLButton);

		ButtonGroup edgeChoice = new ButtonGroup();

		JRadioButton EdgeRadioButton_1 = new JRadioButton("기본");
		EdgeRadioButton_1.setBounds(530, 335, 80, 23);
		contentPane.add(EdgeRadioButton_1);
		EdgeRadioButton_1.setBorderPainted(true);
		EdgeRadioButton_1.setSelected(true);
		edgeChoice.add(EdgeRadioButton_1);
		Utility.invisibleRadio(EdgeRadioButton_1);

		JRadioButton EdgeRadioButton_2 = new JRadioButton("노엣지");
		EdgeRadioButton_2.setBounds(530, 367, 80, 23);
		contentPane.add(EdgeRadioButton_2);
		EdgeRadioButton_2.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_2);

		JRadioButton EdgeRadioButton_3 = new JRadioButton("치즈크러스트");
		EdgeRadioButton_3.setBounds(530, 400, 120, 23);
		contentPane.add(EdgeRadioButton_3);
		EdgeRadioButton_3.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_3);

		JRadioButton EdgeRadioButton_4 = new JRadioButton("고구마무스");
		EdgeRadioButton_4.setBounds(530, 433, 120, 23);
		contentPane.add(EdgeRadioButton_4);
		EdgeRadioButton_4.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_4);

		JButton cancelButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup\\cancel.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton.setBounds(450, 490, 143, 46);
		cancelButton.setBorderPainted(false);
		cancelButton.setContentAreaFilled(false);
		contentPane.add(cancelButton);

		JButton addButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("popup\\선택.png")));
		addButton.setBounds(600, 490, 143, 46);
		addButton.setBorderPainted(false);
		addButton.setContentAreaFilled(false);
		contentPane.add(addButton);

		JLabel toppingPlus = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup\\토핑추가.png")));
		toppingPlus.setBounds(446, 130, 295, 162);
		contentPane.add(toppingPlus);

		JLabel changeEdge = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("popup\\엣지변경.png")));
		changeEdge.setBounds(446, 307, 295, 162);
		contentPane.add(changeEdge);

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
