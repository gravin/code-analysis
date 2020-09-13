package com.codeanalysis.springboot.annotation;

import com.codeanalysis.springboot.test.FirstClass;
import com.codeanalysis.springboot.test.SecondClass;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Gavin
 * @date 2020/9/13
 */
public class GpImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{FirstClass.class.getName(), SecondClass.class.getName()};
    }
}
