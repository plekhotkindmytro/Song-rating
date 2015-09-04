package service;

import model.*;
import model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ErrorRepository;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Service
public class ErrorService {

    @Autowired
    private ErrorRepository errorRepository;

    public Error save(Error error) {
        return errorRepository.save(error);
    }
}
