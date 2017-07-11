package sender;

import java.net.DatagramPacket;  
import java.net.DatagramSocket;  
import java.net.InetAddress;  
public class Sender {  
    /*����ip*/  
    public static String localIP = "127.0.0.1";  
    
    //public static String serverIP = "127.0.0.1";
    public static String serverIP = "101.200.75.182";  //������Ĭ��Ϊ��������������ʱ��Ҫ���е�ַӳ��
    /*������Ϣ*/  
    public static String err_msg = "";  
     /*Ĭ�Ϸ��Ͷ˿�*/  
    public static int ServerPort = 10000;  
    /*Ĭ������˿�*/  
    public static int chatPort;
    public static DatagramSocket chatSoc;
    public static String uname;

    /** 
     * @param msgType ��Ϣ��� 
     * @param uname 
     * @param serverIP 
     * @param SeverPort 
     * @return �����Ƿ�ɹ� 
     */  
    public static boolean sendUDPMsg(int msgType,String uname,String serverIP,int SeverPort,String messae,String tar_uname){  
        try  
        {  
            /*�������еõ�Ҫ���͵����ݣ�ʹ��UTF-8���뽫��Ϣת��Ϊ�ֽ�*/  
            byte[] msg = (msgType+"*"+uname+"*"+messae+"*"+tar_uname).getBytes("UTF-8");  
            /*�õ�������internet��ַ*/  
            InetAddress address = InetAddress.getByName(serverIP);  
  
            /*�����ݺ͵�ַ��ʼ��һ�����ݱ����飨���ݰ���*/  
            DatagramPacket packet = new DatagramPacket(msg, msg.length, address,  
            		SeverPort);  
  
            //String recStr = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            /*����һ��Ĭ�ϵ��׽��֣�ͨ�����׽��ַ������ݰ�*/  
           chatSoc.send(packet);  
  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            err_msg = "ϵͳ�����쳣��";  
            return false;  
        }  
        return true;  
    }  
}  