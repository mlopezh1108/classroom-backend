package net.developz.classroom.backend;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithArchitectureTest {

    @Test
    void verifiesModularStructure() {
        String basePackage = "net.developz.classroom.backend";
        System.out.println("DEBUG: Starting robust discovery for " + basePackage);

        try {
            // Priority 1: Package-string based discovery (most stable across versions)
            ApplicationModules modules = ApplicationModules.of(basePackage);
            System.out.println("DEBUG: Found " + modules.stream().count() + " modules via package string.");
            modules.verify();

        } catch (Exception e) {
            System.err.println("DEBUG: Discovery failed. Diagnostic info:");
            System.err.println(" - BackendApplication location: "
                    + BackendApplication.class.getProtectionDomain().getCodeSource().getLocation());
            throw e;
        }
    }
}
