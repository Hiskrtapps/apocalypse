/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import javax.persistence.Column;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Criteri test common class
 *
 */
abstract class ColumnTest {

  private static final String COLUMN_NAME = "COLUMN_NAME";

  @Column(name = COLUMN_NAME)
  private String columnField;

  Column readColumn() {

    for (final Field field : getAnnotatedDeclaredFields(this.getClass(), Column.class, true)) {
      /**
       * extracting column information
       */
      return field.getAnnotation(Column.class);

    }

    return null;
  }

  /**
   * Retrieving fields list of specified class
   *
   * @param clazz       where fields are searching
   * @param recursively param
   * @return list of fields
   */
  private static Field[] getDeclaredFields(Class clazz, boolean recursively) {
    List<Field> fields = new ArrayList<>();
    Field[] declaredFields = clazz.getDeclaredFields();
    Collections.addAll(fields, declaredFields);

    Class superClass = clazz.getSuperclass();

    if (superClass != null && recursively) {
      Field[] declaredFieldsOfSuper = getDeclaredFields(superClass, recursively);
      if (declaredFieldsOfSuper.length > 0)
        Collections.addAll(fields, declaredFieldsOfSuper);
    }

    return fields.toArray(new Field[fields.size()]);
  }

  /**
   * Retrieving fields list of specified class and which
   * are annotated by incoming annotation class
   * If recursively is true, retrieving fields from all class hierarchy
   *
   * @param clazz           - where fields are searching
   * @param annotationClass - specified annotation class
   * @param recursively     param
   * @return list of annotated fields
   */
  private static Field[] getAnnotatedDeclaredFields(Class clazz, Class<? extends Annotation> annotationClass,
      boolean recursively) {
    Field[] allFields = getDeclaredFields(clazz, recursively);
    List<Field> annotatedFields = new ArrayList<>();

    for (Field field : allFields) {
      if (field.isAnnotationPresent(annotationClass))
        annotatedFields.add(field);
    }

    return annotatedFields.toArray(new Field[annotatedFields.size()]);
  }
}
