package TokoObat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Menu_Utama extends JFrame {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_Utama frame = new Menu_Utama();
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
	public Menu_Utama() {
		super("Menu Utama");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 430);
		getContentPane().setLayout(null);
		
		JLabel lblMenuUtama = new JLabel("Toko Obat Herbal");
		lblMenuUtama.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblMenuUtama.setBounds(403, 11, 247, 47);
		getContentPane().add(lblMenuUtama);
		
		JButton btnMProduk = new JButton("Maintenance Produk");
		btnMProduk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produk mprod = new Produk();
				mprod.setVisible(true);
				setVisible(false);
			}
		});
		btnMProduk.setBounds(68, 102, 156, 58);
		getContentPane().add(btnMProduk);
		
		JButton btnMUser = new JButton("Maintenance Users");
		btnMUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users usr = new Users();
				usr.setVisible(true);
				setVisible(false);
			}
		});
		btnMUser.setBounds(434, 101, 156, 60);
		getContentPane().add(btnMUser);
		
		JButton btnMSupplier = new JButton("Maintenance Supplier");
		btnMSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				supplier msup = new supplier();
				msup.setVisible(true);
				setVisible(false);
			}
		});
		btnMSupplier.setBounds(250, 102, 164, 58);
		getContentPane().add(btnMSupplier);
		
		JButton btnLihatLaporan = new JButton("Lihat Laporan");
		btnLihatLaporan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Laporan lap = new Laporan();
				lap.setVisible(true);
				setVisible(false);
			}
		});
		btnLihatLaporan.setBounds(785, 102, 156, 58);
		getContentPane().add(btnLihatLaporan);
		
		JButton btnTransaksi = new JButton("Transaksi");
		btnTransaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kasir trans = new Kasir();
				trans.setVisible(true);
				setVisible(false);
			}
		});
		btnTransaksi.setBounds(614, 102, 150, 58);
		getContentPane().add(btnTransaksi);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login login = new login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		btnLogout.setBounds(434, 216, 156, 47);
		getContentPane().add(btnLogout);
	}
}
