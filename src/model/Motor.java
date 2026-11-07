package model;

import layanan.Pemeriksaan;
import layanan.Servis;

// Motor adalah Kendaraan, dan bisa diservis serta diperiksa (dua interface)
public class Motor extends Kendaraan implements Servis, Pemeriksaan {

    private static final int BIAYA_SERVIS_MOTOR = 75000;

    private String jenisMotor;

    // Constructor 1
    public Motor(String merek, String nomorPolisi, String namaPemilik, String jenisMotor) {
        super(merek, nomorPolisi, namaPemilik); // memanggil constructor Kendaraan
        this.jenisMotor = jenisMotor;
    }

    // Constructor 2: status servis ditentukan sendiri (overloading)
    public Motor(String merek, String nomorPolisi, String namaPemilik, String jenisMotor, String statusServis) {
        super(merek, nomorPolisi, namaPemilik, statusServis);
        this.jenisMotor = jenisMotor;
    }

    public String getJenisMotor() {
        return jenisMotor;
    }

    public void setJenisMotor(String jenisMotor) {
        this.jenisMotor = jenisMotor;
    }

    // Overriding: menambah info khusus motor setelah info umum
    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi(); // memanggil method milik Kendaraan
        System.out.println("Jenis Motor    : " + jenisMotor);
    }

    // Implementasi abstract method: if/else membaca dan mengubah status
    @Override
    public void prosesServis() {
        if (getStatusServis().equals(BELUM_DISERVIS)) {
            System.out.println("Memproses servis motor (ininya diituin, terus itunya diiniin)...");
            setStatusServis(SUDAH_DISERVIS);
            System.out.println("Servis motor berhasil dilakukan.");
        } else {
            System.out.println("Motor ini sudah diservis, tidak perlu diservis lagi.");
        }
    }

    // Implementasi interface Servis
    @Override
    public int hitungBiayaServis() {
        return BIAYA_SERVIS_MOTOR;
    }

    // Implementasi interface Pemeriksaan
    @Override
    public String periksaKendaraan() {
        if (getStatusServis().equals(SUDAH_DISERVIS)) {
            return "Kondisi motor Baik.";
        } else {
            return "Motor perlu diservis.";
        }
    }
}