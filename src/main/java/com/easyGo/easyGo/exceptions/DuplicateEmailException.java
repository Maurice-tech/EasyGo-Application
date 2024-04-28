package com.easyGo.easyGo.exceptions;

public class DuplicateEmailException extends RuntimeException{
   public DuplicateEmailException(String message) {
       super(message);
   }
}
