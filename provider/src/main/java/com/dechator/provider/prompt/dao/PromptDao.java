package com.dechator.provider.prompt.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromptDao {

  String selectPromptTextByPromptType(String promptType);

}
