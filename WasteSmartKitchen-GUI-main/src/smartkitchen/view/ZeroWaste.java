/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package smartkitchen.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import org.apache.poi.xwpf.usermodel.*;

/**
 *
 * @author Astral Express
 */
public class ZeroWaste extends javax.swing.JFrame {
    private final Connection conn;

    public ZeroWaste() {
        initComponents();
        DatabaseConnection db = new DatabaseConnection();
        conn = db.getConnection();
        loadData();
    }

    private void loadData() {
        try {
            String sql = "SELECT * FROM stok_bahan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            DefaultTableModel model = (DefaultTableModel) tabelStok.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int id              = rs.getInt("id");
                String nama         = rs.getString("nama_bahan");
                String jenis        = rs.getString("jenis_bahan");
                int jumlah          = rs.getInt("jumlah");
                String satuan       = rs.getString("satuan");
                Date kadalDb        = rs.getDate("kadaluarsa");
                Date masukDb        = rs.getDate("tanggal_masuk");
                String tanggalMasuk = (masukDb != null) ? masukDb.toString() : "";
                String tanggalKadal = (kadalDb != null) ? kadalDb.toString() : "";
                String lokasi       = rs.getString("lokasi");
                String ket          = rs.getString("keterangan");

                model.addRow(new Object[]{
                    id,
                    nama,
                    jenis,
                    jumlah,
                    satuan,
                    tanggalMasuk,
                    tanggalKadal,
                    lokasi,
                    ket
                });
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load data:\n" + e.getMessage());
        }
    }
    
    private void tambahData(String nama, String jenis, int jumlah, String satuan,
                            String kadaluarsa, String lokasi, String keterangan) {
        try {
            String sql = "INSERT INTO stok_bahan "
                       + "(nama_bahan, jenis_bahan, jumlah, satuan, kadaluarsa, lokasi, keterangan) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, jenis);
            pst.setInt(3, jumlah);
            pst.setString(4, satuan);

            if (kadaluarsa != null && !kadaluarsa.isEmpty()) {
                pst.setDate(5, Date.valueOf(kadaluarsa));
            } else {
                pst.setNull(5, java.sql.Types.DATE);
            }

            pst.setString(6, lokasi);
            pst.setString(7, keterangan);

            pst.executeUpdate();
            pst.close();

            loadData();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal tambah data:\n" + e.getMessage());
        }
    }
    
