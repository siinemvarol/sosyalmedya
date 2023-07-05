package com.sinem.hastanerandevu.exceptions;

import lombok.Getter;

/**
 *  Bu kisimda uygulamaniz icinde olusabilecek tum hatalari kapsayan bir kapsayiciya
 *  ihtiyaciniz var, yani hata tiplerinin listesini iceren bir Enum sinifi
 *  olusturabilirsiniz.
 *  Ayrica, uygulamamiz RestApi kurgusunda oldugu icin bu hatalarin ResponseBody
 *  seklinde bir entity olarak donmek dogru olacaktir. Bu nedenle bir ReturnType
 *  Response Entity olusturmak dogru yaklasimdir.
 *
 */

@Getter
public class HastaneRandevuException extends RuntimeException {

    private final ErrorType errorType;
    public HastaneRandevuException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public HastaneRandevuException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
