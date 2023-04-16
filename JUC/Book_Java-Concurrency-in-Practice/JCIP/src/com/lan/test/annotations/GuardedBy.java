/*
 * Copyright (c) 2005 Brian Goetz and Tim Peierls
 * Released under the Creative Commons Attribution License
 *   (http://creativecommons.org/licenses/by/2.5)
 * Official home: http://www.jcip.net
 *
 * Any republication or derived work distributed in source code form
 * must include this copyright and license notice.
 */

package com.lan.test.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The field or method to which this annotation is applied can only be accessed
 * when holding a particular lock, which may be a built-in (synchronization) lock,
 * or may be an explicit java.util.concurrent.Lock.
 * 应用此注释的字段或方法只能在持有特定锁时访问
 * 该锁可以是内置（同步）锁
 * 也可以是显式java.util.concurrent.lock。
 * 
 * The argument determines which lock guards the annotated field or method:
 * 该参数确定哪个锁保护带注释的字段或方法：
 * <ul>
 * <li>
 * <code>this</code> : The intrinsic lock of the object in whose class the field is defined.
 * </li>
 * <li>
 * <code>class-name.this</code> : For inner classes, it may be necessary to disambiguate 'this';
 * the <em>class-name.this</em> designation allows you to specify which 'this' reference is intended
 * </li>
 * <li>
 * <code>itself</code> : For reference fields only; the object to which the field refers.
 * </li>
 * <li>
 * <code>field-name</code> : The lock object is referenced by the (instance or static) field
 * specified by <em>field-name</em>.
 * </li>
 * <li>
 * <code>class-name.field-name</code> : The lock object is reference by the static field specified
 * by <em>class-name.field-name</em>.
 * </li>
 * <li>
 * <code>method-name()</code> : The lock object is returned by calling the named nil-ary method.
 * </li>
 * <li>
 * <code>class-name.class</code> : The Class object for the specified class should be used as the lock object.
 * </li>
 * @GuardedBy（lock）表示只有在持有了某个特定的锁时才能访问这个域或方法。
 * 参数lock表示在访问被标注的域或方法时需要持有的锁。
 * lock的可能取值包括：
 * ·@GuardedBy（“this”），表示在包含对象上的内置锁（被标注的方法或域是该对象的成员）。
 *
 * ·@GuardedBy（“fieldName”），表示与fieldName引用的对象相关联的锁，
 *    可以是一个隐式锁（对于不引用一个Lock的域），也可以是一个显式锁（对于引用了一个Lock的域）。
 * 
 * ·@GuardedBy（“Class Name.fieldName”），类似于@GuardedBy（“fieldName”），
 *    但指向在另一个类的静态域中持有的锁对象。·@GuardedBy（“methodName（）”），是指通过调用命名方法返回的锁对象。
 * 
 * ·@GuardedBy（“ClassName.class”），是指命名类的类字面量对象。
 * 
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GuardedBy {
    String value();
}
