package org.example.dao;

import java.util.List;
import org.example.domain.Post;

public interface PostDao {
	public void persist(Post post);
	
	public List<Post> getAllPosts();
}
