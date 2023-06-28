package utilty;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class invisibility {
	
	public void invisible(JButton button) {		
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
	}
	public void invisibleRadio(JRadioButton rBtn) {		
		rBtn.setOpaque(false);
		rBtn.setContentAreaFilled(false);
		rBtn.setBorderPainted(false);
		rBtn.setFocusPainted(false);
	}
	


}
