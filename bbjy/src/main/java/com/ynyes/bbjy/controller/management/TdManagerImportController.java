package com.ynyes.bbjy.controller.management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.bbjy.entity.TdArticle;
import com.ynyes.bbjy.entity.TdManager;
import com.ynyes.bbjy.entity.TdManagerRole;
import com.ynyes.bbjy.entity.TdUser;
import com.ynyes.bbjy.service.TdArticleService;
import com.ynyes.bbjy.service.TdManagerLogService;
import com.ynyes.bbjy.service.TdManagerRoleService;
import com.ynyes.bbjy.service.TdManagerService;
import com.ynyes.bbjy.service.TdUserService;
import com.ynyes.bbjy.util.SiteMagConstant;
/**
 * 后台首页控制器
 * 
 * @author Sharon
 */ 

@Controller
@RequestMapping(value="/Verwalter/import")
public class TdManagerImportController {
	
	@Autowired
	TdManagerLogService tdManagerLogService;
	
	@Autowired
	TdUserService tdUserService;
	
 	private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
	
    /*--------------------------------------------------------
	  * =========导入表格模块////////==========
	  =================================*/
	 //票据处理：导入excel表格
		 @RequestMapping(value="/{type}")
		    public String billGatherImport(
		    					@PathVariable String type,
		    					String time,
		                        String __VIEWSTATE,
		                        ModelMap map,
		                        HttpServletRequest req){
		        String username = (String) req.getSession().getAttribute("manager");
		        if (null == username)
		        {
		            return "redirect:/Verwalter/login";
		        }
		        
		        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
	            map.addAttribute("time", time);
	            
	            if(null != type && type.equals("gather"))
	            {
	            	 return "/site_mag/bill_gather_import";
	            }
	            

		       return "redirect:/Verwalter/center";
		 } 
		 
