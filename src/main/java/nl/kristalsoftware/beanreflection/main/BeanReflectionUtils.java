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
	 * get a field with a specified annotation
	 * @param bean
	 * @param annotation
	 * @param <A>
	 * @return
	 */
	<A extends Annotation> Field getAnnotatedField(Class<?> bean, Class<A> annotation);

	/**
	 * get a list of fields with a specified annotation
	 * @param bean
	 * @param annotationClass
	 * @param <A>
	 * @return
	 */
	<A extends Annotation> List<Field> getAnnotatedFields(Class<?> bean, Class<A> annotationClass);

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

	/**
	 * get the value of a field in the bean using a getter
	 * @param bean
	 * @param field
	 * @param clazz
	 * @param <T>
	 * @param <V>
	 * @return
	 */
	<T,V> V getFieldValueWithGetter(T bean, Field field, Class<V> clazz) throws NoSuchMethodException;

//	Map<String, Field> createFieldsMap(List<Field> fieldList);
//
//	<V,T> void setFieldValue(Field field, V value, T bean);
//
//
//	/**
//	 * Set the value with a setter method where val has a parameterized Collection<> interface
//	 * @param field
//	 * @param val
//	 * @param bean
//	 */
//	<V,T> void setFieldCollectionValueWithSetter(Field field, Collection<V> val, T bean);

}
