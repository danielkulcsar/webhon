<?php
interface int_class {		//인터페이스 클래스
	function ex();
}

class test01 implements int_class {
	function ex() {
		echo "인터페이스";
	}
}

$obj=new test01;
$obj -> ex();
?>
