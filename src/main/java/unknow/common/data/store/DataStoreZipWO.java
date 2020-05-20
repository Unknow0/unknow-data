package unknow.common.data.store;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

public class DataStoreZipWO extends DataStore
	{
	private ZipOutputStream zip;
	private ZipEntry current;

	public DataStoreZipWO(Path file) throws ZipException, IOException
		{
		this.zip=new ZipOutputStream(new FileOutputStream(file.toFile()));
		}

	@Override
	public void close() throws IOException
		{
		zip.close();
		}

	@Override
	protected InputStream in(String key) throws IOException
		{
		throw new IOException("Unsupported exception");
		}

	@Override
	protected OutputStream out(String key) throws IOException
		{
		synchronized (zip)
			{
			current=new ZipEntry(key);
			zip.putNextEntry(current);
			return new ZOS(current);
			}
		}

	private class ZOS extends OutputStream
		{
		private ZipEntry e;

		public ZOS(ZipEntry e)
			{
			this.e=e;
			}

		private void check() throws IOException
			{
			if(e!=current)
				throw new IOException("concurrent");
			}

		@Override
		public void close() throws IOException
			{
			synchronized (zip)
				{
				check();
				zip.closeEntry();
				current=null;
				}
			}

		@Override
		public void flush() throws IOException
			{
			synchronized (zip)
				{
				check();
				zip.flush();
				}
			}

		@Override
		public void write(int b) throws IOException
			{
			synchronized (zip)
				{
				check();
				zip.write(b);
				}
			}

		@Override
		public void write(byte[] b, int off, int len) throws IOException
			{
			synchronized (zip)
				{
				check();
				zip.write(b, off, len);
				}
			}

		@Override
		public void write(byte[] b) throws IOException
			{
			synchronized (zip)
				{
				check();
				zip.write(b);
				}
			}
		}
	}