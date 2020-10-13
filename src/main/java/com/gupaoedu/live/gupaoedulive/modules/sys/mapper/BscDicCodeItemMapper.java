package com.gupaoedu.live.gupaoedulive.modules.sys.mapper;

import com.gupaoedu.live.gupaoedulive.core.mapper.MyMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.BscDicCodeItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BscDicCodeItemMapper extends MyMapper<BscDicCodeItem> {
    List<BscDicCodeItem> getBscDicCodeItemListByTypeCode(String typeCode);

    BscDicCodeItem getBscDicCodeItemListByTypeCodeAndItemCode(@Param("typeCode") String typeCode, @Param("itemCode") String itemCode);

    void deleteByTypeId(Integer id);
}