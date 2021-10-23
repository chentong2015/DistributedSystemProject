package distributed_transactions.seata;

// TCC: �ֲ�ʽ������, ��ҵ���������(��Ҫ�ֶ��޸Ĵ��룬�Զ����ύ����)
// Seata: �ֲ�ʽ������
// 1. ֧��TCC, ������(��Ӱ��ҵ���߼�)
// 2. ����ͨѶ�ײ����Netty��ʵ��
public class BaseSeata {

    // TC: ȫ������Э���� DefaultCoordinator
    // TM: ȫ����������� @GlobalTransactional
    // RM: ��Դ������ DataSourceProxy, ConnectionProxy
    // XID: TransactionPropagationFilter

}
