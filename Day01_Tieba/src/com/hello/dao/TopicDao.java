package com.hello.dao;

import java.util.List;

import com.hello.domain.Topic;

public interface TopicDao {
	//��������
	public void save(Topic t);
	//����������ѯ����
	public List<Topic> getAll(String key);
}
