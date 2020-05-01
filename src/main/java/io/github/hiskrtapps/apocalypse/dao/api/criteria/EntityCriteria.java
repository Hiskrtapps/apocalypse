/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.criteria;

public class EntityCriteria<E> {

  private Class<E> entityClass;

  private Criteria criteria;

  public EntityCriteria(final Criteria criteria, final Class<E> entityClass) {
    this.entityClass = entityClass;
    this.criteria = criteria;
  }

  public Class<E> entityClass() {
    return entityClass;
  }

  public Criteria criteria() {
    return criteria;
  }

}
