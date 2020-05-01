/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.paging;

import io.github.hiskrtapps.apocalypse.dao.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Page represent a single page containing entities
 *
 * @param <E> entity type contained in the page
 */
public final class Page<E extends Entity> implements Iterable<E> {

  /**
   * total entities without considering paging
   */
  private final Long total;

  /**
   * offset in the whole dataset
   */
  private final Long offset;

  /**
   * Maximum elements to retrieve
   */
  private final Integer limit;

  /**
   * entities contained in this page
   */
  private final List<E> entities = new ArrayList<>();

  /**
   * Construct a new Page
   *
   * @param offset to start paging from
   * @param limit of the page
   * @param total entities without considering paging
   * @param entities contained in this page
   */
  public Page(final Long total, final Long offset, final Integer limit, final List<E> entities) {
    this.total = total;
    this.offset = offset;
    this.limit = limit;
    this.entities.addAll(entities);
  }

  /**
   * @return total entities without considering paging; a null can be return in
   *         case of page overflow
   */
  public final Long total() {
    return total;
  }

  /**
   * @return offset in the whole dataset
   */
  public final Long offset() {
    return offset;
  }

  public final Integer limit() {
    return limit;
  }

  @Override
  public Iterator<E> iterator() {
    return entities.iterator();
  }

  /**
   * Returns an unmodifiable List of entities
   * @return Entities
   */
  public List<E> content() {
    return Collections.unmodifiableList(entities);
  }
}
