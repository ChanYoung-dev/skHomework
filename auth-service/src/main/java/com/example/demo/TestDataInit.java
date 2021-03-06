package com.example.demo;

import com.example.demo.UserAccount.Service.UserAccountService;
import com.example.demo.UserAccount.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final UserAccountService userAccountService;

    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        SignUpDto dto = new SignUpDto("emrhssla", "emrhssla@gmail.com", "김찬영", "em89138913");
        userAccountService.register(dto);
    }

}
