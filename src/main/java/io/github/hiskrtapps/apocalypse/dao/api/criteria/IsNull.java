/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.criteria;

import javax.persistence.Column;

/**
 * The {@link IsNull} criteria implements the comparison: "'column' is not null"
 *
 */
public final class IsNull extends ColumnCriteria {

  /**
   * comparison template that is applied
   */
  private static final String TEMPLATE = "%s is null";

  /**
   * Creates an instance of {@link IsNull}
   *
   * @param column to be compared
   */
  public IsNull(final Column column) {
    this(IsNull.class.getSimpleName(), column);
  }

  /**
   * Creates an instance of {@link IsNull}
   *
   * @param name of the {@link IsNull} criteria
   * @param column to be compared
   */
  public IsNull(String name, final Column column) {
    this(name, column.name());
  }

  /**
   * Creates an instance of {@link IsNull}
   *
   * @param columnName of the {@link Column} to be compared
   */
  public IsNull(final String columnName) {
    this(IsNull.class.getSimpleName(), columnName);
  }

  /**
   * Creates an instance of {@link IsNull}
   *
   * @param name of the {@link IsNull} criteria
   * @param columnName of the {@link Column} to be compared
   */
  public IsNull(String name, final String columnName) {
    super(name, TEMPLATE, columnName);
  }

}