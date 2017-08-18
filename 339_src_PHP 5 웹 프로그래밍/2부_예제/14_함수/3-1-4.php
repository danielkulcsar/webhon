<?php
   $data1 = "전역 변수"; 

  function myfunc() { 
       $data1 = "지역 변수"; 

       echo "$data1 <BR><BR>";  
   } 

   myfunc ();
   echo $data1;
?>