		 @RequestMapping(value="/gather/submit", method=RequestMethod.POST)
		 @ResponseBody
		    public Map<String,Object> billGatherImportSubmit(
		    					String time,
		    					String fileUrl,
//		                        ModelMap map,
		                        HttpServletResponse resp,
                               HttpServletRequest req) throws IOException{
			 Map<String, Object> res = new HashMap<String, Object>();
				res.put("code", 1);
		        String username = (String) req.getSession().getAttribute("manager");
		        if (null == username)
		        {
		        	res.put("login", 1);
		            return res;
		        }
		        
		        //导入过程
		        String filepath = SiteMagConstant.imagePath;	
		        File file = new File(filepath +"/" +fileUrl);

		        try {
		            // 对读取Excel表格标题测试
//		            InputStream is = new FileInputStream(file);
//		            String[] title = readExcelTitle(is);
//		            System.out.println("获得Excel表格的标题:");
//		            for (String s : title) {
//		                System.out.print(s + " ");
//		            }

		            // 读取Excel表格内容
		            InputStream is2 = new FileInputStream(file);
		            //测试
//		            Map<Integer, String> map = readExcelContent(is2);
//		            System.out.println("获得Excel表格的内容:");
//		            for (int i = 1; i <= map.size(); i++) {
//		                System.out.println(map.get(i));
//		            }
		            
		            //zhangji 把表格内容放入json；
			        try {
			            fs = new POIFSFileSystem(is2);
			            wb = new HSSFWorkbook(fs);
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			        sheet = wb.getSheetAt(0);
			        // 得到总行数
			        int rowNum = sheet.getLastRowNum();
			        row = sheet.getRow(0);
			        int colNum = row.getPhysicalNumberOfCells();
			        // 正文内容应该从第二行开始,第一行为表头的标题
			        for (int i = 1; i <= rowNum; i++) {
			            row = sheet.getRow(i);
			            int j = 0;
			            while (j < colNum) {
			                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
			                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
			                // str += getStringCellValue(row.getCell((short) j)).trim() +
			                // "-";
			                res.put("cell"+j, getCellFormatValue(row.getCell((short) j))) ;
			                
			                j++;
			            }
			        }
			        
			        is2.close();
		            
		        } catch (FileNotFoundException e) {
		            System.out.println("未找到指定路径的文件!");
		            e.printStackTrace();
		        }
		        
		        
		        
		        res.put("code", 0);
		        return res;
		 } 
		 
		 
		 /**
		  * 导入客户代理费清单 
		  * 批量导入，直接写入数据库
		 * @throws ParseException 
		  */
//		 @RequestMapping(value="/pay/submit", method=RequestMethod.POST)
//		 @ResponseBody
//		    public Map<String,Object> importPaySubmit(
//		    					String time,
//		    					String fileUrl,
////		                        ModelMap map,
//		                        HttpServletResponse resp,
//                               HttpServletRequest req) throws IOException, ParseException{
//			 Map<String, Object> res = new HashMap<String, Object>();
//				res.put("code", 1);
//		        String username = (String) req.getSession().getAttribute("manager");
//		        if (null == username)
//		        {
//		        	res.put("login", 1);
//		            return res;
//		        }
//		        
//		        if( null == time ||null == fileUrl)
//		        {
//		        	res.put("msg", "资料不能为空！");
//		        	return res;
//		        }
//		        
//		        //导入过程
//		        String filepath = SiteMagConstant.imagePath;	
//		        File file = new File(filepath +"/" +fileUrl);
//
//		        try {
//		            // 读取Excel表格内容
//		            InputStream is = new FileInputStream(file);
//		            //测试
////		            Map<Integer, String> map = readExcelContent(is2);
////		            System.out.println("获得Excel表格的内容:");
////		            for (int i = 1; i <= map.size(); i++) {
////		                System.out.println(map.get(i));
////		            }
//		            
//		            //zhangji 把表格内容放入json；
//			        try {
//			            fs = new POIFSFileSystem(is);
//			            wb = new HSSFWorkbook(fs);
//			        } catch (IOException e) {
//			            e.printStackTrace();
//			        }
//			        sheet = wb.getSheetAt(0);
//			        // 得到总行数
//			        int rowNum = sheet.getLastRowNum();
//			        	 row = sheet.getRow(0);
//					        // 正文内容应该从第二行开始,第一行为表头的标题
//					        for (int i = 1; i <= rowNum; i++) {
//					            row = sheet.getRow(i);
//					      
//					                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
//					                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
//					                // str += getStringCellValue(row.getCell((short) j)).trim() +
//					                // "-";
//					            	
//					            	//用户id验证 若tdPay中已存在则将其覆盖
//					            	String number = getCellFormatValue(row.getCell((short) 0)) ;
//					            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//					            	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//					            	Date date = sdf.parse(time);
//					            	TdPay tdPay = tdPayService.findByNumberAndTime(number, date);
//					    
//					            	
//					            	if(null == tdPay)
//					            	{
//					            		 TdPay newPay = new TdPay();
//					            		 //用户ID
//					            		 if(null !=tdUserService.findByNumber(number))
//					            		 {
//					            			 newPay.setUserId(tdUserService.findByNumber(number).getId());   
//					            		 }
//					            		 //单位名称
//					            		 newPay.setTitle(getCellFormatValue(row.getCell((short) 1)));
//					            		 //金额/月
//					            		 if(null != getCellFormatValue(row.getCell((short) 2)) && !getCellFormatValue(row.getCell((short) 2)).equals(""))
//					            		 {
//					            			 newPay.setBasePrice(getCellFormatValue(row.getCell((short) 2)));
//					            		 }
//					            		 //收费月份
//					            		 newPay.setTime(date);
//					            		 //代理记账费
//					            		 if(null != getCellFormatValue(row.getCell((short) 4)) && !getCellFormatValue(row.getCell((short) 4)).equals(""))
//					            		 {
//					            			 Double price = Double.parseDouble(getCellFormatValue(row.getCell((short) 4)));
//					            			 newPay.setPrice(price);
//					            		 }
//					            		 //代理费备注
//					            		 newPay.setContentOne(getCellFormatValue(row.getCell((short) 5)));
//					            		 //其他费用
//					            		 if(null !=getCellFormatValue(row.getCell((short) 6)) && !getCellFormatValue(row.getCell((short) 6)).equals(""))
//					            		 {
//					            			 newPay.setOtherPrice(Double.parseDouble(getCellFormatValue(row.getCell((short) 6))));
//					            		 }
//					            		 //其他备注
//					            		 newPay.setContentTwo(getCellFormatValue(row.getCell((short) 7)));
//					            		//应缴日期
//					            		 if (null !=getCellFormatValue(row.getCell((short) 8)) && !getCellFormatValue(row.getCell((short) 8)).equals(""))
//					            		 {
//					            			 newPay.setDeadline(sdf2.parse(getCellFormatValue(row.getCell((short) 8))));
//					            		 }
//					            		 //总金额
//					            		 if (null !=getCellFormatValue(row.getCell((short) 9)) && !getCellFormatValue(row.getCell((short) 9)).equals(""))
//					            		 {
//					            			 newPay.setTotalPrice(Double.parseDouble(getCellFormatValue(row.getCell((short) 9))));
//					            		 }
//	
//					            		 
//					            		 newPay.setNumber(number);
//					            		 newPay.setSortId(99L);
//					            		 newPay.setIsPaid(0L);
//					            		 tdPayService.save(newPay);
//					            	}
//					            	else{
//					            		 //单位名称
//					            		tdPay.setTitle(getCellFormatValue(row.getCell((short) 1)));
//					            		 //金额/月
//					            		 if(null != getCellFormatValue(row.getCell((short) 2)) && !getCellFormatValue(row.getCell((short) 2)).equals(""))
//					            		 {
//					            			 tdPay.setBasePrice(getCellFormatValue(row.getCell((short) 2)));
//					            		 }
//					            		 //收费月份
//					            		 tdPay.setTime(date);
//					            		 //代理记账费
//					            		 if(null != getCellFormatValue(row.getCell((short) 4)) && !getCellFormatValue(row.getCell((short) 4)).equals(""))
//					            		 {
//					            			 Double price = Double.parseDouble(getCellFormatValue(row.getCell((short) 4)));
//					            			 tdPay.setPrice(price);
//					            		 }
//					            		 //代理费备注
//					            		 tdPay.setContentOne(getCellFormatValue(row.getCell((short) 5)));
//					            		 //其他费用
//					            		 if(null !=getCellFormatValue(row.getCell((short) 6)) && !getCellFormatValue(row.getCell((short) 6)).equals(""))
//					            		 {
//					            			 tdPay.setOtherPrice(Double.parseDouble(getCellFormatValue(row.getCell((short) 6))));
//					            		 }
//					            		 //其他备注
//					            		 tdPay.setContentTwo(getCellFormatValue(row.getCell((short) 7)));
//					            		//应缴日期
//					            		 if (null !=getCellFormatValue(row.getCell((short) 8)) && !getCellFormatValue(row.getCell((short) 8)).equals(""))
//					            		 {
//					            			 tdPay.setDeadline(sdf2.parse(getCellFormatValue(row.getCell((short) 8))));
//					            		 }
//					            		 //总金额
//					            		 if (null !=getCellFormatValue(row.getCell((short) 9)) && !getCellFormatValue(row.getCell((short) 9)).equals(""))
//					            		 {
//					            			 tdPay.setTotalPrice(Double.parseDouble(getCellFormatValue(row.getCell((short) 9))));
//					            		 }
//					            		 
//					            		 tdPay.setNumber(number);
//					            		 tdPay.setSortId(99L);
//					            		 tdPay.setIsPaid(0L);
//					            		 tdPayService.save(tdPay);
//					            	}
//					        }
//			        is.close();
//		            
//		        } catch (FileNotFoundException e) {
//		            System.out.println("未找到指定路径的文件!");
//		            e.printStackTrace();
//		        }
//		        
//		        
//		        
//		        res.put("code", 0);
//		        return res;
//		 } 
		 

		 /**
		     * 读取Excel表格表头的内容
		     * @param InputStream
		     * @return String 表头内容的数组
		     */
		    public String[] readExcelTitle(InputStream is) {
		        try {
		            fs = new POIFSFileSystem(is);
		            wb = new HSSFWorkbook(fs);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        sheet = wb.getSheetAt(0);
		        row = sheet.getRow(0);
		        // 标题总列数
		        int colNum = row.getPhysicalNumberOfCells();
		        System.out.println("colNum:" + colNum);
		        String[] title = new String[colNum];
		        for (int i = 0; i < colNum; i++) {
		            //title[i] = getStringCellValue(row.getCell((short) i));
		            title[i] = getCellFormatValue(row.getCell((short) i));
		        }
		        return title;
		    }

		    /**
		     * 读取Excel数据内容
		     * @param InputStream
		     * @return Map 包含单元格数据内容的Map对象
		     */
		    public Map<Integer, String> readExcelContent(InputStream is) {
		        Map<Integer, String> content = new HashMap<Integer, String>();
		        String str = "";
		        try {
		            fs = new POIFSFileSystem(is);
		            wb = new HSSFWorkbook(fs);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        sheet = wb.getSheetAt(0);
		        // 得到总行数
		        int rowNum = sheet.getLastRowNum();
		        row = sheet.getRow(0);
		        int colNum = row.getPhysicalNumberOfCells();
		        // 正文内容应该从第二行开始,第一行为表头的标题
		        for (int i = 1; i <= rowNum; i++) {
		            row = sheet.getRow(i);
		            int j = 0;
		            while (j < colNum) {
		                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
		                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
		                // str += getStringCellValue(row.getCell((short) j)).trim() +
		                // "-";
		                str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
		                
		                j++;
		            }
		            content.put(i, str);
		            str = "";
		            
		        }
		        return content;
		    }

		    /**
		     * 获取单元格数据内容为字符串类型的数据
		     * 
		     * @param cell Excel单元格
		     * @return String 单元格数据内容
		     */
		    private String getStringCellValue(HSSFCell cell) {
		        String strCell = "";
		        switch (cell.getCellType()) {
		        case HSSFCell.CELL_TYPE_STRING:
		            strCell = cell.getStringCellValue();
		            break;
		        case HSSFCell.CELL_TYPE_NUMERIC:
		            strCell = String.valueOf(cell.getNumericCellValue());
		            break;
		        case HSSFCell.CELL_TYPE_BOOLEAN:
		            strCell = String.valueOf(cell.getBooleanCellValue());
		            break;
		        case HSSFCell.CELL_TYPE_BLANK:
		            strCell = "";
		            break;
		        default:
		            strCell = "";
		            break;
		        }
		        if (strCell.equals("") || strCell == null) {
		            return "";
		        }
		        if (cell == null) {
		            return "";
		        }
		        return strCell;
		    }

		    /**
		     * 获取单元格数据内容为日期类型的数据
		     * 
		     * @param cell
		     *            Excel单元格
		     * @return String 单元格数据内容
		     */
		    private String getDateCellValue(HSSFCell cell) {
		        String result = "";
		        try {
		            int cellType = cell.getCellType();
		            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
		                Date date = cell.getDateCellValue();
		                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
		                        + "-" + date.getDate();
		            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
		                String date = getStringCellValue(cell);
		                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
		            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
		                result = "";
		            }
		        } catch (Exception e) {
		            System.out.println("日期格式不正确!");
		            e.printStackTrace();
		        }
		        return result;
		    }

		    /**
		     * 根据HSSFCell类型设置数据
		     * @param cell
		     * @return
		     */
		    private String getCellFormatValue(HSSFCell cell) {
		        String cellvalue = "";
		        if (cell != null) {
		            // 判断当前Cell的Type
		            switch (cell.getCellType()) {
		            // 如果当前Cell的Type为NUMERIC
		            case HSSFCell.CELL_TYPE_NUMERIC:{
		                // 判断当前的cell是否为Date
		                if (HSSFDateUtil.isCellDateFormatted(cell)) {
		                    // 如果是Date类型则，转化为Data格式
		                    
		                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
		                    //cellvalue = cell.getDateCellValue().toLocaleString();
		                    
		                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
		                    Date date = cell.getDateCellValue();
		                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		                    cellvalue = sdf.format(date);
		                    
		                }
		                // 如果是纯数字
		                else {
		                	/* 原版，没有区分整数和小数 备注掉
		                    // 取得当前Cell的数值
		                    cellvalue = String.valueOf(cell.getNumericCellValue());
		                    */
		                	 {// 纯数字  
//		                         BigDecimal big=new BigDecimal(cell.getNumericCellValue());  
//		                         cellvalue = big.toString();  
		                         
		                         java.text.DecimalFormat formatter = new java.text.DecimalFormat("0.00");   //zhangji 格式化
		                         cellvalue = formatter.format(cell.getNumericCellValue());
		                         //解决1234.0  去掉后面的.0  
		                         if(null!=cellvalue&&!"".equals(cellvalue.trim())){  
		                              String[] item = cellvalue.split("[.]");  
		                              if(1<item.length&&"00".equals(item[1])){  
		                            	  cellvalue=item[0];  
		                              }  
		                         }  
		                     } 
		                }
		                break;
		            }
		            	
		            case HSSFCell.CELL_TYPE_FORMULA: 
		            // 如果当前Cell的Type为STRIN
		            case HSSFCell.CELL_TYPE_STRING:
		                // 取得当前的Cell字符串
		                cellvalue = cell.getRichStringCellValue().getString();
		                break;
		            // 默认的Cell值
		            default:
		                cellvalue = " ";
		            }
		        } else {
		            cellvalue = "";
		        }
		        return cellvalue;

		    }

		 
		    
		 
		 /*-----------------------------------------------------
		  * =========/////导入表格模块=============
		  =================================*/
}
