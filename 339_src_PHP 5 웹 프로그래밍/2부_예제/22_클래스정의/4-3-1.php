<?
class Hap { // 클래스 이름
	var $sum; // 합 구하는 변수

	function add($a,$b) // add()는 사용자 정의 함수
	{
		$this->sum=$a+$b;
		echo "합 = ".$this->sum;
	}
}

$obj=new Hap;		//클래스 생성
$obj->add(50,30);	//함수 호출
?>
