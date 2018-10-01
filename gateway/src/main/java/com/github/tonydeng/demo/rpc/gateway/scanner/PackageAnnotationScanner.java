package com.github.tonydeng.demo.rpc.gateway.scanner;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PackageAnnotationScanner {
    private PackageAnnotationScanner() {
    }

    public static Set<Class<?>> scan(ClassLoader loader, List<String> scanPackages,
                                     List<Class<? extends Annotation>> scanClasses) {
        Set<Class<?>> classes = Sets.newHashSet();
        if (ObjectUtils.allNotNull(loader) && CollectionUtils.isNotEmpty(scanPackages)
                && CollectionUtils.isNotEmpty(scanClasses)) {
            ClassPathScanningCandidateComponentProvider scanner =
                    new ClassPathScanningCandidateComponentProvider(false);

            scanClasses.forEach(c -> scanner.addIncludeFilter(new AnnotationTypeFilter(c)));

            scanPackages.forEach(
                    p -> classes.addAll(
                            scanner.findCandidateComponents(p)
                                    .stream()
                                    .map(bean ->
                                            ClassUtils.resolveClassName(bean.getBeanClassName(), loader))
                                    .collect(Collectors.toList())
                    )
            );
        }
        return classes;
    }
}
