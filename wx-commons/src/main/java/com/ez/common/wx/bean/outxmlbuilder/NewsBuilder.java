package com.ez.common.wx.bean.outxmlbuilder;


import com.easytnt.wechat.bean.WxXmlOutNewsMessage;
import com.easytnt.wechat.bean.WxXmlOutNewsMessage.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息builder
 * 
 * @author antgan
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, WxXmlOutNewsMessage> {

	protected final List<Item> articles = new ArrayList<Item>();

	public NewsBuilder addArticle(Item item) {
		this.articles.add(item);
		return this;
	}

	public WxXmlOutNewsMessage build() {
		WxXmlOutNewsMessage m = new WxXmlOutNewsMessage();
		for (Item item : articles) {
			m.addArticle(item);
		}
		setCommon(m);
		return m;
	}

}
