/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.statements;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.EntityMetadata;
import com.github.hiskrtapps.apocalypse.dao.impl.entitymetadata.EntityMetadataImpl;
import com.github.hiskrtapps.apocalypse.dao.TestCompleteMockEntity;
import com.github.hiskrtapps.apocalypse.dao.TestNoSequenceMockEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityMetadataPersistTest {

  private static final String GET_WITHOUT_SEQUENCE_EXPECTED =
      "insert into LCP_TESTMOCKENTITY (ID, REQUEST_ID, TYPE) values (:id, :requestId, :type)";

  private EntityMetadata<TestCompleteMockEntity> completeEntityMetadata;

  private EntityMetadata<TestNoSequenceMockEntity> noSequenceEntityMetadata;

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    completeEntityMetadata = new EntityMetadataImpl<>(TestCompleteMockEntity.class);
    noSequenceEntityMetadata = new EntityMetadataImpl<>(TestNoSequenceMockEntity.class);
  }

  /**
   * Test method for
   */
  @Test
  public void testCommandWithoutSequence() {
    String response = new EntityMetadataPersist<TestNoSequenceMockEntity>(noSequenceEntityMetadata, Arrays.asList(new TestNoSequenceMockEntity())).command();
    Assert.assertEquals(GET_WITHOUT_SEQUENCE_EXPECTED, response);
  }

  /**
   * Test method for
   */
  @Test
  public void testNameWithSequence() {
    EntityMetadataPersist<TestCompleteMockEntity> entityMetadataPersist =
        new EntityMetadataPersist<TestCompleteMockEntity>(completeEntityMetadata, new ArrayList<>());
    String response = entityMetadataPersist.name();
    Assert.assertEquals(EntityMetadataPersist.class.getSimpleName() + "#" + TestCompleteMockEntity.class.getSimpleName(), response);
  }

  /**
   * Test method for
   */
  @Test
  public void testNameWithNoSequence() {
    EntityMetadataPersist<TestNoSequenceMockEntity> entityMetadataPersist =
        new EntityMetadataPersist<TestNoSequenceMockEntity>(noSequenceEntityMetadata, new ArrayList<>());
    String response = entityMetadataPersist.name();
    Assert.assertEquals(EntityMetadataPersist.class.getSimpleName() + "#" + TestNoSequenceMockEntity.class.getSimpleName(), response);
  }

}
