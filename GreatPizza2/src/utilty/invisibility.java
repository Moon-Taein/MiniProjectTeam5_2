package utilty;

import javax.swing.JButton;

public class invisibility {
	
	public void invisible(JButton button) {		
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
	}

}
