/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.exceptions;

import com.github.hiskrtapps.apocalypse.dao.TestCompleteMockEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class OneAffectedEntityExpectedExceptionTest {

  private static final int ID_VALUE = 23;

  private static final int AFFECTED = 11;

  @Before
  public void setUp() throws Exception {
  }

  /**
   * Test method for
   * {@link OneAffectedEntityExpectedException)}.
   */
  @Test
  public void testExceptionInstantiation() {
    final Object[] values = new Object[] { ID_VALUE };
    final OneAffectedEntityExpectedException oneAffectedEntityExpectedException =
        new OneAffectedEntityExpectedException(AFFECTED, TestCompleteMockEntity.CHKS.LCP_IDX_PK_TESTMOCKENTITY, values);

    Assert.assertEquals(AFFECTED, oneAffectedEntityExpectedException.getAffected());
    Assert.assertEquals(TestCompleteMockEntity.CHKS.LCP_IDX_PK_TESTMOCKENTITY, oneAffectedEntityExpectedException.getUniqueConstraintName());
    Assert.assertArrayEquals(values, oneAffectedEntityExpectedException.getValues());
  }

}
