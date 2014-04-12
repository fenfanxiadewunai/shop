//package com.huang.spring.test;
//
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.beanutils.ConvertUtils;
//import org.apache.commons.lang.StringUtils;
////import org.dom4j.Document;
////import org.dom4j.Element;
////import org.dom4j.XPath;
////import org.dom4j.io.SAXReader;
//
//@SuppressWarnings("unchecked")
//public class MyApplicationContext {
//
//	
//    private List<BeanDefinition> beanDefines = new ArrayList<BeanDefinition>();
//    private Map<String, Object>  sigletons   = new HashMap<String, Object>();
//    
//    public Object getBean(String beanName) {
//        return this.sigletons.get(beanName);
//    }
//    
//    public MyApplicationContext(String filename){
//        this.readXML(filename);
//        this.instanceBeans();
//        this.injectObject();
//        this.annotationInject();
//    }
//    
//    private void readXML(String filename) {
//        SAXReader saxReader = new SAXReader();
//        Document document = null;
//        try {
//            URL xmlpath = this.getClass().getClassLoader().getResource(filename);
//            document = saxReader.read(xmlpath);
//            Map<String, String> nsMap = new HashMap<String, String>();
//            nsMap.put("ns", "http://www.springframework.org/schema/beans");
//            XPath xsub = document.createXPath("//ns:beans/ns:bean");
//            xsub.setNamespaceURIs(nsMap);
//			List<Element> beans = xsub.selectNodes(document);
//            for (Element element : beans) {
//                String id = element.attributeValue("id");
//                String clazz = element.attributeValue("class");
//                BeanDefinition beanDefine = new BeanDefinition(id, clazz);
//                XPath propertysub = element.createXPath("ns:property");
//                propertysub.setNamespaceURIs(nsMap);
//                List<Element> propertys = propertysub.selectNodes(element);
//                for (Element property : propertys) {
//                    String propertyName = property.attributeValue("name");
//                    String propertyRef = property.attributeValue("ref");
//                    String propertyValue = property.attributeValue("value");
//                    PropertyDefinition propertyDefinition = new PropertyDefinition(propertyName, propertyRef,
//                                                                                   propertyValue);
//                    beanDefine.getPropertys().add(propertyDefinition);
//                }
//                beanDefines.add(beanDefine);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    private void instanceBeans() {
//        for (BeanDefinition beanDefinition : beanDefines) {
//            try {
//                if (beanDefinition.getClassName() != null && !"".equals(beanDefinition.getClassName().trim())) {
//                    sigletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    
//    private void injectObject() {
//        for (BeanDefinition beanDefinition : beanDefines) {
//            Object bean = sigletons.get(beanDefinition.getId());
//            if (bean != null) {
//                try {
//                    PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
//                    for (PropertyDefinition propertyDefinition : beanDefinition.getPropertys()) {
//                        for (PropertyDescriptor properdesc : ps) {
//                            if (propertyDefinition.getName().equals(properdesc.getName())) {
//                                Method setter = properdesc.getWriteMethod();
//                                Object value = null;
//                                if (setter != null) {
//                                    if (StringUtils.isNotBlank(propertyDefinition.getRef())) {
//                                        value = sigletons.get(propertyDefinition.getRef());
//                                    } else {
//                                        value = ConvertUtils.convert(propertyDefinition.getValue(),
//                                                                     properdesc.getPropertyType());
//                                    }
//                                    setter.invoke(bean, value);
//                                }
//                                break;
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
//    
//    
//    private void annotationInject() {
//        for (String beanName : sigletons.keySet()) {
//            Object bean = sigletons.get(beanName);
//            if (bean != null) {
//                try {
//                    PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
//                    for(int i = 0 ;i< ps.length;i++){
//                    	System.out.println(ps[i].getName()+":"+ps[i].getPropertyType()+":"+ps[i].getDisplayName());
//                    }
//                    for (PropertyDescriptor properdesc : ps) {
//                        Method setter = properdesc.getWriteMethod();
//                        if (setter != null && setter.isAnnotationPresent(NewResource.class)) {
//                            NewResource resource = setter.getAnnotation(NewResource.class);
//                            Object value = null;
//                            if (StringUtils.isNotBlank(resource.name())) {
//                                value = sigletons.get(resource.name());
//                            } else {
//                                value = sigletons.get(properdesc.getName());
//                                if (value == null) {
//                                    for (String key : sigletons.keySet()) {
//                                        if (properdesc.getPropertyType().isAssignableFrom(sigletons.get(key).getClass())) {
//                                            value = sigletons.get(key);
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//                            setter.setAccessible(true);
//                            setter.invoke(bean, value);
//                        }
//                    }
//                    Field[] fields = bean.getClass().getDeclaredFields();
//                    for (Field field : fields) {
//                        if (field.isAnnotationPresent(NewResource.class)) {
//                            NewResource resource = field.getAnnotation(NewResource.class);
//                            Object value = null;
//                            if (StringUtils.isNotBlank(resource.name())) {
//                                value = sigletons.get(resource.name());
//                            } else {
//                                value = sigletons.get(field.getName());
//                                if (value == null) {
//                                    for (String key : sigletons.keySet()) {
//                                        if (field.getType().isAssignableFrom(sigletons.get(key).getClass())) {
//                                            value = sigletons.get(key);
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//                            field.setAccessible(true);
//                            field.set(bean, value);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
