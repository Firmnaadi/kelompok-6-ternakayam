<?php
$connect = new mysqli("localhost", "root", "", "ternak_ayam");
$id_telur=$_POST['id_telur'];
$gread=$_POST['gread'];
$berat_telur=$_POST['berat_telur'];
$connect->query("UPDATE brg_telur SET gread='".$gread."', berat_telur='".$berat_telur."' WHERE id_telur=". $id_telur);
if ($connect) {
    echo json_encode("Sukses");
} else {
    echo json_encode("Gagal");
}
?>