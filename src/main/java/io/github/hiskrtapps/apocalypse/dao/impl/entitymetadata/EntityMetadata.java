/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.impl.entitymetadata;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

import io.github.hiskrtapps.apocalypse.dao.Entity;

/**
 * EntityMetadata related to an Entity
 * 
 * An EntityMetadata can be created starting from any bean class if properly
 * annotated; in details Class should have:
 * <ul>
 * <li>{@link javax.persistence.Entity} annotation at class level</li>
 * <li>{@link javax.persistence.Table} annotation at class level specifying:
 * <ul>
 * <li>name representing the name of the table</li>
 * <li>array of {@link UniqueConstraint} representing primary key constraint and
 * any other unique key constraint. Each constraint shall specify columns it is
 * composed by</li>
 * </ul>
 * </li>
 * <li>{@link javax.persistence.Column} annotation at field level representing
 * the name of the column related to the field</li>
 * <li>{@link javax.persistence.SequenceGenerator} annotation at field level to
 * specify that the field should be valued from a database sequence (actually
 * this annotation shall be used only on a field that represent a single-field
 * primary key)</li>
 * <li>{@link javax.persistence.Id} annotation at field level to specify that
 * field is part of the primary key. If this annotation is missing on a field
 * any sequence information will be lost</li>
 * <li>{@link javax.persistence.Lob} annotation at field level to specify that
 * field is of type Lob</li>
 * </ul>
 * 
 * All these information can be asked to EntityMetadata at runtime to perform
 * dynamically database operations.
 *
 *
 * @param <E> entity metadata are related to
 */
public interface EntityMetadata<E extends Entity> {

  /**
   * Gets the value of entityClass
   * 
   * @return the entityClass
   */
  Class<E> getEntityClass();

  /**
   * Gets the value of tableName
   * 
   * @return the tableName
   */
  String getTableName();

  /**
   * Gets the value of primaryKey
   * 
   * @return the primaryKey
   */
  UniqueConstraint getPrimaryKey();

  /**
   * Gets unique constraint identified by the list of columns passed as
   * parameter
   * 
   * @param columnNames identifying columns that compound the unique constraint
   * @return identified unique constraint
   */
  UniqueConstraint getUniqueKeyByColumnNames(String... columnNames);

  /**
   * get Column metadata object identified by columnName
   * 
   * @param columnName used to find Column metadata object
   * @return Column metadata object
   */
  Column getColumnByName(String columnName);

  /**
   * Retrieve all the unique constraints
   *
   * @return a copy of all the unique constraints
   */
  Map<String, UniqueConstraint> getUniqueKeys();

  /**
   * get iterable of column names
   * 
   * @return iterable of column names
   */
  Iterable<String> getColumnNames();

  /**
   * get field name related to column name passed as parameter
   * 
   * @param columnName used to find field name
   * @return field name
   */
  Field getFieldByColumnName(String columnName);

  /**
   * return sequence if one is related to primary key field
   *
   * @return sequence if any, null otherwise
   */
  String getSequenceName();

  /**
   * return the Field for a given columnName
   *
   * @param columnName to get the field by
   * @return field if any, null otherwise
   */
  Field getField(String columnName);

}
