package milazzodavide.panda.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Panda API",
                description = "Documentazione delle API del progetto",
                version = "1.0"
        ),
        security = {
                @SecurityRequirement(name = "bearerAuth") // Applica la sicurezza a tutte le API globalmente
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Inserisci il token JWT per autenticarti",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}