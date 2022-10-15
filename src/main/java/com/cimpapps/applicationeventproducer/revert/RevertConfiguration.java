package com.cimpapps.applicationeventproducer.revert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RevertConfiguration {

    @Bean
    public RevertContext revertContext() {
        return new RevertContext();
    }

    @Bean
    public RevertIfFailsProxy revertIfFailsAspect(RevertContext revertContext) {
        return new RevertIfFailsProxy(revertContext);
    }
}
