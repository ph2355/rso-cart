package si.fri.rso.cart.filters;

import com.kumuluz.ee.logs.cdi.Log;
import org.apache.logging.log4j.CloseableThreadContext;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
@Priority(Interceptor.Priority.PLATFORM_BEFORE)
public class LogContextInterceptor {

    @Inject
    AdditionalHeaderData additionalHeaderData;

    @AroundInvoke
    public Object logMethodEntryAndExit(InvocationContext context) throws Exception {

        try(final CloseableThreadContext.Instance ctc = CloseableThreadContext.put("Request-ID", additionalHeaderData.getRequest_id())) {
            Object result = context.proceed();
            return result;
        }
    }
}
