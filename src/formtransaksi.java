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

public class formtransaksi extends javax.swing.JFrame {
    
    private DefaultTableModel model;
    String nmbarang, harga, jumlah, uang, kembalian, total;
    /**
     * Creates new form formtransaksi
     */
    public formtransaksi() {
        initComponents();
        
        model = new DefaultTableModel();
         
        tbltransaksi.setModel(model);
        model.addColumn("NAMA BARANG");
        model.addColumn("HARGA SATUAN");
        model.addColumn("JUMLAH");
        model.addColumn("TOTAL");
        model.addColumn("UANG BAYAR");
        model.addColumn("KEMBALIAN");
        
        getDatabeli();
    }

    public void getDatabeli(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            
            //perintah sql untuk membaca data dari tabel gaji        
            String sql = "SELECT * FROM tbl_beli";
            ResultSet res = stat.executeQuery(sql);
            
            //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[6];
                obj[0]=res.getString("nm_barang");
                obj[1]=res.getString("harga_satuan");
                obj[2]=res.getString("jumlah");
                obj[3]=res.getString("total");
                obj[4]=res.getString("uang_byr");
                obj[5]=res.getString("kembalian");
                model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void loadDatabeli(){
        //mengambil data dari textbox dan menyimpan nilainya pada variabel
        nmbarang = txtnmbarang.getText();
        harga = txtharga.getText();
        jumlah = txtjumlah.getText();
        total = txttotal.getText();
        uang = txtuang.getText();
        kembalian = txtkembali.getText();  
    }
    
    public void dataselect(){
        //deklarasi variabel
        int i = tbltransaksi.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        txtnmbarang.setText(""+model.getValueAt(i,0));
        txtharga.setText(""+model.getValueAt(i,1));
        txtjumlah.setText(""+model.getValueAt(i,2));
        txtuang.setText(""+model.getValueAt(i,3));
        txttotal.setText(""+model.getValueAt(i,4));
        txtkembali.setText(""+model.getValueAt(i,5));
    }
    
    public void reset(){
        nmbarang = "";
        harga = "";
        jumlah = "";
        uang = "";
        total = "";
        kembalian = "";
        
        txtnmbarang.setText(nmbarang);
        txtjumlah.setText(jumlah);
        txtharga.setText(harga);
        txtuang.setText(uang);
        txttotal.setText(total);
        txtkembali.setText(kembalian);
    }
    
    public void simpanDatabeli(){
         
        loadDatabeli();
        
        try{
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            String  sql =   "INSERT INTO tbl_beli(nm_barang, harga_satuan, jumlah, uang_byr, total, kembalian)"
                            + "VALUES('"+ nmbarang +"','"+ jumlah +"','"+ harga +"','"+ uang +"','"+ total +"','"+ kembalian +"')";
            PreparedStatement p = (PreparedStatement) koneksiDB.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
          
            getDatabeli();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void hapusDatabeli(){
       
        loadDatabeli(); 
        
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA "+ nmbarang +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM tbl_beli WHERE nm_barang='"+ nmbarang +"'";
                PreparedStatement p =(PreparedStatement)koneksiDB.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDatabeli();
                
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

        tmblkembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tmblsimpan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnmbarang = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        tmblbayar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltransaksi = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtuang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtkembali = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        tmblbyr = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tmblkembali.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        tmblkembali.setText("BACK");
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

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel1.setText("FORM PEMBELIAN");

        tmblsimpan.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        tmblsimpan.setText("SIMPAN");
        tmblsimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmblsimpanMouseClicked(evt);
            }
        });
        tmblsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmblsimpanActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel2.setText("NAMA BARANG   :");

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel3.setText("JUMLAH                  :");

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel4.setText("HARGA SATUAN :");

        txtnmbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmbarangActionPerformed(evt);
            }
        });

        txtjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtjumlahKeyReleased(evt);
            }
        });

        tmblbayar.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        tmblbayar.setText("BAYAR");
        tmblbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmblbayarActionPerformed(evt);
            }
        });

        tbltransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbltransaksi);

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel5.setText("UANG BAYAR       :");

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel6.setText("KEMBALIAN          :");

        txtkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkembaliActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jLabel7.setText("TOTAL                     :");

        tmblbyr.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        tmblbyr.setText("TOTAL");
        tmblbyr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmblbyrActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jButton1.setText("HAPUS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtjumlah)
                                    .addComponent(txtharga)
                                    .addComponent(txtnmbarang)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(tmblsimpan)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(tmblkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtkembali))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttotal)
                                    .addComponent(txtuang))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tmblbyr)
                            .addComponent(tmblbayar))
                        .addGap(45, 45, 45))))
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnmbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtuang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tmblbayar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tmblsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tmblkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tmblbyr)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tmblkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmblkembaliMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tmblkembaliMouseClicked

    private void tmblkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmblkembaliActionPerformed
        new frmutama().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_tmblkembaliActionPerformed

    private void tmblsimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmblsimpanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tmblsimpanMouseClicked

    private void tmblsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmblsimpanActionPerformed
        simpanDatabeli();
    }//GEN-LAST:event_tmblsimpanActionPerformed

    private void txtnmbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmbarangActionPerformed

    }//GEN-LAST:event_txtnmbarangActionPerformed

    private void txtjumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjumlahKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtjumlahKeyReleased

    private void tmblbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmblbayarActionPerformed
        int a,b,c,tot;
        a=Integer.parseInt(txtharga.getText());
        b=Integer.parseInt(txtjumlah.getText());
        c=Integer.parseInt(txtuang.getText());
        
        tot=c-(a*b);
        txtkembali.setText(String.valueOf(tot));
        
    }//GEN-LAST:event_tmblbayarActionPerformed

    private void txtkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkembaliActionPerformed

    private void tmblbyrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmblbyrActionPerformed
            int a,b,tot;
        a=Integer.parseInt(txtharga.getText());
        b=Integer.parseInt(txtjumlah.getText());
        
        tot=a*b;
        txttotal.setText(String.valueOf(tot));
    }//GEN-LAST:event_tmblbyrActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hapusDatabeli();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(formtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formtransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbltransaksi;
    private javax.swing.JButton tmblbayar;
    private javax.swing.JButton tmblbyr;
    private javax.swing.JButton tmblkembali;
    private javax.swing.JButton tmblsimpan;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkembali;
    private javax.swing.JTextField txtnmbarang;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtuang;
    // End of variables declaration//GEN-END:variables
}
