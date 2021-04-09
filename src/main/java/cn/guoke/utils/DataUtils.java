package cn.guoke.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc 返回数据的封装
 * @author 语录
 *
 */
public class DataUtils {
	
	/**
	 * 封装的json返回工具类
	 * 
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static Map<String, Object> MyPrint(int code, String msg, Object data) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		if (data != null) {
			map.put("data", data);
		}
		return map;
	}
	
	public static Map<String, Object> print(Map<String, Object> data,String code,String msg){
		data.put("code", code);
		data.put("msg", msg);
		return data;
	}
	
	public static Map<String, Object> print(Map<String, Object> data){
		data.put("code", "200");
		data.put("msg", "SUCCEED");
		return data;
	}
	
	public static Map<String, Object> print(Map<String, Object> data,String msg){
		data.put("code", "-1");
		data.put("msg", msg);
		return data;
	}
	
	public static Map<String, Object> print(String code,String msg){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", code);
		data.put("msg", msg);
		return data;
	}
	
	public static Long getDataTime(){
		return System.currentTimeMillis();
	}
	
	
	/* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s.trim());
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static void main(String[] args) {
		System.out.println(getDataTime());
	}
	
}
