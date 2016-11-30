package mainEntry.repositories;

import java.util.List;

import javax.transaction.Transactional;

import mainEntry.model.Role;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface RoleRepository   extends CrudRepository<Role, Integer>{

	Role findByName(String name);
}
