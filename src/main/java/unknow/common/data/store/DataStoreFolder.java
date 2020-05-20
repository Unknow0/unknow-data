package unknow.common.data.store;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class DataStoreFolder extends DataStore
	{
	private Path path;

	public DataStoreFolder(Path path)
		{
		this.path=path;
		}

	@Override
	protected InputStream in(String key) throws IOException
		{
		return new FileInputStream(path.resolve(key.toString()).toFile());
		}

	@Override
	protected OutputStream out(String key) throws IOException
		{
		return new FileOutputStream(path.resolve(key.toString()).toFile());
		}
	}
