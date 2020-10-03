package com.teammatch.tournament;

import com.teammatch.tournament.domain.service.OrganizerService;
import com.teammatch.tournament.service.OrganizerServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class OrganizerServiceImplIntegrationTest {
   @Autowired
   private OrganizerService organizerService;
   @TestConfiguration
    static class  OrganizerImplTestConfiguration{
       @Bean
       public OrganizerService organizerService(){
           return  new OrganizerServiceImpl();
       }
   }
}
