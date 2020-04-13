/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.paging;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class OrderTest {

  private static final String EXPECTED_PROPERTY = "property";

  private Order orderDefault;
  
  private Order orderASC;
  
  private Order orderDESC;

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    orderDefault = new Order(EXPECTED_PROPERTY);
    orderASC = new Order(EXPECTED_PROPERTY, Order.Direction.ASC);
    orderDESC = new Order(EXPECTED_PROPERTY, Order.Direction.DESC);
  }

  @Test
  public void testGetProperty() {
    String response = orderDefault.property();
    Assert.assertEquals(EXPECTED_PROPERTY, response);
  }

  @Test
  public void testToString() {
    Assert.assertEquals(orderDefault.property() + " " + orderDefault.direction().name(), orderDefault.toString());
  }

  @Test
  public void testGetDirectionDefault() {
    Order.Direction direction = orderDefault.direction();
    Assert.assertEquals(Order.Direction.ASC, direction);
  }

  @Test
  public void testGetDirectionAsc() {
    Order.Direction direction = orderASC.direction();
    Assert.assertEquals(Order.Direction.ASC, direction);
  }

  @Test
  public void testGetDirectionDesc() {
    Order.Direction direction = orderDESC.direction();
    Assert.assertEquals(Order.Direction.DESC, direction);
  }

}
