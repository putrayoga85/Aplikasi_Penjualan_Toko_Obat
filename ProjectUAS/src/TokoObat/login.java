package TokoObat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class login extends JFrame {


	private JLabel backgroundlogin;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	public static String nama;
	public static javax.swing.JFrame form_admin;
	public static javax.swing.JFrame form_Kasir;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		super("Menu Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		form_admin=new Menu_Utama();
		form_Kasir=new Kasir();
		
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setForeground(new Color(255, 200, 0));
		lblNewLabel.setBounds(39, 105, 75, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.ORANGE);
		lblPassword.setBounds(39, 150, 75, 27);
		contentPane.add(lblPassword);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(124, 108, 299, 20);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(124, 153, 154, 20);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Masuk");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
			        Connection Con=Koneksi.getCon();
			        String sql = "Select * from login where username='"+txtUserName.getText()+"' and password='" +txtPassword.getText()+ "'";
			        Statement st=Con.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()){
			        if (rs.getString(1).equalsIgnoreCase("admin"))
			        {
			            JOptionPane.showMessageDialog(null, "Login berhasil");
			            form_admin.show();
			            setVisible(false);
			  
			        }else{
			        	JOptionPane.showMessageDialog(null, "Login Kasir Berhasil");
			            form_Kasir.show();
			            setVisible(false);
			          
			        }
			        }else{
			        JOptionPane.showMessageDialog(null, "Login gagal");
			        }
			        }catch (Exception ex){
			        JOptionPane.showMessageDialog(null, "Login gagal (koneksi)");
			        }
			}
		});
		btnNewButton.setBounds(229, 222, 89, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Menu Login Toko Obat");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setForeground(new Color(127, 255, 0));
		lblNewLabel_1.setBounds(87, 29, 326, 65);
		contentPane.add(lblNewLabel_1);
		
		backgroundlogin = new JLabel("");
		backgroundlogin.setIcon(new ImageIcon("/C:/Users/Yoga/workspace/ProjectUAS/backgroundlogin.jpg"));
		backgroundlogin.setBounds(0,0,617,375);
		getContentPane().add(backgroundlogin);
		
	}
}
