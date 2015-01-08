package TokoObat;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Users extends JFrame {
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTable table;
	private DefaultTableModel tabelModel;
	private DefaultComboBoxModel model = new DefaultComboBoxModel(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users frame = new Users();
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
	public Users() {
		super ("Users");
		setBounds(0, 0, 925, 612);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 289, 889, 133);
		getContentPane().add(scrollPane);
		
		final JComboBox cmbNamaUser = new JComboBox();
		cmbNamaUser.setModel(new DefaultComboBoxModel(new String[] {"admin","kasir"}));
		cmbNamaUser.setBounds(127, 220, 108, 20);
		getContentPane().add(cmbNamaUser);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		       
		        String usr = (String) tabelModel.getValueAt(a, 0);
		        txtUsername.setText(usr);
		        String pass = (String) tabelModel.getValueAt(a, 1);
		        txtPassword.setText(pass);
		        String cbn = (String) tabelModel.getValueAt(a, 2);
		        cmbNamaUser.setSelectedItem(cbn);
		        txtUsername.setEnabled(false);
			}
		});
		
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("Username");
        tabelModel.addColumn("Password");
        tabelModel.addColumn("Status");
		table.setModel(tabelModel);	
        tampilTabel();
		
		JLabel lblNewLabel = new JLabel("USERS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(379, 45, 83, 31);
		getContentPane().add(lblNewLabel);
		
		JLabel Username = new JLabel("Username  :");
		Username.setBounds(43, 118, 71, 14);
		getContentPane().add(Username);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setBounds(43, 170, 71, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblStatus = new JLabel("Status         :");
		lblStatus.setBounds(43, 223, 71, 14);
		getContentPane().add(lblStatus);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(124, 115, 257, 20);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(124, 167, 156, 20);
		getContentPane().add(txtPassword);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = Koneksi.getCon();
		        String query = "INSERT INTO login VALUES(?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, txtUsername.getText());
		        prepare.setString(2, txtPassword.getText());
		        prepare.setString(3, (String) cmbNamaUser.getSelectedItem());

		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		            tampilTabel();
		            //refresh();
		        }
			}
		});
		btnTambah.setBounds(416, 106, 96, 38);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Koneksi.getCon();
			        String query = "UPDATE login SET password = ?, status = ? WHERE username = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, txtPassword.getText());
			        prepare.setString(2, (String) cmbNamaUser.getSelectedItem());
			        prepare.setString(3, txtUsername.getText());
			      
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            txtUsername.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(416, 170, 96, 38);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus ?", "Confirmasi", JOptionPane.YES_NO_OPTION);

				if (opcion == 0) { //The ISSUE is here
					try
			        {
			            Connection konek = Koneksi.getCon();
			            String query = "DELETE FROM login WHERE username = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, txtUsername.getText());
			            prepare.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
			            prepare.close();
			        }
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            txtUsername.setEnabled(true);
			            //refresh();
			        }
				} else {
				   System.out.print("Data Tidak Terhapus");
				}
				
			}
		});
		btnHapus.setBounds(416, 240, 96, 38);
		getContentPane().add(btnHapus);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Menu_Utama Menu = new Menu_Utama();
				Menu.setVisible(true);
				setVisible(false);
			}
		});
		btnKembali.setBounds(532, 106, 96, 38);
		getContentPane().add(btnKembali);
	}
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Koneksi.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM login";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

}
