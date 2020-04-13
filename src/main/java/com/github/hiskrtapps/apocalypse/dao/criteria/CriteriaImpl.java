/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.criteria;

/**
 * abstract base class for any {@link Criteria}.
 * it implements the basic methods for name attribute manipulation
 *
 */
public abstract class CriteriaImpl implements Criteria {

  /**
   * name of the criteria
   */
  private final String name;

  /**
   * @param name of the criteria
   */
  protected CriteriaImpl(final String name) {
    this.name = name;
  }

  @Override
  public String name() {
    return name;
  }

}