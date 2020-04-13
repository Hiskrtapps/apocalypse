/*
 * © 2020 Ceppi Productions.
 */
package com.ceppi.apocalypse.dao.statements;

/**
 * Common class for generic statement
 *
 *
 */
public interface Statement {

  /**
   * return the name of the statement
   * 
   * @return name of the statement
   */
  String name();
  
  /**
   * return a command for specified parameters
   * 
   * @return command
   */
  String command();

}
