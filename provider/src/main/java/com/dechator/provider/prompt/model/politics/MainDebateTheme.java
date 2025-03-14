package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class MainDebateTheme implements Serializable {

  @Serial
  private static final long serialVersionUID = -4933411445058947292L;

  private String theme;

  private List<String> opposingViews;

  private List<String> arguments;

}