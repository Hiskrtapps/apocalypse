/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.criteria;

import static java.util.Arrays.copyOf;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@link Truth} criteria implements the ever-true condition
 *
 */
public final class Truth extends CriteriaImpl {

  /**
   * truth constant instance/
   */
  public static final Truth INSTANCE = new Truth();

  /**
   * test template that is applied
   */
  private static final String TEMPLATE = "1 = 1";

  /**
   * always empty parameter bindings
   */
  private final Map<String, Object>[] bindings = new LinkedHashMap[0];

  /**
   * Creates an instance of {@link Truth}
   */
  private Truth() {
    this(Truth.class.getSimpleName());
  }

  /**
   * Creates an instance of {@link Truth}
   *
   * @param name of the {@Exists Equal} criteria
   */
  private Truth(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return TEMPLATE;
  }

  @Override
  public Map<String, Object>[] bindings() {
    return copyOf(bindings, bindings.length);
  }

}