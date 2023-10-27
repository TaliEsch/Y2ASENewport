package com.example.wastemanagement.web.Advice;

import com.example.wastemanagement.web.Form.BlogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FileUploadExceptionAdvice {
    // run when MaxUploadSizeExceededException is thrown
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ModelAndView model = new ModelAndView("error/maxImageSize");

        model.addObject("message", "File too large!");
        return model;
    }
}
