/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.exceptions;

import io.github.hiskrtapps.apocalypse.dao.api.TestCompleteMockEntity;
//import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class UniqueConstraintNotFoundExceptionTest {

  private static final String NOT_EXISTENT_CONSTRAINT = "NOT_EXISTENT_COLUMN";

  private static final String OBJECT_TYPE = "Unique Constraint";

  @Before
  public void setUp() throws Exception {
  }

  /**
   * Test method for
   * {@link OneAffectedEntityExpectedException)}.
   */
  @Test
  public void testExceptionInstantiationWithConstraintName() {
    final UniqueConstraintNotFoundException uniqueConstraintNotFoundException =
        new UniqueConstraintNotFoundException(TestCompleteMockEntity.class, NOT_EXISTENT_CONSTRAINT);

    Assert.assertEquals(TestCompleteMockEntity.class, uniqueConstraintNotFoundException.getEntityClass());
    Assert.assertEquals(OBJECT_TYPE, uniqueConstraintNotFoundException.getObjectType());
    Assert.assertEquals(NOT_EXISTENT_CONSTRAINT, uniqueConstraintNotFoundException.getObjectKey());
  }

  /**
   * Test method for
   * {@link OneAffectedEntityExpectedException)}.
   */
  @Test
  public void testExceptionInstantiationWithConstraintNameAndMissingPArt() {
    ColumnNotFoundException missingPart = new ColumnNotFoundException(TestCompleteMockEntity.class, NOT_EXISTENT_CONSTRAINT);
    final UniqueConstraintNotFoundException uniqueConstraintNotFoundException =
        new UniqueConstraintNotFoundException(TestCompleteMockEntity.class, NOT_EXISTENT_CONSTRAINT, missingPart);

    Assert.assertEquals(TestCompleteMockEntity.class, uniqueConstraintNotFoundException.getEntityClass());
    Assert.assertEquals(OBJECT_TYPE, uniqueConstraintNotFoundException.getObjectType());
    Assert.assertEquals(NOT_EXISTENT_CONSTRAINT, uniqueConstraintNotFoundException.getObjectKey());
    Assert.assertEquals(missingPart, uniqueConstraintNotFoundException.getCause());
  }

  /**
   * Test method for
   * {@link OneAffectedEntityExpectedException)}.
   */
  @Test
  public void testExceptionInstantiationWithColumnList() {
    final UniqueConstraintNotFoundException uniqueConstraintNotFoundException =
        new UniqueConstraintNotFoundException(TestCompleteMockEntity.class, TestCompleteMockEntity.COLS.REQUEST_ID,
            TestCompleteMockEntity.COLS.TYPE);

    Assert.assertEquals(TestCompleteMockEntity.class, uniqueConstraintNotFoundException.getEntityClass());
    Assert.assertEquals(OBJECT_TYPE, uniqueConstraintNotFoundException.getObjectType());
    Assert.assertEquals(StringUtils
        .join(new Object[] { TestCompleteMockEntity.COLS.REQUEST_ID, TestCompleteMockEntity.COLS.TYPE }, ", "),
        uniqueConstraintNotFoundException.getObjectKey());
  }

}
