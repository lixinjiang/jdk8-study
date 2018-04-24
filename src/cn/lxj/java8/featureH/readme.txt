八、访问接口的默认方法

Predicate接口:
Predicate 接口只有一个参数，返回boolean类型。
    该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）：
public static void main(String[] args) {
    Predicate<String> predicate = (s) -> s.length() > 0;
    System.out.println(predicate.test("foo"));              // true
    System.out.println(predicate.negate().test("foo"));     // false
    Predicate<Boolean> nonNull = Objects::nonNull;
    Predicate<Boolean> isNull = Objects::isNull;
    Predicate<String> isEmpty = String::isEmpty;
    Predicate<String> isNotEmpty = isEmpty.negate();
    System.out.println(nonNull.test(null));
    System.out.println(isNull.test(null));
    System.out.println(isEmpty.test("sss"));
    System.out.println(isNotEmpty.test(""));
}
运行结果：
true
false
false
true
false
false

Function 接口
Function 接口有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen）：

        Function<String, Integer> toInteger = Integer::valueOf;
        System.out.println(toInteger.apply("123").getClass());
        Function<String, Object> toInteger2 = toInteger.andThen(String::valueOf);
        System.out.println(toInteger2.apply("123").getClass());
        输出：
        class java.lang.Integer
        class java.lang.String
Supplier 接口
Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person
Consumer 接口
Consumer 接口表示执行在单个参数上的操作。接口只有一个参数，且无返回值
        Supplier<LambdaClassTest> personSupplier = LambdaClassTest::new;
        Consumer<LambdaClassTest> greeter = (lt) -> System.out.println("Hello, " + lt.getTest());
        greeter.accept(personSupplier.get());
Comparator 接口
Comparator 是老Java中的经典接口， Java 8在此之上添加了多种默认方法：
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0
Optional 接口
Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型，这是下一届中将要用到的重要概念，
         现在先简单的看看这个接口能干什么：
Optional 被定义为一个简单的容器，其值可能是null或者不是null。
         在Java 8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，而在Java 8中，不推荐你返回null而是返回Optional。
***************
重点          *
***************
Stream 接口 重要！！！
创建stream–通过of方法
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
        Stream<String> stringStream = Stream.of("taobao");
创建stream–通过generator方法
生成一个无限长度的Stream，其元素的生成是通过给定的Supplier（这个接口可以看成一个对象的工厂，
    每次调用返回一个给定类型的对象）
    Stream.generate(new Supplier<Double>() {

        @Override

        public Double get() {

            return Math.random();

        }

    });

    Stream.generate(() -> Math.random());

    Stream.generate(Math::random);
三条语句的作用都是一样的，只是使用了lambda表达式和方法引用的语法来简化代码。每条语句其实都是生成一个无限长度的Stream，其中值是随机的。
这个无限长度Stream是懒加载，一般这种无限长度的Stream都会配合Stream的limit()方法来用。

创建stream–通过iterate方法
也是生成无限长度的Stream，和generator不同的是，其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。其中包含的元素可以认为是：
seed，f(seed),f(f(seed))无限循环
Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
这段代码就是先获取一个无限长度的正整数集合的Stream，然后取出前10个打印。千万记住使用limit方法，不然会无限打印下去。

通过Collection子类获取Stream
public interface Collection<E> extends Iterable<E> {
    //其他方法省略
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
java.util.Stream 表示能应用在一组元素上一次执行的操作序列。Stream 操作分为中间操作或者最终操作两种，
最终操作返回一特定类型的计算结果，而中间操作返回Stream本身，这样你就可以将多个操作依次串起来。
Stream 的创建需要指定一个数据源，比如 java.util.Collection的子类，List或者Set， Map不支持。
Stream的操作可以串行执行或者并行执行。

Java 8扩展了集合类，可以通过 Collection.stream() 或者 Collection.parallelStream() 来创建一个Stream。
Stream有串行和并行两种，串行Stream上的操作是在一个线程中依次完成，而并行Stream则是在多个线程上同时执行。

下面的例子展示了是如何通过并行Stream来提升性能：
首先我们创建一个没有重复元素的大表：
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
然后我们计算一下排序这个Stream要耗时多久，
串行排序：
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
// 串行耗时: 899 ms
并行排序：
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
// 并行排序耗时: 472 ms
上面两个代码几乎是一样的，但是并行版的快了50%之多，唯一需要做的改动就是将stream()改为parallelStream()；
stream的其他应用：
1、count()、max()、min()方法
        import java.util.ArrayList;
        import java.util.List;

        public class Main {

            public static void main(String[] args) {
                List<Integer> collection = new ArrayList<Integer>();
                collection.add(14);
                collection.add(5);
                collection.add(43);
                collection.add(89);
                collection.add(64);
                collection.add(112);
                collection.add(55);
                collection.add(55);
                collection.add(58);
                //list长度
                System.out.println(collection.parallelStream().count());

                //求最大值,返回Option,通过Option.get()获取值
                System.out.println(collection.parallelStream().max((a,b)->{return a-b;}).get());

                //求最小值,返回Option,通过Option.get()获取值
                System.out.println(collection.parallelStream().min((a,b)->{return a-b;}).get());

            }
        }
2、Filter 过滤方法
过滤通过一个predicate接口来过滤并只保留符合条件的元素，该操作属于中间操作。
        import java.util.ArrayList;
        import java.util.List;

        public class Main {

