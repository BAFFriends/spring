package com.inner_medicine.domain.address.service;

import com.inner_medicine.domain.address.dto.Regcode;
import com.inner_medicine.domain.address.dto.RegcodeResponse;

import java.util.List;

public interface AddressQueryService {

    RegcodeResponse getAddresses(String regcode);

    Regcode getSpecificAddress(String regcode);
}
