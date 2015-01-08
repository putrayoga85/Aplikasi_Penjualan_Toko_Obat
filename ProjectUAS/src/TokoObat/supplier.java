package TokoObat;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;


public class supplier extends JFrame {
	private JTable table;
	private JTextField IDsupplier;
	private JTextField NamaSupplier;
	private DefaultTableModel tabelModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supplier frame = new supplier();
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
	public supplier() {
		super ("Supplier Obat");
		setBounds(0, 0, 925, 612);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 287, 889, 265);
		getContentPane().add(scrollPane);
		
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
		        
		        String ids = (String) tabelModel.getValueAt(a, 0);
		        IDsupplier.setText(ids);
		        String nama= (String) tabelModel.getValueAt(a, 1);
		        NamaSupplier.setText(nama);
		        IDsupplier.setEnabled(true);
			}
		});
		
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Nama Supplier");
		table.setModel(tabelModel);	
        tampilTabel();
		
		JLabel lblNewLabel = new JLabel("Supplier Obat");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(320, 11, 164, 58);
		getContentPane().add(lblNewLabel);
		
		JLabel IDSupplier = new JLabel("ID Supplier          :");
		IDSupplier.setBounds(38, 121, 103, 14);
		getContentPane().add(IDSupplier);
		
		JLabel lblNamaSupplier = new JLabel("Nama Supplier   :");
		lblNamaSupplier.setBounds(38, 169, 119, 14);
		getContentPane().add(lblNamaSupplier);
		
		IDsupplier = new JTextField();
		IDsupplier.setBounds(140, 118, 197, 20);
		getContentPane().add(IDsupplier);
		IDsupplier.setColumns(10);
		
		NamaSupplier = new JTextField();
		NamaSupplier.setColumns(10);
		NamaSupplier.setBounds(140, 166, 286, 20);
		getContentPane().add(NamaSupplier);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				 try
			        {
			        Connection konek = Koneksi.getCon();
			        String query = "INSERT INTO supplier VALUES(?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, IDsupplier.getText());
			        prepare.setString(2, NamaSupplier.getText());

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
		btnTambah.setBounds(493, 110, 89, 36);
		getContentPane().add(btnTambah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int opcion = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus ?", "Confirmasi", JOptionPane.YES_NO_OPTION);

				if (opcion == 0) {
					try
			        {
			            Connection konek = Koneksi.getCon();
			            String query = "DELETE FROM supplier WHERE idsup = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, IDsupplier.getText());
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
			            IDsupplier.setEnabled(true);
			            //refresh();
			        }
				} else {
				   System.out.print("Data Tidak Terhapus");
				}
				
				
			}
		});
		btnHapus.setBounds(609, 110, 89, 36);
		getContentPane().add(btnHapus);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Koneksi.getCon();
			        String query = "UPDATE supplier SET  namasup = ? WHERE idsup = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, NamaSupplier.getText());
			        prepare.setString(2, IDsupplier.getText());
			        
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
			            IDsupplier.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(493, 169, 89, 36);
		getContentPane().add(btnUbah);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IDsupplier.setText("");
				NamaSupplier.setText("");
			}
		});
		btnReset.setBounds(609, 169, 89, 36);
		getContentPane().add(btnReset);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Menu_Utama Menu = new Menu_Utama();
				Menu.setVisible(true);
				setVisible(false);
			}
		});
		btnKembali.setBounds(727, 110, 89, 36);
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
            String query = "SELECT * FROM supplier";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[2];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
