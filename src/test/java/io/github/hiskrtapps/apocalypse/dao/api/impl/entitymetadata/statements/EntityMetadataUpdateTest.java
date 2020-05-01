/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.Column;

import io.github.hiskrtapps.apocalypse.dao.criteria.And;
import io.github.hiskrtapps.apocalypse.dao.criteria.Criteria;
import io.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.EntityMetadata;
import io.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.EntityMetadataImpl;
import io.github.hiskrtapps.apocalypse.dao.statements.Statement;
import io.github.hiskrtapps.apocalypse.dao.TestCompleteMockEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class EntityMetadataUpdateTest {

  private static final String GET_WITH_NULL_FILTER_COLUMN_EXPECTED =
      "update LCP_TESTMOCKENTITY set ID = :id, MODE = :mode, REQUEST_ID = :requestId, TYPE = :type";

  private static final String GET_WITH_NOT_NULL_FILTER_COLUMN_EXPECTED =
      "update LCP_TESTMOCKENTITY set ID = :id, MODE = :mode, REQUEST_ID = :requestId, TYPE = :type where ID = :id and TYPE = :type and REQUEST_ID = :requestId";

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
   */
  @Test
  public void testCommandWithNullFilterColumn() {
    Statement entityMetadataUpdate = new EntityMetadataUpdate<TestCompleteMockEntity>(entityMetadata, null, Arrays.asList(new TestCompleteMockEntity()));

    String response = entityMetadataUpdate.command();
    Assert.assertEquals(GET_WITH_NULL_FILTER_COLUMN_EXPECTED, response);
  }

  /**
   * Test method for
   */
  @Test
  public void testCommandTwiceToActivateCache() {
    Statement entityMetadataUpdate = new EntityMetadataUpdate<TestCompleteMockEntity>(entityMetadata, null, new ArrayList<>());

    String response1 = entityMetadataUpdate.command();
    String response2 = entityMetadataUpdate.command();
    // TODO: cache has been disabled. Reactivate the check below when the new implementation will be ready
    // Assert.assertTrue(response1 == response2);
  }

  /**
   * Test method for
   */
  @Test
  public void testNameWithNullFilterColumn() {
    EntityMetadataUpdate<TestCompleteMockEntity> entityMetadataUpdate = new EntityMetadataUpdate(entityMetadata, null, new ArrayList<>());
    String response = entityMetadataUpdate.name();

    Assert.assertEquals(EntityMetadataUpdate.class.getSimpleName() + "#" + TestCompleteMockEntity.class.getSimpleName(), response);
  }

  /**
   * Test method for
   */
  @Test
  public void testNameWithNotNullFilterColumn() {
    Column[] columns = new Column[3];
    columns[0] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.ID);
    columns[1] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.TYPE);
    columns[2] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.REQUEST_ID);
    EntityMetadataUpdate<TestCompleteMockEntity> entityMetadataUpdate =
        new EntityMetadataUpdate(entityMetadata, new And(Criteria.convert(columns, new ArrayList())), new ArrayList());
    String response = entityMetadataUpdate.name();

    Assert.assertEquals(EntityMetadataUpdate.class.getSimpleName() + "#" + TestCompleteMockEntity.class.getSimpleName(), response);
  }

}
