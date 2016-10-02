package ua.dp.itstep.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dp.itstep.domain.Post;

@Service
public class PostServiceImpl implements PostService {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public void save(Post post) {
		em.persist(post);
	}

	@Override
	public List<Post> getAll() {		
		return em.createQuery("SELECT Post", Post.class).getResultList();
	}

}
