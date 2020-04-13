/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata;

import com.github.hiskrtapps.apocalypse.dao.Entity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Standard implementation for EntityMetadata interface
 * <p>
 * actually {@link javax.persistence.Entity} and {@link javax.persistence.Lob}
 * are not parsed but will be useful for future uses
 *
 * @param <E> entity metadata are related to
 */
public final class EntityMetadataImpl<E extends Entity> implements EntityMetadata<E> {

  /**
   * class of entity metadata are related
   */
  private Class<E> entityClass;

  /**
   * name of the table related to this entity
   */
  private String tableName;

  /**
   * primary key unique constraint if the table specify one
   */
  private UniqueConstraint primaryKey;

  /**
   * sequenceName related to the primary key field if the primary key is composed by
   * a single field
   */
  private String sequenceName;

  /**
   * it contains the map columnNames -> Fields
   */
  private final Map<String, Field> fieldsByColumnName = new HashMap<>();

  /**
   * maps to bind list of columns compound an unique constraint to the unique
   * constraint itself to perform retrieving of the UniqueConstraint metadata by
   * column names
   */
  private final Map<String, UniqueConstraint> uniqueKeyByColumnNames = new TreeMap<>();

  /**
   * maps to bind column names to Column metadata object to perform retrieving
   * of the Column metadata by column name
   */
  private final Map<String, Column> columnsByName = new TreeMap<>();

  /**
   * parse class metadata and construct EntityMetadata object
   *
   * @param entityClass metadata are related to
   */
  public EntityMetadataImpl(final Class<E> entityClass) {
    super();
    this.entityClass = entityClass;
    createMetadata(entityClass);
  }

  @Override
  public final Class<E> getEntityClass() {
    return entityClass;
  }

  @Override
  public final String getTableName() {
    return tableName;
  }

  @Override
  public final UniqueConstraint getPrimaryKey() {
    return primaryKey;
  }

  @Override
  public final UniqueConstraint getUniqueKeyByColumnNames(final String... columnNames) {
    return uniqueKeyByColumnNames.get(StringUtils.join(columnNames, '-'));
  }

  @Override
  public final Map<String, UniqueConstraint> getUniqueKeys() {
    return new TreeMap<>(uniqueKeyByColumnNames);
  }

  @Override
  public final Column getColumnByName(final String columnName) {
    return columnsByName.get(columnName);
  }

  @Override
  public final Iterable<String> getColumnNames() {
    return columnsByName.keySet();
  }

  @Override
  public final Field getFieldByColumnName(final String columnName) {
    return EntityDataRegistry.instance().field(entityClass, columnName);
  }

  @Override
  public final String getSequenceName() {
    return sequenceName;
  }

  @Override
  public Field getField(String columnName) {
    return fieldsByColumnName.get(columnName);
  }

  /**
   * method is called during construction of the metadata. entityClass is
   * analyzed using reflection, all needed annotation containing metadata
   * information are scanned extracted and data is used to populate
   * EntityMetadata object
   *
   * @param entityClass to extract metadata from
   */
  private final void createMetadata(final Class<E> entityClass) {

    /**
     * Check for class level @Table annotation presence
     */
    if (entityClass.isAnnotationPresent(Table.class)) {
      /**
       * extracting table information
       */
      final Table table = entityClass.getAnnotation(Table.class);
      tableName = table.name();

      for (final UniqueConstraint uniqueConstraint : table.uniqueConstraints()) {
        /**
         * extracting unique keys information
         */
        if (uniqueConstraint.name().contains("_PK")) {
          /**
           * extracting primary key information
           */
          primaryKey = uniqueConstraint;
        }
        uniqueKeyByColumnNames.put(StringUtils.join(uniqueConstraint.columnNames(), '-'), uniqueConstraint);
      }
    }

    /**
     * Check for field level @Column, @Id, @SequenceGenerator annotations
     * presence
     */
    for (final Field field : getAnnotatedDeclaredFields(entityClass, Column.class, true)) {
      /**
       * extracting column information
       */
      final Column column = field.getAnnotation(Column.class);

      columnsByName.put(column.name(), column);

      /**
       * extracting sequence information related to primary key of course a PK
       * constraint containing the field should exists (even if not checked
       * now)
       */
      if (field.isAnnotationPresent(SequenceGenerator.class) && field.isAnnotationPresent(Id.class)) {
        SequenceGenerator sequence = field.getAnnotation(SequenceGenerator.class);
        sequenceName = sequence.name();
        EntityDataRegistry.instance().register(sequenceName, sequence);
      }

      /*
       * for the entity it registers a new couple columnName -> Field.
       * Field accessibility is set to true
        */
      field.setAccessible(true);
      fieldsByColumnName.put(column.name(), field);

    }

    EntityDataRegistry.instance().register(entityClass, this);

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
    List<Field> annotatedFields = new ArrayList<Field>();

    for (Field field : allFields) {
      if (field.isAnnotationPresent(annotationClass))
        annotatedFields.add(field);
    }

    return annotatedFields.toArray(new Field[annotatedFields.size()]);
  }

}
