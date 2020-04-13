/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

import com.ceppi.apocalypse.dao.impl.entitymetadata.EntityMetadata;
import com.ceppi.apocalypse.dao.impl.entitymetadata.EntityMetadataImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityMetadataImplTest {

  private static final String PK_STRING_CONTRAINT =
      "@javax.persistence.UniqueConstraint(name=LCP_IDX_PK_TESTMOCKENTITY, columnNames=[ID])";

  /**
   * The field gives where EntityMetadata is stored
   */
  private EntityMetadata<TestCompleteMockEntity> completeEntityMetadata;

  private EntityMetadata<TestNotCompleteMetadataMockEntity> notCompleteMetadataEntity;

  private EntityMetadata<TestNoSequenceMockEntity> noSequenceEntityMetadata;

  @Before
  public void setUp() throws Exception {
    completeEntityMetadata = new EntityMetadataImpl<>(TestCompleteMockEntity.class);
  }

  @Test
  public void testEntityMetadata_withEntityClass_Constructor() {
    EntityMetadata<TestCompleteMockEntity> entityMetadataConstructor =
        new EntityMetadataImpl<>(TestCompleteMockEntity.class);
    Assert.assertEquals(TestCompleteMockEntity.class, entityMetadataConstructor.getEntityClass());
  }

  @Test
  public void testGetEntityClass() {
    Assert.assertEquals(TestCompleteMockEntity.class, completeEntityMetadata.getEntityClass());
  }

  @Test
  public void testGetTableName() {
    String response = completeEntityMetadata.getTableName();
    Assert.assertEquals("LCP_TESTMOCKENTITY", response);
  }

  @Test
  public void testGetPrimaryKey() {
    UniqueConstraint primaryKey = completeEntityMetadata.getPrimaryKey();
    Assert.assertEquals(PK_STRING_CONTRAINT, primaryKey.toString());
  }

  @Test
  public void testGetUniqueKeyByColumnNames() {
    String response = completeEntityMetadata.getFieldByColumnName(TestCompleteMockEntity.COLS.ID).getName();
    Assert.assertEquals("id", response);
  }

  @Test
  public void testGetColumnByEmptyStringName() {
    Column response = completeEntityMetadata.getColumnByName("");
    Assert.assertNull(response);
  }

  @Test
  public void testGetColumnByNotPresentName() {
    Column response = completeEntityMetadata.getColumnByName("not present");
    Assert.assertNull(response);
  }

  @Test
  public void testGetColumnNames() {
    Iterable<String> response = completeEntityMetadata.getColumnNames();

    HashMap<String, Column> expect = new HashMap<>();
    expect.put("ID", null);
    expect.put("REQUEST_ID", null);
    expect.put("TYPE", null);
    expect.put("MODE", null);

    Assert.assertEquals(expect.keySet(), response);
  }

  @Test
  public void testGetFieldsNameByColumnName() {
    String response = completeEntityMetadata.getFieldByColumnName(TestCompleteMockEntity.COLS.ID).getName();
    Assert.assertEquals("id", response);
  }

  @Test
  public void testUniqueKeyByColumnNames() {
    UniqueConstraint uniqueConstraint =
        completeEntityMetadata.getUniqueKeyByColumnNames(TestCompleteMockEntity.COLS.ID);
    Assert.assertEquals(PK_STRING_CONTRAINT, uniqueConstraint.toString());
  }

  @Test
  public void testGetSequence() {
    String response = completeEntityMetadata.getSequenceName();
    Assert.assertEquals("LCP_TESTMOCKENTITY_S", response);
  }

  @Test
  public void testCreateMetadataWithNotCompleteInformation() {
    notCompleteMetadataEntity = new EntityMetadataImpl<>(TestNotCompleteMetadataMockEntity.class);
    Assert.assertNull(notCompleteMetadataEntity.getPrimaryKey());
    Assert.assertNull(notCompleteMetadataEntity.getSequenceName());
  }

  @Test
  public void testCreateMetadataWithNoSequenceInformation() {
    noSequenceEntityMetadata = new EntityMetadataImpl<>(TestNoSequenceMockEntity.class);
    Assert.assertNull(noSequenceEntityMetadata.getSequenceName());

  }

}
