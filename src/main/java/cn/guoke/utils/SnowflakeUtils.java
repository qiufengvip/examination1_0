package cn.guoke.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @Desc 雪花算法 唯一 id 工具
 * @author 语录
 *
 */
public class SnowflakeUtils {
	
	//定义的 
	private static final  Snowflake SONWFLAKE = IdUtil.getSnowflake(1, 1);
	
	// 获取下一个 
	public static Long getSnowflake(){
		return SONWFLAKE.nextId();
	}
	
}
