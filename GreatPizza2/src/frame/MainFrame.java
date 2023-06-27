package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utilty.Icon;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private Icon icon = new Icon();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		JLabel lbl = new JLabel(icon.getMainFrame1());
		
		JButton orderBtn = new JButton(icon.getOrderBtn());
		orderBtn.setBounds(250, 650, 300, 100);
		
		icon.invisibility(orderBtn);//버튼 투명화
		
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		JLayeredPane jpn = new JLayeredPane();
		
		jpn.setPreferredSize(new Dimension(800,900));
		
		//불러온 이미지의 너비와 높이사이즈
		lbl.setBounds(0, 0, icon.getMainFrame1().getIconWidth(), icon.getMainFrame1().getIconHeight());
		
		jpn.add(lbl, new Integer(1));
		jpn.add(orderBtn, new Integer(2));
		
		setContentPane(jpn);
		
		setVisible(true);
		setSize(800, 900);
		setLocationRelativeTo(null);// 창이 화면중앙에 위치시킴
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
