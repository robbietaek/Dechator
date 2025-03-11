package com.dechator.provider.youtube.dao.snippet;

import com.dechator.provider.youtube.model.snippet.Snippet;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SnippetDao {

  List<Snippet> selectSnippetListByTargetId(String targetId);

}
