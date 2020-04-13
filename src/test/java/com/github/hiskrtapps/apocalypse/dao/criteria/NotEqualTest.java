/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.criteria;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Column;

/**
 *
 */
public class NotEqualTest extends ColumnTest {

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
    final NotEqual c = new NotEqual(column, values);
    Assert.assertTrue(c.toString().startsWith(column.name() + " <> :"));
    Assert.assertEquals(c.bindings().length, values.length);
  }

}
