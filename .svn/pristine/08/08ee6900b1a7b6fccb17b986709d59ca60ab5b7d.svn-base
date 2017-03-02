package com.hhh.fund.waterwx.model;

import java.util.List;
/*
 * 响应消息之图文消息
 */
public class SendNewsMessage extends SendBaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    // 图文消息个数，限制为10条以内  如果图文数超过10，则将会无响应 
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个item为大图  
    private List<SendArticle> Articles;  
  
    public int getArticleCount() {  
        return ArticleCount;  
    }  
  
    public void setArticleCount(int articleCount) {  
        ArticleCount = articleCount;  
    }

	public List<SendArticle> getArticles() {
		return Articles;
	}

	public void setArticles(List<SendArticle> articles) {
		Articles = articles;
	}  
    
}
