package com.zl.fastjson;

import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class Test2 {

	@Test
	public void T2(){
		JSONObject json = new JSONObject();
		json.put("aa", "11");
		json.put("bb", "22");
		json.put("cc", "33");
		String jsonStr = json.toString();
		System.out.println(jsonStr);
		// {"aa":"11","bb":"22","cc":"33"}	
		  
		System.out.println(JSONObject.parseObject(jsonStr).get("aa"));
		// 11

		String o = "{'area':{'area':'1','pagetype':'home'},'pagetype':'home'}";
		System.out.println(((Map) JSONObject.parseObject(o).get("area")).get("area"));
		// 1
		String text = JSON.toJSONString(o);
		Map<String, Object> userMap = 
		    JSON.parseObject(o, new TypeReference<Map<String, Object>>() {});
		System.out.println(((Map) userMap.get("area")).get("NotExsit"));
		// null

		System.out.println(JSON.toJSONString((Map) userMap.get("area")));
		// {"area":"1","pagetype":"home"}
	}
}
