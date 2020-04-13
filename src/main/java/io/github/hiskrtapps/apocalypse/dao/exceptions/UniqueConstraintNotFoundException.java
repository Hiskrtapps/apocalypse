/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.exceptions;

import io.github.hiskrtapps.apocalypse.dao.Entity;
import org.apache.commons.lang3.StringUtils;

/**
 * Exception raised when a Unique Constraint object is not found using specified
 * parameters
 *
 *
 */
public class UniqueConstraintNotFoundException extends MetadataObjectNotFoundException {

  /**
   * serial
   */
  private static final long serialVersionUID = -1599452764425874811L;

  /**
   * missing object type
   */
  private static final String OBJECT_TYPE = "Unique Constraint";

  /**
   * constructor
   * 
   * @param entityClass missing unique constraint belong to
   * @param uniqueConstraintName name of the missing unique constraint
   */
  public UniqueConstraintNotFoundException(final Class<? extends Entity> entityClass,
      final String uniqueConstraintName) {
    super(entityClass, OBJECT_TYPE, uniqueConstraintName);
  }

  /**
   * constructor
   * 
   * @param entityClass missing unique constraint belong to
   * @param uniqueConstraintName name of the missing unique constraint
   */
  public UniqueConstraintNotFoundException(final Class<? extends Entity> entityClass, final String uniqueConstraintName,
      MetadataObjectNotFoundException missingPart) {
    super(entityClass, OBJECT_TYPE, uniqueConstraintName, missingPart);
  }

  /**
   * constructor
   * 
   * @param entityClass missing unique constraint belong to
   * @param columnNames representing the missing uniqueConstraint
   */
  public UniqueConstraintNotFoundException(final Class<? extends Entity> entityClass, final String... columnNames) {
    super(entityClass, OBJECT_TYPE, StringUtils.join(columnNames, ", "));
  }

}