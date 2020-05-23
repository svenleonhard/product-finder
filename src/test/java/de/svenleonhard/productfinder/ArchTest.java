package de.svenleonhard.productfinder;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("de.svenleonhard.productfinder");

        noClasses()
            .that()
            .resideInAnyPackage("de.svenleonhard.productfinder.service..")
            .or()
            .resideInAnyPackage("de.svenleonhard.productfinder.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..de.svenleonhard.productfinder.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
