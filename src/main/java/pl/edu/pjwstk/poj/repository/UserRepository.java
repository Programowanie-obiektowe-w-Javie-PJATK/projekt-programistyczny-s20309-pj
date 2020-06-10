package pl.edu.pjwstk.poj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.poj.dto.UserDto;
import pl.edu.pjwstk.poj.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

}
