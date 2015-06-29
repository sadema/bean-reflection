package nl.kristalsoftware.beanreflection.main;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BeanReflectionUtilsImpl implements BeanReflectionUtils {

	public BeanReflectionUtilsImpl() {}

	@Inject
	private Logger log;

	@Override
	public Field getField(Class<?> bean, String fieldName) throws NoSuchFieldException {
		Field f = bean.getDeclaredField(fieldName);
		return f;
	}

	@Override
	public List<Field> getNonStaticFields(Class<?> c) {
		Field[] fieldArr = c.getDeclaredFields();
		List<Field> list = new ArrayList<Field>();
		for (Field f : fieldArr) {
			if (!Modifier.isStatic(f.getModifiers())) {
				list.add(f);
				log.log(Level.FINE, f.toString());
			}
		}
		return list;
	}

	private <T> Object getFieldValue(T bean, Field field) {
		int mod = field.getModifiers();
		if (!Modifier.isPublic(mod)) {
			field.setAccessible(true);
		}
		Object val = null;
		try {
			val = field.get(bean);
		} catch (IllegalAccessException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return val;
	}

	@Override
	public <T,V> V getFieldValue(T bean, Field field, Class<V> clazz) {
		int mod = field.getModifiers();
		if (!Modifier.isPublic(mod)) {
			field.setAccessible(true);
		}
		V val = null;
		try {
			val = (V) field.get(bean);
		} catch (IllegalAccessException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return val;
	}

//
//	public Map<String, Field> createFieldsMap(List<Field> fieldList) {
//		Map<String,Field> fieldMap = new HashMap<String,Field>();
//		Iterator<Field> iter = fieldList.iterator();
//		while (iter.hasNext()) {
//			Field field = iter.next();
//			fieldMap.put(field.getName(), field);
//			Logger.debug(field.toString());
//		}
//		return fieldMap;
//	}
//
//	@SuppressWarnings("unchecked")
//	public <V,T> V getFieldValue(Field field, T bean) {
//		//Class<?> c = bean.getClass();
//		//int mod = c.getModifiers();
//		int mod = field.getModifiers();
//		if (!Modifier.isPublic(mod)) {
//			field.setAccessible(true);
//		}
//		V var = null;
//		try {
//			var = (V) field.get(bean);
//		} catch (Exception e) {
//			Logger.error(e.toString());
//		}
//		return var;
//	}
//
//	@Override
//	public <V,T> void setFieldValue(Field field, V value, T bean) {
//		int mod = field.getModifiers();
//		if (!Modifier.isPublic(mod)) {
//			field.setAccessible(true);
//		}
//		try {
//			field.set(bean, value);
//		} catch (Exception e) {
//			Logger.error(e.toString());
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public <V,T> V getFieldValueWithGetter(Field field, T bean) {
//		V var = null;
//		Class<?> c = bean.getClass();
//		String fieldName = field.getName();
//		Logger.debug(fieldName);
//		Method method;
//		try {
//			method = c.getMethod("get" + StringUtils.capitalize(fieldName));
//			Logger.debug(method.toString());
//			var = (V) method.invoke(bean);
//		} catch (Exception e) {
//			Logger.error(e.getMessage());
//		}
//		return var;
//	}
//
//	public <V,T> void setFieldValueWithSetter(Field field, V val, T bean) {
//		Class<?> c = bean.getClass();
//		String fieldName = field.getName();
//		Logger.debug(fieldName);
//		Method method;
//		try {
//			Class<?>[] ptypes = new Class<?>[1];
//			/*
//			Class<?> valClass = val.getClass();
//			TypeVariable<?>[] tvar = valClass.getTypeParameters();
//			if (tvar.length > 0) {
//				// parameterized class
//				valClass.get
//			}
//			else {
//				// non parameterized class
//			}
//			*/
//			ptypes[0] = val.getClass();
//			method = c.getMethod("set" + StringUtils.capitalize(fieldName), ptypes);
//			Logger.info(method.toString());
//			method.invoke(bean, val);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Logger.error(e.getMessage());
//		}
//	}
//
//	@Override
//	public <V,T> void setFieldCollectionValueWithSetter(Field field, Collection<V> val, T bean) {
//		Class<?> beanClass = bean.getClass();
//		Logger.debug("bean: " + beanClass.getName());
//		String fieldName = field.getName();
//		Logger.debug("fieldName: " + fieldName);
//		Class<?> valClass = val.getClass();	//ArrayList
//		Logger.debug("val: " + valClass.getName());
//		Method[] methodArr = beanClass.getMethods();
//		for (Method method : methodArr) {
//			if (method.getName().startsWith("set" + StringUtils.capitalize(fieldName))) {
//				Logger.info(method.toGenericString());
//				Type[] genParams = method.getGenericParameterTypes();
//				if (genParams.length > 0) {
//					Logger.debug(genParams[0].toString());
//					try {
//						method.invoke(bean, val);
//					} catch (Exception e) {
//						e.printStackTrace();
//						Logger.error(e.getMessage());
//					}
//				}
//			}
//		}
//
//		/*
//		Type[] interfaceArr = valClass.getGenericInterfaces();
//		for (Type interfaceType : interfaceArr) {
//			if (interfaceType instanceof ParameterizedType) {
//				Logger.info(interfaceType.getClass().getName());
//			}
//		}
//
//		TypeVariable<?>[] tvar = valClass.getTypeParameters();
//		Class<?> beanClass = bean.getClass();
//		try {
//			Class<?>[] ptypes = new Class<?>[1];
//			ptypes[0] = valClass;
//			Method method = beanClass.getMethod("set" + StringUtils.capitalize(fieldName), ptypes);
//			Logger.info("FieldName: " + fieldName + " - " + "Method: " + " " + method.toString());
//			method.invoke(bean, val);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Logger.error(e.getMessage());
//		}
//		*/
//	}
//
//	/*
//	@Override
//	public <V,T> void setFieldCollectionValueWithSetter(Field field, Collection<V> val, T bean) {
//		String fieldName = field.getName();
//		Class<?> valClass = val.getClass();
//		Type[] interfaceArr = valClass.getGenericInterfaces();
//		for (Type interfaceType : interfaceArr) {
//			Logger.info(interfaceType.getClass().toString());
//			if (interfaceType instanceof ParameterizedType) {
//				ParameterizedType ptype = (ParameterizedType) interfaceType;
//				String rawName = ptype.getRawType().toString();
//				Logger.info(rawName);
//				if (rawName.endsWith("Collection")) {
//					Class<?>[] ptypes = new Class<?>[1];
//					ptypes[0] = valClass;
//					//TypeVariable<?>[] tvar = valClass.getTypeParameters();
//					//String parameterName = tvar[0].getName();
//					Class<?> beanClass = bean.getClass();
//					try {
//						Method method = beanClass.getMethod("set" + StringUtils.capitalize(fieldName), ptypes);
//						Logger.info("FieldName: " + fieldName + " - " + "Method: " + " " + method.toString());
//						method.invoke(bean, val);
//					} catch (Exception e) {
//						e.printStackTrace();
//						Logger.error(e.getMessage());
//					}
//				}
//			}
//		}
//	}
//	*/
//	public <T,A extends Annotation> List<Field> getAnnotatedFields(T bean, Class<A> annotationClass) {
//		List<Field> annotatedFields = new ArrayList<Field>();
//		Class<?> c = bean.getClass();
//		Field[] fieldArr = c.getDeclaredFields();
//		for (Field f : fieldArr) {
//			if (f.isAnnotationPresent(annotationClass)) {
//				annotatedFields.add(f);
//			}
//		}
//		return annotatedFields;
//	}
//
//	@Override
//	public <T, A extends Annotation> Field getAnnotatedField(T bean, Class<A> annotationClass) {
//		Field fld = null;
//		Class<?> c = bean.getClass();
//		Field[] fieldArr = c.getDeclaredFields();
//		for (Field f : fieldArr) {
//			if (f.isAnnotationPresent(annotationClass)) {
//				fld = f;
//				break;
//			}
//		}
//		return fld;
//	}

}
