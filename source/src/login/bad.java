package login;

import javax.swing.JFrame;

import java.awt.Rectangle;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class bad extends JFrame{
	static JLabel label_1 = new JLabel();
	public bad() {
		ImageIcon img = new ImageIcon(getClass().getResource("bad.png"));
		setAlwaysOnTop(true);
		getContentPane().setSize(125, 172);
		getContentPane().setLocation(0, -8);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u6D88\u606F");	
		setBounds(new Rectangle(400, 300, 300, 200));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 274, 172);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u51FA\u9519\u5566");
		label.setFont(new Font("YouYuan", Font.BOLD, 25));
		label.setBounds(155, 39, 94, 49);
		panel.add(label);
		
		label_1 = new JLabel("\u5F88\u62B1\u6B49\uFF0C\u4F46\u662F\u7A0B\u5E8F\u51FA\u9519\u4E86");
		label_1.setFont(new Font("YouYuan", Font.BOLD, 12));
		label_1.setBounds(32, 123, 232, 49);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 127, 121);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(img);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public bad(String info){
		ImageIcon img = new ImageIcon(getClass().getResource("bad.png"));
		setAlwaysOnTop(true);
		getContentPane().setSize(125, 172);
		getContentPane().setLocation(0, -8);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u6D88\u606F");	
		setBounds(new Rectangle(400, 300, 300, 200));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 274, 172);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u51FA\u9519\u5566");
		label.setFont(new Font("YouYuan", Font.BOLD, 25));
		label.setBounds(155, 39, 94, 49);
		panel.add(label);
		
		label_1 = new JLabel("\u5F88\u62B1\u6B49\uFF0C\u4F46\u662F\u7A0B\u5E8F\u51FA\u9519\u4E86");
		label_1.setFont(new Font("YouYuan", Font.BOLD, 12));
		label_1.setBounds(32, 123, 232, 49);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 125, 112);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(img);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		label_1.setText(info);
	}
}

