/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.exceptions;

import io.github.hiskrtapps.apocalypse.dao.TestCompleteMockEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class PrimaryKeyNotFoundExceptionTest {

  private static final String OBJECT_TYPE = "Unique Constraint";
  
  private static final String OBJECT_KEY = "Primary Key";

  @Before
  public void setUp() throws Exception {
  }

  /**
   * Test method for
   * {@link OneAffectedEntityExpectedException)}.
   */
  @Test
  public void testExceptionInstantiationWithConstraintName() {
    final PrimaryKeyNotFoundException primaryKeyNotFoundException =
        new PrimaryKeyNotFoundException(TestCompleteMockEntity.class);

    Assert.assertEquals(TestCompleteMockEntity.class, primaryKeyNotFoundException.getEntityClass());
    Assert.assertEquals(OBJECT_TYPE, primaryKeyNotFoundException.getObjectType());
    Assert.assertEquals(OBJECT_KEY, primaryKeyNotFoundException.getObjectKey());
  }

}
