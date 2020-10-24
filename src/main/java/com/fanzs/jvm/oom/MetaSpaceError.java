package com.fanzs.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MetaSpaceError {

    static class OOMTest {

    }

    /**
     * 原空间异常
     *
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                ++i;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);

                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return proxy.invoke(obj, args);
                    }
                });
                enhancer.create();

            }
        } catch (Exception e) {
            System.out.println("异常数量 i = " + i);
            System.out.println(e);
            throw e;
        }
    }

    /**
     * result
     *
     * 异常数量 i = 9333
     * net.sf.cglib.core.CodeGenerationException: java.lang.reflect.InvocationTargetException-->null
     * Exception in thread "main" net.sf.cglib.core.CodeGenerationException: java.lang.reflect.InvocationTargetException-->null
     * 	at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:345)
     * 	at net.sf.cglib.proxy.Enhancer.generate(Enhancer.java:492)
     * 	at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:114)
     * 	at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:291)
     * 	at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:480)
     * 	at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:305)
     * 	at com.fanzs.jvm.oom.MetaSpaceError.main(MetaSpaceError.java:30)
     * Caused by: java.lang.reflect.InvocationTargetException
     * 	at sun.reflect.GeneratedMethodAccessor1.invoke(Unknown Source)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at net.sf.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:459)
     * 	at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:336)
     * 	... 6 more
     * Caused by: java.lang.OutOfMemoryError: Metaspace
     * 	at java.lang.ClassLoader.defineClass1(Native Method)
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
     * 	... 11 more
     */
}