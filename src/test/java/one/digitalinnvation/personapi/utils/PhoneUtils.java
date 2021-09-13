package one.digitalinnvation.personapi.utils;

import one.digitalinnvation.personapi.dto.request.PhoneDTO;
import one.digitalinnvation.personapi.entity.Phone;
import one.digitalinnvation.personapi.enums.PhoneType;

public class PhoneUtils {
    private static final String PHONE_NUMBER = "3299976-4024";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
