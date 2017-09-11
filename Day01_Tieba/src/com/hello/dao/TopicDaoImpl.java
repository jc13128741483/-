package com.hello.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hello.domain.Topic;
import com.hello.utils.HibernateUtil;

public class TopicDaoImpl implements TopicDao {

	public void save(Topic t) {
		Session currentSession = HibernateUtil.getCurrentSession();
		currentSession.save(t);
	}

	public List<Topic> getAll(String key) {
		Session currentSession = HibernateUtil.getCurrentSession();
		Query createQuery = currentSession.createQuery("from Topic where title like :key");
		//判断是否有参数
		if(key!=null && !"".equals(key.trim())){
			createQuery.setString("key", "%key%");
		}else{
			createQuery.setString("key","%");
		}
		return createQuery.list();
	}

}
