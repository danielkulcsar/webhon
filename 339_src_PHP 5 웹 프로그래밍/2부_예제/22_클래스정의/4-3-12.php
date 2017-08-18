<?php
class MyCloneable {
   static $id = 0;

   function __construct() {		//생성자 함수
       $this->id = self::$id++;
   }

   function __clone() {
       $this->address = "서울";
       $this->id = self::$id++;
   }
}

$obj = new MyCloneable;			//객체 생성
$obj->address = "부산";			//address변수에 "부산"대입
print $obj->id . "\n<p>";		//생성자 함수에 의해 1증가되어 출력 - 1

$obj_cloned = clone $obj;		//객체 복사에 의해 __clone()함수 실행

print $obj_cloned->id . "\n<br>";	//__clone()함수에 의해 1증가되어 출력 - 2
print $obj_cloned->address . "\n";	//"부산"이라고 대입했지만 "서울" 출력
?> 
