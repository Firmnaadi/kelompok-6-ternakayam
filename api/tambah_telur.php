<?php
$conn=new mysqli("localhost", "root", "", "ternak_ayam");
$id_telur = $_POST['id_telur'];
$tanggal =  date('Y-m-d');
$berat = $_POST['berat'];
$conn ->query("INSERT INTO pendapatan_telur(id_telur, tanggal, berat) VALUES( '$id_telur', '$tanggal', '$berat')");
if ($conn) {
    echo json_encode("Sukses");
} else {
    echo json_encode("Gagal");
    }
?>