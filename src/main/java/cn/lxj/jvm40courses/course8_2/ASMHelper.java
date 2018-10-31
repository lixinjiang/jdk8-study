package cn.lxj.jvm40courses.course8_2;

import jdk.internal.org.objectweb.asm.*;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ASMHelper
 * description 更改同一目录下Circuit类的starRace(Object o)方法，使其包含invokedynamic指令
 * create class by lxj 2018/10/30
 **/
public class ASMHelper implements Opcodes {
    private static class MyMethodVisitor extends MethodVisitor {
        private static final String BOOTSTRAP_CLASS_NAME = Circuit.class.getName().replace(".", "/");
        private static final String BOOTSTRAP_METHOD_NAME = "bootstrap";
        private static final String BOOTSTRAP_METHOD_DESC = MethodType
                .methodType(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class)
                .toMethodDescriptorString();
        private static final String TARGET_METHOD_NAME = "race";
        private static final String TARGET_METHOD_DESC = "(Ljava/lang/Object;)V";

        private final MethodVisitor mv;

        public MyMethodVisitor(int api, MethodVisitor mv) {
            super(api);
            this.mv = mv;
        }

        @Override
        public void visitCode() {
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
//            Handle handle = new InvocationHandler(H_INVOKESTATIC, BOOTSTRAP_CLASS_NAME, BOOTSTRAP_METHOD_NAME,
//                    BOOTSTRAP_METHOD_DESC, false);
            mv.visitInvokeDynamicInsn(TARGET_METHOD_NAME, TARGET_METHOD_DESC, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
    }

    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("Circuit");
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new ClassVisitor(ASM5,cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[]
                    exceptions) {
                MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
                if ("startRace".equals(name)) {
                    return new MyMethodVisitor(ASM5, visitor);
                }
                return visitor;
            }
        };
        cr.accept(cv,ClassReader.SKIP_FRAMES);
        Files.write(Paths.get("Circuit.class"), cw.toByteArray());
    }
}
