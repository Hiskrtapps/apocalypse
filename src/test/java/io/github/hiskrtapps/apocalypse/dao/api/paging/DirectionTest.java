/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.paging;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class DirectionTest {

  /**
   * Test method for
   * {@link com.domain.entity.enums.ProductSnapshotEntityStatus#valueOf()}.
   */
  @Test
  public void testValueOfEquals() {
    Assert.assertEquals(Order.Direction.ASC, Order.Direction.valueOf("ASC"));
  }
  
}
