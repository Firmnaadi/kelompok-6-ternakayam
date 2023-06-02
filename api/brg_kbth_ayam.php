<?php
$connect = new mysqli("localhost", "root", "", "ternak_ayam");
$queryResult=$connect->query("SELECT * FROM brg_kbth_ayam");
$result=array();
while($fetchData=$queryResult->fetch_assoc()){
    $result[]=$fetchData;
}
echo json_encode($result);
?>