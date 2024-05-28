package site.potatolog.potatolog.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByGithubId(Integer githubId);

    Optional<User> findBySocialAccountUid(String socialAccountUid);

}
