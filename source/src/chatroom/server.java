package chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.*;

import javax.swing.JFrame;

import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class server extends JFrame{
	public server() {
		setResizable(false);
		setTitle("\u4FE1\u9E3D\u4F20\u4E66\u670D\u52A1");
		setBounds(new Rectangle(400, 200, 300, 200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 274, 150);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("\u542F\u52A8\u670D\u52A1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prot=Integer.valueOf(textField.getText().trim());
				new Thread(new gogo()).start();
				textArea.append("服务已启动！"+"\n");
			}
		});
		btnNewButton.setBounds(33, 116, 87, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u505C\u6B62\u670D\u52A1");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(s!=null){
						s.close();
					}
					if(ss!=null){
						ss.close();
					}	
					textArea.append("服务成功停止！"+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(153, 116, 87, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("\u670D\u52A1\u7AEF\u53E3");
		lblNewLabel.setBounds(39, 91, 68, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(146, 88, 89, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setText("8989");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 8, 254, 72);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	static boolean started = false;
	static boolean bconnected = false;
	static ServerSocket ss = null;
	static Socket s = null;
	static int prot=8989;
	ArrayList<Client> clients = new ArrayList<Client>();
	private JTextField textField;
	private JTextArea textArea;
	
	public static void main(String args[]){
		new server().setVisible(true);
	}

	public void start() {
		try {
			ss = new ServerSocket(prot);
			started = true;
		} catch (BindException e) {
			textArea.append("端口忙"+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (started) {
				s = ss.accept();
				Client c = new Client(s);
				textArea.append("a client connected!"+"\n");
				new Thread(c).start();
				clients.add(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class gogo implements Runnable{
		public void run() {
			start();
		}
	}
	class Client implements Runnable {
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;

		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			bconnected = true;
		}

		public void send(String str) {
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (NullPointerException e) {
				textArea.append("对方已经关闭流"+"\n");
			} catch (IOException e) {
				clients.remove(this);
				textArea.append("对方已退出"+"\n");
			}
		}

		public void run() {
			try {
				while (bconnected) {
					String str = dis.readUTF();
					System.out.println(str);
					for (int i = 0; i < clients.size(); i++) {
						Client c = clients.get(i);
						c.send(str);
					}
				}
			} catch (EOFException e) {
				System.out.println("client closed!");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (s != null)
						s.close();
					if (dis != null)
						dis.close();
					if (dos != null) {
						dos.close();
						dos = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}