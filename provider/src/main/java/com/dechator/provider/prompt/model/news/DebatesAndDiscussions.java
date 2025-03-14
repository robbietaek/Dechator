package com.dechator.provider.prompt.model.news;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class DebatesAndDiscussions implements Serializable {

  @Serial
  private static final long serialVersionUID = 5187358921455033698L;

  private List<String> controversialTopics;
  
  private List<String> argumentsFor;

  private List<String> argumentsAgainst;

  private List<String> intenseDebateMessages;

  private String chatSurgePatterns;

}