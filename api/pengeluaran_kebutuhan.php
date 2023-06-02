<?php
$conn=new mysqli("localhost", "root", "", "ternak_ayam");
$id_kbth = $_POST['id_kbth'];
$tanggal =  $_POST['tanggal'];
// $tanggal =  date('Y-m-d');
$jumlah = $_POST['jumlah'];
$conn ->query("INSERT INTO pengeluaran_kbth(id_kbth, tanggal, jumlah) VALUES( '$id_kbth', '$tanggal', '$jumlah')");
if ($conn) {
    echo json_encode("Sukses");
} else {
    echo json_encode("Gagal");
    }
?>