/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import javax.persistence.Column;

import org.apache.commons.lang3.tuple.Pair;

/**
 * The {@link Between} criteria implements the comparison:
 * "'column' between minimum-value and maximum-value"
 *
 */
public final class Between extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s between :%s and :%s";

  /**
   * Creates an instance of {@link Between}
   *
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Between(final Column column, final Pair... values) {
    this(Equal.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link Between}
   *
   * @param name of the {@link Between} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Between(String name, final Column column, final Pair... values) {
    this(name, column.name(), values);
  }

  /**
   * Creates an instance of {@link Between}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Between(final String columnName, final Pair... values) {
    this(Equal.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link Between}
   *
   * @param name of the {@link Between} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Between(String name, final String columnName, final Pair... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}
