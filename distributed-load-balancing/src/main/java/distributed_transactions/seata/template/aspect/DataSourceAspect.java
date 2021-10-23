package distributed_transactions.seata.template.aspect;

import distributed_transactions.seata.template.connection.MyConnection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
public class DataSourceAspect {

    // �Եײ�DataSource�����棬��Spring�����ݿⴴ������ʱ�����߸�������߼�
    // ��һ���ӿڷ��������棬���иýӿڵ�ʵ���඼�ᱻ�е�
    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection invoke(ProceedingJoinPoint point) throws Throwable {
        // TODO: ����᷵��Spring�����Connectionʵ����
        Connection connection = (Connection) point.proceed();
        // ʹ��ԭʼ�õ���ʵ����Ķ���
        // �����Զ���ʵ�ֵ�Connection��Spring���Ա��õ�������Ŀ���Ȩ
        return new MyConnection(connection);
    }
}
