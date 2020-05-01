/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.criteria;

import javax.persistence.Column;

/**
 * The {@link Greater} criteria implements the comparison: "'column' &gt; value"
 *
 */
public final class Greater extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s > :%s";

  /**
   * Creates an instance of {@link Greater}
   *
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Greater(final Column column, final Object... values) {
    this(Greater.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link Greater}
   *
   * @param name of the {@link Greater} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Greater(String name, final Column column, final Object... values) {
    this(name, column.name(), values);
  }

  /**
   * Creates an instance of {@link Greater}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Greater(final String columnName, final Object... values) {
    this(Greater.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link Greater}
   *
   * @param name of the {@link Greater} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Greater(String name, final String columnName, final Object... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}