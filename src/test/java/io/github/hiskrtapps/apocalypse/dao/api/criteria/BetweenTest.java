/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.criteria;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Column;

/**
 *
 */
public class BetweenTest extends ColumnTest {

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testCreation() {
    Column column = readColumn();
    Pair[] values = new Pair[] { Pair.of("value1", "value2"), Pair.of("value1", "value2") };
    final Between c = new Between(column, values);
    Assert.assertTrue(c.toString().startsWith(column.name() + " between :"));
    Assert.assertEquals(c.bindings().length, values.length);
  }

}
