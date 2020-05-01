/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

import io.github.hiskrtapps.apocalypse.dao.api.criteria.Criteria;
import io.github.hiskrtapps.apocalypse.dao.api.exceptions.ColumnNotFoundException;
import io.github.hiskrtapps.apocalypse.dao.api.exceptions.OneAffectedEntityExpectedException;
import io.github.hiskrtapps.apocalypse.dao.api.exceptions.PrimaryKeyNotFoundException;
import io.github.hiskrtapps.apocalypse.dao.api.exceptions.UniqueConstraintNotFoundException;
import io.github.hiskrtapps.apocalypse.dao.api.paging.Order;
import io.github.hiskrtapps.apocalypse.dao.api.paging.Page;
import io.github.hiskrtapps.apocalypse.dao.api.paging.PageFind;
import io.github.hiskrtapps.apocalypse.dao.api.statements.Find;
import io.github.hiskrtapps.apocalypse.dao.api.statements.Modification;

/**
 * Interface for generic Data Access Object operations for specific type all
 * entities selections are expected to be with equal operator: No LIKE, BETWEEN
 * or other operators are supposed to be used when selecting by any
 * UniqueConstraint or by any Column
 * <p>
 * Intentionally Dao interface has not any dependency from any third-party
 * library; that's because it is expected to be as much as general and portable
 * as possible
 *
 * @param <E> entity handled by generic dao
 */
public interface Dao<E extends Entity> {

  /**
   * Saves a given entity.
   *
   * @param entity to persist
   * @return true if entity has been persisted and the information is returned
   *         by the underlying system, false otherwise
   */
  boolean persistOne(E entity);

  /**
   * Saves all given entities.
   *
   * @param entities to persist
   * @return the number of persisted entities if the information is returned by
   *         the underlying system, a negative number otherwise
   */
  int persist(@SuppressWarnings("unchecked") E... entities);

  /**
   * Saves all given entities.
   *
   * @param entities to persist
   * @return the number of persisted entities if the information is returned by
   *         the underlying system, a negative number otherwise
   */
  int persist(List<E> entities);

  /**
   * Update given entity. All fields are updated by primary key.
   *
   * @param entity to persist
   * @return true if entity has been updated and the information is returned by
   *         the underlying system, false otherwise
   * @throws PrimaryKeyNotFoundException if primary key is not found
   * @throws OneAffectedEntityExpectedException if more entities are affected
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           updated
   */
  boolean updateOne(E entity);

  /**
   * Update given entity. All fields are updated by unique constraint provided.
   *
   * @param uniqueConstraint to persist by
   * @param entity to persist
   * @return true if entity has been updated and the information is returned by
   *         the underlying system, false otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           updated
   */
  boolean updateOne(UniqueConstraint uniqueConstraint, E entity);

  /**
   * Update given entity. All fields are updated by criteria provided.
   *
   * @param criteria to apply
   * @param entity to persist
   * @return true if entity has been updated and the information is returned by
   *         the underlying system, false otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           updated
   */
  boolean updateOne(Criteria criteria, E entity);

  /**
   * Update given entities. All fields are updated by unique constraint provided
   *
   * @param uniqueConstraint to persist by
   * @param entities to persist
   * @return the number of updated entities if the information is returned by
   *         the underlying system, a negative number otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   */
  int update(UniqueConstraint uniqueConstraint, @SuppressWarnings("unchecked") E... entities);

  /**
   * Update given entities. All fields are updated by unique constraint provided
   *
   * @param uniqueConstraint to persist by
   * @param entities to persist
   * @return the number of updated entities if the information is returned by
   *         the underlying system, a negative number otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   */
  int update(UniqueConstraint uniqueConstraint, List<E> entities);

  /**
   * Update given entities. All fields are updated by criteria provided
   *
   * @param criteria to apply
   * @param entities to persist
   * @return the number of updated entities if the information is returned by
   *         the underlying system, a negative number otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   */
  int update(Criteria criteria, List<E> entities);

