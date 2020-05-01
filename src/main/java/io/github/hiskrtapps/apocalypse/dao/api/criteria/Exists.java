/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.criteria;

import static java.lang.String.format;
import static java.util.Arrays.copyOf;

import java.util.LinkedHashMap;
import java.util.Map;

import io.github.hiskrtapps.apocalypse.dao.api.statements.Find;

/**
 * The {@link Exists} criteria implements the condition: "exists (selection)"
 * It is used to test for the existence of any record in a sub-selection
 * It returns true if the sub-selection returns one or more records.
 *
 */
public final class Exists extends CriteriaImpl {

  /**
   * test template that is applied
   */
  private static final String TEMPLATE = "exists (%s)";

  /**
   * sub-selection that is executed
   */
  private final Find find;

  /**
   * parameter bindings
   */
  private final Map<String, Object>[] bindings;

  /**
   * Creates an instance of {@link Exists}
   *
   * @param name of the {@link Equal} criteria
   * @param find the sub-selection to be executed and tested
   */
  public Exists(String name, Find find) {
    super(name);
    this.find = find;
    this.bindings = new LinkedHashMap[] { new LinkedHashMap(find.valuesMap()) };
  }

  /**
   * Creates an instance of {@link Exists}
   *
   * @param find the sub-selection to be executed and tested
   */
  public Exists(Find find) {
    this(Exists.class.getSimpleName(), find);
  }

  @Override
  public String toString() {
    return format(TEMPLATE, find.command());
  }

  @Override
  public Map<String, Object>[] bindings() {
    return copyOf(bindings, bindings.length);
  }

}