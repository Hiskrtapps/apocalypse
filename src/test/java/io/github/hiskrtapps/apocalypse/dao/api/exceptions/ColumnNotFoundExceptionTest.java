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
public class ColumnNotFoundExceptionTest {

  private static final String NOT_EXISTENT_COLUMN = "NOT_EXISTENT_COLUMN";

  private static final String OBJECT_TYPE = "Column";

  @Before
  public void setUp() throws Exception {
  }

  /**
   * Test method for
   * {@link ColumnNotFoundException)}.
   */
  @Test
  public void testExceptionInstantiation() {
    final ColumnNotFoundException columnNotFoundException =
        new ColumnNotFoundException(TestCompleteMockEntity.class, NOT_EXISTENT_COLUMN);

    Assert.assertEquals(TestCompleteMockEntity.class, columnNotFoundException.getEntityClass());
    Assert.assertEquals(OBJECT_TYPE, columnNotFoundException.getObjectType());
    Assert.assertEquals(NOT_EXISTENT_COLUMN, columnNotFoundException.getObjectKey());
  }

}
