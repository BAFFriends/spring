package com.inner_medicine.infra.address.client;

import com.inner_medicine.domain.address.dto.RegcodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "address-client",
        url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app"
)
public interface AddressClient {

    @GetMapping("/v1/regcodes")
    RegcodeResponse getAddress(@RequestParam("regcode_pattern") String recode_pattern);


}
