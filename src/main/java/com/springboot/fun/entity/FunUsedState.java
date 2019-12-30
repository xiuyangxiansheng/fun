package com.springboot.fun.entity;

public class FunUsedState {

  private String id;
  private String usedText;
  private long usedType;
  private long usedStates;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getUsedText() {
    return usedText;
  }

  public void setUsedText(String usedText) {
    this.usedText = usedText;
  }


  public long getUsedType() {
    return usedType;
  }

  public void setUsedType(long usedType) {
    this.usedType = usedType;
  }


  public long getUsedStates() {
    return usedStates;
  }

  public void setUsedStates(long usedStates) {
    this.usedStates = usedStates;
  }

}
