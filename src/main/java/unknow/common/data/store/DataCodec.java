package unknow.common.data.store;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public interface DataCodec<T>
	{
	public void write(OutputStream out, T t) throws IOException;

	public T read(InputStream in) throws IOException;

	public static final DataCodec<byte[]> BYTES=new DataCodec<byte[]>()
		{
		@Override
		public void write(OutputStream out, byte[] t) throws IOException
			{
			out.write(t);
			}

		@Override
		public byte[] read(InputStream in) throws IOException
			{
			ByteArrayOutputStream sb=new ByteArrayOutputStream();
			byte[] buf=new byte[4096];
			int l;
			while ((l=in.read(buf))!=-1)
				sb.write(buf, 0, l);
			return sb.toByteArray();
			}
		};
	public static final DataCodec<String> STRING=new DataCodec<String>()
		{
		@Override
		public void write(OutputStream out, String t) throws IOException
			{
			out.write(t.getBytes(StandardCharsets.UTF_8));
			}

		@Override
		public String read(InputStream in) throws IOException
			{
			StringBuilder sb=new StringBuilder();
			try (InputStreamReader irs=new InputStreamReader(in))
				{
				char[] cbuf=new char[4096];
				int l;
				while ((l=irs.read(cbuf))!=-1)
					sb.append(cbuf, 0, l);
				}
			return sb.toString();
			}
		};
	}