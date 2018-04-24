package cn.lxj.java8.featureH;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * MainTestStream
 * description TODO
 * create by lxj 2018/4/24
 **/
public class MainTestStream {
    public static void main(String[] args) {
//        每条语句其实都是生成一个无限长度的Stream
//        这个无限长度Stream是懒加载，一般这种无限长度的Stream都会配合Stream的limit()方法来用。
        // 通过of方法创建Stream
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
        Stream<String> stringStream = Stream.of("taobao");

        // 通过generator方法创建Stream
        Stream.generate(new Supplier<Double>() {

            @Override
            public Double get() {
                return Math.random();
            }
        });
        // 通过Lambda表达式创建
        Stream.generate(() -> Math.random());
        Stream.generate(Math::random);

        System.out.println("华丽的分隔符*******************");
        // 创建Stream-通过iterate方法
//        这段代码就是先获取一个无限长度的正整数集合的Stream，然后取出前10个打印。千万记住使用limit方法，不然会无限打印下去。
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
    }

    // 通过Collection子类获取Stream
    public interface Collection<E> extends Iterable<E> {
        //其他方法省略
        default Stream<E> stream() {
            return StreamSupport.stream(spliterator(), false);
        }
    }

    /**
     * 下面的例子展示了是如何通过并行Stream来提升性能：
     * 首先我们创建一个没有重复元素的大表：
     * 串行
     */
    @Test
    public void testStream1() {

        int max = 10000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
//      然后我们计算一下排序这个Stream要耗时多久，
//      串行排序：
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took:%d ms", millis));
    }

    /**
     * 计算并行排序耗时，数据量大的时候提高百分之五十效率
     */
    @Test
    public void testStream2() {
        int max = 10000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    /**
     * stream的count，max和min的值
     */
    @Test
    public void testStream3() {
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
        System.out.println(collection.parallelStream().max((a, b) -> {
            return a - b;
        }).get());
        //求最小值,返回Option,通过Option.get()获取值
        System.out.println(collection.parallelStream().min((a, b) -> {
            return a - b;
        }).get());
    }

    /**
     * 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素；
     */
    @Test
    public void testStreamLimit(){
        Stream<String> integerStream = Stream.of("1", "2", "3", "5");
        integerStream.limit(6).forEach(System.out::println);
    }

    /**
     * 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；
     */
    @Test
    public void testStreamSkip(){
        Stream<Integer> integerStream = Stream.of(1,24,45,54,6,4,1,0);
//        integerStream.skip(3).forEach();
    }
}
