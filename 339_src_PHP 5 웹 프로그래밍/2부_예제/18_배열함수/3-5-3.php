<?php
$a = array( "xDSL"  => array("ADSL", "VDSL", "HDSL"),
            "INPUT"  => array("USB", "IEEE", "BLUETOOTH"));
echo count($a); 
echo "<br>" . "======" . "<br>";
echo sizeof($a,COUNT_RECURSIVE);
?> 
