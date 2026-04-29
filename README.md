Link YouTube : https://youtu.be/FGdUVLyGdKc?si=cVPS2PrBfOXrUrxK
# Wireframe :
<div align="center">
  
  <!-- Baris pertama: 2 gambar -->
  <p align="center">
    <img width="45%" src="https://github.com/user-attachments/assets/ff713be1-6faf-44d2-bd33-0428530c57ad" />
    <img width="45%" src="https://github.com/user-attachments/assets/addaf02d-bf93-4acd-8ab3-8234329765a2" />
  </p>
  
  <!-- Baris kedua: 3 gambar dengan ukuran lebih kecil -->
  <p align="center">
    <img width="30%" src="https://github.com/user-attachments/assets/572f06b1-ace6-4c6d-9f0c-d4bdcc8a2640" />
    <img width="30%" src="https://github.com/user-attachments/assets/f5e59626-40f7-459c-b9a1-29c6c7a89dad" />
    <img width="30%" src="https://github.com/user-attachments/assets/cd1f299e-0496-4e81-89d3-e9e97763258f" />
  </p>
  
</div>


# Perseminaran App (Neobrutalism & Material Design)

Aplikasi pendaftaran seminar Android yang menggabungkan estetika **Neobrutalism** (garis tepi tebal, warna kontras) dengan fungsionalitas **Material Design 3** (TextInputLayout, MaterialButton, Material Icons).

## Fitur & Halaman

### 1. Halaman Login & Registrasi Akun (`MainActivity`)
*   **Dual-Form Interface**: Menggunakan sistem tab untuk berpindah antara form Login dan Register secara mulus.
*   **Keamanan Dasar**: Login menggunakan kredensial statis (Username: `admin`, Password: `123`).
*   **Material Input**: Menggunakan `TextInputLayout` dengan ikon Material dan fitur *password toggle*.

### 2. Halaman Utama (`DashboardSeminarActivity`)
*   **Dynamic Greeting**: Menyapa pengguna secara personal berdasarkan username yang dimasukkan saat login.
*   **Daftar Seminar Dinamis**: Menampilkan 6 seminar yang dibuat secara programatik melalui kode Kotlin untuk efisiensi.
*   **Neobrutalism Cards**: Setiap item seminar memiliki ikon Material untuk Kategori, Tanggal, dan Lokasi.
*   **Sistem Navigasi Pintar**:
    *   **Tombol DAFTAR**: Membuka form dengan seminar tersebut otomatis terpilih dan dikunci.
    *   **Tombol Plus (+)**: Membuka form pendaftaran kosong untuk pilihan manual.

### 3. Form Pendaftaran Seminar (`RegisterSeminarActivity`)
*   Formulir lengkap mencakup: Nama Lengkap, Email, Nomor HP, Jenis Kelamin, Pilihan Seminar (Spinner), dan Persetujuan.
*   UI modern dengan ikon Material Design pada setiap kolom input.

### 4. Sistem Validasi (Real-time Error)
Menerapkan standar validasi ketat yang muncul langsung saat pengguna mengetik:
*   **Wajib Isi**: Semua kolom tidak boleh kosong.
*   **Format Email**: Wajib mengandung karakter `@`.
*   **Format Nomor HP**:
    *   Hanya menerima input angka.
    *   Harus diawali dengan prefix `08`.
    *   Panjang karakter antara 10 hingga 13 digit.
*   **Checkbox**: Menampilkan peringatan jika persetujuan belum dicentang.

### 5. Dialog Konfirmasi
*   Muncul setelah klik "Submit". Menampilkan pesan: *"Apakah data yang Anda isi sudah benar?"* untuk memastikan akurasi data sebelum diproses.

### 6. Halaman Hasil (`SeminarResultActivity`)
*   Menampilkan ringkasan sukses pendaftaran.
*   Menyajikan data yang diinput (Nama, Email, HP, Gender, Seminar) dalam format kartu yang rapi.

## Penjelasan Struktur Kode

*   **`MainActivity.kt`**: Logika autentikasi dan validasi pendaftaran akun baru.
*   **`DashboardSeminarActivity.kt`**: Manajemen data seminar statis dan perulangan (*looping*) UI menggunakan `LayoutInflater`.
*   **`RegisterSeminarActivity.kt`**: Logika validasi tingkat lanjut dan penanganan `Intent Extras` untuk sistem pilihan seminar otomatis.
*   **`SeminarResultActivity.kt`**: Pengambil data akhir dan penampil ringkasan.
*   **`styles.xml`**: Definisi gaya global Neobrutalism (Border lebar 2dp-3dp, Radius 0dp).
*   **`drawables`**: Berisi aset vektor Material Design dan shape kustom untuk tombol dan input.

---
Proyek ini dikembangkan dengan fokus pada pengalaman pengguna (UX) melalui feedback instan dan desain visual yang berani.
