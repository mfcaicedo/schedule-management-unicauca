package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Person;
import com.pragma.api.repository.IPersonRepository;
import com.pragma.api.util.PageableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements IPersonService {

    private final IPersonRepository iPersonRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PersonServiceImpl(IPersonRepository iPersonRepository, ModelMapper modelMapper) {
        this.iPersonRepository = iPersonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenericPageableResponse findAllPerson(Pageable pageable) {
        Page<Person> personPage = this.iPersonRepository.findAll(pageable);
        if(personPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.person.empty", "");
        return this.validatePageList(personPage);
    }

    @Override
    public PersonDTO findByCode(String code) {
        Optional<Person> person = this.iPersonRepository.findById(code);

        PersonDTO personDTO = new PersonDTO();

        personDTO = modelMapper.map(person,PersonDTO.class);

        return  personDTO;

    }


    private GenericPageableResponse validatePageList(Page<Person> personsPage){
        List<PersonDTO> resourcesDTOS = personsPage.stream().map(x->modelMapper.map(x, PersonDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(personsPage, resourcesDTOS);
    }
}
