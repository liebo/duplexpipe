package pipe;

/**
 * <p>
 * <B>Verboser: </B>�������� -v ѡ��ʱ������ stderr �����ϸ����ʾ��
 * </p>
 * <p>
 * ����Ϊ�����࣬ͨ�� getVerboser() �����ʵ����
 * </p>
 * 
 * @author redraiment
 * @version 0.1.0
 * @category Logger
 */
public class Verboser
{
	private static Verboser v = new Verboser ();
	private boolean verbose = false;

	/** ������Ĺ��췽��Ϊ˽�С� */
	private Verboser ()
	{
	}

	/** �����ʵ���� */
	public static Verboser getVerboser ()
	{
		return v;
	}

	/** �Ƿ������ʾ��Ϣ�� */
	public boolean isVerbose ()
	{
		return verbose;
	}

	/** �����Ƿ������ʾ��Ϣ�� */
	public void setVerbose ( boolean verbose )
	{
		this.verbose = verbose;
	}

	/** ����������ʾ��Ϣ�� */
	public void holler ( String msg )
	{
		if ( verbose )
		{
			System.err.println ( msg );
		}
	}

	/** ����������ʾ���˳��� */
	public void bail ( String msg )
	{
		holler ( msg );
		System.exit ( 1 );
	}
}
