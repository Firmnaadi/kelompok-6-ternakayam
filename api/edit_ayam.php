<?php
$connect = new mysqli("localhost", "root", "", "ternak_ayam");
$id_ayam =$_POST['id_ayam'];
$jumlah_ayam=$_POST['jumlah_ayam'];
$connect->query("UPDATE brg_ayam SET jumlah_ayam='".$jumlah_ayam."' WHERE id_ayam=". $id_ayam);
if ($connect) {
    echo json_encode("Sukses");
} else {
    echo json_encode("Gagal");
}
?>