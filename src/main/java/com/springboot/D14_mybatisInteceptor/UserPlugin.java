package com.springboot.D14_mybatisInteceptor;

import com.springboot.domain.TestUser;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by hzq on 18/5/11.
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class UserPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        Object parameter = invocation.getArgs()[1];
        if (parameter instanceof TestUser) {
            TestUser bankAccount = (TestUser) parameter;
            if (methodName.equals("update")) {
                String number = bankAccount.getName();
                bankAccount.setName("112233");
            }
        }
        Object returnValue = invocation.proceed();
        if (returnValue instanceof ArrayList<?>) {
            List<?> result = (ArrayList<?>) returnValue;
            for (Object val : result) {
                if (val instanceof TestUser) {
                    TestUser bankAccount = (TestUser) val;
                    bankAccount.setName("112233");
                }
            }
        }
        return returnValue;
    }

    @Override
//    用于某些处理器(Handler)的构建过程
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

        System.err.println("1");
    }
}
