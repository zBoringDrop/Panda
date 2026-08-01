package milazzodavide.panda.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IdNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleIdNotFoundException(IdNotFoundException ex) {
        log.warn("IdNotFoundException: {}", ex.getMessage());
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle(ExceptionMessage.ID_NOT_FOUND);
        problem.setDetail(ex.getMessage());
        return problem;
    }
}
