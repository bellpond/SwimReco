package com.swimreco.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UnitTestUtils {
    public static String entity2JsonText(Object entity) throws Exception {
        return new ObjectMapper().writeValueAsString(entity);
    }
}
