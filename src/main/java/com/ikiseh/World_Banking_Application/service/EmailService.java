package com.ikiseh.World_Banking_Application.service;

import com.ikiseh.World_Banking_Application.payload.request.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);
}
