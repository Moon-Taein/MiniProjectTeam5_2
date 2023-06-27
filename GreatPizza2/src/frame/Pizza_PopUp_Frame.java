package frame;

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

import Function.Sql_Methods;

public class Pizza_PopUp_Frame extends JDialog {

	private JPanel contentPane;

	// 불고기피자 -> 불고기피자 라는 이름을 받아서 구현해보자
	public Pizza_PopUp_Frame(String target) {

		setModal(true);
		Sql_Methods sqm = new Sql_Methods();

		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel backgroundImage = new JLabel();
		backgroundImage.setBounds(0, 0, 800, 500);
		ImageIcon backImg = new ImageIcon("background2.png");
		backgroundImage.setIcon(backImg);
		contentPane.add(backgroundImage);

		JLabel imageLabel = new JLabel("image");
		imageLabel.setBounds(24, 36, 410, 380);
		// image icon 사용해서 각 피자의 이미지 사용해서 넣어줘야함
		ImageIcon ic = new ImageIcon(sqm.findPizzaImageMenuId("피자_" + target + "M"));
		imageLabel.setIcon(ic);
		backgroundImage.add(imageLabel);

		JLabel menu_name_Label = new JLabel("name");
		menu_name_Label.setBounds(517, 36, 87, 27);
		menu_name_Label.setText(target);
		backgroundImage.add(menu_name_Label);

		JButton sizeMButton = new JButton("M");
		sizeMButton.setBounds(459, 83, 97, 23);
		backgroundImage.add(sizeMButton);

		JButton sizeLButton = new JButton("L");
		sizeLButton.setBounds(459, 132, 97, 23);
		backgroundImage.add(sizeLButton);

		JLabel sizeMPrice = new JLabel("원");
		sizeMPrice.setBounds(595, 87, 57, 15);
		// 피자M의 가격
		sizeMPrice.setText(sqm.findPriceMenuId(target + "M") + "원");
		backgroundImage.add(sizeMPrice);

		JLabel sizeLPrice = new JLabel("원");
		sizeLPrice.setBounds(595, 136, 57, 15);
		// 피자L의 가격
		sizeLPrice.setText(sqm.findPriceMenuId(target + "L") + "원");
		backgroundImage.add(sizeLPrice);

		JLabel currentCount = new JLabel("1");
		currentCount.setBounds(551, 184, 25, 15);
		backgroundImage.add(currentCount);

		JButton minusCountButton = new JButton("<");
		minusCountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(currentCount.getText()) > 1) {
					String input = String.valueOf(Integer.parseInt(currentCount.getText()) - 1);
					currentCount.setText(input);
				}
			}
		});
		minusCountButton.setBounds(472, 180, 57, 23);
		backgroundImage.add(minusCountButton);

		JButton plusCountButton = new JButton(">");
		plusCountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(currentCount.getText()) < 5) {
					String input = String.valueOf(Integer.parseInt(currentCount.getText()) + 1);
					currentCount.setText(input);
				}
			}
		});
		plusCountButton.setBounds(571, 180, 57, 23);
		backgroundImage.add(plusCountButton);

		JButton addCheezeButton = new JButton("치즈추가");
		addCheezeButton.setBounds(459, 227, 97, 23);
		backgroundImage.add(addCheezeButton);

		JButton addToppingButton_5 = new JButton("토핑추가");
		addToppingButton_5.setBounds(571, 227, 97, 23);
		backgroundImage.add(addToppingButton_5);

		JLabel changeEdge_Label = new JLabel("엣지변경");
		changeEdge_Label.setBounds(460, 273, 57, 15);
		backgroundImage.add(changeEdge_Label);

		ButtonGroup edgeChoice = new ButtonGroup();

		JRadioButton EdgeRadioButton_1 = new JRadioButton("기본");
		EdgeRadioButton_1.setBounds(572, 269, 80, 23);
		backgroundImage.add(EdgeRadioButton_1);
		EdgeRadioButton_1.setBorderPainted(true);
		EdgeRadioButton_1.setSelected(true);
		edgeChoice.add(EdgeRadioButton_1);

		JRadioButton EdgeRadioButton_2 = new JRadioButton("노엣지");
		EdgeRadioButton_2.setBounds(572, 302, 80, 23);
		backgroundImage.add(EdgeRadioButton_2);
		EdgeRadioButton_2.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_2);

		JRadioButton EdgeRadioButton_3 = new JRadioButton("치즈크러스트");
		EdgeRadioButton_3.setBounds(532, 335, 120, 23);
		backgroundImage.add(EdgeRadioButton_3);
		EdgeRadioButton_3.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_3);

		JRadioButton EdgeRadioButton_4 = new JRadioButton("고구마무스");
		EdgeRadioButton_4.setBounds(532, 368, 120, 23);
		backgroundImage.add(EdgeRadioButton_4);
		EdgeRadioButton_4.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_4);

		JButton cancelButton = new JButton("취소");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton.setBounds(459, 411, 97, 23);
		backgroundImage.add(cancelButton);

		JButton addButton = new JButton("담기");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(571, 411, 97, 23);
		backgroundImage.add(addButton);

		setLocationRelativeTo(null);
	}

}
