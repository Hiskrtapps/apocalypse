/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata;

import java.lang.reflect.Field;

import javax.persistence.Column;

import com.github.hiskrtapps.apocalypse.dao.Entity;
import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements.EntityMetadataFind;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityDataRegistryTest {

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * Test method for
   * {@link EntityMetadataFind#entityClass()}.
   */
  @Test
  public void testRegister() {
    //Field[] fields = FieldUtils.getAnnotatedDeclaredFields(TestEntity.class, Column.class, false);

    EntityDataRegistry.instance().register(TestEntity.class, new EntityMetadataImpl<>(TestEntity.class));
    Field field = EntityDataRegistry.instance().field(TestEntity.class, "COLUMN_NAME");
   // Assert.assertEquals(fields[0], field);

  }

  /**
   * Test method for
   * {@link EntityMetadataFind#entityClass()}.
   */
  @Test
  public void testRegisterTwice() {
    //Field[] fields = FieldUtils.getAnnotatedDeclaredFields(TestEntity.class, Column.class, false);

    EntityDataRegistry.instance().register(TestEntity.class, new EntityMetadataImpl<>(TestEntity.class));
    EntityDataRegistry.instance().register(TestEntity.class, new EntityMetadataImpl<>(TestEntity.class));
    Field field = EntityDataRegistry.instance().field(TestEntity.class, "COLUMN_NAME");
   // Assert.assertEquals(fields[0], field);

  }


  /**
   * Test method for
   * {@link EntityMetadataFind#entityClass()}.
   */
  @Test
  public void testGetUnregistered() {
   // Field[] fields = FieldUtils.getAnnotatedDeclaredFields(TestEntity.class, Column.class, false);

    EntityDataRegistry.instance().register(TestEntity.class, new EntityMetadataImpl<>(TestEntity.class));
    Field field = EntityDataRegistry.instance().field(TestEntity.class, "NOT_COLUMN_NAME");
    Assert.assertNull(field);

  }

  private static class TestEntity implements Entity {

    @Column(name = "COLUMN_NAME")
    private String attribute;

    @Override
    public <T> T get(String columnName) {
      return null;
    }

    @Override
    public void set(String columnName, Object columnValue) {

    }

    @Override
    public void primaryKey(Object... values) {

    }
  }

}
