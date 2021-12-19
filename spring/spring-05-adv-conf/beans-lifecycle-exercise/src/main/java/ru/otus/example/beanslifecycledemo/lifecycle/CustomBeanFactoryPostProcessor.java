package ru.otus.example.beanslifecycledemo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        if (beanFactory.containsBean("customLifeCycleBean")) {
            System.out.println("Шаг #1: BeanFactoryPostProcessor.postProcessBeanFactory\n");
        }

/*
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition d = beanFactory.getBeanDefinition(beanName);

            if (GirlfiendPhoneNumber.class.getName().equalsIgnoreCase(d.getBeanClassName())) {
                d.setBeanClassName(FriendPhoneNumber.class.getName());
                ((ScannedGenericBeanDefinition) d).addMetadataAttribute(new BeanMetadataAttribute("className", FriendPhoneNumber.class.getName()));
                d.setAutowireCandidate(true);
            }
        }
*/
    }
}
