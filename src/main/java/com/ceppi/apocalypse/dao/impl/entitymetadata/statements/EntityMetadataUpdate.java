/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.impl.entitymetadata.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ceppi.apocalypse.dao.impl.entitymetadata.EntityMetadata;
import org.apache.commons.lang3.StringUtils;

import com.ceppi.apocalypse.dao.Entity;
import com.ceppi.apocalypse.dao.criteria.Criteria;
import com.ceppi.apocalypse.dao.statements.Modification;

/**
 * Class to format an update statement.
 * 
 * By convention parameters in the statements are formatted in the
 * ":parameterName" form.
 *
 *
 */
public final class EntityMetadataUpdate<E extends Entity> extends EntityMetadataStatement<E> implements Modification {

  /**
   * statement template
   */
  private static final String TEMPLATE = "update %s set %s%s";

  /**
   * value separator
   */
  private static final String COLUMN_SEP = ", ";

  /*
   * entities to be updated;
   */
  private final List<E> entities = new ArrayList();

  /**
   * Constructor that need filter columns
   * 
   * @param entityMetadata on which the context is based upon
   * @param criteria to be used as a filter
   * @param entities to take data from
   */
  public EntityMetadataUpdate(final EntityMetadata<E> entityMetadata, final Criteria criteria,
                              final List<E> entities) {
    super(EntityMetadataUpdate.class.getSimpleName() + "#" + entityMetadata.getEntityClass().getSimpleName(),
        entityMetadata, criteria, entities);
    this.entities.addAll(entities);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.ceppi.apocalypse.templates.FindFormatter#format(com.
   * ceppi.apocalypse.EntityMetadata, javax.persistence.Column[])
   */
  @Override
  public final String command() {

    if (!entities.isEmpty()) {
      /**
       * format statement
       */
      return String.format(TEMPLATE, entityMetadata().getTableName(), setClause(), whereClause());

    } else {
      return null;
    }
  }

  /**
   * Generate a set clause for passed context
   * 
   * @return
   */
  private final String setClause() {
    /**
     * compose modifications list by cycling on column names
     */
    final List<String> modifications = new ArrayList<>();
    for (String columnName : entityMetadata().getColumnNames()) {
      modifications.add(columnName + " = " + ":" + entityMetadata().getFieldByColumnName(columnName).getName());
    }
    return StringUtils.join(modifications, COLUMN_SEP);
  }

  @Override
  public Map<String, Object>[] valuesMaps() {
    return super.valuesMaps();
  }

}