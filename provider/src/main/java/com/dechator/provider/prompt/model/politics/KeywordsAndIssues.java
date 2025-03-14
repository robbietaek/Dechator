package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class KeywordsAndIssues implements Serializable {

  @Serial
  private static final long serialVersionUID = -3159518546882949686L;

  private List<TopKeyword> topKeywords;

  private List<String> politicalIssues;

}