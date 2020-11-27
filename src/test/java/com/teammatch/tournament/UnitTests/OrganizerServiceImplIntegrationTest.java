package com.teammatch.tournament.UnitTests;

import com.teammatch.tournament.domain.model.Organizer;
import com.teammatch.tournament.domain.repository.OrganizerRepository;
import com.teammatch.tournament.domain.service.OrganizerService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
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
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
public class OrganizerServiceImplIntegrationTest extends TournamentApplicationTests{
        @Autowired
        private OrganizerService organizerService;

        @MockBean
        private OrganizerRepository organizerRepository;

        @TestConfiguration
        static class OrganizerServiceImplConfiguration{
            @Bean
            public OrganizerService organizerService(){ return new OrganizerServiceImpl();}
        }

        @Test
        @DisplayName("When findById But Id Does Not Exists Return player not found for Id with value 1")
        public void whenFindByIdButIdDoesNotExistsReturnOrganizerNotFound(){
            //Arrange
            String response = "Resource %s not found for %s with value %s";
            Organizer organizer = new Organizer();
            organizer.setId(1L).setFirstName("Diego").setLastName("Johnson");

            Mockito.when(organizerRepository.findById(1L))
                    .thenReturn(Optional.empty());
            String expectedMessage = String.format(response, "Organizer","Id", organizer.getId());
            //Act
            Throwable exception = catchThrowable(()-> {
                Organizer result = organizerService.getOrganizerById(1L);
            });
            //Assert
            assertThat(exception)
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage(expectedMessage);
        }
}
