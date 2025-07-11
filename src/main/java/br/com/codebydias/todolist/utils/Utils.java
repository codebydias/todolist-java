package br.com.codebydias.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


public class Utils {
    // Revisar ainda n entendi mt bem

    //Pegar tudo que for null para atribuir ao getNullProperty
    
    // OQ ENTENDI!: ele considera oq manda mo body e oq for null mantem o dado antigo faz um put parcial porem existe patch para esse caso
    
    public static void copyNoNullProperties(Object source, Object target){
    BeanUtils.copyProperties(source, target, getNullProperty(source));
}

    public static String[] getNullProperty(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
