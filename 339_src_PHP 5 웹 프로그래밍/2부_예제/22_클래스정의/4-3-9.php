<?php
abstract class test01 {			//부모 클래스 (상속함)
	abstract protected function abc();

	public function print_out() {
		echo $this -> abc();
	}
}

class test02 extends test01 {		//자식 클래스 (상속 받음)
	protected function abc() {	//상속한 메소드로 문자열 리턴
		return "추상 클래스와 추상 메소드\n<p>";
	}
}

$obj=new test02;
echo $obj -> print_out();
?>
