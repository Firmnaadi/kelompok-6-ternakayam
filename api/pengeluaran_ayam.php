<?php
$conn=new mysqli("localhost", "root", "", "ternak_ayam");
$id_ayam = $_POST['id_ayam'];
$keterangan = $_POST['keterangan'];
// $tanggal =  $_POST['tanggal'];
$tanggal =  date('Y-m-d');
$jumlah = $_POST['jumlah'];
$conn ->query("INSERT INTO pengeluaran_ayam(id_ayam, keterangan, tanggal, jumlah) VALUES( '$id_ayam','$keterangan','$tanggal', '$jumlah')");
if ($conn) {
    echo json_encode("Sukses");
} else {
    echo json_encode("Gagal");
    }
?>