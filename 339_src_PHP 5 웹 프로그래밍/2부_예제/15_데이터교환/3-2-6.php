<?php
	$data1 = $_POST["check1"];
	$data2 = $_POST["check2"];
	$data3 = $_POST["check3"];
	$data4 = $_POST["check4"];
	$data5 = $_POST["group1"];
	$data6 = $_POST["group2"];

	echo "<H5>관심 분야는 :";
	if($data1){
	   echo(" 정보 통신");
	} 
	if($data2) {
	    echo(" 취업,자동차");
	}
	if($data3) {
	    echo(" 스포츠,레져");
	}
	if($data4) {
	    echo(" 쇼핑,신상품");
	}

	echo("<BR><BR><BR>성별 : $data5");
	echo("<BR><BR><BR>결혼여부 : $data6 </H5>"); 
?>
