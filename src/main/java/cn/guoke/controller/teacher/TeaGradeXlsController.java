package cn.guoke.controller.teacher;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.guoke.utils.SnowflakeUtils;
import cn.hutool.core.lang.generator.UUIDGenerator;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/**
 * @Desc 导入学生成绩
 * @author 语录
 *
 */
@Controller
@RequestMapping("/grade")
@ResponseBody
public class TeaGradeXlsController {

	@RequestMapping("/up/g")
	public Map<String, Object> leadExals(MultipartFile file1,HttpServletRequest request) throws IOException  {
		Map<String,Object> map= new HashMap<String,Object>();  
      if(file1.isEmpty()){  
        map.put( "result", "error");  
        map.put( "msg", "上传文件不能为空" );  
      } else{  
        String originalFilename=file1.getOriginalFilename();  

         try{   
               //创建要上传的路径
             File fdir = new File("D:/file");
             if (!fdir.exists()) { 
                 fdir.mkdirs(); 
                 }
              Long name = SnowflakeUtils.getSnowflake(); 
                //文件上传到路径下
              FileUtils. copyInputStreamToFile(file1.getInputStream(), new File(fdir,name+originalFilename)); 
              System.out.println(fdir+"\\"+name+originalFilename);
              ExcelReader reader = ExcelUtil.getReader(fdir+"\\"+name+originalFilename);
              List<Map<String,Object>> readAll = reader.readAll();
              System.out.println(readAll);	
               //coding  
              map.put( "result", "success");  
                
        } catch (Exception e) {  
              map.put( "result", "error");  
              map.put( "msg",e.getMessage());  
                
        }  
      }
	return map;  
    }
	

}
