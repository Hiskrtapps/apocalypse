/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.paging;

import com.github.hiskrtapps.apocalypse.dao.Entity;

/**
 * Page result is the object mapping the result of a paged find statement
 * 
 * <pre>
 * A page result have two different informations:
 * 1) the total of the entities without considering paging
 * 2) the entity mapping a single row
 * </pre>
 *
 *
 * @param <E>
 */
public final class PagedResult<E extends Entity> {

  /**
   * this is the first row
   */
  private final boolean first;

  /**
   * total entities without considering paging
   */
  private final Long total;

  /**
   * entity mapping the row
   */
  private final E entity;
  
  
  /**
   * Construct a new PagedResult
   * 
   * @param first is true if this is the first row
   * @param total entities without considering paging
   * @param entity mapping the row
   */
  public PagedResult(final boolean first, final Long total, final E entity) {
    super();
    this.first = first;
    this.total = total;
    this.entity = entity;
  }

  /**
   * @return total entities without considering paging
   */
  public final Long total() {
    return total;
  }

  /**
   * @return entity mapping the row
   */
  public final E entity() {
    return entity;
  }

  /**
   * @return true if this is the first row
   */
  public final boolean first() {
    return first;
  }

}
