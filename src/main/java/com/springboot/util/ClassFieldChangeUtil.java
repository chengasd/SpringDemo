package com.springboot.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: miachelyin
 * @Description: 比较字段是否有变更工具类
 * @Date: 2018/3/2
 * @Modified By:
 */
public class ClassFieldChangeUtil {
	
	public static <T> void contrastObj(T oldBean, T newBean) {
		T pojo1 = (T) oldBean;
		T pojo2 = (T) newBean;
		try {
			Class clazz = pojo1.getClass();
			Field[] fields = pojo1.getClass().getDeclaredFields();
			for (Field field : fields) {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				Method getMethod = pd.getReadMethod();
				Object o1 = getMethod.invoke(pojo1);
				Object o2 = getMethod.invoke(pojo2);
				if (o1 == null || o2 == null) {
					continue;
				}

				if (o1.toString().equals(o2.toString())) {
					Method setMethod = pd.getWriteMethod();
					setMethod.invoke(pojo1, new Object[] { null });
					setMethod.invoke(pojo2, new Object[] { null });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}