package site.potatolog.potatolog.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.potatolog.potatolog.user.domain.User;
import site.potatolog.potatolog.user.domain.UserRepository;

import java.util.Optional;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User userInfo) {
        log.info("DB에 사용자 정보 저장");

        Optional<User> existingUserBySocialAccountUid = userRepository.findBySocialAccountUid(userInfo.getSocialAccountUid());
        if (existingUserBySocialAccountUid.isPresent()) {
            log.info("User already exists with socialAccountUid: {}", userInfo.getSocialAccountUid());
            return existingUserBySocialAccountUid.get();
        }

        log.info("User: {}", userInfo);

        return userRepository.save(userInfo);
    }

}