  /**
   * Update given entities. All fields are updated by criteria provided
   *
   * @param criteria to apply
   * @param entities to persist
   * @return the number of updated entities if the information is returned by
   *         the underlying system, a negative number otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   */
  int update(Criteria criteria, E... entities);

  /**
   * Update given entities. All fields are updated by unique constraint provided
   *
   * @param entities to persist
   * @return the number of updated entities if the information is returned by
   *         the underlying system, a negative number otherwise
   * @throws PrimaryKeyNotFoundException if primary key is not found
   */
  int update(@SuppressWarnings("unchecked") E... entities);

  /**
   * Update given entities. All fields are updated by unique constraint provided
   *
   * @param entities to persist
   * @return the number of updated entities if the information is returned by
   *         the underlying system, a negative number otherwise
   * @throws PrimaryKeyNotFoundException if primary key is not found
   */
  int update(List<E> entities);

  /**
   * Delete entity by primary key
   *
   * @param values of primary key fields
   * @return true if entity has been deleted and the information is returned by
   *         the underlying system, false otherwise
   * @throws PrimaryKeyNotFoundException if primary key is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           deleted
   */
  boolean deleteOne(Object... values);

  /**
   * Delete entity by unique constraint provided
   *
   * @param uniqueConstraint to persist by
   * @param values of unique constraint fields
   * @return true if entity has been deleted and the information is returned by
   *         the underlying system, false otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           deleted
   */
  boolean deleteOne(UniqueConstraint uniqueConstraint, Object... values);

  /**
   * Delete entity by unique constraint composed by provided columns
   *
   * @param columns that constitute an unique constraint
   * @param values of unique constraint fields
   * @return true if entity has been deleted and the information is returned by
   *         the underlying system, false otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           deleted
   */
  boolean deleteOne(Column[] columns, Object... values);

  /**
   * Delete entity by unique constraint composed by provided column names
   *
   * @param columnNames that constitute an unique constraint
   * @param values of unique constraint fields
   * @return true if entity has been deleted and the information is returned by
   *         the underlying system, false otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           deleted
   */
  boolean deleteOne(String[] columnNames, Object... values);

  /**
   * Delete entity by unique constraint composed by provided criteria
   *
   * @param criteria that constitute an unique constraint
   * @return true if entity has been deleted and the information is returned by
   *         the underlying system, false otherwise
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           deleted
   */
  boolean deleteOne(Criteria criteria);

  /**
   * Delete entities by provided columns
   *
   * @param columns to filter for
   * @param values of provided column fields
   * @return the number of deleted entities if the information is returned by
   *         the underlying system, a negative number otherwise
   */
  int delete(Column[] columns, Object... values);

  /**
   * Delete entities by provided column names
   *
   * @param columnNames to filter for
   * @param values of provided column fields
   * @return the number of deleted entities if the information is returned by
   *         the underlying system, a negative number otherwise
   */
  int delete(String[] columnNames, Object... values);

  /**
   * Delete entities by provided criteria
   *
   * @param criteria to filter for
   * @return the number of deleted entities if the information is returned by
   *         the underlying system, a negative number otherwise
   */
  int delete(Criteria criteria);

  /**
   * Delete all entities
   *
   * @return the number of deleted entities if the information is returned by
   *         the underlying system, a negative number otherwise
   */
  int delete();

  /**
   * Find entity by primary key
   *
   * @param values of primary key fields
   * @return found entity, null if not found
   * @throws PrimaryKeyNotFoundException if primary key is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is found
   */
  E findOne(Object... values);

  /**
   * Find entity by unique constraint
   *
   * @param uniqueConstraint to persist by
   * @param values of unique constraint fields
   * @return found entity, null if not found
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is found
   */
  E findOne(UniqueConstraint uniqueConstraint, Object... values);

  /**
   * Find entity by unique constraint
   *
   * @param columns that constitute an unique constraint
   * @param values of unique constraint fields
   * @return found entity, null if not found
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is found
   */
  E findOne(Column[] columns, Object... values);

  /**
   * Find entity by unique constraint criteria
   *
   * @param criteria that constitute an unique constraint
   * @return found entity, null if not found
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is found
   */
  E findOne(Criteria criteria);

