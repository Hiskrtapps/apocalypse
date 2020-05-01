/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.criteria;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;

import org.apache.commons.lang3.tuple.Pair;

/**
 * abstract base class for {@link Criteria} that are applied to a single
 * {@link Column}. Often the are used to compare in such way the value of the
 * given {@link Column}. For this reason they are also called Comparison
 * Criteria.
 *
 */
public abstract class ColumnCriteria extends CriteriaImpl {

  /**
   * static variable to discriminate parameters. The variable is incremented and
   * a new value is returned each time a new parameted binding is requested
   */
  private static int DISCRIMINATOR = 0;

  /**
   * template of the {@link ColumnCriteria}. It specify the type of comparison
   * will be applied
   */
  private final String template;

  /**
   * name of the {@link Column} the criteria is applied to
   */
  private final String columnName;

  /**
   * parameter bindings
   */
  private final Map<String, Object>[] bindings;

  /**
   * @param name of the criteria
   * @param template of the {@link ColumnCriteria}. It specify the type of
   *          comparison will be applied
   * @param column the criteria is applied to
   * @param arrayOfValues for parameter bindings. As parameter binding has to
   *          support batch an array is always needed
   */
  protected ColumnCriteria(final String name, final String template, final Column column,
      final Object[]... arrayOfValues) {
    this(name, template, column.name(), arrayOfValues);
  }

  /**
   * @param name of the criteria
   * @param template of the {@link ColumnCriteria}. It specify the type of
   *          comparison will be applied
   * @param columnName of the {@link Column} the criteria is applied to
   * @param arrayOfValues for parameter bindings. As parameter binding has to
   *          support batch an array is always needed
   */
  protected ColumnCriteria(final String name, final String template, final String columnName,
      final Object[]... arrayOfValues) {
    super(name);
    this.template = template;
    this.columnName = columnName;
    this.bindings = new LinkedHashMap[arrayOfValues.length];
    bind(arrayOfValues);
  }

  @Override
  public final String toString() {
    final Set values = new LinkedHashSet();
    values.add(columnName);
    if (bindings.length != 0) {
      values.addAll(bindings[0].keySet());
    }
    return String.format(template, values.toArray(new String[values.size()]));
  }

  /**
   * @return the name of the {@link Column} the criteria is applied to
   */
  public String columnName() {
    return columnName;
  }

  @Override
  public final Map<String, Object>[] bindings() {
    return bindings;
  }

  /**
   * bind parameters for the criteria. Each parameter is unique. DISCRIMINATOR
   * attribute is used to achieve this.
   */
  private void bind(final Object[]... arrayOfValues) {
    if (arrayOfValues.length == 0) {
      return;
    }
    for (int i = 0; i < arrayOfValues.length; i++) {
      bindings[i] = new LinkedHashMap();
    }
    for (int i = 0; i < arrayOfValues[0].length; i++) {
      for (int j = 0; j < bindings.length; j++) {
        bindings[j].put(columnName + DISCRIMINATOR, arrayOfValues[j][i]);
      }
      DISCRIMINATOR++;
    }
  }

  /**
   * create the double array of values starting from an array of {@link Object}
   * needed to support batch
   * 
   * @param values to create the double array
   * @return the double array
   */
  protected final static Object[][] arrayOfValues(Object... values) {
    final Object[][] arrayOfValues = new Object[values.length][];
    for (int i = 0; i < values.length; i++) {
      arrayOfValues[i] = new Object[] { values[i] };
    }
    return arrayOfValues;
  }

  /**
   * create the double array of values starting from an array of {@link Pair}
   * needed to support batch
   *
   * @param values to create the double array
   * @return the double array
   */
  protected final static Object[][] arrayOfValues(Pair... values) {
    final Object[][] arrayOfValues = new Object[values.length][];
    for (int i = 0; i < values.length; i++) {
      arrayOfValues[i] = new Object[] { values[i].getLeft(), values[i].getRight() };
    }
    return arrayOfValues;
  }

  /**
   * create the double array of values starting from an array of {@link List}
   * needed to support batch
   *
   * @param values to create the double array
   * @return the double array
   */
  protected final static Object[][] arrayOfValues(List... values) {
    return arrayOfValues((Object[])values);
  }

}