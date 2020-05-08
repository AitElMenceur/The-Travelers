package com.Reseau;

import java.io.Serializable;

public abstract class Data implements Serializable // must implement Serializable in order to be sent over a Socket
{
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String Command;

  public Data(String Command) {

    this.Command = Command;
  }

  public String toString() {
    return GetCommand();
  }

  public String GetCommand() {
    return Command;
  }

  public abstract String GetMessage();

  public abstract void SetGroupCode(String next);

  public abstract String GetGroupCode();

  public abstract String GetUsername();
}