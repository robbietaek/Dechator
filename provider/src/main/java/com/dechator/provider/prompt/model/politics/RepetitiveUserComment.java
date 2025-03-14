package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class RepetitiveUserComment implements Serializable {

  @Serial
  private static final long serialVersionUID = -8276930961517488146L;

  private String user;

  private String comments;
  
}
