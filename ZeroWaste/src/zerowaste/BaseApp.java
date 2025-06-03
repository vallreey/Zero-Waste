/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zerowaste;

/**
 *
 * @author ACER
 */
// BaseApp.java
import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseApp extends JFrame {
    protected Connection conn;

    public BaseApp() {
        DatabaseConnection dbConn = new DatabaseConnection();
        conn = dbConn.getConnection();

        setTitle("Smart Kitchen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    protected void loadData() {

    }

    // Ubah private jadi protected supaya bisa dipakai oleh subclass
    protected void tambahData(String nama, String jenis, int jumlah, String satuan,
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
    protected void hapusData(String idStr) {
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

protected void updateData(
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

        loadData(); // dipanggil dari subclass jika override
        JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal update data:\n" + e.getMessage());
    }
}
}
