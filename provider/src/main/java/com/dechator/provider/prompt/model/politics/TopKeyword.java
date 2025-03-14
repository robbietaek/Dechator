package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class TopKeyword implements Serializable {

  @Serial
  private static final long serialVersionUID = -97875054250228554L;

  private String keyword;

  private List<String> exampleChats;

}