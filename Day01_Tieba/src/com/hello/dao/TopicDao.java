package com.hello.dao;

import java.util.List;

import com.hello.domain.Topic;

public interface TopicDao {
	//保存帖子
	public void save(Topic t);
	//根据条件查询帖子
	public List<Topic> getAll(String key);
}
