/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.statements;

import java.util.Map;

import io.github.hiskrtapps.apocalypse.dao.api.Entity;

/**
 * Common class for generic find statement
 *
 *
 * @param <E> entity returned by the find
 */
public interface Find<E extends Entity> extends Statement {

  /**
   * return entityClass Find statement will be executed for
   * 
   * @return entity class of the returned by the find
   */
  Class<E> entityClass();

  /**
   * return the map of parameters. Each parameter has a name and a value
   *
   * @return Map of parameters
   */
  Map<String, Object> valuesMap();

}