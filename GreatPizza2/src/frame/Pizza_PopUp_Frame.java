package frame;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class Pizza_PopUp_Frame extends JFrame {

	private JPanel contentPane;

	public Pizza_PopUp_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel imageLabel = new JLabel("image");
		imageLabel.setBounds(35, 52, 250, 170);
		contentPane.add(imageLabel);

		JLabel menu_name_Label = new JLabel("name");
		menu_name_Label.setBounds(328, 31, 87, 27);
		contentPane.add(menu_name_Label);

		JButton sizeMButton = new JButton("M");
		sizeMButton.setBounds(328, 82, 97, 23);
		contentPane.add(sizeMButton);

		JButton sizeLButton = new JButton("L");
		sizeLButton.setBounds(328, 126, 97, 23);
		contentPane.add(sizeLButton);

		JLabel sizeMPrice = new JLabel("100원");
		sizeMPrice.setBounds(449, 86, 57, 15);
		contentPane.add(sizeMPrice);

		JLabel sizeLPrice = new JLabel("200원");
		sizeLPrice.setBounds(449, 130, 57, 15);
		contentPane.add(sizeLPrice);

		JButton minusCountButton = new JButton("<");
		minusCountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		minusCountButton.setBounds(324, 167, 97, 23);
		contentPane.add(minusCountButton);

		JLabel currentCount = new JLabel("1");
		currentCount.setBounds(427, 171, 25, 15);
		contentPane.add(currentCount);

		JButton plusCountButton = new JButton(">");
		plusCountButton.setBounds(448, 167, 97, 23);
		contentPane.add(plusCountButton);

		JButton addCheezeButton = new JButton("치즈추가");
		addCheezeButton.setBounds(328, 213, 97, 23);
		contentPane.add(addCheezeButton);

		JButton addToppingButton_5 = new JButton("토핑추가");
		addToppingButton_5.setBounds(448, 213, 97, 23);
		contentPane.add(addToppingButton_5);

		JLabel changeEdge_Label = new JLabel("엣지변경");
		changeEdge_Label.setBounds(40, 277, 57, 15);
		contentPane.add(changeEdge_Label);

		ButtonGroup edgeChoice = new ButtonGroup();

		JRadioButton EdgeRadioButton_1 = new JRadioButton("기본");
		EdgeRadioButton_1.setBounds(123, 273, 80, 23);
		contentPane.add(EdgeRadioButton_1);
		EdgeRadioButton_1.setBorderPainted(true);
		EdgeRadioButton_1.setSelected(true);
		edgeChoice.add(EdgeRadioButton_1);

		JRadioButton EdgeRadioButton_2 = new JRadioButton("노엣지");
		EdgeRadioButton_2.setBounds(215, 273, 80, 23);
		contentPane.add(EdgeRadioButton_2);
		EdgeRadioButton_2.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_2);

		JRadioButton EdgeRadioButton_3 = new JRadioButton("치즈크러스트");
		EdgeRadioButton_3.setBounds(307, 273, 120, 23);
		contentPane.add(EdgeRadioButton_3);
		EdgeRadioButton_3.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_3);

		JRadioButton EdgeRadioButton_4 = new JRadioButton("고구마무스");
		EdgeRadioButton_4.setBounds(441, 273, 120, 23);
		contentPane.add(EdgeRadioButton_4);
		EdgeRadioButton_4.setBorderPainted(true);
		edgeChoice.add(EdgeRadioButton_4);

		JButton cancelButton = new JButton("취소");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton.setBounds(147, 316, 97, 23);
		contentPane.add(cancelButton);

		JButton addButton = new JButton("담기");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(324, 316, 97, 23);
		contentPane.add(addButton);
	}
}
