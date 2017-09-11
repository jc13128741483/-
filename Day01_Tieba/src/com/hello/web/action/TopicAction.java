package com.hello.web.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hello.dao.TopicDao;
import com.hello.dao.TopicDaoImpl;
import com.hello.domain.Topic;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TopicAction extends ActionSupport {
	private TopicDao tdao=new TopicDaoImpl();
	private Topic topic;
	private String key;
	
	public String add(){	
		//ip
		topic.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		//时间
		topic.setCreateDate(new Date());
		topic.setLastReplyDate(topic.getCreateDate());
		//保存
		tdao.save(topic);
		return "toList";
	}
	
	public String list(){
		//获得帖子列表
		List<Topic> list=tdao.getAll(key);
		//放入ActionContext
		ActionContext.getContext().put("list", list);
		return "list";
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
