package br.com.challengersicredi.commons.exeption;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { DuplicateKeys.class, RuntimeException.class })
    protected Mono<ResponseEntity<Object>> handleConflict(RuntimeException ex, ServerWebExchange request) {
        String bodyOfResponse = "This value already exists in DB";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { SessionClosed.class })
    protected Mono<ResponseEntity<Object>> sessionClosed(RuntimeException ex, ServerWebExchange request) {
        String bodyOfResponse = "Session Closed, create another session";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

    @ExceptionHandler(value = { SessionNotOpenOrClosed.class })
    protected Mono<ResponseEntity<Object>> sessionNotOpenOrClosed(RuntimeException ex, ServerWebExchange request) {
        String bodyOfResponse = "Session not Created or already closed";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

    @ExceptionHandler(value = { VoteDuplicated.class })
    protected Mono<ResponseEntity<Object>> alreadyVoted (RuntimeException ex, ServerWebExchange request) {
        String bodyOfResponse = "You have already voted in this section, wait for the next one to open";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { ResourceNotFound.class })
    protected Mono<ResponseEntity<Object>> resourceNotFound(RuntimeException ex, ServerWebExchange request) {
        String bodyOfResponse = "Resource not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }
}
