/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import javax.persistence.Column;

/**
 * The {@link Equal} criteria implements the comparison: "'column' = value"
 *
 */
public final class Equal extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s = :%s";

  /**
   * Creates an instance of {@link Equal}
   * 
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Equal(final Column column, final Object... values) {
    this(Equal.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link Equal}
   *
   * @param name of the {@link Equal} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Equal(String name, final Column column, final Object... values) {
    this(name, column.name(), values);
  }

  /**
   * Creates an instance of {@link Equal}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Equal(final String columnName, final Object... values) {
    this(Equal.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link Equal}
   *
   * @param name of the {@link Equal} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Equal(String name, final String columnName, final Object... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}