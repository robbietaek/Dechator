package com.dechator.scheduler.youtube.dao.target;

import com.dechator.scheduler.youtube.model.target.Target;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TargetDao {

  List<Target> selectTargetList();

  void upsertTarget(@Param("target") Target target);

}
