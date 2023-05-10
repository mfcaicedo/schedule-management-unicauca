package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import org.springframework.data.domain.Pageable;

public interface IPersonService {
    GenericPageableResponse findAllPerson(Pageable pageable);
}
