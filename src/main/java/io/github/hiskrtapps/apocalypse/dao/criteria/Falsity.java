/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.criteria;

import static java.util.Arrays.copyOf;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@link Falsity} criteria implements the ever-false condition
 *
 */
public final class Falsity extends CriteriaImpl {

  /**
   * falsity constant instance/
   */
  public static final Falsity INSTANCE = new Falsity();

  /**
   * test template that is applied
   */
  private static final String TEMPLATE = "0 = 1";

  /**
   * always empty parameter bindings
   */
  private final Map<String, Object>[] bindings = new LinkedHashMap[0];

  /**
   * Creates an instance of {@link Falsity}
   */
  private Falsity() {
    this(Falsity.class.getSimpleName());
  }

  /**
   * Creates an instance of {@link Falsity}
   *
   * @param name of the {@Exists Equal} criteria
   */
  private Falsity(String name) {
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