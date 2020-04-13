/*
 * © 2020 Ceppi Productions
 */
package com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.SequenceGenerator;

import com.github.hiskrtapps.apocalypse.dao.Entity;

/**
 * Registry of all Entity fields and sequences This class is a standard-java
 * singleton
 */
public class EntityDataRegistry {

  /**
   * singleton instance
   */
  private static EntityDataRegistry INSTANCE;

  /**
   * it contains the map EntityClass -> EntityMetadata
   */
  private final Map<Class<? extends Entity>, EntityMetadata> entityMetadataRegistry = new HashMap<>();

  /**
   * it contains the map sequenceName -> Sequence
   */
  private final Map<String, SequenceGenerator> sequenceRegistry = new HashMap<>();

  /**
   * this is a singleton
   */
  private EntityDataRegistry() {
  }

  /**
   * It return the instance of the singleton
   *
   * @return
   */
  public static EntityDataRegistry instance() {
    if (INSTANCE == null) {
      INSTANCE = new EntityDataRegistry();
    }
    return INSTANCE;
  }

  /**
   * for a given entity it register a new couple sequenceName ->
   * SequenceGenerator. The new value override any already existing one
   *
   * @param sequenceName
   * @param sequence
   */
  public void register(final String sequenceName, SequenceGenerator sequence) {
    sequenceRegistry.put(sequenceName, sequence);
  }

  /**
   * for a given class it register a new couple entityClass -> entityMetadata.
   * The new value override any already existing one
   *
   * @param entityClass
   * @param entityMetadata
   * @param <E>
   */
  public <E extends Entity> void register(Class<E> entityClass, EntityMetadata<E> entityMetadata) {
    entityMetadataRegistry.put(entityClass, entityMetadata);
  }

  /**
   * for a given entity and a given column identified by the name, a field is
   * returned
   *
   * @param entityClass
   * @param columnName
   * @return
   */
  public Field field(final Class<? extends Entity> entityClass, final String columnName) {
    EntityMetadata entityMetadata = entityMetadataRegistry.get(entityClass);
    if (entityMetadata == null) {
      return null;
    } else {
      return entityMetadata.getField(columnName);
    }
  }

  /**
   * for a given entity and a given column identified by the name, a sequence is
   * returned
   *
   * @param sequenceName
   * @return
   */
  public SequenceGenerator sequence(final String sequenceName) {
    return sequenceRegistry.get(sequenceName);
  }

  /**
   * for a given entity and a given column identified by the name, a sequence is
   * returned
   *
   * @param sequenceName
   * @return
   */
  public EntityMetadata metadata(final Class<? extends Entity> entityClass) {
    return entityMetadataRegistry.get(entityClass);
  }
}