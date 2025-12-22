package com.sai.inventory.util;

import com.sai.inventory.domain.common.response.ResponseStatus;
import com.sai.inventory.domain.common.response.SystemResponse;
import com.sai.inventory.exception.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseBuilder.class);

	public static ResponseEntity<Object> getErrorResponse(ErrorDetails errorDetails) {
		//LOGGER.info("getErrorResponse(ErrorDetails errorDetails)");
		try {
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setMessage(errorDetails.getMessage());
			responseStatus.setStatusCode(errorDetails.getErrorCode());
			responseStatus.setErrorTypeCode(errorDetails.getErrorTypeCode());
			responseStatus.setErrorType(errorDetails.getErrorType());

			SystemResponse response = new SystemResponse();
			response.setResponseStatus(responseStatus);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("Error while parsing error response " + errorDetails.toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static ResponseEntity<Object> getErrorResponse() {
		//LOGGER.info("getErrorResponse()");
		try {
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setMessage(ApplicationConstant.FAILURE_MSG);
			responseStatus.setStatusCode(ApplicationConstant.API_FAILURE);
			responseStatus.setErrorTypeCode(0);
			responseStatus.setErrorType(null);

			SystemResponse response = new SystemResponse();
			response.setResponseStatus(responseStatus);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("Error while parsing error response " + ex.toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static ResponseEntity<Object> getErrorResponse(String message) {
		//LOGGER.info("getErrorResponse(String message)");
		try {
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setMessage(ApplicationConstant.FAILURE_MSG);
			responseStatus.setStatusCode(ApplicationConstant.API_FAILURE);
			responseStatus.setErrorTypeCode(0);
			responseStatus.setErrorType(message);

			SystemResponse response = new SystemResponse();
			response.setResponseStatus(responseStatus);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("Error while parsing error response " + ex.toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static ResponseEntity<Object> getValidationErrorResponse(int code, String message) {
		//LOGGER.info("getValidationErrorResponse(int code, String message)");
		try {
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setMessage(ApplicationConstant.FAILURE_MSG);
			responseStatus.setStatusCode(ApplicationConstant.API_FAILURE);
			responseStatus.setErrorTypeCode(code);
			responseStatus.setErrorType(message);

			SystemResponse response = new SystemResponse();
			response.setResponseStatus(responseStatus);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("Error while parsing error response " + ex.toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static ResponseEntity<Object> getSuccessResponse() {
		//LOGGER.info("getSuccessResponse()");
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setMessage(ApplicationConstant.SUCCESS_MSG);
		responseStatus.setStatusCode(ApplicationConstant.API_SUCCESS);
		responseStatus.setErrorTypeCode(0);
		responseStatus.setErrorType(null);

		SystemResponse response = new SystemResponse();
		response.setResponseStatus(responseStatus);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public static ResponseEntity<Object> getSuccessResponse(Object responseData) {
		//LOGGER.info("getSuccessResponse(Object responseData)");
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setMessage(ApplicationConstant.SUCCESS_MSG);
		responseStatus.setStatusCode(ApplicationConstant.API_SUCCESS);
		responseStatus.setErrorTypeCode(0);
		responseStatus.setErrorType(null);

		SystemResponse response = new SystemResponse();
		response.setResponseStatus(responseStatus);
		response.setResponseData(responseData);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public static ResponseEntity<Object> getErrorResponse(ErrorDetails errorDetails, HttpStatusCode responseCode) {
		//LOGGER.info("getErrorResponse(ErrorDetails errorDetails)");
		try {
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setMessage(errorDetails.getMessage());
			responseStatus.setStatusCode(errorDetails.getErrorCode());
			responseStatus.setErrorTypeCode(errorDetails.getErrorTypeCode());
			responseStatus.setErrorType(errorDetails.getErrorType());

			SystemResponse response = new SystemResponse();
			response.setResponseStatus(responseStatus);

			return new ResponseEntity<>(response, responseCode);
		} catch (Exception ex) {
			return new ResponseEntity<>("Error while parsing error response " + errorDetails.toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
