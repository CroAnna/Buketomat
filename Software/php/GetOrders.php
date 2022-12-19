<?php
$dbhost = 'localhost';
$dbuser = 'id19882868_user';
$password = "Stapic123456!";
$dbname = "id19882868_test_db";

$response = array();
$success = array();
$error = array();
header('Content-Type: application/json');
// Create connection
$conn = new mysqli($dbhost, $dbuser, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
 
    // collect value of input field
    $json = file_get_contents('php://input');
// Converts it into a PHP object
    $data = json_decode($json)[0];
    if (empty($data)) {
        $error["error"] = "data is empty";
        echo json_encode($error);
    } 
    else {
        $sql = "SELECT * FROM narudzba WHERE korisnik_id = $data->korisnik_id ";
        $result = $conn->query($sql);
        if (!empty($result) && $result->num_rows > 0) {
            // output data of each row
            while($row = $result->fetch_assoc()) {
        
                $tmp = array();
                $tmp["id"] = $row["id"];
                $tmp["vrijeme"] = $row["vrijeme"];
                $tmp["ukupni_iznos"] = $row["ukupni_iznos"];
                $tmp["vrijeme_dostave"] = $row["vrijeme_dostave"];
                $tmp["korisnik_id"] = $row["korisnik_id"];
                array_push($response, $tmp);  
            }
            
            // echoing json result
            echo json_encode($response);
            //echo "hello";
        } 
        else {
            $error["error"] = "0 results";
            $response[] = $error;
            echo json_encode($response);
        }
    }
}
else{
    $error["error"] = "Wrong method!!!";
    $response[] = $error;
    echo json_encode($response);
}
$conn->close();
?>


