package eu.solidcraft.starter.aop;

import eu.solidcraft.starter.domain.loan.LoanEntity;
import eu.solidcraft.starter.domain.some.SomeEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Profile({"starter.test", "starter.development"})
class SomeAspect {
    Map<String, List<LoanEntity>> cache = new HashMap<>();

    @Around("eu.solidcraft.starter.aop.SomeAspect.getByUsernameCall()")
    public List<LoanEntity> addToName(ProceedingJoinPoint joinPoint) throws Throwable {
        String username = (String) joinPoint.getArgs()[0];

        if (cache.containsKey(username)) {
            return cache.get(username);
        }

        List<LoanEntity> list = (List<LoanEntity>) joinPoint.proceed();
        list.add(new LoanEntity("test", new BigDecimal(1337), new BigDecimal(23), new Date(), 3));

        cache.put(username, list);

        return list;
    }

    @Pointcut("execution(* eu.solidcraft.starter.domain.loan.LoanEntityRepository.getByUsername(..))")
    public void getByUsernameCall() {}
}
