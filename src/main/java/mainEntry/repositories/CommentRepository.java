package mainEntry.repositories;

import java.util.List;

import javax.transaction.Transactional;

import mainEntry.model.Comment;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface CommentRepository extends CrudRepository<Comment, Integer> {

	@Modifying
	@Query("update Comment c set c.confirmed =true where c.id =:entryId")
	void confirmComment(@Param("entryId") int rssFeedEntryId);
	List<Comment> findByConfirmed(boolean confirmed);

}
