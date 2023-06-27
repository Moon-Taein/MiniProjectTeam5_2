package img;

import javax.swing.ImageIcon;

public class imageIcon {
	public ImageIcon getMainFrame() {
		return new ImageIcon(getClass().getClassLoader().getResource("MainFrame.png"));
	}
	public ImageIcon getOrderBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("OrderBtn.png"));
	}
	public ImageIcon getMenuTapPizza() {
		return new ImageIcon(getClass().getClassLoader().getResource("MenuTapPizza.png"));
	}

}
