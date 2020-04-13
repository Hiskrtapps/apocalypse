/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.criteria;

import javax.persistence.Column;

/**
 * The {@link Equal} criteria implements the comparison: "'column' >= value"
 *
 */
public final class GreaterOrEqual extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s >= :%s";

  /**
   * Creates an instance of {@link GreaterOrEqual}
   *
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public GreaterOrEqual(final Column column, final Object... values) {
    this(GreaterOrEqual.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link GreaterOrEqual}
   *
   * @param name of the {@link GreaterOrEqual} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public GreaterOrEqual(String name, final Column column, final Object... values) {
    this(name, column.name(), values);
  }

  /**
   * Creates an instance of {@link GreaterOrEqual}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public GreaterOrEqual(final String columnName, final Object... values) {
    this(GreaterOrEqual.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link GreaterOrEqual}
   *
   * @param name of the {@link GreaterOrEqual} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public GreaterOrEqual(String name, final String columnName, final Object... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}