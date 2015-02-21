package chatroom;

import javax.swing.JFrame;

import java.awt.Rectangle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import login.bad;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class client extends JFrame{
	String username; 
	static JTextArea textArea_1 = new JTextArea();
	static JTextArea textArea = new JTextArea();
	static private JTextField textField_1;
	static private JTextField textField_2;
	
	static Socket s = null;
	static DataOutputStream dos = null;
	static DataInputStream dis = null;
	private static boolean bConnected = false;
	private static Thread tRecv = null;

	public client(String uname) {
		setResizable(false);
		username=uname;// 用户名
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(300, 200, 590, 480));
		setTitle("\u4FE1\u9E3D\u4F20\u4E66");
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 231, 564, 45);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblip = new JLabel("\u804A\u5929\u5BA4IP");
		lblip.setBounds(207, 11, 55, 23);
		panel_1.add(lblip);
		
		JLabel label_1 = new JLabel("\u7AEF\u53E3");
		label_1.setBounds(368, 11, 55, 23);
		panel_1.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(272, 12, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText("127.0.0.1");
		
		textField_2 = new JTextField();
		textField_2.setBounds(433, 12, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText("8989");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 276, 564, 155);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		
		textArea_1.setBounds(10, 11, 544, 100);
		panel_2.add(textArea_1);
		
		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String str = username + ": " + labtime.getText() + "\n"+ sendtext.getText().trim();	
				String str = username + ": " + "\n"+ textArea_1.getText().trim();
				textArea_1.setText("");
				try {
					dos.writeUTF(str);
					dos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button.setBounds(240, 122, 89, 23);
		panel_2.add(button);
		
		JButton button_1 = new JButton("\u6E05\u7A7A\u6D88\u606F\u7A97");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("");
			}
		});
		button_1.setBounds(339, 122, 89, 23);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("\u9000\u51FA");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setBounds(438, 122, 89, 23);
		panel_2.add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 209);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(textArea);
		connect();
		start();
	}
	
	public void connect() {
		try {
			s = new Socket(textField_1.getText().trim(), Integer.valueOf(textField_2.getText().trim()));
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			bConnected = true;
			System.out.println("connected!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			new bad("服务器未启动，请先启动服务器!").setVisible(true);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void disconnected() {// 释放链接
		try {
			bConnected = false;
			tRecv.join(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				dis.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class OtherClient implements Runnable {
		public void run() {
			try {
				while (bConnected) {
					String str = dis.readUTF();
					textArea.setText(textArea.getText() + str + '\n');
				}
			} catch (EOFException e) {
				new bad("退出了，byte！").setVisible(true);
			} catch (SocketException e) {
				new bad("退出了，byte！").setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		OtherClient oc = new OtherClient();
		tRecv = new Thread(oc);
		tRecv.start();
	}
}
