/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.exceptions;

import io.github.hiskrtapps.apocalypse.dao.Entity;

/**
 * Exception raised when a Column object is not found using specified parameters
 *
 *
 */
public final class ColumnNotFoundException extends MetadataObjectNotFoundException {

  /**
   * serialize
   */
  private static final long serialVersionUID = -1599452764425874811L;

  /**
   * missing object type
   */
  private static final String OBJECT_TYPE = "Column";

  /**
   * constructor
   * 
   * @param entityClass missing unique constraint belong to
   * @param columnName name of the missing column
   */
  public ColumnNotFoundException(final Class<? extends Entity> entityClass, final String columnName) {
    super(entityClass, OBJECT_TYPE, columnName);
  }

}
