
package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.core.result.PageResult;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.BscDicCodeItem;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.BscDicCodeType;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.ERROR;


/**
 */

@Api(value ="数据字典管理模块", description = "数据字典管理模块Api",tags = {"数据字典管理模块操作接口"})
@RestController
@RequestMapping("/data/dic")
public class DicController {
    @Autowired
    private DictService dictService;

    @ApiOperation(value = "字典字典管理视图",notes = "字典字典管理视图")
    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("/modules/data/dic/list");
    }

    @ApiOperation(value = "获取字典数据列表",notes = "获取字典数据列表")
    @GetMapping("/listItem/{id}")
    public PageResult<BscDicCodeItem> listItem(@ApiParam(name = "id",value="字典数据ID",required = true) @PathVariable Integer id,
                                               @ApiParam(name = "bscDicCodeItem",value="字典数据实体",required = true) BscDicCodeItem bscDicCodeItem,
                                               @ApiParam(name = "keyword",value="查询字段",required = false) String keyword){
        bscDicCodeItem.setTypeId(id);
        List<BscDicCodeItem> list = dictService.listItem(bscDicCodeItem, keyword);
        return new PageResult<>(new PageInfo<>(list));
    }

    @ApiOperation(value = "获取字典类型列表",notes = "获取字典数据列表")
    @GetMapping("/listType")
    public PageResult<BscDicCodeType> listType(@ApiParam(name = "bscDicCodeType",value="字典类型实体",required = true) BscDicCodeType bscDicCodeType,
                                               @ApiParam(name = "keyword",value="查询字段",required = false) String keyword){
        List<BscDicCodeType> list=dictService.listType(bscDicCodeType,keyword);
        return new PageResult(new PageInfo(list));
    }

    @ApiOperation(value = "保存字典数据",notes = "保存字典数据")
    @PostMapping("/saveOrUpdateItem")
    public ProcessResult saveOrUpdateItem(@ApiParam(name = "bscDicCodeItem",value="字典数据实体",required = true) BscDicCodeItem bscDicCodeItem){
        try {
            ProcessResult result= new ProcessResult();
            dictService.saveOrUpdateItem(bscDicCodeItem);
            result.setData(bscDicCodeItem.getTypeId());
            return result;
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @ApiOperation(value = "删除字典数据",notes = "删除字典数据")
    @PostMapping("/deleteItem/{id}")
    public ProcessResult deleteItem(@ApiParam(name = "id",value="字典数据ID",required = true) @PathVariable Integer id){
        try {
            dictService.deleteItem(id);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @ApiOperation(value = "获取字典数据信息",notes = "获取字典数据信息")
    @GetMapping("/view/{id}")
    public BscDicCodeItem view(@ApiParam(name = "id",value="字典数据ID",required = true) @PathVariable Integer id){
        return dictService.view(id);
    }

    @ApiOperation(value = "保存字典类型",notes = "保存字典类型")
    @PostMapping("/saveOrUpdateType")
    public ProcessResult saveOrUpdateType(@ApiParam(name = "bscDicCodeType",value="字典类型实体",required = true) BscDicCodeType bscDicCodeType){
        try {
            ProcessResult result=new ProcessResult();
            dictService.saveOrUpdateType(bscDicCodeType);
            result.setData(bscDicCodeType.getId());
            return result;
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }

    }

    @ApiOperation(value = "删除字典类型",notes = "删除字典类型")
    @PostMapping("/deleteType/{id}")
    public ProcessResult deleteType(@ApiParam(name = "id",value="字典类型ID",required = true) @PathVariable Integer id){
        try {
            dictService.deleteType(id);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @ApiOperation(value = "获取字典类型信息",notes = "获取字典类型信息")
    @GetMapping("/viewType/{id}")
    public BscDicCodeType viewType(@ApiParam(name = "id",value="字典类型ID",required = true) @PathVariable Integer id){
        return dictService.viewType(id);
    }

    /**
     *
     * @param typeCode
     * @return
     */
    @ApiOperation(value = "根据字典类型编码获取字典数据列表",notes = "根据字典类型编码获取字典数据列表")
    @PostMapping(value = "/getBscDicCodeItemListByTypeCode/{typeCode}")
    public List<BscDicCodeItem> getBscDicCodeItemListByTypeCode(@ApiParam(name = "typeCode",value="字典类型编码",required = true) @PathVariable String typeCode){
        return dictService.getBscDicCodeItemListByTypeCode(typeCode);
    }

    /**
     *
     * @param typeCode
     * @param itemCode
     * @return
     */
    @ApiOperation(value = "根据字典类型编码和字典数据编码获取字典数据信息",notes = "根据字典类型编码和字典数据编码获取字典数据信息")
    @PostMapping(value = "/getBscDicCodeItemListByTypeCode/{typeCode}/{itemCode}")
    public BscDicCodeItem getBscDicCodeItemListByTypeCodeAndItemCode(@ApiParam(name = "typeCode",value="字典类型编码",required = true) @PathVariable String typeCode,
                                                                     @ApiParam(name = "itemCode",value="字典数据编码",required = true) @PathVariable String itemCode){
        return dictService.getBscDicCodeItemListByTypeCodeAndItemCode(typeCode,itemCode);
    }


}
