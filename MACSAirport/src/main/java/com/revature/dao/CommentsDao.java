package com.revature.dao;

import java.util.List;

import com.revature.domain.Comment;

public interface CommentsDao {

	public void createComment(Comment c);
	
	public Comment getCommentById(int id);
	
	public List<Comment> getAllComments();
}
