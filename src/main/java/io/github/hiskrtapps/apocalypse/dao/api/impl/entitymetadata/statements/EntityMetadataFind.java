/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.impl.entitymetadata.statements;

import java.util.Map;

import io.github.hiskrtapps.apocalypse.dao.api.impl.entitymetadata.EntityMetadata;
import org.apache.commons.lang3.StringUtils;

import io.github.hiskrtapps.apocalypse.dao.api.Entity;
import io.github.hiskrtapps.apocalypse.dao.api.criteria.Criteria;
import io.github.hiskrtapps.apocalypse.dao.api.statements.Find;

/**
 * Class to format a find statement.
 * 
 * By convention parameters in the statements are formatted in the
 * ":parameterName" form.
 *
 */
public final class EntityMetadataFind<E extends Entity> extends EntityMetadataStatement<E> implements Find<E> {

  /**
   * statement template
   */
  private static final String TEMPLATE = "select %s from %s%s";

  /**
   * value separator
   */
  private static final String COLUMN_SEP = ", ";

  /**
   * Constructor that need filter columns
   * 
   * @param entityMetadata on which the context is based upon
   * @param criteria to be used as a filter
   */
  public EntityMetadataFind(EntityMetadata<E> entityMetadata, Criteria criteria) {
    super(EntityMetadataFind.class.getSimpleName() + "#" + entityMetadata.getEntityClass().getSimpleName(),
        entityMetadata, criteria);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.ceppi.apocalypse.templates.FindFormatter#format(com.
   * ceppi.apocalypse.EntityMetadata, javax.persistence.Column[])
   */
  @Override
  public final String command() {

    /**
     * format statement
     */
    return String.format(TEMPLATE, StringUtils.join(entityMetadata().getColumnNames(), COLUMN_SEP), entityMetadata()
        .getTableName(), whereClause());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.ceppi.apocalypse.templates.Find#entityClass()
   */
  @Override
  public final Class<E> entityClass() {
    return entityMetadata().getEntityClass();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.ceppi.apocalypse.templates.Statement#valuesMap()
   */
  public Map<String, Object> valuesMap() {
    return super.valuesMaps()[0];
  }

}