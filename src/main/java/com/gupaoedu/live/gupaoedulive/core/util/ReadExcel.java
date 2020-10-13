package com.gupaoedu.live.gupaoedulive.core.util;


import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.gupaoedu.live.gupaoedulive.core.util.WDWUtil1.isExcel2003;
import static com.gupaoedu.live.gupaoedulive.core.util.WDWUtil1.isExcel2007;


/**
 * 工具类读取Excel类中内容
 */
public class ReadExcel {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息-暂时未用到暂时留着
    public String getErrorInfo() { return errorMsg; }

    /**
     * 读EXCEL文件，获取客户信息集合
     * @param
     * @return
     */
    public Map<String,Object> getExcelInfo(String description, MultipartFile Mfile){

        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //获取本地存储路径
        File file = new  File("D:\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = cf.getFileItem().getName();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        String prefix=fileName.substring(0,fileName.lastIndexOf("."));
        fileName=prefix+ new Date().getTime()+suffix;
        String path="D:\\fileupload\\" +fileName;
        //新建一个文件
        File file1 = new File(path);
        //将上传的文件写入新建的文件中
        try {
            cf.getFileItem().write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //初始化输入流
        FileInputStream is = null;
        Workbook wb = null;
        try{
            //根据新建的文件实例化输入流
            is = new FileInputStream(file1);
            //根据excel里面的内容读取客户信息
            if(isExcel2003(path)){//当excel是2003时
                wb = new HSSFWorkbook(is);
            }else if(isExcel2007(path)) {//当excel是2007时
                wb = new XSSFWorkbook(is);
            }

            //读取Excel里面客户的信息
            //deptinfoVoList=readExcelValue(wb);

            is.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        ImportExecl importExecl=new ImportExecl();
        List<List<String>> list=importExecl.read(path);
        Map<String,Object> excelMap=new HashedMap<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                List<String> cellList = list.get(i);
                for (int j = 0; j < cellList.size(); j++) {
                    System.out.print("    " + cellList.get(j));
                }
            }

        }
        return excelMap;
    }

    /**
     * 得到Excel表中的值
     *
     * @param cell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings({ "static-access", "unused" })
    private String getValue(Cell cell) {
        if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(cell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }

}
