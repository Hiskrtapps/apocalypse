/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.criteria;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Column;

/**
 *
 */
public class LikeTest extends ColumnTest {

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
    final Like c = new Like(column, values);
    Assert.assertTrue(c.toString().startsWith(column.name() + " like ':"));
    Assert.assertEquals(c.bindings().length, values.length);
  }

}
