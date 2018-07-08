package org.imitationspring.test.v1;

import org.imitationspring.context.ApplicationContext;
import org.imitationspring.context.support.ClassPathXmlApplicationContext;
import org.imitationspring.service.v1.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author liaocx
 */
public class ApplicationContextTest {
    @Test
    public void getBean() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ac.getBean("petStore");
        assertNotNull(petStoreService);
    }
}
