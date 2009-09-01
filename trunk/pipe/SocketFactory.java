package pipe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <p>
 * <B>SocketFactory: </B>Socket ���������Լ������ض˿ڻ�������Զ�̶˿ڡ�
 * </p>
 * <p>
 * ������Ҫ��Ϊ�����λ�ȡ Socket ��ϸ�ڣ���������ı��롣
 * </p>
 * 
 * @author redraiment
 * @version 0.1.0
 * @category Factory
 */
public class SocketFactory
{
	private Verboser v = Verboser.getVerboser ();
	private ServerSocket ss = null;
	private String ip = null;
	private int port;

	/** ͨ���ƶ��˿�����ȡ Socket�� */
	public SocketFactory ( int port )
	{
		try
		{
			ss = new ServerSocket ( port );
		}
		catch ( IOException e )
		{
			ss = null;
		}
	}

	/** ͨ���ƶ�Զ�̵�ַ�Ͷ˿�����ȡ Socket�� */
	public SocketFactory ( String ip, int port )
	{
		this.ip = ip;
		this.port = port;
	}

	/** ��ȡ Socket�� */
	public Socket getSocket ()
	{
		try
		{
			return ss == null ? new Socket ( ip, port ) : ss.accept ();
		}
		catch ( UnknownHostException e )
		{
			v.bail ( "Unknown Host: " + ip );
		}
		catch ( IOException e )
		{
			v.bail ( "Are you sure the remote host open?" );
		}

		return null;
	}
}
