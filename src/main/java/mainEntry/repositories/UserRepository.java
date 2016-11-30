package mainEntry.repositories;

import javax.transaction.Transactional;

import mainEntry.model.User;

import org.springframework.data.repository.CrudRepository;


@Transactional
public interface UserRepository   extends CrudRepository<User, Integer>{

}
