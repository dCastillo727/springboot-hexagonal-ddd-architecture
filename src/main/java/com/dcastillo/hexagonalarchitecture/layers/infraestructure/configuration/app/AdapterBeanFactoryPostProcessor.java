package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration.app;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DrivenAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DriverAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.annotations.port.DrivenPort;
import com.dcastillo.hexagonalarchitecture.common.utils.annotations.port.DriverPort;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class AdapterBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            try {
                Class<?> beanClass = beanFactory.getType(beanName);

                if (beanClass == null) continue;

                for (Class<?> iface : beanClass.getInterfaces()) {
                    if (iface.isAnnotationPresent(DrivenPort.class)) {
                        if (!beanClass.isAnnotationPresent(DrivenAdapter.class)) {
                            ((DefaultListableBeanFactory) beanFactory).removeBeanDefinition(beanName);
                        }
                        break;
                    }
                    if (iface.isAnnotationPresent(DriverPort.class)) {
                        if (!beanClass.isAnnotationPresent(DriverAdapter.class)) {
                            ((DefaultListableBeanFactory) beanFactory).removeBeanDefinition(beanName);
                        }
                        break;
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
