package com.zkrypto.zkMatch.domain.corporation.domain.entity;

import com.zkrypto.zkMatch.domain.corporation.application.dto.request.CorporationCreationCommand;
import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
public class Corporation {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID corporationId;

    private String corporationName;
    private String registerNumber;
    private String ceoName;
    private String corporationAddress;

    @OneToOne(mappedBy = "corporation", cascade = CascadeType.PERSIST)
    private Member member;

    private Corporation(String corporationName, String registerNumber, String ceoName, String corporationAddress, Member member) {
        this.corporationName = corporationName;
        this.registerNumber = registerNumber;
        this.ceoName = ceoName;
        this.corporationAddress = corporationAddress;
        this.member = member;
    }

    public static Corporation from(CorporationCreationCommand command, Member member) {
        Corporation corporation = new Corporation(command.getCorporationName(), command.getCorporationRegisterNumber(), command.getCeoName(), command.getCorporationAddress(), member);
        member.setCorporation(corporation);
        return corporation;
    }
}
