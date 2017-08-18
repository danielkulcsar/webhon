<?PHP
	$a = "hello";			
	// 변수 a에 문자열 "hello" 저장
	$$a = "world";		
	// 가변 변수 a에 저장된 hello 변수에 문자열 “world" 저장
	echo "$a";
	echo "${$a}";		
	/* 변수 a에 저장된 문자열은 “hello"가 되고, 가변 변수에 저장된 hello 변수에 저장된 데이터는 "world"이기 때문에 결과는 ”hello world“가 출력됩니다. */
?>
