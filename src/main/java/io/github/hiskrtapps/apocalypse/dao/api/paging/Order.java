/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api.paging;

/**
 * PropertyPath implements the pairing of an {@link Direction} and a property.
 *
 */
public class Order {

  /**
   * direction of the order
   */
  private final Direction direction;
  
  /**
   * property of the order
   */
  private final String property;

  /**
   * Construct a new order
   * 
   * @param direction must not be null
   * @param property must not be null or empty.
   */
  public Order(final String property, final Direction direction) {
    this.property = property;
    this.direction = direction;
  }

  /**
   * Construct a new order.
   * Direction is set to ASC
   * 
   * @param property must not be null or empty.
   */
  public Order(final String property) {
    this(property, Direction.ASC);
  }

  /**
   * @return direction
   */
  public Direction direction() {
    return direction;
  }

  /**
   * @return property
   */
  public String property() {
    return property;
  }
  
  /**
   * Enumeration for order directions.
   *
   */
  public enum Direction {

    ASC, DESC;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return property + " " + direction.name();
  }

}