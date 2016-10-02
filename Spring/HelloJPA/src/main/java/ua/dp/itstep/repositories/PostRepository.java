package ua.dp.itstep.repositories;

import org.springframework.data.repository.CrudRepository;

import ua.dp.itstep.domain.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
