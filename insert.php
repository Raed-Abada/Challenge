<?php
    $con = mysqli_connect("localhost", "id2240683_raedabada", "raedabada", "id2240683_database");
    
    $city = $_POST["name"];
    $population = $_POST["number"];
    
    $statement = mysqli_prepare($con, "INSERT INTO cities (Name, Population) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "si", $city, $population);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
    ?>