            public static void main(String[] args) {
                List<Integer> collection = new ArrayList<Integer>();
                collection.add(14);
                collection.add(5);
                collection.add(43);
                collection.add(89);
                collection.add(64);
                collection.add(112);
                collection.add(55);
                collection.add(55);
                collection.add(58);
                Long count =collection.stream().filter(num -> num!=null).
                        filter(num -> num.intValue()>50).count();
                System.out.println(count);
            }
        }
3、distinct方法
去除重复
        import java.util.ArrayList;
        import java.util.List;

        public class Main {

            public static void main(String[] args) {
                List<Integer> collection = new ArrayList<Integer>();
                collection.add(14);
                collection.add(5);
                collection.add(43);
                collection.add(89);
                collection.add(64);
                collection.add(112);
                collection.add(55);
                collection.add(55);
                collection.add(58);
                collection.stream().distinct().forEach(System.out::println);;
            }
        }
4、Sort 排序
排序是一个中间操作，返回的是排序好后的Stream。如果你不指定一个自定义的Comparator则会使用默认排序。
        stringCollection
            .stream()
            .sorted()
            .filter((s) -> s.startsWith("a"))
            .forEach(System.out::println);
        // "aaa1", "aaa2"
需要注意的是，排序只创建了一个排列好后的Stream，而不会影响原有的数据源，排序之后原数据stringCollection是不会被修改的：
        System.out.println(stringCollection);
        // ddd2, aaa2, bbb1, aaa1, bbb3, ccc, bbb2, ddd1
5、Map 映射

对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。这个方法有三个对于原始类型的变种方法，
分别是：mapToInt，mapToLong和mapToDouble。这三个方法也比较好理解，比如mapToInt就是把原始Stream转换成一个新的Stream，
这个新生成的Stream中的元素都是int类型。之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗；
        import java.util.ArrayList;
        import java.util.List;

        public class Main {

            public static void main(String[] args) {
                List<String> collection = new ArrayList<String>();
                collection.add("14");
                collection.add("5");
                collection.add("43");
                collection.add("89");
                collection.add("64");
                collection.add("112");
                collection.add("55");
                collection.add("55");
                collection.add("58");
                //将String转化为Integer类型
                collection.stream().mapToInt(Integer::valueOf).forEach(System.out::println);
                //或
                collection.stream().mapToInt(a->Integer.parseInt(a)).forEach(System.out::println);
            }
        }

也可以这样用：
        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println(“sum is:”+nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).
                    peek(System.out::println).skip(2).limit(4).sum());
7、limit：
对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素；

8、skip：
返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；

9、Match 匹配
Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个Stream。所有的匹配操作都是最终操作，并返回一个boolean类型的值。
        boolean anyStartsWithA =
            stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);      // true
        boolean allStartsWithA =
            stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA);      // false
        boolean noneStartsWithZ =
            stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);      // true
10、Count 计数
计数是一个最终操作，返回Stream中元素的个数，返回值类型是long。
        long startsWithB =
            stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();
        System.out.println(startsWithB);    // 3
11、Reduce 规约
这是一个最终操作，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规越后的结果是通过Optional接口表示的：
        Optional<String> reduced =
            stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
Map
前面提到过，Map类型不支持stream，不过Map提供了一些新的有用的方法来处理一些日常任务。
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val));
以上代码很容易理解， putIfAbsent 不需要我们做额外的存在性检查，而forEach则接收一个Consumer接口来对map里的每一个键值对进行操作。
下面的例子展示了map上的其他有用的函数：
        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33
        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false
        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true
        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33
接下来展示如何在Map里删除一个键值全都匹配的项：
        map.remove(3, "val3");
        map.get(3);             // val33
        map.remove(3, "val33");
        map.get(3);             // null
另外一个有用的方法：
        map.getOrDefault(42, "not found");  // not found
对Map的元素做合并也变得很容易了：
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat
Merge做的事情是如果键名不存在则插入，否则则对原键对应的值做合并操作并重新插入到map中。
steam在实际项目中使用的代码片段：
        //1、有list集合生成以productId为key值得map集合
        Map<String, List<CartManager>> cartManagerGroup =
                carts.stream().collect(
                        Collectors.groupingBy(CartManager::getProductId)
                );
        //2、取得购物车中数量之和
        IntStream  is = list.stream().mapToInt((CartManager c)->c.getQuantity());
        is.sum();//数量之和

        //3、所有订单中商品数量*订单金额求和
        orderDetailsNew.parallelStream()
                                    .mapToDouble(orderDetailMid -> orderDetailMid.getQuantity()*orderDetailMid.getFinalPrice()).sum()

        //4、过滤出指定类型的订单，并生成新的集合
        orderDetails.stream().
            filter(orderDetail ->    StringUtil.isEmpty(orderDetail.getPromotionsType())|| !orderDetail.getPromotionsType().equals(PromotionTypeEnum.ORDERGIFTPROMOTION.getType())).collect(Collectors.toList());

        //5、过滤购物车未被选中商品并生成新的list
        carts.stream().filter(cart -> cart.getSelectFlag()==1).collect(Collectors.toList());

        //6、将list以商品促销委key转化为map
        Map<String,List<PromotionsGiftProduct>> map =
                        promotionsGiftProducts.stream().collect(                    Collectors.groupingBy(PromotionsGiftProduct::getPromotionId));

        //7、从list<Cart>中分离出只存储productId的列表list<String>
        List<String> productIds = needUpdate.parallelStream()
                                .map(CartManager::getProductId)
                                .collect(Collectors.toList());
