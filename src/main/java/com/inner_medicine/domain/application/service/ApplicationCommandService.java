package com.inner_medicine.domain.application.service;

import com.inner_medicine.domain.application.dto.CreateApplicationRequest;

public interface ApplicationCommandService {
    void createApplication(CreateApplicationRequest request);
}
