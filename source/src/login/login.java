package login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import chatroom.client;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class login extends  JFrame{
	private JTextField textField;
	private JTextField textField_1 ;
	public login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u767B\u5F55");
		setBounds(new Rectangle(300, 300, 450, 300));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 424, 250);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u804A\u5929\u5BA4");
		label.setFont(new Font("YouYuan", Font.PLAIN, 30));
		label.setBounds(93, 11, 253, 42);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D");
		label_1.setBounds(54, 86, 58, 23);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801");
		label_2.setBounds(54, 137, 46, 14);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(200, 87, 146, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(200, 134, 146, 20);
		panel.add(textField_1);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id =new String(textField.getText());
				String passcode=new String(textField_1.getText());
				
				//Ê¹ÓÃJDBCÁ´½ÓÊý¾Ý¿â
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				String url="jdbc:sqlserver://127.0.0.1;DatabaseName=Chat";
				Connection conn=null;
				try{
					String username="arthur";
					String password="asd";

					boolean flag=false;					
					conn=DriverManager.getConnection(url,username,password);
					Statement st=conn.createStatement();
					
					String sql=("select * from userinfo where id=\'"+id+"\'"+";");				
					ResultSet rs=st.executeQuery(sql);
					if(rs.next()){
						String tpasscode=new String();
						tpasscode=rs.getString(2);
						if(passcode.equals(tpasscode)){
							flag=true;
						}
						else{
							flag=false;
						}
					}
					
					if(flag){	
						dispose();
						new client(id).setVisible(true);
					}
					else{
						new bad("µÇÂ¼Ê§°Ü").setVisible(true);
						dispose();
					//Ê§°Ü
					}
					
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					if(conn!=null){
						try{
							conn.close();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				
			}
		});
		
		button.setBounds(54, 202, 100, 23);
		panel.add(button);
		JButton button_1 = new JButton("\u6CE8\u518C\u65B0\u7528\u6237");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new reg().setVisible(true);
			}
		});
		button_1.setBounds(164, 202, 100, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("\u9000\u51FA");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setBounds(274, 202, 100, 23);
		panel.add(button_2);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String args[]){
		new login().setVisible(true);
	}
}
