package unknow.common.data.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class DataStoreZip extends DataStore
	{
	private ZipFile zip;

	public DataStoreZip(Path file) throws ZipException, IOException
		{
		this.zip=new ZipFile(file.toFile());
		}

	@Override
	protected InputStream in(String key) throws IOException
		{
		ZipEntry entry=zip.getEntry(key);
		if(entry==null)
			return null;

		return zip.getInputStream(entry);
		}

	@Override
	protected OutputStream out(String key) throws IOException
		{
		throw new IOException("Unsupported exception");
		}
	}
