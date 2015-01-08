package TokoObat;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class Laporan extends JFrame {
	private JTable table;
	private DefaultTableModel tabelModel;
	private JLabel lblOmset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Laporan frame = new Laporan();
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
	public Laporan() {
		this.setTitle("Laporan");
		setBounds(100, 100, 850, 530);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 250);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Transaksi");
        tabelModel.addColumn("Tanggal");
        tabelModel.addColumn("Nama Kasir");
        tabelModel.addColumn("Nama Pelanggan");
        tabelModel.addColumn("Total");
		table.setModel(tabelModel);
		
		lblOmset = new JLabel("-");
		lblOmset.setBounds(346, 302, 78, 14);
		getContentPane().add(lblOmset);
		
		JLabel lblNewLabel = new JLabel("TOTAL OMSET :");
		lblNewLabel.setBounds(192, 302, 115, 14);
		getContentPane().add(lblNewLabel);
		tampilTabel();
		tampilTotalOmset();

	}
	public void tampilTotalOmset(){
		try{
        Connection Con=Koneksi.getCon();
        String sql = "Select sum(total) from transaksi";
        Statement st=Con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()){
        	lblOmset.setText(""+rs.getInt(1));
        }
        }catch (Exception ex){

        }
    }
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Koneksi.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM transaksi";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getString(4);
                obj[4] = rs.getInt(5);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	
}
