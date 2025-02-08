package com.mx.development.loadresourceasstring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = LoadResourceConfig.class)
public class LoadResourceAsStringIntegrationTest {

    private static final String EXPECTED_RESOURCE_VALUE = "This is a resource text file. This file will be loaded as a " + "resource and use its contents as a string.";

    @Value("#{T(com.mx.development.loadresourceasstring.ResourceReader).readFileToString('classpath:resource.txt')}")
    private String resourceStringUsingSpel;

    @Autowired
    @Qualifier("resourceString")
    private String resourceString;

    @Autowired
    private ResourceLoader resourceLoader;

    public LoadResourceAsStringIntegrationTest() {
    }

    public static LoadResourceAsStringIntegrationTest createLoadResourceAsStringIntegrationTest() {
        return new LoadResourceAsStringIntegrationTest();
    }

    @Test
    public void givenUsingResourceLoadAndFileCopyUtils_whenConvertingAResourceToAString_thenCorrect() {
        Resource resource = resourceLoader.getResource("classpath:resource.txt");
        assertEquals(EXPECTED_RESOURCE_VALUE, ResourceReader.asString(resource));
        String x = ResourceReader.asString(resource);
        System.out.println("x: " + x);
    }

    @Test
    public void givenUsingResourceStringBean_whenConvertingAResourceToAString_thenCorrect() {
        assertEquals(EXPECTED_RESOURCE_VALUE, resourceString);
        System.out.println("resourceString: " +resourceString);
    }

    @Test
    public void givenUsingSpel_whenConvertingAResourceToAString_thenCorrect() {
        assertEquals(EXPECTED_RESOURCE_VALUE, resourceStringUsingSpel);
        System.out.println("resourceStringUsingSpel: " +resourceStringUsingSpel);
    }




}
