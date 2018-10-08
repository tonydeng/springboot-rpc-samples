package com.github.tonydeng.demo.rpc.gateway.scanner;

import com.github.tonydeng.demo.rpc.gateway.BaseTest;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class PackageAnnotationScannerTest extends BaseTest {

    private static List<String> paths;

    private static List<Class<? extends Annotation>> scanClasses;

    @BeforeAll
    static void setUp() {
        paths = Lists.newArrayList("com.github.tonydeng.demo.rpc");
        scanClasses = Lists.newArrayList(Component.class, Service.class);
    }

    @Test
    void testScan() {
        Set<Class<?>> classes = PackageAnnotationScanner.scan(this.getClass().getClassLoader(),
                paths, scanClasses);

        assertNotNull(classes);

        assertTrue(CollectionUtils.containsAny(classes, PackageAnnotationScanner.scan(this.getClass().getClassLoader(),
                Lists.newArrayList("com.github.tonydeng.demo.rpc"),
                Lists.newArrayList(Service.class, Component.class))));
    }

    @Test
    void testEmptyPathAndScanClasses() {
        Set<Class<?>> classes = PackageAnnotationScanner.scan(this.getClass().getClassLoader(),
                Lists.newArrayList(), Lists.newArrayList());

        assertEquals(0,classes.size());
        assertTrue(CollectionUtils.isEmpty(classes));
    }
}
