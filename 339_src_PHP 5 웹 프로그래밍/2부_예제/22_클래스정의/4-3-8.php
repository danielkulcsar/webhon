<?php
class abc {
   const avar = "abc's";			//클래스 상수 선언
   function show() {
       echo self::avar . "\r\n";
   }
}

class def extends abc {
   const avar = "def's";
   function showmore() {
       echo self::avar . "\r\n";
       echo "<p>";
       $this -> show();
   }
}

$bob = new def();		//클래스 생성
$bob -> showmore();		//함수 호출
?>
