/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements;

import java.util.Map;

import com.github.hiskrtapps.apocalypse.dao.Entity;
import com.github.hiskrtapps.apocalypse.dao.criteria.Criteria;
import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.EntityMetadata;
import com.github.hiskrtapps.apocalypse.dao.statements.Modification;

/**
 * Class to format a delete statement.
 *
 * By convention parameters in the statements are formatted in the
 * ":parameterName" form.
 */
public class EntityMetadataDelete<E extends Entity> extends EntityMetadataStatement<E> implements Modification {

  /**
   * statement template
   */
  private static final String TEMPLATE = "delete from %s%s";

  /**
   * Constructor that need filter columns
   *
   * @param entityMetadata on which the context is based upon
   * @param criteria to be used as a filter
   */
  public EntityMetadataDelete(final EntityMetadata<E> entityMetadata, final Criteria criteria) {
    super(EntityMetadataDelete.class.getSimpleName() + "#" + entityMetadata.getEntityClass().getSimpleName(),
        entityMetadata, criteria);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.ceppi.apocalypse.templates.DeleteFormatte#format(com.
   * ceppi.apocalypse.EntityMetadata, javax.persistence.Column[])
   */
  @Override
  public final String command() {

    /**
     * format statement
     */
    return String.format(TEMPLATE, entityMetadata().getTableName(), whereClause());
  }

  @Override
  public Map<String, Object>[] valuesMaps() {
    return super.valuesMaps();
  }

}
