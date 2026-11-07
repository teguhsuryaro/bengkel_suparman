package model;

// Abstract class: induk dari Mobil dan Motor
public abstract class Kendaraan {

    // Konstanta global: biaya administrasi yang sama untuk semua kendaraan
    public static final int BIAYA_ADMINISTRASI = 10000;

    // Teks status servis (dipakai agar tidak salah ketik)
    public static final String BELUM_DISERVIS = "Belum Diservis";
    public static final String SUDAH_DISERVIS = "Sudah Diservis";

    // Atribut private (enkapsulasi)
    private String merek;
    private final String nomorPolisi; // final: tidak boleh berubah, tanpa setter
    private String namaPemilik;
    private String statusServis;

    // Constructor 1: status otomatis "Belum Diservis"
    public Kendaraan(String merek, String nomorPolisi, String namaPemilik) {
        this(merek, nomorPolisi, namaPemilik, BELUM_DISERVIS);
    }

    // Constructor 2: status ditentukan sendiri (constructor overloading)
    public Kendaraan(String merek, String nomorPolisi, String namaPemilik, String statusServis) {
        this.merek = merek;
        this.nomorPolisi = nomorPolisi;
        this.namaPemilik = namaPemilik;
        this.statusServis = statusServis;
    }

    // Getter
    public String getMerek() {
        return merek;
    }

    public String getNomorPolisi() {
        return nomorPolisi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public String getStatusServis() {
        return statusServis;
    }

    // Setter (tidak ada setNomorPolisi karena atributnya final)
    public void setMerek(String merek) {
        this.merek = merek;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public void setStatusServis(String statusServis) {
        this.statusServis = statusServis;
    }

    // Method prosedur (void): menampilkan data umum kendaraan
    public void tampilkanInformasi() {
        System.out.println("Nama Pemilik   : " + namaPemilik);
        System.out.println("Nomor Polisi   : " + nomorPolisi);
        System.out.println("Merek          : " + merek);
        System.out.println("Status Servis  : " + statusServis);
    }

    // Method fungsi (return): biaya servis ditambah biaya administrasi
    public int hitungBiayaTotal(int biayaServis) {
        return biayaServis + BIAYA_ADMINISTRASI;
    }

    // Abstract method: wajib diisi oleh Mobil dan Motor
    public abstract void prosesServis();
}