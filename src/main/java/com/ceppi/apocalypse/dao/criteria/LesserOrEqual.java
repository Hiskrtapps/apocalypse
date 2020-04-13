/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import javax.persistence.Column;

/**
 * The {@link LesserOrEqual} criteria implements the comparison: "'column' <= value"
 *
 */
public final class LesserOrEqual extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s <= :%s";

  /**
   * Creates an instance of {@link LesserOrEqual}
   *
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public LesserOrEqual(final Column column, final Object... values) {
    this(LesserOrEqual.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link LesserOrEqual}
   *
   * @param name of the {@link LesserOrEqual} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public LesserOrEqual(String name, final Column column, final Object... values) {
    this(name, column.name(), values);
  }

  /**
   * Creates an instance of {@link LesserOrEqual}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public LesserOrEqual(final String columnName, final Object... values) {
    this(LesserOrEqual.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link LesserOrEqual}
   *
   * @param name of the {@link LesserOrEqual} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public LesserOrEqual(String name, final String columnName, final Object... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}