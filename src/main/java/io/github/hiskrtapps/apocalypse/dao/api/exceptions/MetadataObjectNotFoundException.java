/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.exceptions;

import javax.persistence.PersistenceException;

import io.github.hiskrtapps.apocalypse.dao.api.Entity;

/**
 * Exception raised when a Metadata object is not found using specified
 * parameters
 *
 */
public abstract class MetadataObjectNotFoundException extends PersistenceException {

  /**
   * serial
   */
  private static final long serialVersionUID = -1599452764425874811L;

  /**
   * Entity class object not found is related
   */
  private final Class<? extends Entity> entityClass;

  /**
   * type of the missing object
   */
  private final String objectType;

  /**
   * key of the missing object (e.g. name of the object, list of components the
   * object is composed by, object key)
   */
  private final String objectKey;

  /**
   * constructor
   * 
   * @param entityClass missing object belong to
   * @param objectType type of the missing object
   * @param objectKey key of the missing object
   */
  public MetadataObjectNotFoundException(final Class<? extends Entity> entityClass, final String objectType,
      final String objectKey) {
    this(entityClass, objectType, objectKey, null);
  }

  /**
   * constructor
   * 
   * @param entityClass missing object belong to
   * @param objectType type of the missing object
   * @param objectKey key of the missing object
   * @param missingPart of the metadata
   */
  public MetadataObjectNotFoundException(final Class<? extends Entity> entityClass, final String objectType,
      final String objectKey, final MetadataObjectNotFoundException missingPart) {
    super("Entity: " + entityClass + "; " + objectType + " > " + objectKey + " not found.", missingPart);
    this.entityClass = entityClass;
    this.objectType = objectType;
    this.objectKey = objectKey;
  }

  /**
   * Gets the value of entityClass
   * 
   * @return the entityClass field of
   *         {@link MetadataObjectNotFoundException#entityClass}
   */
  public final Class<? extends Entity> getEntityClass() {
    return entityClass;
  }

  /**
   * Gets the value of objectType
   * 
   * @return the objectType field of
   *         {@link MetadataObjectNotFoundException#objectType}
   */
  public final String getObjectType() {
    return objectType;
  }

  /**
   * Gets the value of objectKey
   * 
   * @return the objectKey field of
   *         {@link MetadataObjectNotFoundException#objectKey}
   */
  public final String getObjectKey() {
    return objectKey;
  }


}