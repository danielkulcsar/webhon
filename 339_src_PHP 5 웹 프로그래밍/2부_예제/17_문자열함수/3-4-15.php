<?
$a="After a storm comes a calm.";
$b="STORM";
if(eregi($b,$a))
{
if(ereg($b,$a))
echo "대문자 $b 단어를 검색하였습니다.";
else echo "소문자 $b 단어를 검색하였습니다.";
}
else "검색 단어를 찾지 못했습니다.";
?>

