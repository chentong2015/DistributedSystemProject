package com.seata.template.aspect;

import com.seata.template.model.GlobalTransaction;
import com.seata.template.model.MyTransaction;
import com.seata.template.model.TransactionType;
import com.seata.template.transactional.GlobalTransactionManager;
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

    // ������������ȼ�: ����Spring��@Transactionalע������ȼ�
    @Override
    public int getOrder() {
        return 10000;
    }

    // ���ע���һ������
    @Around("@annotation(template.model.GlobalTransaction)")
    public void invoke(ProceedingJoinPoint point) {
        // �õ����淽�������ע��ע�⣬ȷ���ֲ�ʽ����Ŀ���
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        GlobalTransaction globalTransaction = method.getAnnotation(GlobalTransaction.class);

        String groupId = "";
        if (globalTransaction.isFirstServer()) {
            groupId = GlobalTransactionManager.createTransactionGroup();
        }
        MyTransaction transaction = GlobalTransactionManager.createMyTransaction(groupId);
        // ִ��Spring�������߼�: �����״����ж�TransactionType(�ύ���߻ع�)
        try {
            point.proceed(); // ִ��Spring����
            transaction.setTransactionType(TransactionType.commit);
        } catch (Throwable e) {
            e.printStackTrace();
            transaction.setTransactionType(TransactionType.rollback);
        }
        // ע�ᵽ�����Э����
        GlobalTransactionManager.registerMyTransaction(transaction);
        // �������û�б�������ȫ�ֵ��ύ
        GlobalTransactionManager.submitGlobalTransaction(groupId);
    }
}
