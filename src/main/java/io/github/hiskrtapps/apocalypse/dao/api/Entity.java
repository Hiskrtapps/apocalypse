/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao;

/**
 * Interface for Entity Beans.
 */
public interface Entity {

  /**
   * @param columnName to get by
   * @param <T> type of the return value
   * @return value for the given column, null if column does not exists
   */
  <T> T get(String columnName);

  /**
   * @param columnName to set by
   * @param columnValue to set
   */
  void set(String columnName, Object columnValue);

  /**
   * @param values sorted that compound primary key
   */
  void primaryKey(Object... values);

}