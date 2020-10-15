package com.teammatch.tournament;

import com.teammatch.tournament.domain.model.Organizer;
import com.teammatch.tournament.domain.repository.OrganizerRepository;
import com.teammatch.tournament.domain.service.OrganizerService;
import com.teammatch.tournament.service.OrganizerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class OrganizerServiceImplIntegrationTest {
   @Autowired
   private OrganizerService organizerService;
   @MockBean
   private OrganizerRepository organizerRepository;
   @TestConfiguration
    static class  OrganizerImplTestConfiguration{
       @Bean
       public OrganizerService organizerService(){
           return  new OrganizerServiceImpl();
       }
   }
   @Test
    @DisplayName("When getOrganizerByUserName with valid userName then Returns Organizer")
    public void whenGetOrganizerByUserNameWithValidUserNameTheReturnsOrganizer(){
       //Arrange
       String userName="user1";
       Organizer organizer=new Organizer();
       organizer.setId(1L);
       organizer.setUsername(userName);
       Mockito.when(organizerRepository.findByUsername(userName))
               .thenReturn(Optional.of(organizer));
       //Act
       Organizer foundOrganizer=organizerService.getOrganizerByUserName(userName);
       //Assert
       assertThat(foundOrganizer.getUsername()).isEqualTo(userName);
   }
}
