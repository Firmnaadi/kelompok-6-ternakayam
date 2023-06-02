<?php
$connect = new mysqli("localhost", "root", "", "ternak_ayam");
$stayam=$connect->query("SELECT SUM(jumlah_ayam) AS stok_ayam FROM brg_ayam");
$penjualanayam=$connect->query("SELECT SUM(jumlah) AS ayam_terjual FROM penjualan_ayam");
$pengeluaranayam=$connect->query("SELECT SUM(jumlah) AS ayam_mati FROM pengeluaran_ayam");

$result=array();
while($fetchData=$stayam->fetch_assoc()){
    $result[]=$fetchData;
}
//echo json_encode($result);

//$result=array();
while($fetchData=$penjualanayam->fetch_assoc()){
    $result[]=$fetchData;
}
//echo json_encode($result);

//$result=array();
while($fetchData=$pengeluaranayam->fetch_assoc()){
    $result[]=$fetchData;
}
echo json_encode($result);
?>