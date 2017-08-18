<?php
   function myfunc() {
       static $a = 0;
       echo "$a <BR>";
       $a++;
   }

   for ($k = 0 ; $k < 10 ; $k++) {
       myfunc();
   }
?>
