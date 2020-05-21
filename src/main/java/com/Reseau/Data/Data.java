package com.Reseau.Data;

import java.io.Serializable;

public abstract class Data implements Serializable // must implement Serializable in order to be sent over a Socket
{
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String command;

  public Data(String command) {

    this.command = command;
  }

  
  /** 
   * @return String
   * toString implementation
   */
  public String toString() {
    return getCommand();
  }

  
  /** 
   * @return String
   * Getter for command
   */
  public String getCommand() {
    return command;
  }

}