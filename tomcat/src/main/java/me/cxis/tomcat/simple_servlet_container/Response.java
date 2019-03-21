package me.cxis.tomcat.simple_servlet_container;

import me.cxis.tomcat.simple_web_server.HttpServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Locale;

/**
HTTP Response = Status-Line
(( general-header | response-header | entity-header ) CRLF)
CRLF
[ message-body ]
Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
*/
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream outputStream;
    PrintWriter writer;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(HttpServer.WEB_ROOT + request.getUri());
            File file = new File(url.getFile());
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                int ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    outputStream.write(bytes, 0, ch);
                    ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" +"\r\n" +"<h1>File Not Found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(outputStream, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
