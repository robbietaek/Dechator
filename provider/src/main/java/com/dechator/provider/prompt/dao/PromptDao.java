package com.dechator.provider.prompt.dao;

import com.dechator.provider.prompt.model.prompt.Prompt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromptDao {

  Prompt selectPromptByPromptType(String promptType);

}
