/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.paging;

import java.util.Arrays;

import com.github.hiskrtapps.apocalypse.dao.TestCompleteMockEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class PageTest {

  private Page<TestCompleteMockEntity> page;
  
  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    page = new Page<>(5L, 1L, 20, Arrays.asList(new TestCompleteMockEntity()));
  }

  @Test
  public void testTotal() {
    Long response = page.total();
    Assert.assertEquals(Long.valueOf(5L), response);
  }

  @Test
  public void testOffset() {
    Long response = page.offset();
    Assert.assertEquals(Long.valueOf(1L), response);
  }

  @Test
  public void testLimit() {
    Integer response = page.limit();
    Assert.assertEquals(Integer.valueOf(20), response);
  }

  @Test
  public void testIterator() {
    int i = 0;
    for (TestCompleteMockEntity testCompleteMockEntity : page) {
      i++;
    }
    Assert.assertEquals(1, i);
  }

}
