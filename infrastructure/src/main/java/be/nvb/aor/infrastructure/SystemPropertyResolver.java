package be.nvb.aor.infrastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SystemPropertyResolver {
    private Map<String, String> environmentVariables;

    public SystemPropertyResolver() {
        environmentVariables =
                Stream.of(System.getProperties(), System.getenv())
                        .map(Map::entrySet)
                        .flatMap(Collection::stream)
                        .collect(
                                Collectors.toMap(
                                        entry -> ((Map.Entry<String, String>) entry).getKey(),
                                        entry -> ((Map.Entry<String, String>) entry).getValue(),
                                        (v1, v2) -> v1 == null ? v2 : v1
                                )
                        );
        String profile = environmentVariables.get("Profile");

        Properties properties = new Properties();

        try {
            properties.load(getClass().getResourceAsStream("/aws/"+profile+".properties"));
        } catch (Throwable e) {
            System.err.println("Error reading properties file");
        }

        environmentVariables.putAll(new HashMap(properties));
    }

    public String getEnvironmentVariable(String name) {
        return getEnvironmentVariable(name, false);
    }

    public String getEnvironmentVariable(String name, boolean required) {
        if (environmentVariables.containsKey(name)) {
            return environmentVariables.get(name);
        } else if (required) {
            throw new RuntimeException(String.format("Enironment variable %s could not be found.", name));
        }

        return null;
    }
}
