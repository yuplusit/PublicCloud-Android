package com.yuplus.cloudsdk.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * @user longzhen
 * @date 2017/1/3
 * @desc Stream流转换工具类
 */

public class StreamUtils {
    /**
     * 获取输入流的数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] toByteArray(InputStream inStream) throws Exception {
        byte data[] = null;
        ByteArrayOutputStream arrayOutStream = null;

        try {
            if (inStream != null) {
                BufferedInputStream bufferInStream = new BufferedInputStream(inStream);
                arrayOutStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[8192]; //8k=8*1024
                int length = -1;

                while ((length = bufferInStream.read(buffer)) != -1) {
                    arrayOutStream.write(buffer, 0, length);
                }

                arrayOutStream.flush();
                data = arrayOutStream.toByteArray();
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            try {
                if (arrayOutStream != null) arrayOutStream.close();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }

        return data;
    }

    /**
     * 获取输入流的数据(http)
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] toHttpByteArray(InputStream inStream) throws Exception {
        byte data[] = null;
        ByteArrayOutputStream arrayOutStream = null;
        try {
            if (inStream != null) {
                BufferedInputStream bufferInStream = new BufferedInputStream(inStream);
                bufferInStream.mark(2);
                //取前两个字节
                byte[] header = new byte[2];
                int result = bufferInStream.read(header);
                // reset输入流到开始位置
                bufferInStream.reset();
                int headerData = (int) ((header[0] << 8) | header[1] & 0xFF);
                // Gzip 流 的前两个字节是 0x1f8b
                boolean isGZip = (result != -1 && headerData == 0x1f8b);

                if (isGZip) {
                    inStream = new GZIPInputStream(bufferInStream);
                } else {
                    inStream = bufferInStream;
                }

                arrayOutStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[8192]; //8k
                int length = -1;

                while ((length = inStream.read(buffer)) != -1) {
                    arrayOutStream.write(buffer, 0, length);
                }

                arrayOutStream.flush();
                data = arrayOutStream.toByteArray();
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            try {
                if (arrayOutStream != null) arrayOutStream.close();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }

        return data;
    }
}