  /**
   * Find entity by unique constraint
   *
   * @param columnNames names that constitute an unique constraint
   * @param values of unique constraint fields
   * @return found entity, null if not found
   * @throws UniqueConstraintNotFoundException if unique constraint is not found
   * @throws OneAffectedEntityExpectedException if more than one entity is found
   */
  E findOne(String[] columnNames, Object... values);

  /**
   * Find entities by given columns as filter
   *
   * @param columns to filter for
   * @param values of provided column fields
   * @return list of found entities, empty list if not found
   * @throws ColumnNotFoundException if column is not found
   */
  List<E> find(Column[] columns, Object... values);

  /**
   * Find entities by given column names as filter
   *
   * @param columnNames names to filter for
   * @param values of provided column fields
   * @return list of found entities, empty list if not found
   * @throws ColumnNotFoundException if column is not found
   */
  List<E> find(String[] columnNames, Object... values);

  /**
   * Find entities by given convert as filter
   *
   * @param criteria to filter for
   * @return list of found entities, empty list if not found
   * @throws ColumnNotFoundException if column is not found
   */
  List<E> find(Criteria criteria);

  /**
   * Find all entities without filters
   *
   * @return list of found entities, empty list if not found
   */
  List<E> find();

  /**
   * Find entities performing strategy defined by the Find object provided
   *
   * @param find strategy provided
   * @return list of found entities, empty list if not found
   */
  List<E> find(Find<E> find);

  /**
   * Find entities performing strategy defined by the Find object provided
   *
   * @param find strategy provided
   * @return found entity, null if not found
   * @throws OneAffectedEntityExpectedException if more than one entity is found
   */
  E findOne(Find<E> find);

  /**
   * Performs strategy defined by the Statement object provided
   *
   * @param modification strategy provided
   * @return for each set of parameters, the number of modified entities if the
   *         information is returned by the underlying system, a negative number
   *         otherwise
   */
  int[] modify(Modification modification);

  /**
   * Performs strategy defined by the Statement object provided
   *
   * @param modification strategy provided
   * @return for each set of parameters, true if entity has been modified and
   *         the information is returned by the underlying system, false
   *         otherwise
   * @throws OneAffectedEntityExpectedException if more than one entity is
   *           modified
   */
  boolean[] modifyOne(Modification modification);

  /**
   * Find a page of entities performing strategy defined by the Criteria object
   * and the pagination parameters provided
   *
   * @param criteria to filter for
   * @param pageOffset offset to apply to the search
   * @param pageLimit limit for the returned amount of elements
   * @param orders items stating the order of the returned elements
   * @return list of found entities, empty list if not found
   * @throws ColumnNotFoundException if column is not found
   */
  Page<E> find(Criteria criteria, long pageOffset, int pageLimit, Order... orders);

  /**
   * Find a page of entities performing strategy defined by the Find object
   * and the pagination parameters provided
   *
   * @param find strategy provided
   * @param pageOffset Offset in the whole dataset
   * @param pageLimit number of elements to retrieve
   * @param orders list of order fields: needed to calculate pagination. One is
   *          mandatory
   * @return page for found entity, page object is always returned even if no
   *         entities are found
   */
  Page<E> find(Find<E> find, long pageOffset, int pageLimit, Order... orders);

  /**
   * Find a page of entities performing strategy defined by the Find object
   * and the pagination parameters provided
   *
   * @param find strategy provided
   * @param pageOffset Offset in the whole dataset
   * @param pageLimit number of elements to retrieve
   * @param orders list of order fields: needed to calculate pagination. One is
   *          mandatory
   * @return page for found entity, page object is always returned even if no
   *         entities are found
   */
  Page<E> find(Find<E> find, long pageOffset, int pageLimit, List<Order> orders);

  /**
   * Find entities performing strategy defined by the PageFind object provided
   *
   * @param pageFind find statement that return paged result
   * @return list of found entities, empty list if not found
   * @throws ColumnNotFoundException if column is not found
   */
  Page<E> find(PageFind<E> pageFind);

}