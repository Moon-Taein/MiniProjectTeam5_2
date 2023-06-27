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
	public ImageIcon getMenuFrame() {
		return new ImageIcon(getClass().getClassLoader().getResource("MenuFrame.png"));
	}
	
	public ImageIcon getwhitePizzaBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("whitePizzaBtn.png"));
	}
	public ImageIcon getdarkPizzaBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("darkPizzaBtn.png"));
	}
	public ImageIcon getwhiteSideBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("whiteSideBtn.png"));
	}
	public ImageIcon getdarkSideBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("darkSideBtn.png"));
	}
	public ImageIcon getwhiteDrinkBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("whiteDrinkBtn.png"));
	}
	public ImageIcon getdarkDrinkBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("darkDrinkBtn.png"));
	}
	public ImageIcon getwhiteMakePizzaBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("whiteMakePizzaBtn.png"));
	}
	public ImageIcon getdarkMakePizzaBtn() {
		return new ImageIcon(getClass().getClassLoader().getResource("darkMakePizzaBtn.png"));
	}

}
