package net.croz.qed.bank.balance;

import io.jaegertracing.internal.JaegerTracer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QedBankBalanceApplication.class)
public class QedBankBalanceApplicationTest {

    @Autowired
    private JaegerTracer jaegerTracer;

    @Value("${spring.application.name}")
    private String springApplicationName;

    @Test
    public void verifyJaegerTracerExist() {
        Assert.assertNotNull("Jaeger Tracer should be defined", jaegerTracer);
    }

    @Test
    public void verifyJaegerTracerConfiguredCorrectly() {
        Assert.assertNotNull("Jaeger Tracer should be defined", jaegerTracer);
        Assert.assertEquals(jaegerTracer.getServiceName(), springApplicationName);
    }

}
