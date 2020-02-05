package pack;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "addressBook", path = "addr")
public interface ABRepository extends CrudRepository<AddressBook, Long> {
    List<AddressBook> findByName(String name);

    AddressBook findById(long id);
}
