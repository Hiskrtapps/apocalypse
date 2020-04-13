/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata;

import static java.util.stream.IntStream.of;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements.EntityMetadataDelete;
import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements.EntityMetadataFind;
import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements.EntityMetadataPersist;
import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements.EntityMetadataUpdate;
import com.github.hiskrtapps.apocalypse.dao.Dao;
import com.github.hiskrtapps.apocalypse.dao.Entity;
import com.github.hiskrtapps.apocalypse.dao.Sao;
import com.github.hiskrtapps.apocalypse.dao.criteria.Criteria;
import com.github.hiskrtapps.apocalypse.dao.exceptions.ColumnNotFoundException;
import com.github.hiskrtapps.apocalypse.dao.exceptions.OneAffectedEntityExpectedException;
import com.github.hiskrtapps.apocalypse.dao.exceptions.PrimaryKeyNotFoundException;
import com.github.hiskrtapps.apocalypse.dao.exceptions.UniqueConstraintNotFoundException;
import com.github.hiskrtapps.apocalypse.dao.impl.DaoImpl;
import com.github.hiskrtapps.apocalypse.dao.paging.Order;
import com.github.hiskrtapps.apocalypse.dao.paging.Page;
import com.github.hiskrtapps.apocalypse.dao.statements.Find;
import com.github.hiskrtapps.apocalypse.dao.statements.Modification;

/**
 * EntityMetadataDaoImpl implementation one purpose:
 * <p>
 * <p>
 * 
 * <pre>
 * 1) handling EntityMetadata information so that subclasses can ignore it
 *    and just create formatted statements
 * </pre>
 * <p>
 * For each CRUD operation the proper Statement is created and invoked to
 * generate statement on-the-fly starting from EntityMetadata and parameters.
 * <p>
 * Intentionally EntityMetadataDaoImpl has not any dependency from any
 * third-party library; that's because it is expected to be as much as general
 * and portable as possible.
 *
 * @param <E> entity handled by generic dao
 */
public abstract class EntityMetadataDaoImpl<E extends Entity> extends DaoImpl<E> {

  /**
   * EntityMetadata related to handled Entity
   */
  protected EntityMetadata<E> entityMetadata;

  /**
   * Sequence Access Object
   */
  @Inject
  private Sao sao;

  /**
   * return enclosing Dao instance
   */
  protected abstract Dao<E> self();

  /**
   * Sets the {@link EntityMetadataDaoImpl#entityMetadata} property.
   * <p>
   * entityMetadata is different for each dao depending by entity class. That's
   * why setter is needed
   *
   * @param entityMetadata the {@link EntityMetadataDaoImpl#entityMetadata}
   *          instance representing object.
   */
  public final void setEntityMetadata(final EntityMetadata<E> entityMetadata) {
    this.entityMetadata = entityMetadata;
  }

  /**
   * Sets the {@link EntityMetadataDaoImpl#sao} property.
   *
   * @param sao the {@link EntityMetadataDaoImpl#sao} instance representing
   *          object.
   */
  public void setSao(Sao sao) {
    this.sao = sao;
  }

  /**
   * @return the field #sao
   **/
  public Sao getSao() {
    return sao;
  }

  @Override
  public final int persist(final List<E> entities) {
    sequenceGeneratedValues(entities);
    return of(self().modify(new EntityMetadataPersist<E>(entityMetadata, entities))).sum();
  }

  @Override
  public final int update(final Criteria criteria, final List<E> entities) {
    return of(self().modify(new EntityMetadataUpdate<E>(entityMetadata, criteria, entities))).sum();
  }

  @Override
  public final int delete(final Criteria criteria) {
    return self().modify(new EntityMetadataDelete<E>(entityMetadata, criteria))[0];
  }

  @Override
  public final List<E> find(final Criteria criteria) {
    return self().find(new EntityMetadataFind<E>(entityMetadata, criteria));
  }

  @Override
  public final Page<E> find(final Criteria criteria, final long pageOffset, final int pageLimit, final Order... orders) {
    return find(new EntityMetadataFind<E>(entityMetadata, criteria), pageOffset, pageLimit, orders);
  }

