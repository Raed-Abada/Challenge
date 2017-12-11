<?php
    $con = mysqli_connect("localhost", "id2240683_raedabada", "raedabada", "id2240683_database");

$i=0;

    $sql= "SELECT * FROM cities ORDER BY Population desc";
    $query = mysqli_query($con, $sql) or die (mysqli_error($con)); 
    $json_array=array();

    while($row = mysqli_fetch_assoc($query))
    { $i++;
    	
$json_array[]=$row;
if($i==3){
    break;
}

    }
echo json_encode(array("cities" =>($json_array)));