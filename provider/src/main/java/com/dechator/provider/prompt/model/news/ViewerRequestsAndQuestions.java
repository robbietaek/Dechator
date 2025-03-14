package com.dechator.provider.prompt.model.news;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ViewerRequestsAndQuestions implements Serializable {

  @Serial
  private static final long serialVersionUID = 3173366358427122321L;

  private List<String> requestedTopics;

  private List<String> frequentQuestions;

}