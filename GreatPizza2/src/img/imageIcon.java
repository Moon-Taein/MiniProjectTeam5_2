package img;

import javax.swing.ImageIcon;

public class imageIcon {
	public ImageIcon getOrderBtnKor() {
		return new ImageIcon(getClass().getClassLoader().getResource("OrderBtnKor.png"));
	}
	public ImageIcon getOrderBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("OrderBtn.png"));
	}
	public ImageIcon getMainFrame() {
		return new ImageIcon(getClass().getClassLoader().getResource("MainFrame.png"));
	}
	public ImageIcon getMenuTapPizza() {
		return new ImageIcon(getClass().getClassLoader().getResource("MenuTapPizza.png"));
	}
	public ImageIcon getMenuTapSide() {
		return new ImageIcon(getClass().getClassLoader().getResource("MenuTapSide.png"));
	}
	public ImageIcon getMenuTapDrink() {
		return new ImageIcon(getClass().getClassLoader().getResource("MenuTapDrink.png"));
	}
	public ImageIcon getMenuTapMakePizza() {
		return new ImageIcon(getClass().getClassLoader().getResource("MenuTapMakePizza.png"));
	}

}