  @Override
  public final E findOne(final Find<E> find) {
    final List<E> found = self().find(find);
    if (found.size() > 1) {
      throw new OneAffectedEntityExpectedException(found.size(), find.getClass().toString(),
          find.valuesMap().values().toArray());
    } else {
      return found.isEmpty() ? null : found.get(0);
    }
  }

  @Override
  public final boolean[] modifyOne(final Modification modification) {
    final int[] modified = self().modify(modification);
    final boolean[] result = new boolean[modified.length];
    for (int i = 0; i < modified.length; i++) {
      if (modified[i] > 1) {
        throw new OneAffectedEntityExpectedException(i, modification.getClass().toString(),
            modification.valuesMaps()[i].values().toArray());
      } else {
        result[i] = modified[i] == 1;
      }
    }
    return result;
  }

  @Override
  public final Page<E> find(final Find<E> find, final long pageOffset, final int pageLimit, final List<Order> orders) {
    return find(find, pageOffset, pageLimit, orders.toArray(new Order[orders.size()]));
  }

  @Override
  protected final UniqueConstraint primaryKey() {
    final UniqueConstraint uniqueConstraint = entityMetadata.getPrimaryKey();
    if (uniqueConstraint != null) {
      return uniqueConstraint;
    } else {
      throw new PrimaryKeyNotFoundException(entityMetadata.getEntityClass());
    }
  }

  @Override
  protected final UniqueConstraint uniqueConstraint(final Column[] columns) {
    Map<String, UniqueConstraint> uniqueConstraints = entityMetadata.getUniqueKeys();

    // The name of all the columns
    final List<String> columnNames = new ArrayList<>();
    for (Column column : columns) {
      columnNames.add(column.name());
    }

    Boolean constraintFound;
    List<String> constraintColumns;
    for (UniqueConstraint uniqueConstraint : uniqueConstraints.values()) {
      constraintFound = true;
      constraintColumns = Arrays.asList(uniqueConstraint.columnNames());
      // Check if all the columns of the unique constraints are provided
      for (String constraintColumnName : constraintColumns) {
        if (!columnNames.contains(constraintColumnName)) {
          constraintFound = false;
          break;
        }
      }

      if (constraintFound) {
        return uniqueConstraint;
      }
    }

    throw new UniqueConstraintNotFoundException(entityMetadata.getEntityClass(),
        columnNames.toArray(new String[columnNames.size()]));
  }

  @Override
  protected final Column[] columns(final UniqueConstraint uniqueConstraint) {
    try {
      return columns(check(uniqueConstraint).columnNames());
    } catch (ColumnNotFoundException e) {
      throw new UniqueConstraintNotFoundException(entityMetadata.getEntityClass(), uniqueConstraint.name(), e);
    }
  }

  @Override
  protected final Column[] columns(final String... columnNames) {
    final Column[] columns = new Column[columnNames.length];
    for (int i = 0; i < columnNames.length; i++) {
      final Column column = entityMetadata.getColumnByName(columnNames[i]);
      if (column == null) {
        throw new ColumnNotFoundException(entityMetadata.getEntityClass(), columnNames[i]);
      }
      columns[i] = column;
    }
    return columns;
  }

  @Override
  protected final Column[] columns(final Column... columns) {
    final String[] columnNames = new String[columns.length];
    for (int i = 0; i < columns.length; i++) {
      columnNames[i] = columns[i].name();
    }
    return columns(columnNames);
  }

  /**
   * @param uniqueConstraint to be checked
   * @return checked unique constraint
   */
  private UniqueConstraint check(final UniqueConstraint uniqueConstraint) {
    if (entityMetadata.getUniqueKeyByColumnNames(uniqueConstraint.columnNames()) != null) {
      return uniqueConstraint;
    } else {
      throw new UniqueConstraintNotFoundException(entityMetadata.getEntityClass(), uniqueConstraint.name());
    }
  }

  /**
   * select sequence keys and set values in passed entities
   *
   * @param entities keys are setted to
   */
  private void sequenceGeneratedValues(final List<E> entities) {
    if (entityMetadata.getSequenceName() != null && !entities.isEmpty()) {
      final Iterable<Integer> sequenceValues = sao.next(entityMetadata.getSequenceName(), entities.size());
      int i = 0;
      for (Integer sequenceValue : sequenceValues) {
        entities.get(i++).primaryKey(sequenceValue);
      }
    }
  }

}