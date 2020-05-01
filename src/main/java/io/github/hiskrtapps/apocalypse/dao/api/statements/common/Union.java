/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.statements.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.github.hiskrtapps.apocalypse.dao.api.Entity;
import io.github.hiskrtapps.apocalypse.dao.api.statements.Find;
import io.github.hiskrtapps.apocalypse.dao.api.statements.Statement;

/**
 * Find composed of several finds of the same entity and join them by an UNION or UNION ALL
 */
public class Union<E extends Entity> implements Find<E> {

  private static final String COMMAND_UNION_ALL = "%s union all (%s)";

  private static final String COMMAND_UNION = "%s union (%s)";

  private Find<E> firstFind;

  private List<Find<E>> otherFinds;

  private boolean unionAll;

  public Union(boolean unionAll, Find<E> firstFind, Find<E> ... otherFind) {
    this.unionAll = unionAll;
    this.firstFind = firstFind;
    this.otherFinds = Arrays.asList(otherFind);
  }

  @Override
  public Class<E> entityClass() {
    return firstFind.entityClass();
  }

  @Override
  public Map<String, Object> valuesMap() {
    Map<String, Object> allValuesMap = new HashMap<>();
    allValuesMap.putAll(firstFind.valuesMap());
    for (Find<E> otherFind : otherFinds) {
      allValuesMap.putAll(otherFind.valuesMap());
    }
    return allValuesMap;
  }

  @Override
  public String name() {
    return firstFind.name() + otherFinds.stream().map(Statement::name).collect(Collectors.joining("|"));
  }

  @Override
  public String command() {
    final String unionCommand = unionAll ? COMMAND_UNION_ALL : COMMAND_UNION;
    String command = String.format("(" + firstFind.command() + ")");
    for (Find<E> otherFind : otherFinds) {
      command = String.format(unionCommand, command, otherFind.command());
    }
    return command;
  }
}
