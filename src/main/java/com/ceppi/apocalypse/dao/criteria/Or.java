/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import static java.util.Arrays.asList;
import static java.lang.String.format;
import static java.util.Arrays.copyOf;
import static org.apache.commons.lang3.StringUtils.repeat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@link Or} criteria’s important characteristics are:
 *
 * It connects two or more criteria and returns true if either criteria is true
 * or if both criteria are true. Any number of criteria can be connected with
 * {@link Or}s. OR will retrieve rows that match any criteria or all the
 * criteria. {@link Or} is commutative (independent of order).
 *
 * If less than 2 criteria are given to compose the {@link Or} criteria; the
 * {@link Falsity} Identity Element is used at the place of the 1 or 2 missing
 * criteria.
 *
 */
public final class Or extends CriteriaImpl {

  /**
   * numebr of mandatory parameters
   */
  public static final int MANDATORY_PARAMETERS = 2;

  /**
   * base template for 2 criteria
   */
  private static final String TEMPLATE = "(%s) or (%s)";

  /**
   * add-on template to be added for each extra criteria
   */
  private static final String ADD_ON_TEMPLATE = " or (%s)";

  /**
   * Identity Element for {@link Or}
   */
  private static final Criteria IDENTITY = Falsity.INSTANCE;

  /**
   * Criteria to be put in OR
   */
  private final List<Criteria> criteria = new ArrayList<>();

  /**
   * parameter bindings
   */
  private final Map<String, Object>[] bindings;

  /**
   * Constructor for {@link Or}.
   *
   * @param name of the criteria
   * @param criteria list of criteria to be joined. If less than 2 criteria are
   *          given to compose the {@link Or} criteria; the {@link Falsity}
   *          Identity Element is used at the place of the 1 or 2 missing
   *          criteria.
   */
  public Or(String name, List<Criteria> criteria) {
    super(name);
    if (criteria.isEmpty()) {
      criteria.add(IDENTITY);
    }
    if (criteria.size() == 1) {
      criteria.add(IDENTITY);
    }
    this.criteria.addAll(criteria);
    this.bindings = new LinkedHashMap[this.criteria.get(0).bindings().length];
    bind();
  }

  /**
   * Constructor for {@link Or}.
   *
   * @param criteria list of criteria to be joined. If less than 2 criteria are
   *          given to compose the {@link Or} criteria; the {@link Falsity}
   *          Identity Element is used at the place of the 1 or 2 missing
   *          criteria.
   */
  public Or(List<Criteria> criteria) {
    this(Or.class.getSimpleName(), criteria);
  }

  /**
   * Constructor for {@link Or}.
   *
   * @param criteria array of criteria to be joined. If less than 2 criteria are
   *          given to compose the {@link Or} criteria; the {@link Falsity}
   *          Identity Element is used at the place of the 1 or 2 missing
   *          criteria.
   */
  public Or(Criteria... criteria) {
    this(Or.class.getSimpleName(), criteria);
  }

  /**
   * Constructor for {@link Or}.
   *
   * @param name of the criteria
   * @param criteria array of criteria to be joined. If less than 2 criteria are
   *          given to compose the {@link Or} criteria; the {@link Falsity}
   *          Identity Element is used at the place of the 1 or 2 missing
   *          criteria.
   */
  public Or(String name, Criteria... criteria) {
    this(name, asList(criteria));
  }

  /**
   * @return the formatted criteria
   */
  @Override
  public String toString() {
    return format(TEMPLATE + repeat(ADD_ON_TEMPLATE, criteria.size() - MANDATORY_PARAMETERS), criteria.toArray());
  }

  @Override
  public Map<String, Object>[] bindings() {
    return copyOf(bindings, bindings.length);
  }

  /**
   * bind parameters for the criteria. This bindings are taken from the list of
   * criteria that constitute this {@link Or} instance
   */
  private void bind() {
    for (int i = 0; i < criteria.get(0).bindings().length; i++) {
      bindings[i] = new LinkedHashMap();
    }
    for (int i = 0; i < bindings.length; i++) {
      for (final Criteria current : this.criteria) {
        bindings[i].putAll(current.bindings().length == 0 ? new LinkedHashMap() : current.bindings()[i]);
      }
    }
  }
}