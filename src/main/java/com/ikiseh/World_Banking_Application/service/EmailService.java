package com.ikiseh.World.Banking.Application.service;

import com.ikiseh.World.Banking.Application.payload.request.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);
}
