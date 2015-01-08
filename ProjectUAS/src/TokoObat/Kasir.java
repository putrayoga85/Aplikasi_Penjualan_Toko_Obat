package TokoObat;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class Kasir extends JFrame {
	
	private JTextField namabeli;
	private JLabel tanggal;
	private JLabel lblIdTransaksi;
	private JLabel lblNamaProduct;
	private JTextField namaproduk;
	private JLabel lblHarga;
	private JTextField harga;
	private JLabel lblJumlah;
	private JLabel lblTotal;
	private JLabel label_1;
	private JLabel lblJumlahBarang;
	private JLabel label_2;
	private JLabel lblBayar;
	private JTextField subtotal;
	private JLabel lblRp;
	private JButton btnBayar;
	private JTable table;
	private DefaultTableModel tabelModel;
	private JLabel label;
	private JLabel lblNamaPembeli;
	private JButton btnBeli;
	private JLabel lblIdProduct;
	private JSpinner spinner;
	private JButton btnTambah;
	private static String dtrans;
	private static int jumlahBeli=0;
	private JComboBox cmbKasir;
	private DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	private JLabel lblTglTransaksi;
	private JLabel lblKasirTokoObat;
	/**
	 * Create the frame.
	 */
	public void disable()
	{
		lblNamaPembeli.setEnabled(false);
		namabeli.setEnabled(false);
		btnBeli.setEnabled(false);
	}
	public void enabled()
	{
		table.setEnabled(true);
		lblIdProduct.setEnabled(true);
		cmbKasir.setEnabled(true);
		lblNamaProduct.setEnabled(true);
		lblHarga.setEnabled(true);
		lblJumlah.setEnabled(true);
		spinner.setEnabled(true);
		btnTambah.setEnabled(true);
		lblJumlahBarang.setEnabled(true);
		lblTotal.setEnabled(true);
		lblBayar.setEnabled(true);
		subtotal.setEnabled(true);
		btnBayar.setEnabled(true);
	}
	public void setTanggal()
    {
        java.util.Date skrng = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        tanggal.setText(kal.format(skrng));
    }
	public void autoNumber()
	{
		try
		{
			Connection konek = Koneksi.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM transaksi ";
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				label.setText("T001");
			}
			else{	
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					label.setText("T00"+IDPesan);

			}
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	public void autoDtrans()
	{
		try
		{
			Connection konek = Koneksi.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM dtransaksi ";
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				dtrans="dt001";
			}
				else
				{
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					String IDFix = "00" + IDPesan;
					dtrans="dt" + IDFix;
				}
					
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	
	public void tampilTotal()
    {
        String kpesan=label.getText();
         try {
            Connection konek = Koneksi.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select SUM(subtotal) from dtransaksi where idtrans='"+kpesan+"'");
            while (rs.next()) {
                label_1.setText(""+rs.getInt(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

	/**
	 * Create the frame.
	 */
	public Kasir() {
		super("Kasir");
		autoDtrans();
		setBounds(100, 100, 850, 530);
		getContentPane().setLayout(null);
		lblNamaPembeli = new JLabel("Nama Pembeli   :");
		lblNamaPembeli.setBounds(10, 128, 100, 14);
		getContentPane().add(lblNamaPembeli);
		
		namabeli = new JTextField();
		namabeli.setBounds(110, 125, 170, 20);
		getContentPane().add(namabeli);
		namabeli.setColumns(10);
		
		btnBeli = new JButton("Beli");
		btnBeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = Koneksi.getCon();
		        String query = "INSERT INTO transaksi VALUES(?,?,?,?,0)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, label.getText());
		        prepare.setString(2, tanggal.getText());
		        prepare.setString(3, login.nama);
		        prepare.setString(4, namabeli.getText());


		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        tampilTabel();
		        disable();
		        enabled();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
			}
		});
		btnBeli.setBounds(290, 124, 89, 23);
		getContentPane().add(btnBeli);
		
		tanggal = new JLabel("-");
		tanggal.setBounds(663, 100, 83, 23);
		getContentPane().add(tanggal);
		setTanggal();
		
		lblIdTransaksi = new JLabel("ID transaksi        :");
		lblIdTransaksi.setBounds(10, 103, 100, 14);
		getContentPane().add(lblIdTransaksi);
		
		Panel panel = new Panel();
		panel.setBounds(10, 164, 814, 318);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblIdProduct = new JLabel("ID Produk          :");
		lblIdProduct.setEnabled(false);
		lblIdProduct.setBounds(26, 154, 107, 20);
		panel.add(lblIdProduct);
		
		lblNamaProduct = new JLabel("Nama Produk   :");
		lblNamaProduct.setEnabled(false);
		lblNamaProduct.setBounds(26, 185, 107, 14);
		panel.add(lblNamaProduct);
		
		namaproduk = new JTextField();
		namaproduk.setEnabled(false);
		namaproduk.setBounds(131, 182, 209, 20);
		panel.add(namaproduk);
		namaproduk.setColumns(10);
		
		lblHarga = new JLabel("Harga                 :");
		lblHarga.setEnabled(false);
		lblHarga.setBounds(26, 216, 107, 14);
		panel.add(lblHarga);
		
		harga = new JTextField();
		harga.setEnabled(false);
		harga.setBounds(131, 213, 120, 20);
		panel.add(harga);
		harga.setColumns(10);
		
		lblJumlah = new JLabel("Jumlah              :");
		lblJumlah.setEnabled(false);
		lblJumlah.setBounds(26, 247, 107, 14);
		panel.add(lblJumlah);
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setBounds(131, 241, 46, 20);
		panel.add(spinner);
		
		btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = Koneksi.getCon();
		        String query = "INSERT INTO dtransaksi VALUES(?,?,?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, dtrans);
		        prepare.setString(2, label.getText());
		        prepare.setString(3, (String) cmbKasir.getSelectedItem());
		        prepare.setInt(4, (Integer)spinner.getValue());
		        prepare.setInt(5,  Integer.valueOf(harga.getText())*(Integer) spinner.getValue());

		        prepare.executeUpdate();
		        jumlahBeli+=1;
		        label_2.setText(""+jumlahBeli);
		        JOptionPane.showMessageDialog(null, "Data pembelian disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data pembelian gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
		        
		        //tampil total
				autoDtrans();
		        tampilTotal();
		         
		         //update total database
		          try{
		        Connection konek = Koneksi.getCon();
		        String query = "UPDATE transaksi SET total = ? WHERE idtrans = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, Integer.parseInt(label_1.getText()));
		        prepare.setString(2, label.getText());
		        
		        prepare.executeUpdate();
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal diubah");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
		          
		          
		        //kurang stok  
		        try{
		        Connection konek = Koneksi.getCon();
		        String query = "UPDATE produk SET stok = stok-? WHERE id = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, (Integer) spinner.getValue());
		        prepare.setString(2, (String) cmbKasir.getSelectedItem());
		        
		        prepare.executeUpdate();
		        tampilTabel();
	            table.setModel(tabelModel);	
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Stok gagal dikurangi");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
			}
		});
		btnTambah.setEnabled(false);
		btnTambah.setBounds(330, 153, 89, 23);
		panel.add(btnTambah);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 794, 121);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Product");
        tabelModel.addColumn("Nama Product");
        tabelModel.addColumn("Harga");
        tabelModel.addColumn("Jumlah");
        tabelModel.addColumn("Total Harga");
		table.setModel(tabelModel);	
        tampilTabel();
        table.setEnabled(false);
		
		lblTotal = new JLabel("Total                       :");
		lblTotal.setEnabled(false);
		lblTotal.setBounds(485, 185, 107, 14);
		panel.add(lblTotal);
		
		label_1 = new JLabel("-");
		label_1.setBounds(591, 185, 46, 14);
		panel.add(label_1);
		
		lblJumlahBarang = new JLabel("Jumlah Barang    :");
		lblJumlahBarang.setEnabled(false);
		lblJumlahBarang.setBounds(485, 157, 107, 14);
		panel.add(lblJumlahBarang);
		
		label_2 = new JLabel("-");
		label_2.setBounds(591, 157, 46, 14);
		panel.add(label_2);
		
		lblBayar = new JLabel("Sub Total               :");
		lblBayar.setEnabled(false);
		lblBayar.setBounds(485, 216, 107, 14);
		panel.add(lblBayar);
		
		subtotal = new JTextField();
		subtotal.setEnabled(false);
		subtotal.setBounds(614, 213, 89, 20);
		panel.add(subtotal);
		subtotal.setColumns(10);
		
		lblRp = new JLabel("Rp.");
		lblRp.setBounds(591, 216, 23, 14);
		panel.add(lblRp);
		
		btnBayar = new JButton("Bayar");
		btnBayar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kembalian =Integer.parseInt(subtotal.getText())-Integer.parseInt(label_1.getText());
	            JOptionPane.showMessageDialog(null,kembalian);
				autoNumber();
				tampilTabel();
				jumlahBeli=0;
			}
		});
		btnBayar.setEnabled(false);
		btnBayar.setBounds(713, 212, 91, 23);
		panel.add(btnBayar);
		
		cmbKasir = new JComboBox();
		cmbKasir.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent arg0) {
				String nama=cmbKasir.getSelectedItem().toString();
				try {
		            Connection konek = Koneksi.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select nama,harga from produk where id='"+nama+"'");
		            while (rs.next()) {
		                namaproduk.setText(rs.getString(1));
		                harga.setText(""+rs.getInt(2));
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
			}
		});
		cmbKasir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		cmbKasir.setBounds(131, 154, 174, 20);
		panel.add(cmbKasir);
		
		label = new JLabel("-");
		label.setBounds(110, 104, 46, 14);
		getContentPane().add(label);
		
		autoNumber();
		
		//akses method isiSupplier
		isiProduk();
		//model comboBox di set sesuai comboBoxModel pada method isiSupplier
		cmbKasir.setModel(model);
		
		lblTglTransaksi = new JLabel("Tgl transaksi    :");
		lblTglTransaksi.setBounds(568, 104, 100, 14);
		getContentPane().add(lblTglTransaksi);
		
		lblKasirTokoObat = new JLabel("KASIR TOKO OBAT");
		lblKasirTokoObat.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblKasirTokoObat.setBounds(317, 11, 214, 41);
		getContentPane().add(lblKasirTokoObat);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login login = new login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		btnLogOut.setBounds(389, 124, 89, 23);
		getContentPane().add(btnLogOut);
	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Koneksi.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT dt.idbarang,p.nama,p.harga,dt.jumlah,dt.subtotal FROM transaksi t join dtransaksi dt on t.idtrans=dt.idtrans join produk p on dt.idbarang=p.id where dt.idtrans='"+label.getText()+"'";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
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
	
	public void isiProduk()
	{
		try {
            Connection konek = Koneksi.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select id from produk");
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}
}
