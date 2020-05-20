package unknow.common.data.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class DataStore
	{
	public <T> T get(String key, DataCodec<T> codec) throws IOException
		{
		try (InputStream read=in(key))
			{
			return read==null?null:codec.read(read);
			}
		}

	public <T> void put(String key, T t, DataCodec<T> codec) throws IOException
		{
		try (OutputStream out=out(key))
			{
			codec.write(out, t);
			}
		}

	public void close() throws IOException
		{
		}

	protected abstract InputStream in(String key) throws IOException;

	protected abstract OutputStream out(String key) throws IOException;

	}
