package distributed_transactions.seata.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ���ע�⣬��ʾ�����ֲ�ʽ����
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalTransaction {

    // ע���Ƿ��ǵ�һ��������Ϊ��������
    boolean isFirstServer() default false;
}
