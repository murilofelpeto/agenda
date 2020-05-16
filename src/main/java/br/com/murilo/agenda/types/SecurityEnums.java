package br.com.murilo.agenda.types;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SecurityEnums {

    EXPIRATION_TIME("3600000"),
    SECRET("SecretKeyToGenJWTs"),
    TOKEN_PREFIX("Bearer "),
    HEADER("Authorization"),
    REGISTER_URI("/auth/register");

    private String securityValue;
    private static Map<String, SecurityEnums> bySecurity = new HashMap<>();

    SecurityEnums(final String securityValue) {
        this.securityValue = securityValue;
    }

    static {
        bySecurity.putAll(
                Stream.of(
                        values())
                        .collect(
                                Collectors.toMap(
                                        SecurityEnums::getSecurityValue,
                                        Function.identity())));
    }

    public String getSecurityValue() {
        return securityValue;
    }

    public static Map<String, SecurityEnums> getBySecurity() {
        return bySecurity;
    }
}
