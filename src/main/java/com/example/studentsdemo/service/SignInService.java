package com.example.studentsdemo.service;

import com.example.studentsdemo.dto.SignInForm;

public interface SignInService {
    boolean doAuthenticate (SignInForm form);
}
