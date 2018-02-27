package com.revature.dao;

import java.util.List;

import org.hibernate.*;

import com.revature.domain.Comment;
import com.revature.util.HibernateUtil;

public class CommentDaoImpl implements CommentDao{

	@Override
	public void createComment(Comment c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		s.persist(c);
		
		tx.commit();
		s.close();
	}

	@Override
	public Comment getCommentById(int id) {
		Session s = HibernateUtil.getSession();
		
		Comment c = (Comment) s.get(Comment.class, id);
		
		s.close();
		
		return c;
	}

	@Override
	public List<Comment> getAllComments() {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();

		List<Comment> commentList = s.createQuery("from Comment").list();
		
		tx.commit();
		s.close();
		
		return commentList;
	}
}
