/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.impl.entitymetadata.statements;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.Column;

import com.ceppi.apocalypse.dao.criteria.And;
import com.ceppi.apocalypse.dao.criteria.Criteria;
import com.ceppi.apocalypse.dao.impl.entitymetadata.EntityMetadata;
import com.ceppi.apocalypse.dao.impl.entitymetadata.EntityMetadataImpl;
import com.ceppi.apocalypse.dao.statements.Statement;
import com.ceppi.apocalypse.dao.TestCompleteMockEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityMetadataFindTest {

  private static final String GET_WITH_NULL_FILTER_COLUMN_EXPECTED =
      "select ID, MODE, REQUEST_ID, TYPE from LCP_TESTMOCKENTITY";

  private static final String GET_WITH_NOT_NULL_FILTER_COLUMN_EXPECTED =
      "select ID, MODE, REQUEST_ID, TYPE from LCP_TESTMOCKENTITY where ID = :id and TYPE = :type and REQUEST_ID = :requestId";

  private EntityMetadata<TestCompleteMockEntity> entityMetadata;

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    entityMetadata = new EntityMetadataImpl<>(TestCompleteMockEntity.class);
  }

  /**
   * Test method for
   * {@link EntityMetadataFind#entityClass()}.
   */
  @Test
  public void testEntityClassWithEntityMetaConstructor() {
    EntityMetadataFind<TestCompleteMockEntity> entityMetadataFind =
        new EntityMetadataFind<TestCompleteMockEntity>(entityMetadata, null);
    Class<TestCompleteMockEntity> entityClass = entityMetadataFind.entityClass();
    Assert.assertEquals(TestCompleteMockEntity.class, entityClass);
  }

  /**
   * Test method for
   * {@link EntityMetadataFind#entityClass()}.
   */
  @Test
  public void testEntityClassWithEntityMetaColumnConstructor() {
    Column[] columns = new Column[3];
    columns[0] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.ID);
    columns[1] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.TYPE);
    columns[2] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.REQUEST_ID);
    EntityMetadataFind<TestCompleteMockEntity> entityMetadataFind =
        new EntityMetadataFind<TestCompleteMockEntity>(entityMetadata, new And(Criteria.convert(columns, new ArrayList<>())));
    Class<TestCompleteMockEntity> entityClass = entityMetadataFind.entityClass();
    Assert.assertEquals(TestCompleteMockEntity.class, entityClass);
  }

  /**
   * Test method for
   */
  @Test
  public void testNameWithEntityMetaConstructor() {
    EntityMetadataFind<TestCompleteMockEntity> entityMetadataFind =
        new EntityMetadataFind<TestCompleteMockEntity>(entityMetadata, null);
    String response = entityMetadataFind.name();
    Assert.assertEquals(EntityMetadataFind.class.getSimpleName() + "#" + TestCompleteMockEntity.class.getSimpleName(), response);
  }

  /**
   * Test method for
   */
  @Test
  public void testNameWithEntityMetaColumnConstructor() {
    Column[] columns = new Column[3];
    columns[0] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.ID);
    columns[1] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.TYPE);
    columns[2] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.REQUEST_ID);

    EntityMetadataFind<TestCompleteMockEntity> entityMetadataFind = new EntityMetadataFind(entityMetadata, new And(Criteria.convert(columns, new ArrayList<>())));
    String response = entityMetadataFind.name();
    Assert.assertEquals(EntityMetadataFind.class.getSimpleName() + "#" + TestCompleteMockEntity.class.getSimpleName(), response);
  }

  /**
   * Test method for
   */
  @Test
  public void testCommandWithNullColumn() {
    Statement entityMetadataFind = new EntityMetadataFind<>(entityMetadata, null);

    String response = entityMetadataFind.command();
    Assert.assertEquals(GET_WITH_NULL_FILTER_COLUMN_EXPECTED, response);
  }

  /**
   * Test method for
   */
  @Test
  public void testActivateCache() {
    Statement entityMetadataFind = new EntityMetadataFind<TestCompleteMockEntity>(entityMetadata, null);

    String response1 = entityMetadataFind.command();
    String response2 = entityMetadataFind.command();
    // TODO: cache has been disabled. Reactivate the check below when the new implementation will be ready
    // Assert.assertTrue(response1 == response2);
  }

  /**
   * Test method for
   */
  @Test
  public void testValuesMap() {
    EntityMetadataFind<TestCompleteMockEntity> entityMetadataFind = new EntityMetadataFind<>(entityMetadata, null);
    Map<String, Object> response = entityMetadataFind.valuesMap();

    Assert.assertEquals(0, response.keySet().size());
  }

}
