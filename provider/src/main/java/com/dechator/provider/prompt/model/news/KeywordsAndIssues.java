package com.dechator.provider.prompt.model.news;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class KeywordsAndIssues implements Serializable {

  @Serial
  private static final long serialVersionUID = 3487551113587113683L;

  private List<String> topKeywords;

  private List<String> representativeMessages;

  private List<String> majorIssues;

  private List<String> mentionedPeopleAndEvents;

}