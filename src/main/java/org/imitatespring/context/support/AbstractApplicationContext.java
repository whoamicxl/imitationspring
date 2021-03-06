package org.imitatespring.context.support;

import org.imitatespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import org.imitatespring.beans.factory.config.ConfigurableBeanFactory;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitatespring.context.ApplicationContext;
import org.imitatespring.core.io.Resource;
import org.imitatespring.util.ClassUtils;

/**
 * 抽象类, 模版方法设计模式, 具体实现类只需要重写获取Resource的方法
 * @author liaocx
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
        //Spring中也是这样处理, 没有提供set ClassLoader的入口, 取得是默认ClassLoader
        factory.setBeanClassLoader(getBeanClassLoader());
        registerBeanPostProcessors(factory);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    /**
     * 不同实现类根据不同文件路径获取Resource对象
     * @param path
     * @return
     */
    protected abstract Resource getResourceByPath(String path);

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader != null ? beanClassLoader : ClassUtils.getDefaultClassLoader();
    }

    protected void registerBeanPostProcessors(ConfigurableBeanFactory beanFactory) {

        AutowiredAnnotationProcessor postProcessor = new AutowiredAnnotationProcessor();
        postProcessor.setBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(postProcessor);
    }
}
