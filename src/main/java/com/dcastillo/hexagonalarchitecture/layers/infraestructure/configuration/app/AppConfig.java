package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.dcastillo.hexagonalarchitecture",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.CUSTOM,
                        classes = {
                                PortAndAdapterFilter.class
                        }
                )
        }
)
public class AppConfig {
}
