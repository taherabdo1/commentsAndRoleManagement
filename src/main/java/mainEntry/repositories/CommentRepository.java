package mainEntry.repositories;

import mainEntry.model.Comment;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
