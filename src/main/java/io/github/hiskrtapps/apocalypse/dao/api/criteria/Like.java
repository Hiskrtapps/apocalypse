/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.criteria;

import javax.persistence.Column;

/**
 * The {@link Like} criteria implements the comparison: "'column' = value"
 *
 */
public final class Like extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s like ':%s'";

  /**
   * Creates an instance of {@link Like}
   *
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Like(final Column column, final Object... values) {
    this(Like.class.getSimpleName(), column, values);
  }

  /**
   * Creates an instance of {@link Like}
   *
   * @param name of the {@link Like} criteria
   * @param column to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Like(String name, final Column column, final Object... values) {
    this(name, column.name(), values);
  }

  /**
   * Creates an instance of {@link Like}
   *
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Like(final String columnName, final Object... values) {
    this(Like.class.getSimpleName(), columnName, values);
  }

  /**
   * Creates an instance of {@link Like}
   *
   * @param name of the {@link Like} criteria
   * @param columnName of the {@link Column} to be compared
   * @param values the column is compared with (multiple values should be
   *          specified in case of batch operation)
   */
  public Like(String name, final String columnName, final Object... values) {
    super(name, TEMPLATE, columnName, arrayOfValues(values));
  }

}