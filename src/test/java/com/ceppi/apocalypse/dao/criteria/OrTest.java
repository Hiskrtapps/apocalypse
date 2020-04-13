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
public class OrTest {

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testCreationZeroCriteria() {
    final Or c = new Or(new ArrayList());
    Assert.assertEquals("(0 = 1) or (0 = 1)", c.toString());
    Assert.assertEquals(c.bindings().length, 0);
  }

  @Test
  public void testCreationOneCriteria() {
    List criteria = new ArrayList();
    final Criteria e = create();
    criteria.add(e);
    final Or c = new Or(criteria);

    Assert.assertTrue(c.toString().endsWith(") or (0 = 1)"));
    Assert.assertEquals(c.bindings().length, e.bindings().length);
  }

  @Test
  public void testCreationTwoCriteria() {
    List criteria = new ArrayList();
    final Criteria e = create();
    criteria.add(e);
    final Or c = new Or(criteria);

    Assert.assertTrue(c.toString().matches("\\((.+)\\) or \\((.+)\\)"));
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
