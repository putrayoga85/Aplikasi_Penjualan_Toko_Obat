package TokoObat;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Choice;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Produk extends JFrame {
	private JTable table;
	private JTextField IDProduk;
	private JTextField IDSupplier;
	private JTextField NamaProduk;
	private JTextField Harga;
	private DefaultTableModel tabelModel;
	private DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	private JTextField stok;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produk frame = new Produk();
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
	public Produk() {
		super ("Produk Obat");
		setBounds(0, 0, 925, 612);
		getContentPane().setLayout(null);
		
		JComboBox cmbNama = new JComboBox();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 412, 889, 160);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String idp = (String) tabelModel.getValueAt(a, 0);
		        IDProduk.setText(idp);
		        String ids= (String) tabelModel.getValueAt(a, 2);
		        IDSupplier.setText(ids);
		        String nps = (String) tabelModel.getValueAt(a, 1);
		        NamaProduk.setText(nps);
		        int hrg = (Integer) tabelModel.getValueAt(a, 3);
		        Harga.setText(""+hrg);
		        int stk = (Integer) tabelModel.getValueAt(a, 4);
		        stok.setText(""+stk);
		        IDProduk.setEnabled(true);
		        IDSupplier.setEnabled(true);
		        NamaProduk.setEnabled(true);
		        cmbNama.setEnabled(true);
				
		        
			}
		});
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Harga Produk");
        tabelModel.addColumn("Stok");
		table.setModel(tabelModel);	
        tampilTabel();
		
		JLabel lblNewLabel = new JLabel("ID Produk            :");
		lblNewLabel.setBounds(22, 120, 96, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblIdsupplier = new JLabel("ID Supplier          :");
		lblIdsupplier.setBounds(22, 172, 96, 14);
		getContentPane().add(lblIdsupplier);
		
		JLabel lblNamaProduk = new JLabel("Nama Produk    :");
		lblNamaProduk.setBounds(22, 220, 96, 14);
		getContentPane().add(lblNamaProduk);
		
		JLabel lblHarga = new JLabel("Harga                  :");
		lblHarga.setBounds(22, 275, 96, 14);
		getContentPane().add(lblHarga);
		
		JLabel lblStok = new JLabel("Stok                    :");
		lblStok.setBounds(22, 317, 96, 14);
		getContentPane().add(lblStok);
		
		
		IDProduk = new JTextField();
		IDProduk.setBounds(128, 118, 280, 18);
		getContentPane().add(IDProduk);
		IDProduk.setColumns(10);
		
		IDSupplier = new JTextField();
		IDSupplier.setColumns(10);
		IDSupplier.setBounds(128, 170, 222, 18);
		getContentPane().add(IDSupplier);
		
		NamaProduk = new JTextField();
		NamaProduk.setColumns(10);
		NamaProduk.setBounds(128, 218, 158, 18);
		getContentPane().add(NamaProduk);
		
		Harga = new JTextField();
		Harga.setColumns(10);
		Harga.setBounds(128, 273, 78, 18);
		getContentPane().add(Harga);
		
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			        Connection konek = Koneksi.getCon();
			        String query = "INSERT INTO produk VALUES(?,?,?,?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, IDProduk.getText());
			        prepare.setString(2, NamaProduk.getText());
			        prepare.setString(3, IDSupplier.getText());
			        prepare.setInt(4, Integer.parseInt(Harga.getText()));
			        prepare.setString(5, stok.getText());


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
		btnTambah.setBounds(128, 361, 89, 32);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Koneksi.getCon();
			        String query = "UPDATE produk SET  harga = ?, stok = ? WHERE id = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, Harga.getText());
			        prepare.setString(2, stok.getText());
			        prepare.setString(3, IDProduk.getText());
			        
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
			            IDProduk.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(261, 361, 89, 32);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus ?", "Confirmasi", JOptionPane.YES_NO_OPTION);
				if (opcion == 0) {
					try
			        {
			            Connection konek = Koneksi.getCon();
			            String query = "DELETE FROM produk WHERE id = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, IDProduk.getText());
			            
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
			            IDProduk.setEnabled(true);
			            //refresh();
			        }
				} else {
				   System.out.print("Data Tidak Terhapus");
				}
				
				
			}
		});
		btnHapus.setBounds(382, 361, 89, 32);
		getContentPane().add(btnHapus);
		
		JLabel lblNewLabel_1 = new JLabel("Produk Obat");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(311, 23, 143, 54);
		getContentPane().add(lblNewLabel_1);
		
		cmbNama.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String nama=cmbNama.getSelectedItem().toString();
				try {
		            Connection konek = Koneksi.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select idsup from supplier where namasup='"+nama+"'");
		            while (rs.next()) {
		                IDSupplier.setText(rs.getString(1));
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
			}
		});
		cmbNama.setBounds(382, 169, 189, 20);
		getContentPane().add(cmbNama);
		
		//akses method isiSupplier
		isiSupplier();
		//model comboBox di set sesuai comboBoxModel pada method isiSupplier
		cmbNama.setModel(model);
		
		JButton btnKeluar = new JButton("Reset");
		btnKeluar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				IDProduk.setText("");
				IDSupplier.setText("");
				NamaProduk.setText("");
				Harga.setText("");
				stok.setText("");
			}
		});
		btnKeluar.setBounds(503, 361, 89, 32);
		getContentPane().add(btnKeluar);
		
		stok = new JTextField();
		stok.setColumns(10);
		stok.setBounds(128, 314, 42, 18);
		getContentPane().add(stok);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu_Utama Menu = new Menu_Utama();
				Menu.setVisible(true);
				setVisible(false);
			}
		});
		btnKembali.setBounds(625, 361, 89, 32);
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
            String query = "SELECT * FROM produk";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	
	public void isiSupplier()
	{
		try {
            Connection konek = Koneksi.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select namasup from supplier");
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}
}
