/**
 * 
 */
package unknow.common.data.kvstore;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * create a simple store with String key
 * 
 * @author unknow
 */
public class StringIndex {
	protected String[] key;
	protected long[] offset;
	protected RandomAccessFile file;

	private byte[] buf = new byte[4096];

	protected StringIndex() {
	}

	public StringIndex(File file) throws IOException {
		this.file = new RandomAccessFile(file, "r");
		load();
	}

	private void load() throws IOException {
		int len = file.readInt();

		key = new String[len];
		offset = new long[len];

		for (int i = 0; i < len; i++) {
			String k = file.readUTF();
			int o = Arrays.binarySearch(key, 0, i, k);
			if (o >= 0)
				continue;
			o = -o - 1;
			if (o < i) {
				System.arraycopy(key, o, key, o + 1, i - o);
				System.arraycopy(offset, o, offset, o + 1, i - o);
			}
			key[o] = k;
			offset[o] = file.readLong();
		}
		long off = file.getFilePointer();
		for (int i = 0; i < len; i++)
			offset[i] += off;
	}

	public String get(String k) throws IOException {
		int i = Arrays.binarySearch(key, k);
		if (i < 0)
			return null;
		file.seek(offset[i]);
		int len = file.readInt();
		if (buf.length < len)
			buf = new byte[len];
		file.read(buf, 0, len);

		return new String(buf, 0, len, StandardCharsets.UTF_8);
	}

	public static class Creator extends StringIndex implements AutoCloseable {
		private int len = 0;

		private File output;
		private RandomAccessFile file;

		public Creator(File output) throws IOException {
			this.output = output;
			File temp = Files.createTempFile("kvstorecreator", "tmp").toFile();
			temp.deleteOnExit();
			file = new RandomAccessFile(temp, "rw");
			key = new String[4096];
			offset = new long[4096];
		}

		public void add(String k, String data) throws IOException {
			int i = Arrays.binarySearch(key, 0, len, k);
			if (i >= 0) {
				System.err.println("duplicate key skip");
				return;
			}
			i = -i - 1;
			if (i < i) {
				System.arraycopy(key, i, key, i + 1, len - i);
				System.arraycopy(offset, i, offset, i + 1, len - i);
			}
			key[len] = k;
			offset[len] = file.getFilePointer();
			len++;

			byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
			file.writeInt(bytes.length);
			file.write(bytes);
		}

		@Override
		public void close() throws IOException {
			try (RandomAccessFile out = new RandomAccessFile(output, "rw")) {
				out.writeInt(len);
				for (int i = 0; i < len; i++) {
					out.writeUTF(key[i]);
					out.writeLong(offset[i]);
				}
				file.setLength(0);
				byte[] buf = new byte[4096];
				int l;
				while ((l = file.read(buf)) > -1)
					out.write(buf, 0, l);
			}
		}
	}
}
