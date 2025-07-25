package com.zkrypto.zkMatch.domain.corporation.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CorporationCreationCommand {
    private String corporationType;
    private String corporationRegisterNumber;
    private String corporationName;
    private String ceoName;
    private String corporationAddress;
    private String loginId;
    private String password;
}
