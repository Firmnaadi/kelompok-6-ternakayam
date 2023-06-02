<?php
$connect = new mysqli("localhost", "root", "", "ternak_ayam");
$sttelur=$connect->query("SELECT SUM(berat_telur) AS stok_telur FROM brg_telur");
$pendapatantelur=$connect->query("SELECT SUM(berat) AS pendapatan_telur FROM pendapatan_telur");
$penjualantelur=$connect->query("SELECT SUM(berat) AS penjualan_telur FROM penjualan_telur");
//$stayam=$connect->query("SELECT SUM(jumlah_ayam) AS stok_ayam FROM brg_ayam");

$result=array();
while($fetchData=$sttelur->fetch_assoc()){
    $result[]=$fetchData;
}


// $result=array();
// while($fetchData=$stayam->fetch_assoc()){
//     $result[]=$fetchData;
// }
// echo json_encode($result);


while($fetchData=$pendapatantelur->fetch_assoc()){
    $result[]=$fetchData;
}


while($fetchData=$penjualantelur->fetch_assoc()){
    $result[]=$fetchData;
}
echo json_encode($result);
?>