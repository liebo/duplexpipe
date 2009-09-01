package pipe;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>
 * <B>PipeIO: </B>�ܵ����������������
 * </p>
 * <p>
 * ͨ���ƶ��ܵ��������������ʵ�ֽ���ͨ�š�<br>
 * ��Ϊ�������Ӧ���첽�ģ���˲��ö��̡߳�
 * </p>
 * 
 * @author redraiment
 * @version 0.1.0
 * @category Thread
 */
public class PipeIO extends Thread
{
	private Verboser v = Verboser.getVerboser ();
	private InputStream in = null;
	private OutputStream out = null;

	/** ��Ҫ�ƶ��ܵ������������������ */
	public PipeIO ( InputStream in, OutputStream out )
	{
		this.in = in;
		this.out = out;
	}

	/** ����������ȡ���ݣ�������д��������� */
	public void run ()
	{
		if ( in == null || out == null )
			return;

		try
		{
			byte[] buffer = new byte[1024];
			int len;

			// ���ģ���д����
			while ( ( len = in.read ( buffer ) ) != -1 )
			{
				out.write ( buffer, 0, len );
				out.flush ();
			}
		}
		catch ( IOException e )
		{
			v.holler ( "Maybe connect is closed." );
		}

		try
		{
			// ������ζ��ر�IO stream��
			if ( in != null )
			{
				in.close ();
			}
			if ( out != null )
			{
				out.close ();
			}
		}
		catch ( IOException e )
		{
			v.holler ( "I can't belive it!" );
			v.holler ( "IO stream can not close?" );
		}
	}
}
