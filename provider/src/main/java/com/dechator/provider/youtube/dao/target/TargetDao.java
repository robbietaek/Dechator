package com.dechator.provider.youtube.dao.target;

import com.dechator.provider.youtube.model.target.Target;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TargetDao {

  List<Target> selectTargetList();

  Target selectTargetByTargetId(String targetId);

}
