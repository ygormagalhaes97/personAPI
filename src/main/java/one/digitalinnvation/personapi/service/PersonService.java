package one.digitalinnvation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnvation.personapi.dto.request.PersonDTO;
import one.digitalinnvation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnvation.personapi.entity.Person;
import one.digitalinnvation.personapi.exception.PersonNotFoundException;
import one.digitalinnvation.personapi.mapper.PersonMapper;
import one.digitalinnvation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO create(PersonDTO personDTO){
        Person person = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(person);
        return createMessageResponse(savedPerson.getId(),"criado uma pessoa com ID ");
    }

    public List<PersonDTO> listAll() {
         List<Person> allPeople = personRepository.findAll();
         return allPeople.stream()
                 .map(personMapper::toDTO)
                 .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {

        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {

        verifyIfExists(id);
        
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person person = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(person);
        return createMessageResponse(updatePerson.getId(),"Update na pessoa com ID ");
    }

    private MessageResponseDTO createMessageResponse(Long id,String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException(id));
    }
}
