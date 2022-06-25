package br.senai.appjmsspringartemis.consumer;

import br.senai.appjmsspringartemis.config.JmsConfig;
import br.senai.appjmsspringartemis.entity.MessageQueueEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Component
public class Consumer {

    private final JmsTemplate jmsTemplate;

    public Consumer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfig.FILA1)
    public void listen(@Payload MessageQueueEntity messageQueueEntity,
//                       @Header MessageHeaders headers,
                       Message message){
//        System.out.println("Mensagem Recebida");
//        System.out.println(messageQueueEntity);

//        throw new RuntimeException("ex");
    }

    @JmsListener(destination = JmsConfig.FILA2)
    public void listen2(@Payload MessageQueueEntity messageQueueEntity,
                       Message message) throws JMSException {
        MessageQueueEntity mensagemQueue = MessageQueueEntity.builder()
                .id(UUID.randomUUID())
                .titulo("Outro Livro")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), mensagemQueue);
    }
}
