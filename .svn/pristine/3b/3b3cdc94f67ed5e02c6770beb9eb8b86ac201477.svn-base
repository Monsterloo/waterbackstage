package com.hhh.fund.waterwx.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


/**
 * JacksonUtils
 * @see http://jackson.codehaus.org/
 * @see https://github.com/FasterXML/jackson
 * @see http://wiki.fasterxml.com/JacksonHome
 * @author xuefz
 *
 */
public class JacksonUtils {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	//private static XmlMapper xmlMapper = new XmlMapper();
	
	private final static String DATE_FORMAT_STR = "yyyy-MM-dd HH:mm";
	
	/**
	 * javaBean,list,array convert to json string
	 */
	public static String obj2json(Object obj){
		return obj2json(obj,DATE_FORMAT_STR);
	}
	
	/**
	 * javaBean,list,array convert to json string
	 */
	public static String obj2json(Object obj,String dateformat){
		if(null != dateformat){
			 SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
			 objectMapper.setDateFormat(formatter);
		}
		String jstr = null;
		try {
			jstr = objectMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jstr;
	}
	
	/**
	 * json string convert to javaBean
	 */
	public static <T> T json2pojo(String jsonStr,Class<T> clazz) throws Exception{
		if(StringUtil.isBlank(jsonStr)) return null;
		return objectMapper.readValue(jsonStr, clazz);
	}
	
	/**
	 * json string convert to map
	 */
	public static <T> Map<String,Object> json2map(String jsonStr)throws Exception{
		if(StringUtil.isBlank(jsonStr)) return null;
		return objectMapper.readValue(jsonStr, Map.class);
	}
	
	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String,T> json2map(String jsonStr,Class<T> clazz)throws Exception{
		if(StringUtil.isBlank(jsonStr)) return null;
		Map<String,Map<String,Object>> map =  objectMapper.readValue(jsonStr, new TypeReference<Map<String,T>>() {
		});
		Map<String,T> result = new HashMap<String, T>();
		for (Entry<String, Map<String,Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}
	
	/**
	 * json array string convert to list with javaBean
	 */
	public static <T> List<T> json2list(String jsonArrayStr,Class<T> clazz)throws Exception{
		if(StringUtil.isBlank(jsonArrayStr)) return null;
		List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
		});
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(map2pojo(map, clazz));
		}
		return result;
	}
	
	/**
	 * map convert to javaBean
	 */
	public static <T> T map2pojo(Map map,Class<T> clazz){
		return objectMapper.convertValue(map, clazz);
	}
	
	
	/**
	 * map convert to json
	 * 支持嵌套
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static String map2josn(Map map) throws JsonGenerationException, JsonMappingException, IOException{
		return objectMapper.writeValueAsString(map);
	}
	
	/**
	 * list convert to json
	 * 支持嵌套
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static String list2josn(List list) throws JsonGenerationException, JsonMappingException, IOException{
		return objectMapper.writeValueAsString(list);
	}
	
	/**
	 * json string convert to xml string
	 *//*
	public static String json2xml(String jsonStr)throws Exception{
		String xml=null;
		try {
			JsonNode root = xmlMapper.readTree(jsonStr);
			xml = xmlMapper.writeValueAsString(root);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
	}
	
	*//**
	 * xml string convert to json string
	 *//*
	public static String xml2json(String xml)throws Exception{
		StringWriter w = new StringWriter();
       JsonParser jp = xmlMapper.getJsonFactory().createJsonParser(xml);
       JsonGenerator jg = xmlMapper.getJsonFactory().createJsonGenerator(w);
        while (jp.nextToken() != null) {
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
	}*/	
	
	
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		 

		 Map<String, Object> userInMap = new HashMap<String, Object>();
		 userInMap.put("name", "henrypoter");
		 userInMap.put("age", 11);
		 
		 Map<String, Object> userInMap2 = new HashMap<String, Object>();
		 userInMap2.put("name2", "henrypoter222");
		 userInMap2.put("age2", 12122);
		 
		 Map<String, Object> userInMap3 = new HashMap<String, Object>();
		 userInMap3.put("name3", "henrypoter3");
		 userInMap3.put("age333", 33);
		 userInMap2.put("bbb", userInMap3);
		 
		 List<Object> list = new ArrayList<Object>();
		 list.add("msg 1");
		 list.add("msg 2");
		 list.add("msg 3");
		 list.add(userInMap2);
		
			userInMap.put("messages", list);
			userInMap.put("1", userInMap2);
			String json = "[{\"id\":1,\"name\":\"张三\"},{\"id\":2,\"name\":\"李四\"}]";
			// write JSON to a file
			// mapper.writeValue(new File("c:\\user.json"), userInMap);
			 //System.out.println(mapper.writeValueAsString(userInMap));
			// System.out.println(mapper.writeValueAsString(list));
			try {
				System.out.println(map2josn(userInMap));
				List<User> list3 = JacksonUtils.json2list(json,User.class);
				System.out.println(list3.size());
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		 
	}
	
	
	private static class User{
		private int id;
		private String name; 
		
		public User() {
		}
		public User(int id, String name) {
			this.id = id;
			this.name = name;
		}
		@Override
		public String toString() {
			return "{\"id\":"+id+",\"name\":\""+name+"\"}";
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
