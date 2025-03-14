package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ViewerRequests implements Serializable {

  @Serial
  private static final long serialVersionUID = -8956211135752560240L;

  private List<String> additionalContent;

  private List<String> frequentQuestions;

}