package com.dhruv.tourBookingApplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
@Configuration
@OpenAPIDefinition(
       info=@Info(
               title = "Tour Booking Api",
               version= "1.0",
               description = "This Api will show all the tours and user can book tickets for the specific tour",
               contact = @Contact(
                       name="Dhruv Chourey",
                       email="dhruvchourey@gmail.com"
               )
       ),
        servers =@Server(
                url="http://localhost:8418/TourBookingApp",
                description = "This application is deployed on following url"
        ),
      security = @SecurityRequirement(name = "Bearer Authentication")
)
@SecurityScheme(
  name="Bearer Authentication",
  type= SecuritySchemeType.HTTP,
  scheme = "bearer",
  bearerFormat = "JWT",
  description = "Paste your JWT token here",
  in =SecuritySchemeIn.HEADER
)
public class SwaggerConfig {

}
