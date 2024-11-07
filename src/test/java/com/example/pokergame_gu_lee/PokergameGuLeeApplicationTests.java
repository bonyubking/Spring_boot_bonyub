package com.example.pokergame_gu_lee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=validate",
    "spring.datasource.url=jdbc:mysql://pokergame-db.cp0acqc0qlat.ap-northeast-2.rds.amazonaws.com:3306/pokergame?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC",
    "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect"
})
class PokergameGuLeeApplicationTests {

    @Test
    void contextLoads() {
    }
}
