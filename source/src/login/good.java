package login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class good extends JFrame {
	public good() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u6D88\u606F");
		setBounds(new Rectangle(400, 300, 300, 200));
		getContentPane().setLayout(null);
		setAlwaysOnTop(true);
		ImageIcon img = new ImageIcon(getClass().getResource("good.png"));
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 274, 150);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u6240\u6709\u64CD\u4F5C\u90FD\u5DF2\u6210\u529F");
		label.setFont(new Font("YouYuan", Font.BOLD, 12));
		label.setBounds(141, 78, 123, 53);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u6210\u529F\u4E86");
		label_1.setFont(new Font("YouYuan", Font.BOLD, 25));
		label_1.setBounds(150, 24, 94, 30);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 123, 120);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(img);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
