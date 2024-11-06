package example;

import com.google.common.reflect.ClassPath;
import example.framework.request.Helper;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationSearch {

    public static Set<Class<?>> findAllClassesWithAnnotation(Class<?> annotation) throws IOException {
        return ClassPath.from(Helper.class.getClassLoader())
                .getAllClasses()
                .stream()
                .filter(classInfo -> !classInfo.getName().contains("module-info"))
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> clazz.isAnnotationPresent(annotation.asSubclass(java.lang.annotation.Annotation.class)))
                .collect(Collectors.toSet());
    }
}
