package pipe;

import java.io.IOException;
import java.net.Socket;

/**
 * <p>
 * <B>DuplexPipe: </B>˫��ܵ���
 * </p>
 * <p>
 * ��������һ��˫��ܵ�����ͳ�Ĺܵ�ֻ�ܴ�һ�����롢һ�������˫��ܵ���������<br>
 * �ý��� A �������Ϊ���� B �����룬Ҳ���ý��� B �������Ϊ���� A �����롣<br>
 * �����Ϳ�������������ʵ�ֽ�����
 * </p>
 * <p>
 * ��������Ҫ�� TCP ת�����ߡ�����������ض˿ڣ�Ҳ������������Զ�̶˿ڡ�<br>
 * �������ʿ������nc -e�����ʹ�ã� ����ʵ�ֱ��ؽ��̺�����������⹵ͨ��
 * </p>
 * <p>
 * ����ľ���ʹ�÷������Բμ� help()��
 * </p>
 * 
 * @author redraiment
 * @version 0.1.0
 * @category Main
 */
public class DuplexPipe
{
	public static void main ( String[] args )
	{
		SocketFactory[] sf = new SocketFactory[2];
		Socket[] s = new Socket[2];
		PipeIO[] p = new PipeIO[2];
		Verboser v = Verboser.getVerboser ();
		String ip;
		int port;

		int ac = 0;
		for ( int i = 0; i < args.length; i++ )
		{
			if ( args[i].charAt ( 0 ) != '-' )
			{
				help ();
			}

			switch ( args[i].charAt ( 1 ) )
			{
			case 'c':
				ip = args[++i];
				port = Integer.parseInt ( args[++i] );
				sf[ac++] = new SocketFactory ( ip, port );
				break;
			case 'l':
				port = Integer.parseInt ( args[++i] );
				sf[ac++] = new SocketFactory ( port );
				break;
			case 'v':
				v.setVerbose ( true );
				break;
			default :
				help ();
				break;
			}
		}

		while ( true )
		{
			s[0] = sf[0].getSocket ();
			s[1] = sf[1].getSocket ();

			try
			{
				p[0] = new PipeIO ( s[0].getInputStream (), s[1]
						.getOutputStream () );
				p[1] = new PipeIO ( s[1].getInputStream (), s[0]
						.getOutputStream () );
			}
			catch ( IOException e )
			{
				v.holler ( "Can't get IO-stream, why??" );
			}

			p[0].start ();
			p[1].start ();
			try
			{
				p[0].join ();
				p[1].join ();
			}
			catch ( InterruptedException e )
			{
				v.holler ( "Don't ask me!" );
				v.holler ( "I nerver interrupted it!" );
			}

			try
			{
				if ( s[0] != null )
				{
					s[0].close ();
					s[0] = null;
				}
				if ( s[1] != null )
				{
					s[1].close ();
					s[1] = null;
				}
			}
			catch ( IOException e )
			{
				v.holler ( "I can't belive it!" );
				v.holler ( "Socket can not close?" );
			}
		}
	}

	/** ����������������... */
	public static void help ()
	{
		Verboser v = Verboser.getVerboser ();
		v.setVerbose ( true );
		v.holler ( "[v0.1.0 in Java]" );
		v.holler ( "����: redraiment" );
		v.holler ( "���κν��飬��ӭ��ϵ redraiment@gmail.com" );
		v.holler ( "���ͣ�http://blog.csdn.net/redraiment\n" );
		v.holler ( "�÷�: java pipe.DuplexPipe [-vh] model model" );
		v.holler ( "ѡ��:" );
		v.holler ( "\t-v\t\t���һЩ��ʾ��Ϣ" );
		v.holler ( "\t-h\t\t��ʾ�������ĵ�" );
		v.holler ( "ģʽ:" );
		v.holler ( "\t-l port\t\t�������ض˿�" );
		v.holler ( "\t-c host port\t\t����Զ�̶˿�" );
		v.holler ( "ʾ��:" );
		v.holler ( "\tjava pipe.DuplexPipe -c 192.168.1.100 3389 -l 1234" );
		v.bail ( "\t������ 1234 �Ŷ˿��ϵ���Ϣת���� 192.168.1.100 �� 3389�˿�" );
	}
}
