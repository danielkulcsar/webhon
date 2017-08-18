<?
if(copy("$upload", "$upload_name")) {
    echo "파일이 성공적으로 업로드되었습니다."."<p>";
    echo "<img src=$upload_name>"."<p>";
    echo "파일이름(\$upload_name) : $upload_name"."<br>";
    echo "파일크기(\$upload_size) : $upload_size"."<br>";
    echo "파일타입(\$upload_type) : $upload_type"."<br>";
    echo "파일의 내용이 저장된 임시 파일명(\$upload) : $upload";
} else {
    echo "파일 업로드가 실패했습니다.";
}
?>
