/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.impl;

import static com.ceppi.apocalypse.dao.criteria.Criteria.convert;
import static java.util.stream.IntStream.of;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

import com.ceppi.apocalypse.dao.criteria.And;
import com.ceppi.apocalypse.dao.criteria.Criteria;
import com.ceppi.apocalypse.dao.exceptions.ColumnNotFoundException;
import com.ceppi.apocalypse.dao.exceptions.OneAffectedEntityExpectedException;
import com.ceppi.apocalypse.dao.exceptions.PrimaryKeyNotFoundException;
import com.ceppi.apocalypse.dao.exceptions.UniqueConstraintNotFoundException;
import com.ceppi.apocalypse.dao.Dao;
import com.ceppi.apocalypse.dao.Entity;

/**
 * Dao implementation with two main purposes:
 * 
 * <pre>
 * 1) reducing Dao interface method overload by proper parameter conversion
 * 2) raising proper Exceptions as defined by Dao interface
 * </pre>
 *
 * Intentionally DaoImpl interface has not any dependency from any third-party
 * library; that's because it is expected to be as much as general and portable
 * as possible.
 *
 *
 * @param <E> entity handled by generic dao
 */
public abstract class DaoImpl<E extends Entity> implements Dao<E> {

  @Override
  public final boolean persistOne(final E entity) {
    return persist(Arrays.asList(entity)) == 1;
  }

  @Override
  public final int persist(@SuppressWarnings("unchecked") final E... entities) {
    return persist(Arrays.asList(entities));
  }

  @Override
  public final boolean updateOne(final E entity) {
    return updateOne(primaryKey(), entity);
  }

  @Override
  public final boolean updateOne(final UniqueConstraint uniqueConstraint, final E entity) {
    final int updated = update(uniqueConstraint, Arrays.asList(entity));
    if (updated > 1) {
      throw new OneAffectedEntityExpectedException(updated, uniqueConstraint.name(), entity);
    } else {
      return updated == 1;
    }
  }

  @Override
  public final int update(final UniqueConstraint uniqueConstraint, @SuppressWarnings("unchecked") final E... entities) {
    return update(uniqueConstraint, Arrays.asList(entities));
  }

  @Override
  public final int update(@SuppressWarnings("unchecked") final E... entities) {
    return update(primaryKey(), Arrays.asList(entities));
  }

  @Override
  public final int update(final List<E> entities) {
    return update(primaryKey(), entities);
  }

  @Override
  public final int update(final UniqueConstraint uniqueConstraint, final List<E> entities) {
    return update(new And(uniqueConstraint.name(), Criteria.convert(columns(uniqueConstraint), (List)entities)), entities);
  }

  @Override
  public final int update(final Criteria criteria, E... entities) {
    return update(criteria, Arrays.asList(entities));
  }

  @Override
  public final boolean updateOne(final Criteria criteria, E entity) {
    final int updated = update(criteria, entity);
    if (updated > 1) {
      throw new OneAffectedEntityExpectedException(updated, criteria.name(), entity);
    } else {
      return updated == 1;
    }
  }

  @Override
  public final boolean deleteOne(final Object... values) {
    return deleteOne(primaryKey(), values);
  }

  @Override
  public final boolean deleteOne(final UniqueConstraint uniqueConstraint, final Object... values) {
    return deleteOne(new And(uniqueConstraint.name(), Criteria.convert(columns(uniqueConstraint), values)));
  }

  @Override
  public final boolean deleteOne(final Column[] columns, final Object... values) {
    return deleteOne(uniqueConstraint(columns), values);
  }

  @Override
  public final boolean deleteOne(final String[] columnNames, final Object... values) {
    return deleteOne(columns(columnNames), values);
  }

  @Override
  public final boolean deleteOne(final Criteria criteria) {
    final int deleted = delete(criteria);
    if (deleted > 1) {
      throw new OneAffectedEntityExpectedException(deleted, criteria.name(), criteria.bindings()[0].values());
    } else {
      return deleted == 1;
    }
  }

  @Override
  public final int delete(final String[] columnNames, final Object... values) {
    return delete(columns(columnNames), values);
  }

  @Override
  public final int delete() {
    return delete(new String[0]);
  }

  @Override
  public final int delete(final Column[] columns, final Object... values) {
    return delete(new And(Criteria.convert(columns(columns), values)));
  }

  @Override
  public final E findOne(final Object... values) {
    return findOne(primaryKey(), values);
  }

  @Override
  public final E findOne(final UniqueConstraint uniqueConstraint, final Object... values) {
    return findOne(columns(uniqueConstraint), values);
  }

  @Override
  public final E findOne(final Column[] columns, final Object... values) {
    return findOne(new And(uniqueConstraint(columns).name(), Criteria.convert(columns(columns), values)));
  }

  @Override
  public final E findOne(final String[] columnNames, final Object... values) {
    return findOne(columns(columnNames), values);
  }

  @Override
  public final List<E> find(final Column[] columns, final Object... values) {
    return find(new And(Criteria.convert(columns(columns), values)));
  }

  @Override
  public final E findOne(final Criteria criteria) {
    List<E> found = find(criteria);
    if (found.size() > 1) {
      throw new OneAffectedEntityExpectedException(found.size(), criteria.name(), criteria.bindings()[0].values());
    } else {
      return found.isEmpty() ? null : found.get(0);
    }
  }

  @Override
  public final List<E> find(final String[] columnNames, final Object... values) {
    return find(columns(columnNames), values);
  }

  @Override
  public final List<E> find() {
    return find(new String[0]);
  }

  /**
   * @return Primary Key unique constraint for handled Entity
   * @throws PrimaryKeyNotFoundException if entity does not have a unique
   *           constraint matching passed columns
   */
  protected abstract UniqueConstraint primaryKey();

  /**
   * @param columns that compounds unique key
   * @return UniqueConstraint composed by passed columns
   * @throws UniqueConstraintNotFoundException if a uniqueConstraint listed column
   *           does not exists
   */
  protected abstract UniqueConstraint uniqueConstraint(Column[] columns);

  /**
   * @param uniqueConstraint
   * @return Columns composing given UniqueConstraint
   * @throws UniqueConstraintNotFoundException if a uniqueConstraint listed column
   *           does not exists
   */
  protected abstract Column[] columns(UniqueConstraint uniqueConstraint);

  /**
   * @param columnNames
   * @return Columns related to passed columnNames
   * @throws ColumnNotFoundException if a listed column does not exists
   */
  protected abstract Column[] columns(String... columnNames);

  /**
   * @param columns
   * @return Columns related to passed columnNames
   * @throws ColumnNotFoundException if a listed column does not exists
   */
  protected abstract Column[] columns(Column... columns);

}