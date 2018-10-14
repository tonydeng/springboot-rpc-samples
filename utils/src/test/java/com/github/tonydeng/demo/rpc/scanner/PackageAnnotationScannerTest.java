package com.github.tonydeng.demo.rpc.scanner;

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

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class PackageAnnotationScannerTest  {

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

        assertEquals(classes, PackageAnnotationScanner.scan(this.getClass().getClassLoader(),
                Lists.newArrayList("com.github.tonydeng.demo.rpc"),
                Lists.newArrayList(Service.class, Component.class)));
    }

    @Test
    void testEmptyPathAndScanClasses() {
        Set<Class<?>> classes = PackageAnnotationScanner.scan(this.getClass().getClassLoader(),
                Lists.newArrayList(), Lists.newArrayList());

        assertEquals(0,classes.size());
        assertTrue(CollectionUtils.isEmpty(classes));
    }
}
