/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartkitchen.model;

/**
 *
 * @author ASUS
 */
public class BahanMakanan {
    private String nama;
    private int jumlah;
    private String tanggalKadaluarsa; // sebagai tanggalkasaluarsa

        public BahanMakanan(String nama, int jumlah, String tanggalKadaluarsa) {
            //unutk melihat nama variabel
        this.nama = nama; // nama untuk user
        this.jumlah = jumlah; // jumlah bahan 
        this.tanggalKadaluarsa = tanggalKadaluarsa; // input manual
    }
    // Getter & Setter
}
