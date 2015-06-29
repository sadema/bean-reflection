package nl.kristalsoftware.beanreflection.main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BeanReflectionUtils {

	/**
	 * Get a field from a bean by fieldname
	 * @param bean
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 */
	Field getField(Class<?> bean, String fieldName) throws NoSuchFieldException;

	/**
	 *
	 * @param c
	 * @return
	 */
	List<Field> getNonStaticFields(Class<?> bean);

	/**
	 * get the value of a field in the bean
	 * @param bean
	 * @param field
	 * @param clazz
	 * @param <T>
	 * @param <V>
	 * @return
	 */
	<T,V> V getFieldValue(T bean, Field field, Class<V> clazz);

//
//	<T,A extends Annotation> List<Field> getAnnotatedFields(T bean, Class<A> annotationClass);
//
//	<T,A extends Annotation> Field getAnnotatedField(T bean, Class<A> annotationClass);
//
//	Map<String, Field> createFieldsMap(List<Field> fieldList);
//
//	<V,T> V getFieldValue(Field field, T bean);
//
//	<V,T> void setFieldValue(Field field, V value, T bean);
//
//	<V,T> V getFieldValueWithGetter(Field field, T bean);
//
//	/**
//	 * Set the value with a setter method where val is NOT a parameterized type
//	 * @param field
//	 * @param val
//	 * @param bean
//	 */
//	<V,T> void setFieldValueWithSetter(Field field, V val, T bean);
//
//	/**
//	 * Set the value with a setter method where val has a parameterized Collection<> interface
//	 * @param field
//	 * @param val
//	 * @param bean
//	 */
//	<V,T> void setFieldCollectionValueWithSetter(Field field, Collection<V> val, T bean);

}
