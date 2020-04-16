/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.EntityMetadata;
import org.apache.commons.lang3.StringUtils;

import io.github.hiskrtapps.apocalypse.dao.Entity;
import io.github.hiskrtapps.apocalypse.dao.criteria.Criteria;
import io.github.hiskrtapps.apocalypse.dao.statements.Statement;

/**
 * Abstract EntityMetadata statement extended by all formatters related to
 * EntityMetadata implementation.
 * 
 * Also this class provided some utility methods used by implementations
 *
 *
 * @param <E> Entity Statement is related to
 */
public abstract class EntityMetadataStatement<E extends Entity> implements Statement {

  /**
   * name of the statement, should be unique
   */
  private String name;

  /**
   * statement parameters
   */
  private Map<String, Object>[] valueMaps;

  /**
   * Entity Metadata on which the context is based upon
   */
  private final EntityMetadata<E> entityMetadata;

  /**
   * criteria to execute the statement with
   */
  private final Criteria criteria;

  /**
   * Constructor for formatters that need filter columns
   *
   * @param name of the statement
   * @param entityMetadata on which the is based upon
   * @param criteria columns to be used as a filter
   */
  protected EntityMetadataStatement(final String name, final EntityMetadata<E> entityMetadata, final Criteria criteria) {
    this.name = name;
    this.entityMetadata = entityMetadata;
    this.valueMaps =
        criteria == null || criteria.bindings().length == 0 ? new Map[] { new HashMap() } : criteria.bindings();
    this.criteria = criteria;
  }

  /**
   * @param name of the statement
   * @param entityMetadata on which the is based upon
   * @param criteria to apply
   * @param entities to be take data from
   */
  protected EntityMetadataStatement(final String name, final EntityMetadata<E> entityMetadata, final Criteria criteria,
      final List<E> entities) {
    this.name = name;
    this.entityMetadata = entityMetadata;
    this.valueMaps =
        criteria == null || criteria.bindings().length == 0 ? new Map[] { new HashMap() } : criteria.bindings();
    valueMapFromEntities(entities);
    this.criteria = criteria;
  }

  /**
   * @param name of the statement
   * @param entityMetadata on which the is based upon
   * @param entities to be take data from
   */
  protected EntityMetadataStatement(final String name, final EntityMetadata<E> entityMetadata, final List<E> entities) {
    this.name = name;
    this.entityMetadata = entityMetadata;
    this.valueMaps = new HashMap[entities.size()];
    valueMapFromEntities(entities);
    this.criteria = null;
  }

  @Override
  public final String name() {
    return name;
  }

  protected final EntityMetadata<E> entityMetadata() {
    return entityMetadata;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.ceppi.apocalypse.templates.Statement#valuesMap()
   */
  protected Map<String, Object>[] valuesMaps() {
    return valueMaps;
  }

  /**
   * create an array of maps columnNames -> values starting from the given list
   * of entities
   *
   * @param entities
   * @return
   */
  private void valueMapFromEntities(List<E> entities) {
    int entityIndex = 0;
    for (E entity : entities) {
      for (String columnName : entityMetadata.getColumnNames()) {
        if (valueMaps[entityIndex] == null) {
          valueMaps[entityIndex] = new HashMap();
        }
        valueMaps[entityIndex].put(entityMetadata.getFieldByColumnName(columnName).getName(), entity.get(columnName));
      }
      entityIndex++;
    }
  }

  /**
   * Generate a where clause for passed convert. This is an utility method
   * callable by extending classes
   *
   * @return the where clause
   */
  protected final String whereClause() {
    if (criteria != null) {
      return " where " + criteria;
    } else {
      return StringUtils.EMPTY;
    }
  }
}