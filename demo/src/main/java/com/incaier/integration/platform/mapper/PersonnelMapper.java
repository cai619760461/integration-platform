package com.incaier.integration.platform.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.Personnel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author caiweijie
 */
@Mapper
public interface PersonnelMapper extends BaseMapper<Personnel> {
    List<Personnel> selectAll(@Param("id") Integer id);

    boolean saveOrUpdateBatch(@Param("entities") List<Personnel> personnelList);

    List<Personnel> getPersonnelByPage(@Param("offset") int offset, @Param("batchSize") int batchSize);

}
