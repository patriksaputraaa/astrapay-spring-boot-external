## Cara Menjalankan

1.  Kloning repositori:
    ```bash
    git clone https://github.com/patriksaputraaa/astrapay-spring-boot-external.git
    cd [NAMA FOLDER PROYEK]
    ```

2.  Jalankan dari IDE tapi pastikan maven sudah loaded

Aplikasi berjalan di **`http://localhost:[Port Anda, default: 8760]`**.

## Endpoint API (Path: `/api/notes`)

| Method | Path          | Deskripsi |
| :--- |:--------------| :--- |
| `GET` | `/api/notes`  | Ambil semua catatan yang ada di memori. |
| `GET` | `/api/notes/id`   | Ambil semua catatan yang ada di memori. |
| `POST` | `/api/notes`      | Buat catatan baru (disimpan di memori). |
| `PUT` | `/api/notes/{id}` | Perbarui catatan spesifik. |
| `DELETE` | `/api/notes/{id}` | Hapus catatan dari memori. |