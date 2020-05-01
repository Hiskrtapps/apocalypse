/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.criteria;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Column;

/**
 *
 */
public class GreaterOrEqualTest extends ColumnTest {

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testCreation() {
    Column column = readColumn();
    Object[] values = new Object[] { "value1", "value2" };
    final GreaterOrEqual c = new GreaterOrEqual(column, values);
    Assert.assertTrue(c.toString().startsWith(column.name() + " >= :"));
    Assert.assertEquals(c.bindings().length, values.length);
  }

}
