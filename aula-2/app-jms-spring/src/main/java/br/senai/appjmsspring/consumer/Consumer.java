package br.senai.appjmsspring.consumer;

import br.senai.appjmsspring.entity.MessageEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Consumer {

    @JmsListener(destination = "jsm-spring-app")
    public void messageListner(MessageEntity message){
        //seu codigo
        System.out.println("Mensagem recebida:"+message.toString());
    }
}
