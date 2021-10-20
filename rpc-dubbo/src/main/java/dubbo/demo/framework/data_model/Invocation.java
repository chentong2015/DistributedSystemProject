package dubbo.demo.framework.data_model;

import java.io.Serializable;

// "����������"���͵�"�����ṩ��"������ģ��
// TODO: ����ʵ��Serializable��ʹ�ø����͵�ʵ���ܹ������л����������紫��
public class Invocation implements Serializable {

    private String interfaceName;
    private String methodName;
    private Class[] paramTypes; // ���������б�
    private Object[] paramValues; // ����ֵ�б�

    public Invocation() {
    }

    public Invocation(String interfaceName) {
        this(interfaceName, null, null, null);
    }

    public Invocation(String interfaceName, String methodName, Class[] paramTypes, Object[] params) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.paramValues = params;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParamValues() {
        return paramValues;
    }

    public void setParamValues(Object[] params) {
        this.paramValues = params;
    }
}
