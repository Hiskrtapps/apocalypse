/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import javax.persistence.Column;

/**
 * The {@link IsNotNull} criteria implements the comparison: "'column' is not null"
 *
 */
public final class IsNotNull extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s is not null";

  /**
   * Creates an instance of {@link IsNotNull}
   *
   * @param column to be compared
   */
  public IsNotNull(final Column column) {
    this(IsNotNull.class.getSimpleName(), column);
  }

  /**
   * Creates an instance of {@link IsNotNull}
   *
   * @param name of the {@link IsNotNull} criteria
   * @param column to be compared
   */
  public IsNotNull(String name, final Column column) {
    this(name, column.name());
  }

  /**
   * Creates an instance of {@link IsNotNull}
   *
   * @param columnName of the {@link Column} to be compared
   */
  public IsNotNull(final String columnName) {
    this(IsNotNull.class.getSimpleName(), columnName);
  }

  /**
   * Creates an instance of {@link IsNotNull}
   *
   * @param name of the {@link IsNotNull} criteria
   * @param columnName of the {@link Column} to be compared
   */
  public IsNotNull(String name, final String columnName) {
    super(name, TEMPLATE, columnName);
  }

}