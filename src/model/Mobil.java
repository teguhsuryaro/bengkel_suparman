package model;

import layanan.Pemeriksaan;
import layanan.Servis;

// Mobil adalah Kendaraan, dan bisa diservis serta diperiksa
public class Mobil extends Kendaraan implements Servis, Pemeriksaan {

    private static final int BIAYA_SERVIS_MOBIL = 150000;

    private int jumlahPintu;

    // Constructor 1
    public Mobil(String merek, String nomorPolisi, String namaPemilik, int jumlahPintu) {
        super(merek, nomorPolisi, namaPemilik); // memanggil constructor Kendaraan
        this.jumlahPintu = jumlahPintu;
    }

    // Constructor 2: status servis ditentukan sendiri (overloading)
    public Mobil(String merek, String nomorPolisi, String namaPemilik, int jumlahPintu, String statusServis) {
        super(merek, nomorPolisi, namaPemilik, statusServis);
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() {
        return jumlahPintu;
    }

    public void setJumlahPintu(int jumlahPintu) {
        this.jumlahPintu = jumlahPintu;
    }

    // Overriding: menambah info khusus mobil setelah info umum
    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi(); // memanggil method milik Kendaraan
        System.out.println("Jumlah Pintu   : " + jumlahPintu);
    }

    // Implementasi abstract method: if/else membaca dan mengubah status
    @Override
    public void prosesServis() {
        if (getStatusServis().equals(BELUM_DISERVIS)) {
            System.out.println("Memproses servis mobil (ininya diituin, terus itunya diiniin)...");
            setStatusServis(SUDAH_DISERVIS);
            System.out.println("Servis mobil berhasil dilakukan.");
        } else {
            System.out.println("Mobil ini sudah diservis, tidak perlu diservis lagi.");
        }
    }

    // Implementasi interface Servis
    @Override
    public int hitungBiayaServis() {
        return BIAYA_SERVIS_MOBIL;
    }

    // Implementasi interface Pemeriksaan
    @Override
    public String periksaKendaraan() {
        if (getStatusServis().equals(SUDAH_DISERVIS)) {
            return "Kondisi mobil Baik.";
        } else {
            return "Mobil perlu diservis.";
        }
    }
}