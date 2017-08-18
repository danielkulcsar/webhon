<?
class BaseClass {				//부모클래스[BaseClass]
    function __construct()
    {
        print "BaseClass 클래스의 constructor(생성자)\n";
    }
    function __destruct()
    {
        print "BaseClass 클래스의 destructor(소멸자)\n";
    }
}
class SubClass extends BaseClass {		//상속받은 클래스[SubClass]
    function __construct()
    {
        parent::__construct();
        print "SubClass 클래스의 constructor(생성자)\n";
    }
    function __destruct()
    {
        parent::__destruct();
        print "Subclass 클래스의 destructor(소멸자)\n";
    }
}

$obj=new SubClass;		//상속받은 클래스 호출
?>
