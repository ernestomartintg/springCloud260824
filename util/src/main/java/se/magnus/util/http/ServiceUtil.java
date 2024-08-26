package se.magnus.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServiceUtil {
    private static final Logger LOG= LoggerFactory.getLogger(ServiceUtil.class);

    private final String port;
    private String serviceAddress=null;

    @Autowired
    private ServiceUtil(@Value("${server.port}") String port){
        this.port=port;
    }

    public String getServiceAddress(){
        if(serviceAddress==null){
            //localhost/192.168.0.1:8080
            serviceAddress=findMyHostName()+"/"+findMyIpAddress()+":"+port;
        }
        return serviceAddress;
    }

    private String findMyIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            return "Unknown IP address";
        }
    }

    private String findMyHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException e){
            return "Unknown HOST name";
        }
    }
}
