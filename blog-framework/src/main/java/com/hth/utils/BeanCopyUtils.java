package com.hth.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    //将构造方法设置为私有，可以确保该类不能被外部代码通过构造函数创建实例。
    // 而静态方法可以直接通过类名来调用，而不需要创建对象。这可以防止不必要的对象创建，提高了代码的效率和安全性。
    private BeanCopyUtils(){

    }

    //将一个源对象的属性值复制到一个新创建的目标对象中，并返回目标对象
    public static <V> V copyBean(Object source,Class<V> clazz){
        //创建目标对象
        V result = null;
        try {
            //调用 newInstance() 方法来创建实例对象
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //将一个源对象列表中的多个对象的属性值复制到目标对象列表中，并返回目标对象列表
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return
                //将输入的列表 list 转换为一个流
                list.stream()
                //将流中的每个元素 o 传递给 copyBean(o, clazz) 方法，即对每个元素执行属性复制操作。
                // 这将生成一个包含属性复制后的目标对象的新流。
                .map(o->copyBean(o,clazz))
                //将流中的元素收集到一个列表中，并返回这个列表。在这里，它将经过属性复制后的目标对象收集到一个新的列表中。
                .collect(Collectors.toList());
    }
}
