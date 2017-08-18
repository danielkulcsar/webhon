<?php
class MyClass {					//부모 클래스
   private $Hello = "Hello, World!\n";		//MyClass에서만 사용가능
   protected $Bar = "Hello, Bar!\n";		//MyClass, MyClass2에서 사용가능
   protected $Foo = "Hello, Foo!\n";		//MyClass, MyClass2에서 사용가능

   function printHello() { //MyClass의 printHello() 사용자 정의 함수
       print "MyClass::printHello() " . $this->Hello . "<br>";
       print "MyClass::printHello() " . $this->Bar . "<br>";
       print "MyClass::printHello() " . $this->Foo . "<br>";
   }
}

class MyClass2 extends MyClass {	//MyClass를 부모클래스로 둔 자식클래스
   protected $Foo;			//여기서의 $Foo는 MyClass의 $Foo가 아님
           				//$Foo 라는 변수만 선언되어 있음
   function printHello() {  //MyClass2의 printHello() 사용자 정의 함수
       MyClass::printHello();		//부모클래스의 메소드를 실행
       print "<p>";
       print "MyClass2::printHello() " . $this->Hello . "<br>";
       print "MyClass2::printHello() " . $this->Bar . "<br>";
       print "MyClass2::printHello() " . $this->Foo . "<br>";
   }
}

$obj01 = new MyClass;
$obj01 -> printHello();

echo "<p>";

$obj02 = new MyClass2;
$obj02 -> printHello();
?>
