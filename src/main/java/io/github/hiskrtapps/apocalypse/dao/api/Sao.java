/*
 * © 2020 Ceppi Productions.
 */
package io.github.hiskrtapps.apocalypse.dao.api;

/**
 * Interface for generic Sequence Access Object operations related to a sequence
 * <p>
 * Intentionally Sao interface has not any dependency from any third-party
 * library; that's because it is expected to be as much as general and portable
 * as possible
 */
public interface Sao {

  /**
   * Return a list of available sequence numbers
   * 
   * @param name of the sequence to take values from
   * @param number of values to take
   * @return a list of available sequence numbers
   */
  Iterable<Integer> next(String name, int number);

  /**
   * Return an available sequence number
   * 
   * @param name of the sequence to take value from
   * @return an sequence number
   */
  default Integer next(String name) {
    return next(name, 1).iterator().next();
  }

  /**
   * Return last used sequence number
   * 
   * @param name of the sequence to take value from
   * @return an sequence number
   */
  Integer current(String name);

}