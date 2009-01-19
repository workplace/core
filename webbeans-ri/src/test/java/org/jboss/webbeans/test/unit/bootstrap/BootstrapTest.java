package org.jboss.webbeans.test.unit.bootstrap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.webbeans.manager.Bean;

import org.jboss.webbeans.bean.AbstractBean;
import org.jboss.webbeans.bean.EnterpriseBean;
import org.jboss.webbeans.bean.ProducerMethodBean;
import org.jboss.webbeans.bean.SimpleBean;
import org.jboss.webbeans.test.mock.MockWebBeanDiscovery;
import org.jboss.webbeans.test.unit.AbstractTest;
import org.testng.annotations.Test;

public class BootstrapTest extends AbstractTest
{
   @Test(groups="bootstrap")
   public void testSingleSimpleBean()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(Tuna.class));
      webBeansBootstrap.boot();
      List<Bean<?>> beans = manager.getBeans();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Tuna.class);
   }
   
   @Test(groups="bootstrap")
   public void testSingleEnterpriseBean()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(Hound.class));
      webBeansBootstrap.boot();
      List<Bean<?>> beans = manager.getBeans();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
   }
   
   @Test(groups="bootstrap")
   public void testMultipleSimpleBean()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(Tuna.class, Salmon.class, SeaBass.class, Sole.class));
      webBeansBootstrap.boot();
      List<Bean<?>> beans = manager.getBeans();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Tuna.class);
      assert classes.containsKey(Salmon.class);
      assert classes.containsKey(SeaBass.class);
      assert classes.containsKey(Sole.class);
      
      assert classes.get(Tuna.class) instanceof SimpleBean;
      assert classes.get(Salmon.class) instanceof SimpleBean;
      assert classes.get(SeaBass.class) instanceof SimpleBean;
      assert classes.get(Sole.class) instanceof SimpleBean;
   }
   
   @Test(groups="bootstrap")
   public void testProducerMethodBean()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(TarantulaProducer.class));
      webBeansBootstrap.boot();
      List<Bean<?>> beans = manager.getBeans();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(TarantulaProducer.class);
      assert classes.containsKey(Tarantula.class);
      
      assert classes.get(TarantulaProducer.class) instanceof SimpleBean;
      assert classes.get(Tarantula.class) instanceof ProducerMethodBean;
   }
   
   @Test(groups="bootstrap")
   public void testMultipleEnterpriseBean()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(Hound.class, Elephant.class, Panther.class, Tiger.class));
      webBeansBootstrap.boot();
      List<Bean<?>> beans = manager.getBeans();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
      assert classes.containsKey(Elephant.class);
      assert classes.containsKey(Panther.class);
      assert classes.containsKey(Tiger.class);
      
      assert classes.get(Hound.class) instanceof EnterpriseBean;
      assert classes.get(Elephant.class) instanceof EnterpriseBean;
      assert classes.get(Panther.class) instanceof EnterpriseBean;
      assert classes.get(Tiger.class) instanceof EnterpriseBean;
   }
   
   @Test(groups="bootstrap")
   public void testMultipleEnterpriseAndSimpleBean()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(Hound.class, Elephant.class, Panther.class, Tiger.class, Tuna.class, Salmon.class, SeaBass.class, Sole.class));
      webBeansBootstrap.boot();
      List<Bean<?>> beans = manager.getBeans();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
      assert classes.containsKey(Elephant.class);
      assert classes.containsKey(Panther.class);
      assert classes.containsKey(Tiger.class);
      assert classes.containsKey(Tuna.class);
      assert classes.containsKey(Salmon.class);
      assert classes.containsKey(SeaBass.class);
      assert classes.containsKey(Sole.class);
      
      assert classes.get(Hound.class) instanceof EnterpriseBean;
      assert classes.get(Elephant.class) instanceof EnterpriseBean;
      assert classes.get(Panther.class) instanceof EnterpriseBean;
      assert classes.get(Tiger.class) instanceof EnterpriseBean;
      assert classes.get(Tuna.class) instanceof SimpleBean;
      assert classes.get(Salmon.class) instanceof SimpleBean;
      assert classes.get(SeaBass.class) instanceof SimpleBean;
      assert classes.get(Sole.class) instanceof SimpleBean;
   }
   
   @Test(groups="bootstrap")
   public void testRegisterProducerMethodBean()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(TarantulaProducer.class));
      webBeansBootstrap.boot();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : manager.getBeans())
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(TarantulaProducer.class);
      assert classes.containsKey(Tarantula.class);
      
      
      assert classes.get(TarantulaProducer.class) instanceof SimpleBean;
      assert classes.get(Tarantula.class) instanceof ProducerMethodBean;
   }
   
   @Test(groups="bootstrap")
   public void testRegisterMultipleEnterpriseAndSimpleBean()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(Hound.class, Elephant.class, Panther.class, Tiger.class, Tuna.class, Salmon.class, SeaBass.class, Sole.class));
      webBeansBootstrap.boot();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : manager.getBeans())
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
      assert classes.containsKey(Elephant.class);
      assert classes.containsKey(Panther.class);
      assert classes.containsKey(Tiger.class);
      assert classes.containsKey(Tuna.class);
      assert classes.containsKey(Salmon.class);
      assert classes.containsKey(SeaBass.class);
      assert classes.containsKey(Sole.class);
      
      assert classes.get(Hound.class) instanceof EnterpriseBean;
      assert classes.get(Elephant.class) instanceof EnterpriseBean;
      assert classes.get(Panther.class) instanceof EnterpriseBean;
      assert classes.get(Tiger.class) instanceof EnterpriseBean;
      assert classes.get(Tuna.class) instanceof SimpleBean;
      assert classes.get(Salmon.class) instanceof SimpleBean;
      assert classes.get(SeaBass.class) instanceof SimpleBean;
      assert classes.get(Sole.class) instanceof SimpleBean;
   }
   
   @Test(groups="bootstrap", expectedExceptions=IllegalStateException.class)
   public void testDiscoverFails()
   {
      webBeansBootstrap.setWebBeanDiscovery(null);
      webBeansBootstrap.boot();
   }
   
   @Test(groups="bootstrap")
   public void testDiscover()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(Hound.class, Elephant.class, Panther.class, Tiger.class, Tuna.class, Salmon.class, SeaBass.class, Sole.class));
      webBeansBootstrap.boot();
      
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : manager.getBeans())
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
      assert classes.containsKey(Elephant.class);
      assert classes.containsKey(Panther.class);
      assert classes.containsKey(Tiger.class);
      assert classes.containsKey(Tuna.class);
      assert classes.containsKey(Salmon.class);
      assert classes.containsKey(SeaBass.class);
      assert classes.containsKey(Sole.class);
      
      assert classes.get(Hound.class) instanceof EnterpriseBean;
      assert classes.get(Elephant.class) instanceof EnterpriseBean;
      assert classes.get(Panther.class) instanceof EnterpriseBean;
      assert classes.get(Tiger.class) instanceof EnterpriseBean;
      assert classes.get(Tuna.class) instanceof SimpleBean;
      assert classes.get(Salmon.class) instanceof SimpleBean;
      assert classes.get(SeaBass.class) instanceof SimpleBean;
      assert classes.get(Sole.class) instanceof SimpleBean;
   }
   
   @Test(groups="bootstrap")
   public void testInitializedEvent()
   {
      assert !InitializedObserver.observered;
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(InitializedObserver.class));
      webBeansBootstrap.boot();
      
      assert InitializedObserver.observered;
   }
   
   @Test(groups="bootstrap")
   public void testRequestContextActiveDuringInitializtionEvent()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(InitializedObserverWhichUsesRequestContext.class, Tuna.class));
      webBeansBootstrap.boot();
   }
   
   @Test(groups={"bootstrap", "broken"})
   public void testApplicationContextActiveDuringInitializtionEvent()
   {
      webBeansBootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(InitializedObserverWhichUsesApplicationContext.class, LadybirdSpider.class));
      webBeansBootstrap.boot();
   }
   
}