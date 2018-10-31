package cn.lxj.jvm40courses.course8_2;

import java.lang.invoke.*;

/**
 * Circuit
 * description TODO
 * create class by lxj 2018/10/30
 **/
public class Circuit {
    public static void startRace(Object o){
        // aload o
        // invokedynamic race()
    }

    public static void main(String[] args) {
        startRace(new Horse());
    }

    public static CallSite bootstrap(MethodHandles.Lookup l, String name, MethodType callSiteType) throws Exception{
        MethodHandle mh = l.findVirtual(Horse.class, name, MethodType.methodType(void.class));
        return new ConstantCallSite(mh.asType(callSiteType));
    }
}

class Horse{
    public void race(){
        System.out.println("Horse.race()");
    }
}
class Deer{
    public void race(){
        System.out.println("Deer.race()");
    }
}
