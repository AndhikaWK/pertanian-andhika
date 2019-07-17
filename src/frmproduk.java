/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANDHIKA WAHYU K
 */

import koneksi.koneksiDB;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmproduk extends javax.swing.JFrame {

    //membuat objek    
    private DefaultTableModel model;
    String kdproduk, kdkategori, nmproduk, satuan;
    int hrg_beli, hrg_jual, stock;
    
    public frmproduk() {
        initComponents();
        
        //membuat obyek
        model = new DefaultTableModel();
        
         //memberi nama header pada tabel
        tblproduk.setModel(model);
        model.addColumn("KODE PRODUK");
        model.addColumn("KATEGORI");
        model.addColumn("NAMA PRODUK");
        model.addColumn("HARGA BELI");
        model.addColumn("HARGA JUAL");
        model.addColumn("STOCK");
        model.addColumn("SATUAN");
        
        
        getDataproduk();
    }
    
    public void getDataproduk(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //perintah sql untuk membaca data dari tabel gaji        
            String sql = "SELECT * FROM tbl_produk";
            ResultSet res = stat.executeQuery(sql);
            
            //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[7];
                obj[0]=res.getString("kd_produk");
                obj[1]=res.getString("kd_kategori");
                obj[2]=res.getString("nm_produk");
                obj[3]=res.getString("hrg_beli");
                obj[4]=res.getString("hrg_jual");
                obj[5]=res.getString("stock");
                obj[6]=res.getString("satuan");
                model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void loadDataproduk(){
        //mengambil data dari textbox dan menyimpan nilainya pada variabel
        kdproduk = txtkdproduk.getText();
        kdkategori = txtkdkategori.getText();
        nmproduk = txtnmproduk.getText();
        hrg_beli = Integer.parseInt(txtbeli.getText());
        hrg_jual = Integer.parseInt(txtjual.getText());
        stock = Integer.parseInt(txtstock.getText());
        satuan = txtsatuan.getText();
    }
    
    public void dataselect(){
        //deklarasi variabel
        int i = tblproduk.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        txtkdproduk.setText(""+model.getValueAt(i,0));
        txtkdkategori.setText(""+model.getValueAt(i,1));
        txtnmproduk.setText(""+model.getValueAt(i,2));
        txtbeli.setText(""+model.getValueAt(i,3));
        txtjual.setText(""+model.getValueAt(i,4));
        txtstock.setText(""+model.getValueAt(i,5));
        txtsatuan.setText(""+model.getValueAt(i,6));
    }
    
    public void reset(){
        kdproduk = "";
        kdkategori = "";
        kdproduk = "";
        hrg_beli = 0;
        hrg_jual = 0;
        stock = 0;
        satuan = "";
        
        txtkdproduk.setText(kdproduk);
        txtkdkategori.setText(kdkategori);
        txtnmproduk.setText(kdproduk);
        txtbeli.setText("");
        txtjual.setText("");
        txtstock.setText("");
        txtsatuan.setText(satuan);
    }
    

     public void simpanDataProduk(){
         
        loadDataproduk();
        
        try{
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            String  sql =   "INSERT INTO tbl_produk(kd_produk, kd_kategori, nm_produk, hrg_beli, hrg_jual, stock, satuan)"
                            + "VALUES('"+ kdproduk +"','"+ kdkategori +"','"+ nmproduk +"','"+ hrg_beli +"','"+ hrg_jual +"','"+ stock +"', '"+ satuan +"')";
            PreparedStatement p = (PreparedStatement) koneksiDB.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
          
            getDataproduk();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     
     public void ubahDataproduk(){
          
        loadDataproduk();
        
        try{
  
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            String sql  =   "UPDATE tbl_produk SET kd_kategori = '"+ kdkategori +"',"
                            + "nm_produk  = '"+ nmproduk +"',"
                            + "hrg_beli  = '"+ hrg_beli +"',"
                            + "hrg_jual  = '"+ hrg_jual +"',"  
                            + "stock  = '"+ stock +"',"
                            + "satuan  = '"+ satuan +"'"
                            + "WHERE kd_produk = '" + kdproduk +"'";
            PreparedStatement p = (PreparedStatement) koneksiDB.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            getDataproduk();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void hapusDataproduk(){
       
        loadDataproduk(); 
        
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA"+ kdproduk +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM tbl_produk WHERE kd_produk='"+ kdproduk +"'";
                PreparedStatement p =(PreparedStatement)koneksiDB.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDataproduk();
                
                //fungsi reset data
                reset();
                JOptionPane.showMessageDialog(null, "KOSONG");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        frmproduk = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtkdproduk = new javax.swing.JTextField();
        txtkdkategori = new javax.swing.JTextField();
        txtnmproduk = new javax.swing.JTextField();
        txtbeli = new javax.swing.JTextField();
        txtjual = new javax.swing.JTextField();
        txtstock = new javax.swing.JTextField();
        txtsatuan = new javax.swing.JTextField();
        tmblsimpan = new javax.swing.JButton();
        tmblubah = new javax.swing.JButton();
        tmblhapus = new javax.swing.JButton();
        tmblkembali = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblproduk = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        frmproduk.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        frmproduk.setText("PRODUK TOKO BERKAH TANI");

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel1.setText("KODE PRODUK");

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel2.setText("KATEGORI");

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel3.setText("NAMA PRODUK");

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel4.setText("HARGA BELI");

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel5.setText("HARGA JUAL");

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel6.setText("STOCK");

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel7.setText("SATUAN");

        txtkdproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkdprodukActionPerformed(evt);
            }
        });

        txtjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjualActionPerformed(evt);
            }
        });

        tmblsimpan.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        tmblsimpan.setText("SIMPAN");
        tmblsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmblsimpanActionPerformed(evt);
            }
        });

        tmblubah.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        tmblubah.setText("UBAH");
        tmblubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmblubahActionPerformed(evt);
            }
        });

        tmblhapus.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        tmblhapus.setText("HAPUS");
        tmblhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmblhapusActionPerformed(evt);
            }
        });

        tmblkembali.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        tmblkembali.setText("KEMBALI");
        tmblkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmblkembaliMouseClicked(evt);
            }
        });
        tmblkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmblkembaliActionPerformed(evt);
            }
        });

        tblproduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblproduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblprodukMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblproduk);

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\ANDHIKA WAHYU K\\Pictures\\KOP2.jpg")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsatuan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txtkdproduk, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtkdkategori)
                            .addComponent(txtnmproduk)
                            .addComponent(txtbeli)
                            .addComponent(txtjual)
                            .addComponent(txtstock))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(frmproduk)
                        .addGap(130, 130, 130)))
                .addComponent(jLabel8)
                .addGap(182, 182, 182))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(tmblsimpan)
                .addGap(29, 29, 29)
                .addComponent(tmblubah)
                .addGap(37, 37, 37)
                .addComponent(tmblhapus)
                .addGap(31, 31, 31)
                .addComponent(tmblkembali)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(frmproduk)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtkdproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtkdkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnmproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tmblsimpan)
                            .addComponent(tmblubah)
                            .addComponent(tmblhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tmblkembali))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtkdprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkdprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkdprodukActionPerformed

    private void tmblsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmblsimpanActionPerformed
        simpanDataProduk();
    }//GEN-LAST:event_tmblsimpanActionPerformed

    private void tmblubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmblubahActionPerformed
        ubahDataproduk();
    }//GEN-LAST:event_tmblubahActionPerformed

    private void tmblhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmblhapusActionPerformed
        hapusDataproduk();
    }//GEN-LAST:event_tmblhapusActionPerformed

    private void tmblkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmblkembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tmblkembaliActionPerformed

    private void tmblkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmblkembaliMouseClicked
        this.dispose();
        new frmutama().setVisible(true);
    }//GEN-LAST:event_tmblkembaliMouseClicked

    private void txtjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjualActionPerformed

    private void tblprodukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblprodukMouseClicked
        dataselect();
    }//GEN-LAST:event_tblprodukMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmproduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmproduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmproduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmproduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmproduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel frmproduk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblproduk;
    private javax.swing.JButton tmblhapus;
    private javax.swing.JButton tmblkembali;
    private javax.swing.JButton tmblsimpan;
    private javax.swing.JButton tmblubah;
    private javax.swing.JTextField txtbeli;
    private javax.swing.JTextField txtjual;
    private javax.swing.JTextField txtkdkategori;
    private javax.swing.JTextField txtkdproduk;
    private javax.swing.JTextField txtnmproduk;
    private javax.swing.JTextField txtsatuan;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
}
