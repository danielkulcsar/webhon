<?php
class Foo {				//부모클래스 ---> 세 번째로 실행
   private function aPrivateMethod() {
       echo "Foo::aPrivateMethod() called.\n<br>";	// ------------------>⑥
   }

   protected function aProtectedMethod() {
       echo "Foo::aProtectedMethod() called.\n<br>";	// ------------------>④
       $this->aPrivateMethod();  //private은 Foo클래스 안에서만 실행가능------->⑤
   }
}

class Bar extends Foo {		//자식클래스
   public function aPublicMethod() {
       echo "Bar::aPublicMethod() called.\n<br>";	//첫번째로 실행 -------->②
       $this->aProtectedMethod(); //protected는 호출하여 실행 --------------->③

   }
}

$obj = new Bar;
$obj -> aPublicMethod();	  //class Bar의 aPublicMethod() 호출 --------->①
?>
