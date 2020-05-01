/*
 * © 2020 Ceppi Productions
 */
package io.github.hiskrtapps.apocalypse.dao.api.impl.entitymetadata;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.SequenceGenerator;

import io.github.hiskrtapps.apocalypse.dao.api.Entity;

/**
 * Registry of all Entity fields and sequences This class is a standard-java
 * singleton
 */
public final class EntityDataRegistry {

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
   * @return the registry instance
   */
  public final static EntityDataRegistry instance() {
    if (INSTANCE == null) {
      INSTANCE = new EntityDataRegistry();
    }
    return INSTANCE;
  }

  /**
   * for a given entity it register a new couple sequenceName -&gt;
   * SequenceGenerator. The new value override any already existing one
   *
   * @param sequenceName to register the sequence by
   * @param sequence to register
   */
  public final void register(final String sequenceName, SequenceGenerator sequence) {
    sequenceRegistry.put(sequenceName, sequence);
  }

  /**
   * for a given class it register a new couple entityClass -&gt; entityMetadata.
   * The new value override any already existing one
   *
   * @param entityClass to register the entity metadata by
   * @param entityMetadata to register
   * @param <E> entity
   */
  public final <E extends Entity> void register(Class<E> entityClass, EntityMetadata<E> entityMetadata) {
    entityMetadataRegistry.put(entityClass, entityMetadata);
  }

  /**
   * for a given entity and a given column identified by the name, a field is
   * returned
   *
   * @param entityClass to get the field by
   * @param columnName to get the field by
   * @return the field
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
   * for a given sequence name, a sequence is returned
   *
   * @param sequenceName to get the sequence by
   * @return the sequence
   */
  public SequenceGenerator sequence(final String sequenceName) {
    return sequenceRegistry.get(sequenceName);
  }

  /**
   * for a given entity class, a sequence is returned
   *
   * @param entityClass to get the entity metadata by
   * @return the entity metadata
   */
  public EntityMetadata metadata(final Class<? extends Entity> entityClass) {
    return entityMetadataRegistry.get(entityClass);
  }
}