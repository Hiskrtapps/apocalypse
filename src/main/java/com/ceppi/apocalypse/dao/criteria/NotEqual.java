/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import javax.persistence.Column;

/**
 * The {@link NotEqual} criteria implements the comparison: "'column' <> value"
 *
 */
public final class NotEqual extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s <> :%s";

  /**
   * Creates an instance of {@link NotEqual}
   *
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public NotEqual(final Column column, final Object... values) {
    this(NotEqual.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link NotEqual}
   *
   * @param name of the {@link NotEqual} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public NotEqual(String name, final Column column, final Object... values) {
    this(name, column.name(), values);
  }
  
  /**
   * Creates an instance of {@link NotEqual}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public NotEqual(final String columnName, final Object... values) {
    this(NotEqual.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link NotEqual}
   *
   * @param name of the {@link NotEqual} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public NotEqual(String name, final String columnName, final Object... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}