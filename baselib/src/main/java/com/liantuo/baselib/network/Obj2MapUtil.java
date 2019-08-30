package com.liantuo.baselib.network;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Obj2MapUtil {

    public static Object mapToObject(HashMap<String, String> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }


    /**
     * 循环向上转型, 获取对象的 DeclaredFields
     *
     * @param object
     * @return
     */
    public static Map<String, Object> objectToMap(Object object) {
        if (object == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    try {
                        // 反射获取对象的属性时，会获得serialVersionUID，serialVersionUID不是真正的参数
                        if (field.get(object) != null && field.getName() != "serialVersionUID") {
                            map.put(field.getName(), field.get(object));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return map;
    }


//    public static Map<String, Object> objectToMap(Object obj) {
//        if (obj == null) {
//            return null;
//        }
//
//        Map<String, Object> map = new HashMap<>();
//
//        Field[] declaredFields = obj.getClass().getDeclaredFields();
//        for (Field field : declaredFields) {
//            field.setAccessible(true);
//            try {
//                if (field.get(obj) != null) {
//                    map.put(field.getName(), field.get(obj));
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        return map;
//    }

}
