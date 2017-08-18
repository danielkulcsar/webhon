<?php
   $k = 0;
   while ($k < 10) {		
	    $k++;			
	    $x = $k % 2;		
	    if ($x == 0) {  
	       continue;   	
	    }
	    $value += $k;	  	
   }
   echo "변수 value에 저장된 값은 $value 입니다.";
?>
