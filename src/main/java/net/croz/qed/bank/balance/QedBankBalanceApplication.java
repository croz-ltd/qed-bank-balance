package net.croz.qed.bank.balance;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QedBankBalanceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(QedBankBalanceApplication.class, args);
    }

    @Bean
    public static JaegerTracer getTracer(@Value("${spring.application.name}") final String applicationName) {
        final Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
        final Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(Boolean.TRUE);
        return new Configuration(applicationName).withSampler(samplerConfig).withReporter(reporterConfig).getTracer();
    }

}
