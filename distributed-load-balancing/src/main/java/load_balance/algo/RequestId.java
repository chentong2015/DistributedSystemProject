package load_balance.algo;

// Nginx, dubbo > RequestId
// �ͻ�������ʱ�������ɵ�id����, 00001,,,
public class RequestId {

    private static int id = 0;

    // ģ������id������
    public static int getRequestId() {
        return id++;
    }
}
