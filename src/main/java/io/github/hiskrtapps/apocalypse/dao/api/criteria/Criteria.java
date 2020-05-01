/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import io.github.hiskrtapps.apocalypse.dao.Entity;

/**
 * Interface for generic criteria to be used to filter data during persistence
 * operations
 *
 */
public interface Criteria {

  /**
   * @return the name of the criteria
   */
  String name();

  /**
   * @return parameter bindings of the criteria. This data structure should be
   *         able to contain bindings for batch operations (that's why it is an
   *         array of maps)
   */
  Map<String, Object>[] bindings();

  /**
   * utility method to transform an array of columns in a list of criteria. The
   * logic is: - if the parameter is an array an {@link In} criteria is created
   * for the column - otherwise an {@link Equal} criteria is created for the
   * column
   *
   * @param filterColumns list of columns that to filter by
   * @param arrayOfValues list of values of the columns to filter by
   * @return the list of criteria
   */
  static List<Criteria> convert(final Column[] filterColumns, final Object... arrayOfValues) {
    final List<Criteria> criteria = new ArrayList<>();
    for (int i = 0; i < filterColumns.length; i++) {
      if (arrayOfValues[i] instanceof List) {
        criteria.add(new In(filterColumns[i], (List)arrayOfValues[i]));
      } else {
        criteria.add(new Equal(filterColumns[i], arrayOfValues[i]));
      }
    }
    return criteria;
  }

  /**
   * utility method to transform an array of columns in a list of criteria. The
   * logic is: - all the values are taken by the given list of entities. An
   * {@link Equal} criteria is created for the column
   *
   * @param filterColumns list of columns that to filter by
   * @param entities list of entities to get value to filter by
   * @return the list of {@link Criteria}
   */
  static List<Criteria> convert(final Column[] filterColumns, final List<Entity> entities) {
    final List<Criteria> criteria = new ArrayList<>();
    if (!entities.isEmpty()) {
      for (int i = 0; i < filterColumns.length; i++) {
        Object[] arrayOfValues = new Object[entities.size()];
        for (int j = 0; j < entities.size(); j++) {
          arrayOfValues[j] = entities.get(j).get(filterColumns[i].name());
        }
        criteria.add(new Equal(filterColumns[i], arrayOfValues));
      }
    }
    return criteria;
  }

}