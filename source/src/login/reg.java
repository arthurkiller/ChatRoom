package login;

import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("serial")
public class reg extends  JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public reg() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u6CE8\u518C");
		setBounds(new Rectangle(300, 400, 400, 300));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 364, 240);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(48, 37, 73, 20);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(48, 87, 73, 20);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_2.setBounds(48, 139, 73, 20);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(170, 37, 161, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(170, 87, 161, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(170, 139, 161, 20);
		panel.add(textField_2);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Chat";
				Connection conn=null;
				try{
					String username="arthur";
					String password="asd";
					conn=DriverManager.getConnection(url,username,password);	
					Statement st1=conn.createStatement();
					
					String id=textField.getText();
					String passcode=textField_1.getText();
					boolean flag=false;
					
					//判断有无此用户ID
					String sql=("select * from userinfo where id=\'"+id+"\'"+";");				
					ResultSet rs=st1.executeQuery(sql);
					if(rs.next()){
						new bad("用户名重复").setVisible(true);
							flag=false;
						}
					else{
							flag=true;
						}
					
					//判断两个密码是否一致
					if (textField_1.getText().equals(textField_2.getText())) {
						flag=true;
					}
					else {
						flag=false;
						new bad("两次密码不一致").setVisible(true);
					}
					
					
					if(flag){
					sql="insert into userinfo values(?,?);";
					PreparedStatement st=conn.prepareStatement(sql);
					st.setString(1, id);
					st.setString(2, passcode);
									
					int n=st.executeUpdate();
					if(n==1){
						new good().setVisible(true);
					}
					else{
						new bad("数据库写入失败").setVisible(true);
					}
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
		button.setBounds(75, 190, 89, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(215, 190, 89, 23);
		panel.add(button_1);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
}
