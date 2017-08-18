<?php
    $program[0][0] = "Word";
    $program[0][1] = "Excel";
    $program[1][0] = "PHP";
    $program[1][1] = "ASP";
    $program[2][0] = "Window 2000";
    $program[2][1] = "Window XP";

    for($k = 0; $k < 3; ++$k){
        for($j = 0; $j < 2; ++$j){
            echo "내가 좋아하는 프로그램은 "; 
 	    echo $program[$k][$j]; 
	    echo " 입니다. <BR>";
        }	
    }
?>
