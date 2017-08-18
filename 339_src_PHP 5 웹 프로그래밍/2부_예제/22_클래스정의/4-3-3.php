<?
class FontChanger {
	function font_color($str, $font_color, $font_size)
	{
		echo "<font color=$font_color size=$font_size>";
		echo "$str</font><br>";
	}
}

$obj=new FontChanger;				//클래스 생성
$obj->font_color('I Love Korea!!!','blue',10);	//함수 호출
$obj->font_color('Welcome To Korea!!!','red',7);	//함수 호출
?>
