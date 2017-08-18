<?php
//count.txt파일을 읽고,쓰기 위해 연다
$fp=fopen("./count.txt","r+");

//count.txt파일에서 10byte만큼 읽어온다
$num=fgets($fp,10);

$num+=1;

//파일 포인터를 count.txt파일의 제일 처음으로 이동
rewind($fp);

//count.txt파일에 10byte크기로 $num값을 쓴다
fputs($fp,$num,10);

/* 만약, $num의 값이 '123'이라면
$num_ar[0]=1, $num_ar[1]=2, $num_ar[2]=3 이 대입됩니다
즉, strval(인수)함수는 인수를 배열로 만드는 함수입니다 */
$num_ar=strval($num);

//strlen()함수는 $num의 길이를 구하는 함수입니다
$len=strlen($num);

for($i=0;$i<=$len-1;$i++)
{
	echo "<img src='$num_ar[$i].gif'>";
}
?>