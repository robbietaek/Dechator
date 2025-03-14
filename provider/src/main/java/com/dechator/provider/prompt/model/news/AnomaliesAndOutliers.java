package com.dechator.provider.prompt.model.news;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class AnomaliesAndOutliers implements Serializable {

  @Serial
  private static final long serialVersionUID = 3281111928398937888L;

  private List<String> extremeSentiment;

  private String botActivity;
  
  private String fakeNews;

}