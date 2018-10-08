package com.github.tonydeng.demo.rpc.gateway.config;

import com.github.tonydeng.demo.rpc.gateway.BaseTest;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class JerseyConfigTest extends BaseTest {
    @Resource
    private JerseyConfig jerseyConfig;

    private static List<Class> annotationClasses;

    @BeforeAll
    static void setUp() {
        annotationClasses = Lists.newArrayList(
                Provider.class,
                Path.class
        );
    }

    @Test
    void testIsNotNull() {
        assertNotNull(jerseyConfig);
    }

    @Test
    void testFilterProviderClass() {
        Set<Class<?>> classes = jerseyConfig.getClasses();
        assertNotNull(classes);

        assertTrue(CollectionUtils.containsAny(classes, FastJsonResolver.class));
    }


    @Test
    void testFilterProviderAnnotations() {
        Set<Class<?>> classes = jerseyConfig.getClasses();
        Set<Class<?>> annotations = Sets.newHashSet();

        for (Class c : classes) {
            for (Class ac : annotationClasses) {
                Annotation a = c.getAnnotation(ac);
                if (Objects.nonNull(a)) {
                    annotations.add(a.annotationType());
                }
            }
        }

        assertTrue(CollectionUtils.containsAny(annotations, Provider.class));
        assertTrue(CollectionUtils.containsAny(annotations, Path.class));
        assertFalse(CollectionUtils.containsAny(annotations,Resource.class));
    }
}
