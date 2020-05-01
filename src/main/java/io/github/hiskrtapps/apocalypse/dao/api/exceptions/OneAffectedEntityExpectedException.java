/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.exceptions;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.PersistenceException;
import java.util.Arrays;

/**
 * Exception raised when a query by UniqueConstraint is performed but more than
 * 1 result is returned
 *
 *
 */
public final class OneAffectedEntityExpectedException extends PersistenceException {

  /**
   * serial
   */
  private static final long serialVersionUID = -1599452764425874811L;

  /**
   * number of rows affected by the operation
   */
  private final int affected;

  /**
   * constraint name forcing single result
   */
  private final String uniqueConstraintName;

  /**
   * values used to perform the operation
   */
  private final Object[] values;

  /**
   * constructor
   * 
   * @param affected number of rows affected by the operation
   * @param uCName constraint name forcing single result
   * @param values used to perform the operation
   */
  public OneAffectedEntityExpectedException(final int affected, final String uCName, final Object... values) {
    super(affected + " affected rows; 1 expected due to " + uCName + " check > " + StringUtils.join(values, ", "));
    this.affected = affected;
    this.uniqueConstraintName = uCName;
    this.values = values;
  }

  /**
   * Gets the value of affected
   * 
   * @return the affected field of
   *         {@link OneAffectedEntityExpectedException#affected}
   */
  public final int getAffected() {
    return affected;
  }

  /**
   * Gets the value of uniqueConstraintName
   * 
   * @return the uniqueConstraintName field of
   *         {@link OneAffectedEntityExpectedException#uniqueConstraintName}
   */
  public final String getUniqueConstraintName() {
    return uniqueConstraintName;
  }

  /**
   * Gets the value of values
   * 
   * @return the values field of
   *         {@link OneAffectedEntityExpectedException#values}
   */
  public final Object[] getValues() {
    return Arrays.copyOf(values, values.length);
  }

}
