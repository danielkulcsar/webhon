<?php
    $program = array(array("Word", "Excel"), 
            	      array("PHP", "ASP"),
	     	         array("Window 2000", "Window XP")
                );

    for($k = 0; $k < 3; ++$k){
        for($j = 0; $j < 2; ++$j){
            echo "내가 좋아하는 프로그램은 "; 
 			echo $program[$k][$j]; 
			echo " 입니다. <BR>";
        }	
    }
?>
