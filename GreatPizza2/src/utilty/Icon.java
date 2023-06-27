package utilty;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Icon {
	
	public void invisibility(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
	}
	
	public ImageIcon getMainFrame1() {
		return new ImageIcon(getClass().getClassLoader().getResource("Frame1.png"));
	}
	public ImageIcon getOrderBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("OrderBtn.png"));
	}

}
