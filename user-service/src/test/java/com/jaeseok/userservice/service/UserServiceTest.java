package com.jaeseok.userservice.service;

import com.jaeseok.userservice.dto.UserDto;
import com.jaeseok.userservice.mapper.UserMapper;
import com.jaeseok.userservice.service.UserService;
import com.jaeseok.userservice.vo.RequestUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
@Transactional
@Rollback
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @DisplayName("유저 생성 반환 데이터 검사")
    @Test
    public void createdUserResultDataValidTest() {
        // given
        final RequestUser requestUser = new RequestUser("gojeasuk2@naver.com", "jaeseok-go", "ikko0301!@");
        final UserDto userDto = UserMapper.INSTANCE.requestToDto(requestUser);

        // when
        final UserDto resultDto = userService.createdUser(userDto);
        final String encodedPassword = encoder.encode("ikko0301!@");

        // then
        assertThat(resultDto.getEmail()).isEqualTo("gojeasuk2@naver.com");
        assertThat(resultDto.getName()).isEqualTo("jaeseok-go");
        assertThat(resultDto.getEncryptedPassword()).isEqualTo(encodedPassword);
        assertThat(resultDto.getUserId()).isNotNull();
        assertThat(resultDto.getOrders()).isNotNull();
    }
}
