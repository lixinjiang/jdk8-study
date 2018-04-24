Lambda表达式：

Lambda表达式可以看成是匿名内部类，使用Lambda表达式时，接口必须是函数式接口
基本语法：
<函数式接口>  <变量名> = (参数1，参数2...) -> {
    //方法体
}
说明：
(参数1，参数2…)表示参数列表；->表示连接符；{}内部是方法体
1、=右边的类型会根据左边的函数式接口类型自动推断；
2、如果形参列表为空，只需保留()；
3、如果形参只有1个，()可以省略，只需要参数的名称即可；
4、如果执行语句只有1句，且无返回值，{}可以省略，若有返回值，则若想省去{}，则必须同时省略return，且执行语句也保证只有1句；
5、形参列表的数据类型会自动推断；
6、Lambda不会生成一个单独的内部类文件；
7、Lambda表达式若访问了局部变量，则局部变量必须是final的，若是局部变量没有加final关键字，系统会自动添加，此后在修改该局部变量，会报错；

Lambda表达式其他特性：
1、引用实例方法：
语法：
    <函数式接口>  <变量名> = <实例>::<实例方法名>
    //调用
    <变量名>.接口方法([实际参数...])
    将调用方法时的传递的实际参数，全部传递给引用的方法，执行引用的方法；
示例代码：
如我们引用PrintStream类中的println方法。我们知道System类中有一个PrintStream的实例为out，引用该实例方法：System.out::println：
public class Main {

    public static void main(String[] args) {

        LambdaTest2 lt1 = s-> System.out.println(s);
        lt1.print("有一个参数");

        //改写为：
        LambdaTest2 lt2 = System.out::println;
        lt2.print("实例引用方式调用");
    }
}
将lt2调用时的实际参数传递给了PrintStream类中的println方法，并调用该方法

2、引用类方法：
语法：

    <函数式接口>  <变量名> = <类>::<类方法名称>
    //调用
    <变量名>.接口方法([实际参数...])
将调用方法时的传递的实际参数，全部传递给引用的方法，执行引用的方法；
示例代码：
我们可以以数组排序方式为例

public interface LambdaTest3 {

    abstract void sort(int[] args);
}

public class Main {

    public static void main(String[] args) {
        List<Integer>  list = new ArrayList<Integer>();
        list.add(50);
        list.add(18);
        list.add(6);
        list.add(99);
        list.add(32);
        System.out.println(list.toString()+"排序之前");
        LambdaTest3 lt3 = Collections::sort;
        lt3.sort(list, (a,b) -> {
            return a-b;
        });
        System.out.println(list.toString()+"排序之后");
    }
}

执行结果：
[50, 18, 6, 99, 32]排序之前
[6, 18, 32, 50, 99]排序之后

再来看Comparator接口，它属于函数式接口，所以我们在Comparator入参时，也采取了lambda表达式写法。

@FunctionalInterface
public interface Comparator<T> {
...
...
...
}

3、引用类的实例方法：
定义、调用接口时，需要多传递一个参数，并且参数的类型与引用实例的类型一致
语法：
    //定义接口
    interface <函数式接口>{
        <返回值> <方法名>(<类><类名称>,[其他参数...]);
    }
    <函数式接口>  <变量名> = <类>::<类实例方法名>
    //调用
    <变量名>.接口方法(类的实例,[实际参数...])
将调用方法时的传递的实际参数，从第二个参数开始（第一个参数指定的类的实例），全部传递给引用的方法，执行引用的方法；
示例代码：
public class LambdaClassTest {

    public int add(int a, int b){
        System.out.println("LambdaClassTest类的add方法");
        return a+b;
    }
}

public interface LambdaTest4 {

    abstract int add(LambdaClassTest lt,int a,int b);
}

public class Main {

    public static void main(String[] args) {
        LambdaTest4 lt4 = LambdaClassTest::add;
        LambdaClassTest lct = new LambdaClassTest();
        System.out.println(lt4.add(lct, 5, 8));
    }
}
4、引用构造器方法：
语法：
    <函数式接口>  <变量名> = <类>::<new>
    //调用
    <变量名>.接口方法([实际参数...])
把方法的所有参数全部传递给引用的构造器，根据参数类型自动推断调用的构造器方法；
示例代码：
public interface LambdaTest5 {

    abstract String creatString(char[] c);
}
public class Main {

    public static void main(String[] args) {
        LambdaTest5 lt5 = String::new;
        System.out.println(lt5.creatString(new char[]{'1','2','3','a'}));
    }
}
根据传入的参数类型，自动匹配构造函数


