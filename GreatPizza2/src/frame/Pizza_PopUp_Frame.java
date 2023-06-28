package frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
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

		setBounds(100, 100, 800, 500);
		contentPane = new ImagePanel(new ImageIcon("gyagya.png").getImage());
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel imageLabel = new JLabel("image");
		imageLabel.setBounds(24, 54, 410, 380);
		// image icon 사용해서 각 피자의 이미지 사용해서 넣어줘야함
		ImageIcon ic = new ImageIcon(sqm.findPizzaImageMenuId("피자_" + target + "M"));
		imageLabel.setIcon(ic);
		contentPane.add(imageLabel);

		JLabel menu_name_Label = new JLabel("name");
		menu_name_Label.setBounds(24, 10, 87, 27);
		menu_name_Label.setText(target);
		contentPane.add(menu_name_Label);

		JButton sizeMButton = new JButton("M");
		sizeMButton.setBounds(459, 54, 97, 23);
		contentPane.add(sizeMButton);

		JButton sizeLButton = new JButton("L");
		sizeLButton.setBounds(601, 54, 97, 23);
		contentPane.add(sizeLButton);

		JLabel sizeMPrice = new JLabel("원");
		sizeMPrice.setBounds(568, 58, 57, 15);
		// 피자M의 가격
		sizeMPrice.setText(sqm.findPriceMenuId(target + "M") + "원");
		contentPane.add(sizeMPrice);

		JLabel sizeLPrice = new JLabel("원");
		sizeLPrice.setBounds(710, 58, 57, 15);
		// 피자L의 가격
		sizeLPrice.setText(sqm.findPriceMenuId(target + "L") + "원");
		contentPane.add(sizeLPrice);

		JLabel currentCount = new JLabel("1");
		currentCount.setBounds(584, 330, 25, 15);
		contentPane.add(currentCount);

		JButton addCheezeButton = new JButton("치즈추가");
		addCheezeButton.setBounds(459, 113, 97, 23);
		contentPane.add(addCheezeButton);

		JButton addToppingButton_5 = new JButton("토핑추가");
		addToppingButton_5.setBounds(459, 158, 97, 23);
		contentPane.add(addToppingButton_5);

		JLabel changeEdge_Label = new JLabel("엣지변경");
		changeEdge_Label.setBounds(459, 218, 57, 15);
		contentPane.add(changeEdge_Label);

		ButtonGroup edgeChoice = new ButtonGroup();

		JRadioButton EdgeRadioButton_1 = new JRadioButton("기본");
		EdgeRadioButton_1.setBounds(545, 214, 80, 23);
		contentPane.add(EdgeRadioButton_1);
		EdgeRadioButton_1.setBorderPainted(true);
		EdgeRadioButton_1.setSelected(true);
		edgeChoice.add(EdgeRadioButton_1);
		Utility.invisibleRadio(EdgeRadioButton_1);

		JRadioButton EdgeRadioButton_2 = new JRadioButton("노엣지");
		EdgeRadioButton_2.setBounds(637, 214, 80, 23);
		contentPane.add(EdgeRadioButton_2);
		EdgeRadioButton_2.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_2);

		JRadioButton EdgeRadioButton_3 = new JRadioButton("치즈크러스트");
		EdgeRadioButton_3.setBounds(567, 247, 120, 23);
		contentPane.add(EdgeRadioButton_3);
		EdgeRadioButton_3.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_3);

		JRadioButton EdgeRadioButton_4 = new JRadioButton("고구마무스");
		EdgeRadioButton_4.setBounds(568, 280, 120, 23);
		contentPane.add(EdgeRadioButton_4);
		EdgeRadioButton_4.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_4);

		// 버튼에 이미지 넣기
		JButton cancelButton = new JButton("취소");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton.setBounds(475, 411, 97, 23);
		contentPane.add(cancelButton);

		// 버튼에 이미지 넣기
		JButton addButton = new JButton("담기");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(601, 411, 97, 23);
		contentPane.add(addButton);

		// 투명버튼일듯
		JButton minusCountButton = new JButton("<");
		contentPane.add(minusCountButton);
		minusCountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(currentCount.getText()) > 1) {
					String input = String.valueOf(Integer.parseInt(currentCount.getText()) - 1);
					currentCount.setText(input);
				}
			}
		});
		minusCountButton.setBounds(515, 326, 57, 23);

		// 투명버튼일듯
		JButton plusCountButton = new JButton(">");
		contentPane.add(plusCountButton);
		plusCountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(currentCount.getText()) < 5) {
					String input = String.valueOf(Integer.parseInt(currentCount.getText()) + 1);
					currentCount.setText(input);
				}
			}
		});
		plusCountButton.setBounds(601, 326, 57, 23);

		setLocationRelativeTo(null);
	}

	// menuitem이 포함된 detailorder 반환해주는 메소드 필요함

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