    private void hapusData(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            String sql = "DELETE FROM stok_bahan WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin hapus data ID " + id + "?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                int rows = pst.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Data tidak ditemukan");
                }
            }
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal hapus data:\n" + e.getMessage());
        }
    }
    
    private void resetAutoIncrement() {
    try {
        String sql = "ALTER TABLE stok_bahan AUTO_INCREMENT = 1";
        Statement st = conn.createStatement();
        st.executeUpdate(sql);
        st.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal reset auto increment:\n" + e.getMessage());
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        NamaBahan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        spinnerJumlah = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelStok = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnDelete1 = new javax.swing.JButton();
        btnDelete2 = new javax.swing.JButton();
        Cetak = new javax.swing.JButton();
        dateChooserKadaluarsa = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        JenisBahan = new javax.swing.JTextField();
        Exit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Satuan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Lokasi = new javax.swing.JTextField();
        Keterangan = new javax.swing.JTextField();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(196, 225, 230));

        jLabel1.setText("Nama Bahan");

        NamaBahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaBahanActionPerformed(evt);
            }
        });

        jLabel2.setText("Satuan");

        spinnerJumlah.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setText("Kadaluarsa");

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tabelStok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Bahan", "Jenis Bahan", "Jumlah",
                "Satuan","Tanggal Masuk", "Tanggal Kadaluarsa",
                "Lokasi", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tabelStok);

        jLabel4.setIcon(new javax.swing.ImageIcon("E:\\Bahan Figma\\Zero waste.png")); // NOI18N

        btnDelete1.setText("Clear");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear(evt);
            }
        });

        btnDelete2.setText("Edit");
        btnDelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit(evt);
            }
        });

        Cetak.setText("Cetak");
        Cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetak(evt);
            }
        });

        jLabel5.setText("Jenis Bahan");

        JenisBahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JenisBahanActionPerformed(evt);
            }
        });

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        jLabel6.setText("Tempat Menyimpan");

        jLabel7.setText("Jumlah");

        Satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SatuanActionPerformed(evt);
            }
        });

        jLabel8.setText("Keterangan");

        Lokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LokasiActionPerformed(evt);
            }
        });

        Keterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(518, 518, 518)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Satuan, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                .addComponent(spinnerJumlah)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(127, 127, 127)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(NamaBahan)
                                            .addComponent(dateChooserKadaluarsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(JenisBahan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                            .addComponent(Lokasi))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Keterangan)
                                        .addGap(33, 33, 33)))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(NamaBahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(dateChooserKadaluarsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spinnerJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JenisBahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Lokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel8)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(Keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(196, 196, 196))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NamaBahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaBahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaBahanActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
      
        String nama       = NamaBahan.getText().trim();
        String jenis      = JenisBahan.getText().trim();
        int jumlahVal     = (int) spinnerJumlah.getValue();
        String satuan     = Satuan.getText().trim();
        java.util.Date ud = dateChooserKadaluarsa.getDate(); 
        String tKadar     = (ud != null) ? new java.sql.Date(ud.getTime()).toString() : "";
        String lokasi     = Lokasi.getText().trim();
        String keterangan = Keterangan.getText().trim();

        if (nama.isEmpty() || jenis.isEmpty() || satuan.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nama, Jenis, dan Satuan bahan harus diisi",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tambahData(nama, jenis, jumlahVal, satuan, tKadar, lokasi, keterangan);

        NamaBahan.setText("");
        JenisBahan.setText("");
        spinnerJumlah.setValue(0);
        Satuan.setText("");
        dateChooserKadaluarsa.setDate(null);
        Lokasi.setText("");
        Keterangan.setText("");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
         int row = tabelStok.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus dari tabel");
            return;
        }
        String id = tabelStok.getValueAt(row, 0).toString();
        hapusData(id);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClear(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear

    NamaBahan.setText("");
    JenisBahan.setText("");
    spinnerJumlah.setValue(0);
    Satuan.setText("");
    dateChooserKadaluarsa.setDate(null);
    Lokasi.setText("");
    Keterangan.setText("");

    idYangSedangDiedit = null;
    }//GEN-LAST:event_btnClear
private Integer idYangSedangDiedit = null;
    private void btnEdit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit
                       
    int row = tabelStok.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris dari tabel yang ingin diedit");
        return;
    }
    String idStr        = tabelStok.getValueAt(row, 0).toString();
    String nama         = tabelStok.getValueAt(row, 1).toString();
    String jenis        = tabelStok.getValueAt(row, 2).toString();
    String jumlahStr    = tabelStok.getValueAt(row, 3).toString();
    String satuan       = tabelStok.getValueAt(row, 4).toString();
    String tanggalMasuk = tabelStok.getValueAt(row, 5).toString(); 
    String tanggalKadal = tabelStok.getValueAt(row, 6).toString();
    String lokasi       = tabelStok.getValueAt(row, 7).toString();
    String keterangan   = tabelStok.getValueAt(row, 8).toString();

    idYangSedangDiedit = Integer.parseInt(idStr);

    NamaBahan.setText(nama);
    JenisBahan.setText(jenis);

    try {
        spinnerJumlah.setValue(Integer.parseInt(jumlahStr));
    } catch (NumberFormatException ex) {
        spinnerJumlah.setValue(0);
    }

    Satuan.setText(satuan);

    if (tanggalKadal != null && !tanggalKadal.trim().isEmpty()) {
        try {
            java.util.Date tKadaluDate = java.sql.Date.valueOf(tanggalKadal);
            dateChooserKadaluarsa.setDate(tKadaluDate);
        } catch (IllegalArgumentException ex) {
            dateChooserKadaluarsa.setDate(null);
        }
    } else {
        dateChooserKadaluarsa.setDate(null);
    }

    Lokasi.setText(lokasi);
    Keterangan.setText(keterangan);  
    }//GEN-LAST:event_btnEdit
