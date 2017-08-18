<?php
final class test01 {
	public function exam() {
		echo "예제입니다.";
	}
}

class test02 extends test01 {
	function print_out() {
		parent::exam();
		echo "상속됩니다.";
	}
}

$obj = new test02;
$obj -> print_out();
?>
