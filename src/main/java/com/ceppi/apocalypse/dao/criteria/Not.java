/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.repeat;

import java.util.Map;

/**
 * The {@link Not} criteria’s important characteristics are:
 *
 * It negates (reverses) a single criteria.
 *
 * NOT acts on one criteria. To negate two or more criteria, repeat the NOT for each one.
 * In comparisons, using NOT often is a matter of style. The following two clauses are equivalent:
 * - WHERE NOT state = 'CA'
 * - WHERE state <> 'CA'
 *
 */
public final class Not extends CriteriaImpl {

  /**
   * base template for 2 criteria
   */
  private static final String TEMPLATE = "not (%s)";

  /**
   * criteria to be negated
   */
  private final Criteria criteria;

  /**
   * parameter bindings
   */
  private final Map<String, Object>[] bindings;

  /**
   * Constructor for {@link Not}.
   *
   * @param name of the criteria
   * @param criteria to be negated
   */
  public Not(String name, Criteria criteria) {
    super(name);
    this.criteria = criteria;
    this.bindings = criteria.bindings();
  }

  /**
   * Constructor for {@link Not}.
   *
   * @param criteria to be negated
   */
  public Not(Criteria criteria) {
    this(Not.class.getSimpleName(), criteria);
  }

  @Override
  public String toString() {
    return format(TEMPLATE, criteria);
  }

  @Override
  public Map<String, Object>[] bindings() {
    return bindings;
  }

}