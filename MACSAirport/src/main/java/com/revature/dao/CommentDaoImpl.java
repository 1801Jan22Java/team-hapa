package com.revature.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.revature.domain.Comment;
import com.revature.util.HibernateUtil;

@Repository("commentDaoImpl")
public class CommentDaoImpl implements CommentDao{

	@Override
	@Transactional
	public void createComment(Comment c) {
		Session s = HibernateUtil.getSession();
		
		s.persist(c);
		
		s.close();
	}

	@Override
	public Comment getCommentById(int id) {
		Session s = HibernateUtil.getSession();
		
		Comment c = (Comment) s.get(Comment.class, id);
		
		s.close();
		
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Comment> getAllComments() {
		Session s = HibernateUtil.getSession();

		List<Comment> commentList = s.createQuery("from Comment").list();
		
		s.close();
		
		return commentList;
	}
}
