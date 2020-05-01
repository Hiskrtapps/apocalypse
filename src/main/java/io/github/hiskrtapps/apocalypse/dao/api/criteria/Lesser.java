/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.criteria;

import javax.persistence.Column;

/**
 * The {@link Lesser} criteria implements the comparison: "'column' &lt; value"
 *
 */
public final class Lesser extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s < :%s";

  /**
   * Creates an instance of {@link Lesser}
   *
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Lesser(final Column column, final Object... values) {
    this(Lesser.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link Lesser}
   *
   * @param name of the {@link Lesser} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Lesser(String name, final Column column, final Object... values) {
    this(name, column.name(), values);
  }

  /**
   * Creates an instance of {@link Lesser}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Lesser(final String columnName, final Object... values) {
    this(Lesser.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link Lesser}
   *
   * @param name of the {@link Lesser} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Lesser(String name, final String columnName, final Object... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}