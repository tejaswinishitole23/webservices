package in.rk.learn.web.java.springbootrest.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class CachedBodyServletInputStream extends ServletInputStream {
	
	private InputStream cachedBodyInputStream;
	
	public CachedBodyServletInputStream(byte[] cachedBody) {
		this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
	}

	@Override
	public int read() throws IOException {
		return cachedBodyInputStream.read();
	}

//  	This method indicates whether InputStream has more data to read or not. It returns true when zero bytes available to read:
	@Override
	public boolean isFinished() {
		try {
			return cachedBodyInputStream.available() == 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * This method indicates whether InputStream is ready for reading or not.
	 * 
	 * Since we've already copied InputStream in a byte array, we'll return true to
	 * indicate that it's always available:
	 */
	@Override
	public boolean isReady() {
	    return true;
	}

	@Override
	public void setReadListener(ReadListener listener) {
		// TODO Auto-generated method stub

	}
}