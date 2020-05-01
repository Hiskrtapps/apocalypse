/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.impl.entitymetadata.statements;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.hiskrtapps.apocalypse.dao.api.Entity;
import io.github.hiskrtapps.apocalypse.dao.api.impl.entitymetadata.EntityMetadata;
import io.github.hiskrtapps.apocalypse.dao.api.statements.Modification;

/**
 * Class to format a persist statement.
 *
 * By convention parameters in the statements are formatted in the
 * ":parameterName" form.
 *
 *
 */
public final class EntityMetadataPersist<E extends Entity> extends EntityMetadataStatement<E> implements Modification {

  /**
   * statement template
   */
  private static final String TEMPLATE = "insert into %s (%s) values (%s)";

  /**
   * column separator
   */
  private static final String COLUMN_SEP = ", ";

  /**
   * value separator
   */
  private static final String VALUE_SEP = ", ";

  /**
   * field indicator compatible with NamedParameterSpringJdbcTemplate
   */
  private static final String FIELD_INDICATOR = ":";

  /*
   * entities to be updated
   */
  private final List<E> entities = new ArrayList();

  /**
   * Constructor that do not need filter columns
   *
   * @param entityMetadata on which the context is based upon
   * @param entities to get data from
   */
  public EntityMetadataPersist(EntityMetadata<E> entityMetadata, final List<E> entities) {
    super(EntityMetadataPersist.class.getSimpleName() + "#" + entityMetadata.getEntityClass().getSimpleName(),
        entityMetadata, entities);
    this.entities.addAll(entities);
  }

  /**
   * Creates a formatted SQL statement. When valuesMaps array only contains one
   * record, the resulting insert script will use the sequence.nextval for the
   * primary column, otherwise the primary column key is expected to be supplied
   * later with a pre-generated sequence value
   *
   *
   * @return the command
   */
  @Override
  public final String command() {

    if (!entities.isEmpty()) {

      /**
       * first time statement is requested for this entity metadata
       */

      /**
       * compose value list by cycling on column names
       */
      final List<String> namedParameters = new ArrayList<>();
      for (final String columnName : entityMetadata().getColumnNames()) {

        /**
         * field map
         */
        namedParameters.add(FIELD_INDICATOR + entityMetadata().getFieldByColumnName(columnName).getName());

      }

      /**
       * format statement
       */
      return String.format(TEMPLATE, entityMetadata().getTableName(),
          join(entityMetadata().getColumnNames(), COLUMN_SEP), join(namedParameters, VALUE_SEP));

    } else {
      return null;
    }
  }

  @Override
  public Map<String, Object>[] valuesMaps() {
    return super.valuesMaps();
  }

}