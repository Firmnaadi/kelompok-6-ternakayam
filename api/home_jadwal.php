<?php
$connect = new mysqli("localhost", "root", "", "ternak_ayam");
$makanpagi=$connect->query("SELECT jam FROM `jadwal` WHERE kegiatan='Memberi pakan pagi'");
$makansiang=$connect->query("SELECT jam FROM `jadwal` WHERE kegiatan='Memberi pakan siang'");
$makansore=$connect->query("SELECT jam FROM `jadwal` WHERE kegiatan='Memberi pakan sore'");

$telurpagi=$connect->query("SELECT jam FROM `jadwal` WHERE kegiatan='Mengambil telur pagi'");
$telursiang=$connect->query("SELECT jam FROM `jadwal` WHERE kegiatan='Mengambil telur siang'");
$telursore=$connect->query("SELECT jam FROM `jadwal` WHERE kegiatan='Mengambil telur sore'");

$vaksin=$connect->query("SELECT tanggal FROM `jadwal` WHERE kegiatan='Memberi vaksin'");

$result=array();
//makan
while($fetchData=$makanpagi->fetch_assoc()){
    $result[]=$fetchData;
}
while($fetchData=$makansiang->fetch_assoc()){
    $result[]=$fetchData;
}
while($fetchData=$makansore->fetch_assoc()){
    $result[]=$fetchData;
}

//telur
while($fetchData=$telurpagi->fetch_assoc()){
    $result[]=$fetchData;
}
while($fetchData=$telursiang->fetch_assoc()){
    $result[]=$fetchData;
}
while($fetchData=$telursore->fetch_assoc()){
    $result[]=$fetchData;
}

//vaksin
while($fetchData=$vaksin->fetch_assoc()){
    $result[]=$fetchData;
}
echo json_encode($result);
?>