private void updateData(
        int id, 
        String nama, 
        String jenis, 
        int jumlah, 
        String satuan, 
        String kadaluarsa, 
        String lokasi, 
        String keterangan
) {
    try {
        String sql = "UPDATE stok_bahan SET "
                   + "nama_bahan = ?, "
                   + "jenis_bahan = ?, "
                   + "jumlah = ?, "
                   + "satuan = ?, "
                   + "kadaluarsa = ?, "
                   + "lokasi = ?, "
                   + "keterangan = ? "
                   + "WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, jenis);
        pst.setInt(3, jumlah);
        pst.setString(4, satuan);

        if (kadaluarsa != null && !kadaluarsa.isEmpty()) {
            pst.setDate(5, Date.valueOf(kadaluarsa));
        } else {
            pst.setNull(5, java.sql.Types.DATE);
        }

        pst.setString(6, lokasi);
        pst.setString(7, keterangan);
        pst.setInt(8, id);

        pst.executeUpdate();
        pst.close();

        loadData();
        JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal update data:\n" + e.getMessage());
    }
}
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

    String nama       = NamaBahan.getText().trim();
    String jenis      = JenisBahan.getText().trim();
    int jumlahVal     = (int) spinnerJumlah.getValue();
    String satuan     = Satuan.getText().trim();

    java.util.Date ud = dateChooserKadaluarsa.getDate();
    String tKadar     = (ud != null) 
            ? new java.sql.Date(ud.getTime()).toString() 
            : "";

    String lokasi     = Lokasi.getText().trim();
    String keterangan = Keterangan.getText().trim();

    if (nama.isEmpty() || jenis.isEmpty() || satuan.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Nama, Jenis, dan Satuan bahan harus diisi",
            "Validasi", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (idYangSedangDiedit != null) {
        updateData(
            idYangSedangDiedit,
            nama,
            jenis,
            jumlahVal,
            satuan,
            tKadar,
            lokasi,
            keterangan
        );
        idYangSedangDiedit = null;
    } else {
        tambahData(nama, jenis, jumlahVal, satuan, tKadar, lokasi, keterangan);
    }

    NamaBahan.setText("");
    JenisBahan.setText("");
    spinnerJumlah.setValue(0);
    Satuan.setText("");
    dateChooserKadaluarsa.setDate(null);
    Lokasi.setText("");
    Keterangan.setText("");
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCetak(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetak
       try {
            XWPFDocument doc = new XWPFDocument();

            // Buat judul
            XWPFParagraph para = doc.createParagraph();
            XWPFRun run = para.createRun();
            run.setText("Laporan Data Stok Bahan");
            run.setBold(true);
            run.setFontSize(16);
            para.setSpacingAfter(200);

            int rowCount = tabelStok.getRowCount();
            int colCount = tabelStok.getColumnCount();

            // Buat tabel di dokumen
            XWPFTable table = doc.createTable(rowCount + 1, colCount);

            // Isi header
            for (int col = 0; col < colCount; col++) {
                table.getRow(0).getCell(col).setText(tabelStok.getColumnName(col));
            }

            // Isi data
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    table.getRow(row + 1).getCell(col)
                         .setText(tabelStok.getValueAt(row, col).toString());
                }
            }

            String fileName = "LaporanStok.docx";
            FileOutputStream out = new FileOutputStream(fileName);
            doc.write(out);
            out.close();
            doc.close();

            JOptionPane.showMessageDialog(this,
                "Dokumen Word berhasil disimpan sebagai '" + fileName + "'");
            Desktop.getDesktop().open(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Gagal menyimpan dokumen Word: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCetak

    private void JenisBahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JenisBahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisBahanActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_ExitActionPerformed

    private void SatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SatuanActionPerformed

    private void LokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LokasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LokasiActionPerformed

    private void KeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {}

        java.awt.EventQueue.invokeLater(() -> {
            new ZeroWaste().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cetak;
    private javax.swing.JButton Exit;
    private javax.swing.JTextField JenisBahan;
    private javax.swing.JTextField Keterangan;
    private javax.swing.JTextField Lokasi;
    private javax.swing.JTextField NamaBahan;
    private javax.swing.JTextField Satuan;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDelete2;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTambah;
    private com.toedter.calendar.JDateChooser dateChooserKadaluarsa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spinnerJumlah;
    private javax.swing.JTable tabelStok;
    // End of variables declaration//GEN-END:variables
}
