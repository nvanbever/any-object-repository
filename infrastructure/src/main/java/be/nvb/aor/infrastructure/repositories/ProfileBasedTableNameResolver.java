package be.nvb.aor.infrastructure.repositories;

import be.nvb.aor.infrastructure.SystemPropertyResolver;

public class ProfileBasedTableNameResolver extends SystemPropertyResolver {

    public static final String PROFILE = "Profile";

    private String profile;

    public ProfileBasedTableNameResolver() {
        profile = getEnvironmentVariable(PROFILE);
    }

    public String getTableName(Class<?> clazz) {
        return clazz.getSimpleName() + "-" + profile;
    }
}
