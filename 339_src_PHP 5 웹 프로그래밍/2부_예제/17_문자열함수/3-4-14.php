<?
$a=getenv(HTTP_USER_AGENT);
$b=preg_match("/compatible; MSIE/",$a);
if($b)
echo "당신은 익스플로러 웹브라우저를 사용하고 있습니다.";
else echo "익스플로러 웹브라우저가 아닙니다.";
?>

