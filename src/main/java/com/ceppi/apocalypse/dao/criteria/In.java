/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import java.util.List;

import javax.persistence.Column;

/**
 * The {@link In} criteria implements the comparison:
 * "'column' in (list-of-values)"
 *
 */
public final class In extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s in (:%s)";

  /**
   * Creates an instance of {@link In}
   *
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public In(final Column column, final List... values) {
    this(In.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link In}
   *
   * @param name of the {@link In} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public In(final String name, final Column column, final List... values) {
    this(name, column.name(), values);
  }

  /**
   * Creates an instance of {@link In}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public In(final String columnName, final List... values) {
    this(In.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link In}
   *
   * @param name of the {@link In} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public In(final String name, final String columnName, final List... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}