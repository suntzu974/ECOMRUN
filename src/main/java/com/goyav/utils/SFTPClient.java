package com.goyav.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.goyav.model.configuration.Server;
import com.goyav.model.configuration.ftprav;
import com.goyav.model.configuration.ftpserver;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPClient {
	
	
//    private ftpserver ftp = new Server().getFtpserver();
	private Session session = null;
    private String host = new String();
    private String username = new String();
    private String password = new String();
    private Integer port = null;
    private String ftpRemoteDirectory= new String(); 
    private String protocol = new String();
	
    public SFTPClient(ftpserver ftp) {
		// TODO Auto-generated constructor stub
        host = new String(ftp.getHost());
        username = new String(ftp.getUser());
        password = new String(ftp.getPassword());
        port = Integer.parseInt(ftp.getPort());
        protocol = new String(ftp.getProtocol());
        ftpRemoteDirectory= new String(ftp.getRemotedirectory()); 		
	}
    public SFTPClient(ftprav ftp) {
		// TODO Auto-generated constructor stub
        host = new String(ftp.getHost());
        username = new String(ftp.getUser());
        password = new String(ftp.getPassword());
        port = Integer.parseInt(ftp.getPort());
        protocol = new String(ftp.getProtocol());
        ftpRemoteDirectory= new String(ftp.getRemotedirectory()); 		
	}
	public void connect() throws JSchException {
	
        JSch jsch = new JSch();
        // Uncomment the line below if the FTP server requires certificate
        //jsch.addIdentity("private-key-path);
//        session = jsch.getSession(server);
        // Uncomment the two lines below if the FTP server requires password
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }
    public void upload(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel(protocol);
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.cd(ftpRemoteDirectory);
        sftpChannel.put(source, destination);
        sftpChannel.exit();
    }
	
    public void download(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel(protocol);
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.get(source, destination);
        sftpChannel.exit();
    }
	public List<String> downloadToList(String file2download) throws SftpException, JSchException, IOException {
		// TODO Auto-generated method stub
        Channel channel = session.openChannel(protocol);
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.cd(ftpRemoteDirectory);
        List<String> products = new ArrayList<String>();
        String product;
        BufferedReader reader = new BufferedReader(new InputStreamReader(sftpChannel.get(file2download)));
        StringBuilder out = new StringBuilder();
        while ((product = reader.readLine()) != null) {
            out.append(product);
            products.add(product); // Add Line in the list
        }
        reader.close();
        
        sftpChannel.exit();
		return products;
	}
    public void disconnect() {
    	
        if (session != null) {
            session.disconnect();
        }
    }
}