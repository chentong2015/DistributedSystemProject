package distributed_transactions.seata.template.aspect;

import distributed_transactions.seata.template.annotation.GlobalTransaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class GlobalTransactionAspect implements Ordered {

    // ���ע���һ������
    @Around("@annotation(distributed_transactions.seata.template.annotation.GlobalTransaction)")
    public void invoke(ProceedingJoinPoint point) throws Throwable {
        // Before
        // �õ����淽�������ע��ע�⣬ȷ���ֲ�ʽ����Ŀ���
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        GlobalTransaction globalTransaction = method.getAnnotation(GlobalTransaction.class);
        if (globalTransaction.isFirstServer()) {
            // ��Ҫȫ������Ĺ�����TM
        }

        // ִ��Spring�������߼�
        point.proceed();

        // After
    }

    // ������������ȼ�: ����Spring��@Transactionalע������ȼ�
    @Override
    public int getOrder() {
        return 10000;
    }
}
