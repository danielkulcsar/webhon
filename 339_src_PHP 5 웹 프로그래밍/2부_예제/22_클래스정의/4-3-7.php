<?
class Base {				//부모클래스
  public $a;				//동적 변수 (항상 초기값으로 실행)
  public static $b;			//정적 멤버 변수 (지속적으로 값을 저장)
}

class Derived extends Base {		//자식클래스
  public function __construct() {		//생성자 함수-클래스 인스턴스 시작시 실행
   $this->a = 0;			//동적 변수가??0??으로 초기화
   parent::$b = 0;			//정적 멤버 변수??0??으로 초기화
  }
  public function f() {
   $this->a++;				//항상??0??에서 1씩 증가
   parent::$b++;			//변화된 값에서 1씩 증가
  }
}

$i1 = new Derived;
$i2 = new Derived;

$i1 -> f();
echo $i1 -> a, ' ', Derived::$b, "<p>\n";
$i2 -> f();
echo $i2 -> a, ' ', Derived::$b, "<p>\n";
?>
