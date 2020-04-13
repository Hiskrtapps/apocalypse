/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.criteria;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class AndTest {

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testCreationZeroCriteria() {
    final And c = new And(new ArrayList());
    Assert.assertEquals("(1 = 1) and (1 = 1)", c.toString());
    Assert.assertEquals(c.bindings().length, 0);
  }

  @Test
  public void testCreationOneCriteria() {
    List criteria = new ArrayList();
    final Criteria e = create();
    criteria.add(e);
    final And c = new And(criteria);

    Assert.assertTrue(c.toString().endsWith(") and (1 = 1)"));
    Assert.assertEquals(c.bindings().length, e.bindings().length);
  }

  @Test
  public void testCreationTwoCriteria() {
    List criteria = new ArrayList();
    final Criteria e = create();
    criteria.add(e);
    final And c = new And(criteria);

    Assert.assertTrue(c.toString().matches("\\((.+)\\) and \\((.+)\\)"));
    Assert.assertEquals(c.bindings().length, e.bindings().length);
  }

  private Criteria create(){
    Object[] values = new Object[] { "value1", "value2" };
    return new Criteria() {
      @Override
      public String name() {
        return null;
      }

      @Override
      public Map<String, Object>[] bindings() {
        return new Map[0];
      }
    };
  }

}
