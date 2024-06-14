package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.Personnel;
import com.incaier.integration.platform.response.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人员(Personnel)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-26 11:58:18
 */
@Mapper
@DS("hipmaster")
public interface PersonnelMapper extends BaseMapper<Personnel> {

    /**
     * 按工号获取角色
     *
     * @param pk personnel pk
     * @return {@link List}<{@link RoleVO}>
     */
    List<RoleVO> getRolesByPersonnelId(@Param("pk") Long pk);
}