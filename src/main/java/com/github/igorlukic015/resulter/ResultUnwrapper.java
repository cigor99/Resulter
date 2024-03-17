package com.github.igorlukic015.resulter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

public interface ResultUnwrapper {
    default <ResultValueType, MapperReturnType> ResponseEntity<?> okOrError(Result<ResultValueType> result,
                                                                            Function<ResultValueType,
                                                                                    MapperReturnType> mapper) {
        if (result.isSuccess()) {
            return ResponseEntity.status(result.getType().mapToHttpStatus()).body(mapper.apply(result.getValue()));
        }

        HttpStatus status = result.getType().mapToHttpStatus();

        return ResponseEntity.status(status)
                .body(ProblemDetail.forStatusAndDetail(status, result.getMessage()));
    }

    default <ResultValueType> ResponseEntity<?> okOrError(Result<ResultValueType> result) {
        if (result.isSuccess()) {
            return ResponseEntity.status(result.getType().mapToHttpStatus()).body(result.getValue());
        }

        HttpStatus status = result.getType().mapToHttpStatus();

        return ResponseEntity.status(status)
                .body(ProblemDetail.forStatusAndDetail(status, result.getMessage()));
    }

    default <ResultValueType> ResponseEntity<ProblemDetail> errorFromResult(Result<ResultValueType> result) {
        if (result.isSuccess()) {
            throw new RuntimeException("No error message for successful result!");
        }

        HttpStatus status = result.getType().mapToHttpStatus();

        return ResponseEntity.status(status)
                .body(ProblemDetail.forStatusAndDetail(status, result.getMessage()));
    }
}
