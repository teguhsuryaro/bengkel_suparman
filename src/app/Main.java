package app;

import java.util.Scanner;
import layanan.Pemeriksaan;
import layanan.Servis;
import model.Kendaraan;
import model.Mobil;
import model.Motor;

public class Main {

    private static final int KAPASITAS = 10;

    // Penyimpanan data: array biasa (bukan ArrayList)
    private static Kendaraan[] daftar = new Kendaraan[KAPASITAS];
    private static int jumlah = 0;

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Data awal supaya bisa langsung dicoba
        daftar[0] = new Mobil("Toyota", "R 1234 AB", "Teguh Surya", 4);
        daftar[1] = new Motor("Honda", "R 5678 CD", "Surya", "Matic");
        jumlah = 2;

        int pilihan = -1;
        while (pilihan != 0) {
            tampilkanMenu();
            pilihan = bacaAngka("Pilih menu: ");

            if (pilihan == 1) {
                lihatDaftar();
            } else if (pilihan == 2) {
                tambahMobil();
            } else if (pilihan == 3) {
                tambahMotor();
            } else if (pilihan == 4) {
                servisKendaraan();
            } else if (pilihan == 5) {
                periksaKendaraan();
            } else if (pilihan == 6) {
                hitungBiaya();
            } else if (pilihan == 0) {
                System.out.println("\nTerima kasih sudah memakai sistem Bengkel Suparman.");
            } else {
                System.out.println("\nMenu tidak tersedia, coba lagi.");
            }
        }
    }

    // ===== MENU =====

    private static void tampilkanMenu() {
        System.out.println("\n========================================");
        System.out.println("            BENGKEL SUPARMAN");
        System.out.println("       SISTEM MANAJEMEN SERVIS");
        System.out.println("========================================");
        System.out.println("1. Lihat daftar kendaraan");
        System.out.println("2. Tambah mobil");
        System.out.println("3. Tambah motor");
        System.out.println("4. Servis kendaraan");
        System.out.println("5. Periksa kondisi kendaraan");
        System.out.println("6. Hitung biaya servis");
        System.out.println("0. Keluar");
        System.out.println("========================================");
    }

    // ===== FITUR =====

    private static void lihatDaftar() {
        System.out.println("\n[DAFTAR KENDARAAN] Total: " + jumlah);
        for (int i = 0; i < jumlah; i++) {
            System.out.println("\n--- Kendaraan ke-" + (i + 1) + " ---");
            daftar[i].tampilkanInformasi(); // versi Mobil/Motor yang berjalan
        }
    }

    private static void tambahMobil() {
        if (jumlah >= KAPASITAS) {
            System.out.println("\nBengkel penuh, tidak bisa menambah kendaraan.");
            return;
        }
        System.out.println("\n[TAMBAH MOBIL]");
        String nomorPolisi = bacaTeks("Nomor polisi : ");
        if (cariIndex(nomorPolisi) != -1) {
            System.out.println("Nomor polisi sudah terdaftar.");
            return;
        }
        String namaPemilik = bacaTeks("Nama pemilik : ");
        String merek = bacaTeks("Merek        : ");
        int jumlahPintu = bacaAngka("Jumlah pintu : ");

        daftar[jumlah] = new Mobil(merek, nomorPolisi, namaPemilik, jumlahPintu);
        jumlah++;
        System.out.println("Mobil berhasil ditambahkan.");
    }

    private static void tambahMotor() {
        if (jumlah >= KAPASITAS) {
            System.out.println("\nBengkel penuh, tidak bisa menambah kendaraan.");
            return;
        }
        System.out.println("\n[TAMBAH MOTOR]");
        String nomorPolisi = bacaTeks("Nomor polisi : ");
        if (cariIndex(nomorPolisi) != -1) {
            System.out.println("Nomor polisi sudah terdaftar.");
            return;
        }
        String namaPemilik = bacaTeks("Nama pemilik : ");
        String merek = bacaTeks("Merek        : ");
        String jenisMotor = bacaTeks("Jenis motor  : ");

        daftar[jumlah] = new Motor(merek, nomorPolisi, namaPemilik, jenisMotor);
        jumlah++;
        System.out.println("Motor berhasil ditambahkan.");
    }

    private static void servisKendaraan() {
        System.out.println("\n[SERVIS KENDARAAN]");
        int index = cariIndex(bacaTeks("Nomor polisi : "));
        if (index == -1) {
            System.out.println("Kendaraan tidak ditemukan.");
            return;
        }
        daftar[index].prosesServis();
    }

    private static void periksaKendaraan() {
        System.out.println("\n[PEMERIKSAAN KENDARAAN]");
        int index = cariIndex(bacaTeks("Nomor polisi : "));
        if (index == -1) {
            System.out.println("Kendaraan tidak ditemukan.");
            return;
        }
        // Polimorfisme interface: variabel bertipe Pemeriksaan
        Pemeriksaan pemeriksaan = (Pemeriksaan) daftar[index];
        System.out.println(pemeriksaan.periksaKendaraan());
    }

    private static void hitungBiaya() {
        System.out.println("\n[BIAYA SERVIS]");
        int index = cariIndex(bacaTeks("Nomor polisi : "));
        if (index == -1) {
            System.out.println("Kendaraan tidak ditemukan.");
            return;
        }
        Kendaraan kendaraan = daftar[index];
        if (kendaraan.getStatusServis().equals(Kendaraan.BELUM_DISERVIS)) {
            System.out.println("Kendaraan belum diservis. Servis dulu lewat menu 4.");
            return;
        }
        // Polimorfisme interface: variabel bertipe Servis
        Servis servis = (Servis) kendaraan;
        int biayaServis = servis.hitungBiayaServis();
        System.out.println("Biaya servis       : Rp" + biayaServis);
        System.out.println("Biaya administrasi : Rp" + Kendaraan.BIAYA_ADMINISTRASI);
        System.out.println("Total              : Rp" + kendaraan.hitungBiayaTotal(biayaServis));
    }

    // ===== BANTUAN =====

    // Mencari kendaraan berdasarkan nomor polisi, -1 jika tidak ada
    private static int cariIndex(String nomorPolisi) {
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getNomorPolisi().equalsIgnoreCase(nomorPolisi)) {
                return i;
            }
        }
        return -1;
    }

    private static String bacaTeks(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    // Membaca angka; kalau yang diketik bukan angka, minta ulang
    private static int bacaAngka(String pesan) {
        System.out.print(pesan);
        while (!input.hasNextInt()) {
            input.next();
            System.out.print("Harus angka, ulangi: ");
        }
        int angka = input.nextInt();
        input.nextLine();
        return angka;
    }
}