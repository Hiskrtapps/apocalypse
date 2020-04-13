/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements;

import com.github.hiskrtapps.apocalypse.dao.criteria.And;
import com.github.hiskrtapps.apocalypse.dao.criteria.Criteria;
import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.EntityMetadata;
import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.EntityMetadataImpl;
import com.github.hiskrtapps.apocalypse.dao.statements.Statement;
import com.github.hiskrtapps.apocalypse.dao.TestCompleteMockEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class EntityMetadataDeleteTest {

  private static final String DELETE_WITH_NULL_FILTER_EXPECTED = "delete from LCP_TESTMOCKENTITY";

  private static final String DELETE_WITH_NOT_NULL_FILTER_EXPECTED =
      "delete from LCP_TESTMOCKENTITY where ID = :id and TYPE = :type and REQUEST_ID = :requestId";

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
    Statement entityMetadataDelete = new EntityMetadataDelete<>(entityMetadata, null);

    String response = entityMetadataDelete.command();
    Assert.assertEquals(DELETE_WITH_NULL_FILTER_EXPECTED, response);
  }

  /**
   * Test method for
   */
  @Test
  public void testNameWithNullFilterColumn() {
    EntityMetadataDelete entityMetadataDelete = new EntityMetadataDelete(entityMetadata, null);
    String response = entityMetadataDelete.name();

    Assert.assertEquals(
        EntityMetadataDelete.class.getSimpleName() + "#" + TestCompleteMockEntity.class.getSimpleName(), response);
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
    EntityMetadataDelete entityMetadataDelete =
        new EntityMetadataDelete(entityMetadata, new And(Criteria.convert(columns, new ArrayList<>())));
    String response = entityMetadataDelete.name();

    Assert.assertEquals(
        EntityMetadataDelete.class.getSimpleName() + "#" + TestCompleteMockEntity.class.getSimpleName(), response);
  }

  /**
   * Test method for
   */
  @Test
  public void testValuesMapNullFilterColumn() {
    EntityMetadataDelete<TestCompleteMockEntity> entityMetadataDelete = new EntityMetadataDelete(entityMetadata, null);
    Assert.assertEquals(1, entityMetadataDelete.valuesMaps().length);
  }

  /**
   * Test method for
   */
  @Test
  public void testValuesMapNotNullFilterColumn() {
    Column[] columns = new Column[3];
    columns[0] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.ID);
    columns[1] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.TYPE);
    columns[2] = entityMetadata.getColumnByName(TestCompleteMockEntity.COLS.REQUEST_ID);
    EntityMetadataDelete<TestCompleteMockEntity> entityMetadataDelete =
        new EntityMetadataDelete(entityMetadata, new And(Criteria.convert(columns,
            Arrays.asList(new TestCompleteMockEntity(), new TestCompleteMockEntity(), new TestCompleteMockEntity()))));
    Map<String, Object> response = entityMetadataDelete.valuesMaps()[0];
    Assert.assertEquals(3, response.keySet().size());
  }
}
