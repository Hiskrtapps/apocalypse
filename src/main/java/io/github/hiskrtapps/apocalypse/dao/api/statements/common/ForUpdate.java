/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.statements.common;

import io.github.hiskrtapps.apocalypse.dao.api.Entity;
import io.github.hiskrtapps.apocalypse.dao.api.statements.Find;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static java.lang.String.format;

/**
 * Decorator for {@link Find}.
 *
 * 'for update' close is appended to the given {@link Find} command
 * (this solution is coupled with Oracle DBMS for which the 'for update' statement makes sense.)
 *
 */
public class ForUpdate<E extends Entity> implements Find<E> {

  /**
   * name pattern of the query
   */
  private static final String NAME = "%s#%s";

  /**
   * command pattern of the query
   */
  private static final String COMMAND = "%s for update%s";

  /**
   * wait pattern of the query
   */
  private static final String WAIT = " wait %s";

  /**
   * {@link Find) to be decorated
   */
  private Find<E> find;

  /**
   * wait
   */
  private int seconds;

/**
   * A new {@link ForUpdate} is constructed for the given {@link Find}
   *
   * @param find {@link Find} to be decorated with {@link ForUpdate}
   */
  public ForUpdate(Find<E> find) {
    this.find = find;
  }

/**
   * A new {@link ForUpdate} is constructed for the given {@link Find}
   *
 * @param find {@link Find} to be decorated with {@link ForUpdate}
 * @param seconds to release automatically the lock after
   */
  public ForUpdate(final Find<E> find, int seconds) {
    this.find = find;
    this.seconds = seconds;
  }

  @Override
  public Class<E> entityClass() {
    return find.entityClass();
  }

  @Override
  public Map<String, Object> valuesMap() {
    return find.valuesMap();
  }

  @Override
  public String name() {
    return format(NAME, getClass().getSimpleName(), find.name());
  }

  @Override
  public String command() {
    return format(COMMAND, find.command(), seconds > 0 ? format(WAIT, seconds) : StringUtils.EMPTY);
  }

}