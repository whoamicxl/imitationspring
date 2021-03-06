package org.imitatespring.context.annotation;

import org.imitatespring.beans.factory.annotation.AnnotatedBeanDefinition;
import org.imitatespring.beans.factory.support.GenericBeanDefinition;
import org.imitatespring.core.type.AnnotationMetadata;

public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();
        super.setBeanClassName(metadata.getClassName());
        this.metadata = metadata;
    }

    @Override
    public final AnnotationMetadata getMetadata() {
        return this.metadata;
    }

}
