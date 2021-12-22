package com.goyav.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import com.goyav.model.configuration.Server;
import com.goyav.model.configuration.ftpserver;

public class FtpClient {
	 
	private ftpserver ftp ;
	private String host;
    private int port;
    private String username;
    private String password;
    private FTPClient ftpclient;
    private String ftpRemoteDirectory= new String(); 
    private String protocol = new String();
 
    // constructor
   	
   public FtpClient(Server server) {
	// TODO Auto-generated constructor stub
    ftp = server.getFtpserver();
    host = new String(ftp.getHost());
    username = new String(ftp.getUser());
    password = new String(ftp.getPassword());
    port = Integer.parseInt(ftp.getPort());
    protocol = new String(ftp.getProtocol());
    ftpRemoteDirectory= new String(ftp.getRemotedirectory()); 		
   	}
    public void open() throws IOException {
        ftpclient = new FTPClient();
 
        ftpclient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
 
        ftpclient.connect(host,port);
        int reply = ftpclient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpclient.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
        ftpclient.login(username, password);
        ftpclient.enterRemotePassiveMode();
        ftpclient.list();
    }
    public void upload(String source, String destination) throws IOException  {
   //     ftpclient.login(username, password);
        ftpclient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftpclient.changeWorkingDirectory(ftpRemoteDirectory);
        InputStream local = new FileInputStream(source);
        ftpclient.storeUniqueFile(local);
    }
    
    public void close() throws IOException {
        ftpclient.disconnect();
    }
    public void uploadFile(String source, String destination) throws IOException {
        try {
        	ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
        	 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(source);

            InputStream inputStream = new FileInputStream("C:/tmp/SofaremStock.xlsx");
            System.out.println("Start uploading first file");
            boolean done = ftpclient.storeFile(destination, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
            
            /*-------------------
        	File LocalFile = new File(source);
        	inputStream = new FileInputStream(LocalFile);
            System.out.println("Start uploading  file");
            OutputStream outputStream = ftpclient.storeFileStream(destination);
            byte[] bytesIn = new byte[4096];
            int read = 0;
 
            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();
 
            boolean completed = ftpclient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }
            -------------------*/
            
            
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpclient.isConnected()) {
                    ftpclient.logout();
                    ftpclient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}