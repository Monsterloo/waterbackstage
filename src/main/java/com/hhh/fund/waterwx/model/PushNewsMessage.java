package com.hhh.fund.waterwx.model;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/*
 * 推送发送图文消息
 */


public class PushNewsMessage extends PushArticle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    // 图文消息个数，限制为10条以内  
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个item为大图  
    private List<PushArticle> Articles = new ArrayList<PushArticle>();
	/*
	 * 推送发送图文消息
	 *{
     *"touser":"OPENID",
     *"msgtype":"news",
     *"news":{
     *   "articles": [
     *     {
     *        "title":"Happy Day",
     *         "description":"Is Really A Happy Day",
     *         "url":"URL",
     *         "picurl":"PIC_URL"
     *       },
     *     {
     *        "title":"Happy Day",
     *        "description":"Is Really A Happy Day",
     *        "url":"URL",
     *        "picurl":"PIC_URL"
     *      }
     *     ]
     *    }
     *  } 
	 */
	
	public static String parseJSONString(PushNewsMessage pushNews){
		if(pushNews==null&&pushNews.getTouser()!=null||pushNews.Articles!=null||pushNews.Articles.size()>0)return null;
	    JSONObject json = new JSONObject();
	    json.put("touser", pushNews.getTouser());
	    json.put("msgtype", "news");
	    JSONArray articles = new JSONArray();
	    for(int i=0;i<pushNews.Articles.size();i++){
	    	PushArticle pushArticle =pushNews.Articles.get(i);
	    	if(pushArticle!=null){
		    	JSONObject article = PushArticle.parseJSONObject(pushArticle);
		    	articles.add(i, article);	    		
	    	}
	    }
	    JSONObject news = new JSONObject();
	    news.put("articles", articles);
	    json.put("news", news);
	    return json.toString();
	}
	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<PushArticle> getArticles() {
		return Articles;
	}
	public void setArticles(List<PushArticle> articles) {
		Articles = articles;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
