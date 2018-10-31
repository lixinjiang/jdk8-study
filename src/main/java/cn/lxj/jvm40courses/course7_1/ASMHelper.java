package cn.lxj.jvm40courses.course7_1;

import jdk.internal.org.objectweb.asm.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ASMHelper
 * description TODO
 * create class by lxj 2018/10/29
 **/
public class ASMHelper implements Opcodes {
    static class MyMethodVisitor extends MethodVisitor {
        private MethodVisitor mv;

        public MyMethodVisitor(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
            this.mv = methodVisitor;
        }

        @Override
        public void visitCode() {
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Hello,World!");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
    }

    static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[]
                exceptions) {
            MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            if ("main".equals(name))
                return new MyMethodVisitor(ASM5, visitor);
            else return visitor;
        }
    }

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader("Foo");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new MyClassVisitor(ASM5, cw);
        cr.accept(cv, ClassReader.SKIP_FRAMES);
        Files.write(Paths.get("Foo.class"), cw.toByteArray());
    }
}
