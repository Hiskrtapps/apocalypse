/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.exceptions;

import io.github.hiskrtapps.apocalypse.dao.api.Entity;

/**
 * Exception raised when a Primary Key object is not found using specified
 * parameters
 *
 *
 */
@SuppressWarnings("squid:MaximumInheritanceDepth")
public final class PrimaryKeyNotFoundException extends UniqueConstraintNotFoundException {

  /**
   * serial
   */
  private static final long serialVersionUID = -1599452764425874811L;

  /**
   * constructor
   * 
   * @param entityClass missing primary key belong to
   */
  public PrimaryKeyNotFoundException(final Class<? extends Entity> entityClass) {
    super(entityClass, "Primary Key");
  }

}