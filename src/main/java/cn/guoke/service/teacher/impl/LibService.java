package cn.guoke.service.teacher.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.teacher.ILibMapper;
import cn.guoke.pojo.Mcq;
import cn.guoke.service.teacher.ILibService;
import cn.guoke.utils.DataUtils;

/**
 * @Desc 获取题库
 * @author 语录
 *
 */
@Service
public class LibService implements ILibService{

	@Autowired
	private ILibMapper iLibMapper;
	
	@Override
	public Map<String, Object> getLiib() {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		List<Mcq> mcq = iLibMapper.getLibMcq();
		data.put("data", mcq);
		return DataUtils.print(data, "0", "succeed");
	}

}
