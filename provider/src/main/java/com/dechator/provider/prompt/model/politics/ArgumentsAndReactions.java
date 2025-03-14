package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ArgumentsAndReactions implements Serializable {

  @Serial
  private static final long serialVersionUID = -8178111843314718898L;

  private List<MainDebateTheme> mainDebateThemes;

  private String reactionPatterns;
  
}