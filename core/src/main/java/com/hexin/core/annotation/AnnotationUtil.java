package com.hexin.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtField;
//import javassist.CtMethod;
//import javassist.NotFoundException;
//import javassist.bytecode.AnnotationsAttribute;
//import javassist.bytecode.ClassFile;
//import javassist.bytecode.ConstPool;
//import javassist.bytecode.FieldInfo;
//import javassist.bytecode.MethodInfo;
//import javassist.bytecode.annotation.StringMemberValue;
//import javassist.bytecode.annotation.Annotation;


public class AnnotationUtil {
	public static Object getPropertyAnnotationValue(Class obj,
			String propertyName, Class<? extends Annotation> annotation,
			String annotationPropertyName) {
		try {
			for (Field f : obj.getDeclaredFields()) {
				if (f.getName().equals(propertyName)) {
					for (Annotation anno : f.getDeclaredAnnotations()) {
						if (anno.annotationType() == annotation) {
							Method method = anno.getClass().getDeclaredMethod(
									annotationPropertyName);
							return method.invoke(anno);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
	public static Object getMethodAnnotationValue(Class obj, String method,
			Class<? extends Annotation> annotation,
			String annotationPropertyName) {
		try {
			for (Method m : obj.getMethods()) {
				if (m.getName().equals(method)) {
					for (Annotation anno : m.getDeclaredAnnotations()) {
						if (anno.annotationType() == annotation) {
							Method mtd = anno.getClass().getDeclaredMethod(
									annotationPropertyName);
							return mtd.invoke(anno);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Object getClassAnnotationValue(Class obj, Class<? extends Annotation> annotation, String annotationPropertyName) {
		try {
			Annotation[] annotations = obj.getAnnotations();
			for (Annotation anno : annotations) {
				if (anno.annotationType() == annotation) {
					Method mtd = anno.getClass().getDeclaredMethod(
							annotationPropertyName);
					return mtd.invoke(anno);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
//	public static Object getPropertyAnnotationValue(Class obj,
//			String propertyName, Class<? extends Annotation> annotation,
//			String annotationPropertyName){
//		try {
//			ClassPool pool = ClassPool.getDefault();
//			CtClass ct = pool.get(obj.getName());
//			CtField cf = ct.getField(propertyName);
//			FieldInfo fieldInfo = cf.getFieldInfo();
//			AnnotationsAttribute attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);
//			javassist.bytecode.annotation.Annotation anno = attribute.getAnnotation(annotation.getName());
//			return anno.getMemberValue(annotationPropertyName);
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public static Object getMethodAnnotationValue(Class obj, String method,
//			Class<? extends Annotation> annotation,
//			String annotationPropertyName){
//		try {
//			ClassPool pool = ClassPool.getDefault();
//			CtClass ct = pool.get(obj.getName());
//			CtMethod cm = ct.getDeclaredMethod(method);
//			MethodInfo methodInfo = cm.getMethodInfo();
//			AnnotationsAttribute attribute = (AnnotationsAttribute)methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
//			javassist.bytecode.annotation.Annotation anno = attribute.getAnnotation(annotation.getName());
//			return anno.getMemberValue(annotationPropertyName);
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	public static Object getClassAnnotationValue(Class obj, Class<? extends Annotation> annotation, String annotationPropertyName){
//		try {
//			ClassPool pool = ClassPool.getDefault();
//			CtClass ct = pool.get(obj.getName());
//			ClassFile cf = ct.getClassFile();
//			AnnotationsAttribute attribute = (AnnotationsAttribute)cf.getAttribute(AnnotationsAttribute.visibleTag);
//			javassist.bytecode.annotation.Annotation anno = attribute.getAnnotation(annotation.getName());
//			return anno.getMemberValue(annotationPropertyName);
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public static void setMethodAnnotationValue(Class obj, String method, Class<? extends Annotation> annotation, 
//			String annotationPropertyName, String value){
//		try {
//			ClassPool pool = ClassPool.getDefault();
//			CtClass ct = pool.get(obj.getName());
//			ClassFile cf = ct.getClassFile();
//			MethodInfo methodInfo = cf.getMethod(method);
//			ConstPool cp = methodInfo.getConstPool();
//			AnnotationsAttribute attribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
//			javassist.bytecode.annotation.Annotation anno = new javassist.bytecode.annotation.Annotation(annotation.getName(), cp);
//			anno.addMemberValue(annotationPropertyName, new StringMemberValue(value, cp));
//			attribute.addAnnotation(anno);
//			
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
