package com.followup.davidson.converter;

import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Person;
import com.followup.davidson.repositories.ClientRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.Assert.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientConverterTest {

    @SpyBean
    @Autowired
    private ClientConverter clientConverter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dtoToEntityTest() {
        //Entry
        ClientDto entry = createDtoClient(1L,"EverySense","contact@gmail.com");
        //Excepted
        Client expected = createEntityClient(1L,"EverySense","contact@gmail.com");
        //call
        Client effective = clientConverter.dtoToEntity(entry);
        //Asset
        assertEquals(expected, effective);

    }

    @Test
    public void entityToDtoTest() {
        //Entry
        Client entry = createEntityClient(1L,"EverySense","contact@gmail.com");
        //Excepted
        ClientDto expected = createDtoClient(1L,"EverySense","contact@gmail.com");
        //call
        ClientDto effective = clientConverter.entityToDto(entry);
        //Asset
        assertEquals(expected, effective);
    }


    private ClientDto createDtoClient(Long id, String clientName, String clientContact) {
        return ClientDto.builder()
                .clientId(id)
                .clientName(clientName)
                .clientContact(clientContact)
                .build();
    }

    private Client createEntityClient(Long id, String clientName, String clientContact) {
        return Client.builder()
                .clientId(id)
                .clientName(clientName)
                .clientContact(clientContact)
                .build();
    }
}
