package com.ai.cloudframe.provider.param.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.ParamDto;
import com.ai.cloudframe.provider.param.entity.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface ParamMapper extends BaseMapper<Param> {

    @Select("select DICT_CODE ,DICT_TYPE_CODE ,DICT_NAME from td_asiainfo_param where DICT_TYPE_CODE = #{dictTypeCode} AND DICT_CODE = #{dictCode} AND sysdate BETWEEN START_DATE AND END_DATE")
    List<ParamDto>  codeToName(@org.apache.ibatis.annotations.Param("dictTypeCode") String dictTypeCode,@org.apache.ibatis.annotations.Param("dictCode") String dictCode);
}
