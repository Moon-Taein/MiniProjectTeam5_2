package frame;

import java.awt.Dimension;
import java.awt.Font;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import function.MainOrder;
import img.imageIcon;
import utilty.invisibility;

public class orderComplete extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private invisibility util = new invisibility();

	/**
	 * Create the frame.
	 */
	public orderComplete(MainOrder mo) {
		frameSetting(mo);
		buttonSetting();

	}

	private void frameSetting(MainOrder mo) {
		Font tftFont2 = getBMJUAFont(25f);

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(icon.getMainFrame().getIconWidth(), icon.getMainFrame().getIconHeight()));
		jlp.setLayout(null);

		JPanel panel4 = new JPanel();
		panel4.setBounds(63, 655, 666, 111);
		jlp.add(panel4, new Integer(2));
		panel4.setLayout(null);

		JLabel lblNewLabel_2_3 = new JLabel("New label");
		lblNewLabel_2_3.setBounds(178, 33, 86, 45);
		panel4.add(lblNewLabel_2_3);

		JLabel lblNewLabel_1_3 = new JLabel("New label");
		lblNewLabel_1_3.setBounds(25, 21, 111, 69);
		panel4.add(lblNewLabel_1_3);

		JLabel lblNewLabel_3_3 = new JLabel("New label");
		lblNewLabel_3_3.setBounds(366, 42, 72, 27);
		panel4.add(lblNewLabel_3_3);

		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(544, 36, 86, 39);
		panel4.add(lblNewLabel_6);

		JPanel panel2 = new JPanel();
		panel2.setBounds(63, 413, 666, 111);
		jlp.add(panel2, new Integer(2));
		panel2.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setBounds(178, 32, 86, 45);
		panel2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setBounds(25, 20, 111, 69);
		panel2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_3_1 = new JLabel("New label");
		lblNewLabel_3_1.setBounds(366, 41, 72, 27);
		panel2.add(lblNewLabel_3_1);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(544, 35, 86, 39);
		panel2.add(lblNewLabel_4);

		JPanel panel3 = new JPanel();
		panel3.setBounds(63, 534, 666, 111);
		jlp.add(panel3, new Integer(2));
		panel3.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("New label");
		lblNewLabel_2_2.setBounds(178, 32, 86, 45);
		panel3.add(lblNewLabel_2_2);

		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setBounds(25, 20, 111, 69);
		panel3.add(lblNewLabel_1_2);

		JLabel lblNewLabel_3_2 = new JLabel("New label");
		lblNewLabel_3_2.setBounds(366, 41, 72, 27);
		panel3.add(lblNewLabel_3_2);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(544, 35, 86, 39);
		panel3.add(lblNewLabel_5);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(63, 292, 666, 111);
		jlp.add(panel1, new Integer(2));

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(550, 40, 86, 39);
		panel1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(31, 25, 111, 69);
		panel1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(184, 37, 86, 45);
		panel1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(372, 46, 72, 27);
		panel1.add(lblNewLabel_3);

		JLabel lbl = new JLabel(icon.orderComplete());
		lbl.setBounds(0, 0, 800, 900);
		jlp.add(lbl, new Integer(2));

		setContentPane(jlp);

		setUndecorated(true);
		setVisible(true);
		setSize(800, 900);
		setLocationRelativeTo(null);
	}

	private void buttonSetting() {
		JButton completeBtn = new JButton(icon.bigOrderBtn());
		completeBtn.setBounds(0, 800, 800, 100);
		jlp.add(completeBtn, new Integer(2));
		util.invisible(completeBtn);
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
}