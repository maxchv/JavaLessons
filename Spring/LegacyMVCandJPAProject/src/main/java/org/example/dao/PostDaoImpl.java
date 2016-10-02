package org.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.domain.Post;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostDaoImpl implements PostDao {
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
    public void persist(Post post) {
        em.persist(post);
    }
	
	public List<Post> getAllPosts() {
    	TypedQuery<Post> query = em.createQuery(
            "SELECT p FROM Post p ORDER BY p.id", Post.class);
    	return query.getResultList();
    }
}
