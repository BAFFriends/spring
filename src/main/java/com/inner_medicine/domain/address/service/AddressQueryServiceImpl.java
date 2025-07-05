package com.inner_medicine.domain.address.service;

import com.inner_medicine.domain.address.dto.Regcode;
import com.inner_medicine.domain.address.dto.RegcodeResponse;
import com.inner_medicine.infra.address.client.AddressClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressQueryServiceImpl implements AddressQueryService{

    private final AddressClient addressClient;

    @Override
    public RegcodeResponse getAddresses(String regcode) {
        return addressClient.getAddress(regcode);
    }

    @Override
    public Regcode getSpecificAddress(String regcode) {
        return addressClient.getAddress(regcode).getRegcodes().get(0);
    }
}
