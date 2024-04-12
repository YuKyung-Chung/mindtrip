package com.a303.consultms.global.config.kafka;

import java.util.UUID;

public class KafkaChatConstants {
    private static String name = UUID.randomUUID().toString(); // group id을 식별하기 위함
    public static final String GROUP_ID = name;
}
