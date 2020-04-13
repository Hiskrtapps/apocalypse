/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.criteria;

import static com.google.common.collect.Lists.partition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@link PartitionedIn} criteria will implement {@link In} partitioning the
 * list of values to face any maximum number limitation.
 *
 * The partitions are created, creating an {@link In} criteria for each one,
 * splitting the number of values basing on a LIMIT number that can be passed.
 *
 * The final condition will be an {@link Or} composition of all the partitions
 *
 */
public final class PartitionedIn extends CriteriaImpl {

  /**
   * default limit used to partition if not provided
   */
  private static final int DEFAULT_LIMIT = 500;

  /**
   * maximum number of elements for each partition
   */
  private final int limit;

  /**
   * result criteria ({@link Or} of partitions)
   */
  private final Or orOfIns;

  /**
   * Creates an instance of {@link PartitionedIn}
   *
   * @param in the {@link In} criteria to be partitioned
   * @param limit the maximum number of elements for each partition
   */
  public PartitionedIn(final In in, int limit) {
    this(PartitionedIn.class.getSimpleName(), in, limit);
  }

  /**
   * Creates an instance of {@link PartitionedIn}
   *
   * @param name of the {@link PartitionedIn} criteria
   * @param in the {@link In} criteria to be partitioned
   */
  public PartitionedIn(final String name, final In in) {
    this(name, in, DEFAULT_LIMIT);
  }

  /**
   * Creates an instance of {@link PartitionedIn}
   *
   * @param in the {@link In} criteria to be partitioned
   */
  public PartitionedIn(final In in) {
    this(PartitionedIn.class.getSimpleName(), in, DEFAULT_LIMIT);
  }

  /**
   * Creates an instance of {@link PartitionedIn}
   *
   * @param name of the {@link PartitionedIn} criteria
   * @param in the {@link In} criteria to be partitioned
   * @param limit the maximum number of elements for each partition
   */
  public PartitionedIn(final String name, final In in, int limit) {
    super(name);
    this.limit = limit;
    this.orOfIns = orOfIns(in);
  }

  @Override
  public Map<String, Object>[] bindings() {
    return orOfIns.bindings();
  }

  @Override
  public String toString() {
    return orOfIns.toString();
  }

  /**
   * Split the {@link In} in partitions and create an {@link Or} criteria with
   * all the partitions
   * 
   * @param in
   * @return
   */
  private Or orOfIns(final In in) {
    final List<Object> values = new ArrayList<>();
    for (final Map<String, Object> arrayOfValues : in.bindings()) {
      for (final Map.Entry entry : arrayOfValues.entrySet()) {
        values.addAll(((List)entry.getValue()));
      }
    }
    final List<Criteria> result = new ArrayList<>();
    for (final List<Object> partition : partition(values, limit)) {
      result.add(new In(in.columnName(), partition));
    }
    return new Or(result);
  }

}