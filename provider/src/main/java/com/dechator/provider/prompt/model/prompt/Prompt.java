package com.dechator.provider.prompt.model.prompt;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class Prompt implements Serializable {

  @Serial
  private static final long serialVersionUID = 4214070034051069273L;

  private String promptType;
  private String promptText;
  private String promptJson;

}
