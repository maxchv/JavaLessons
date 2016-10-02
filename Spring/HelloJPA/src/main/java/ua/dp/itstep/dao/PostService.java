package ua.dp.itstep.dao;

import java.util.List;

import ua.dp.itstep.domain.Post;

public interface PostService {
	public void save(Post post);
	public List<Post> getAll();
}
