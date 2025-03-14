package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class IdeologicalBias implements Serializable {

  @Serial
  private static final long serialVersionUID = 833497187872131159L;

  private Double progressive;

  private Double conservative;
  
  private Double neutral;
}