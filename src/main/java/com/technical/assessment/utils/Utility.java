package com.technical.assessment.utils;

import com.technical.assessment.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

public class Utility {
    private static final Utility INSTANCE = new Utility();

    private Utility() {
    }


    public static Utility getInstance() {
        return INSTANCE;
    }

    public Object modifyField(Map<String, Object> updates, Object object){
        updates.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(object.getClass(), k);
            if (!k.equals("id")){
                field.setAccessible(true);
                ReflectionUtils.setField(field, object, v);
            }
        });
        return object;
    }

}
