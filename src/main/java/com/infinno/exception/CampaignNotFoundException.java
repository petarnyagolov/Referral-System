package com.infinno.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.annotation.RequestScope;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "No such campaign!")
public class CampaignNotFoundException extends RuntimeException{

}
