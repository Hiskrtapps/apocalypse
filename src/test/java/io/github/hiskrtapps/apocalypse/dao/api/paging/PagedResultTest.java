/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.paging;

import io.github.hiskrtapps.apocalypse.dao.api.TestCompleteMockEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class PagedResultTest {

  private PagedResult<TestCompleteMockEntity> pagedResult;
  
  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    pagedResult = new PagedResult<>(true, 5L, new TestCompleteMockEntity());
  }

  @Test
  public void testTotal() {
    Long response = pagedResult.total();
    Assert.assertEquals(Long.valueOf(5L), response);
  }

  @Test
  public void testFirst() {
    boolean response = pagedResult.first();
    Assert.assertTrue(response);
  }

  @Test
  public void testEntity() {
    TestCompleteMockEntity response = pagedResult.entity();
    Assert.assertNotNull(response);
  }

}
