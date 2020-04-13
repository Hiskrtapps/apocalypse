/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.paging;

import com.ceppi.apocalypse.dao.Dao;
import com.ceppi.apocalypse.dao.Entity;
import com.ceppi.apocalypse.dao.statements.Find;

/**
 * Common class for paginated find statement
 *
 *
 * @param <E> entity returned by the find
 */
public interface PageFind<E extends Entity> extends Find<E> {

  /**
   * offset to be used to move the cursor in page record selection
   *
   * @return the offset parameter to perform the find with
   */
  long offset();

  /**
   * limit to be used to as numeber of records of a page
   *
   * @return the limit parameter to perform the find with
   */
  int limit();

  /**
   * list of {@link Order} fields to be used to sort records before selection
   *
   * @return the orders parameters to perform the find with
   */
  Order[] orders();

  /**
   * Mapper object able to map result coming from datasource to the the java
   * {@link Entity} used in the {@link PageFind} implementation.
   *
   * @return a result mapper object compatible with the used strategy and the
   *         {@link Dao} implementation
   */
  Object resultMapper();

}