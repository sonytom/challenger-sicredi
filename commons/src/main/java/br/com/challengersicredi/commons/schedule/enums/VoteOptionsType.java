package br.com.challengersicredi.commons.schedule.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Objects;
@Getter
@AllArgsConstructor
public enum VoteOptionsType {
    YES("SIM"),
    NO("NÃO");

    private String value;
    public static VoteOptionsType fromValue(String value) throws Exception {
        return EnumSet.allOf(VoteOptionsType.class).stream()
                .filter(Objects::nonNull)
                .filter(voteOptionsType -> voteOptionsType.getValue().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(Exception::new);
    }

}
