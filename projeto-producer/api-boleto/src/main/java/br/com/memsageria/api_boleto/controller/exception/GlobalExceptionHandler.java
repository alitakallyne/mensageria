// package br.com.memsageria.api_boleto.controller.exception;


// import java.util.Date;
// import java.util.stream.Collectors;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.context.request.WebRequest;

// @ControllerAdvice
// public class GlobalExceptionHandler {
	

// 	@ExceptionHandler(ApplicationException.class)
// 	public ResponseEntity<Object> applicationException(ApplicationException e, WebRequest request) {
		
// 		  var response = ErroResponse.builder()
// 				  .erro(e.getMessage())
// 				  .codigo(HttpStatus.BAD_REQUEST.value())
// 	              .timestamp(new Date())
// 	              .path(request.getDescription(false)).build();

// 	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
// 	}
	
// 	  @ExceptionHandler(MethodArgumentNotValidException.class)
// 	    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
// 	       var erros = e.getFieldErrors().stream()
// 	               .map(item -> item.getField() + " " + item.getDefaultMessage() + " ")
// 	               .collect(Collectors.joining());

// 	        var response = ErroResponse.builder()
// 	                .erro(erros)
// 	                .codigo(HttpStatus.BAD_REQUEST.value())
// 	                .timestamp(new Date())
// 	                .path(request.getDescription(false)).build();

// 	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
// 	    }
// }
