package br.senai.appjmsspringartemis.producer;

import br.senai.appjmsspringartemis.config.JmsConfig;
import br.senai.appjmsspringartemis.entity.MessageQueueEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class Producer {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;


    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
        MessageQueueEntity mensagem = MessageQueueEntity.builder()
                .id(UUID.randomUUID())
                .titulo("Narnia")
                .mensagem("O leão a feiticeira e o guarda-roupa")
                .build();

//        jmsTemplate.convertAndSend(JmsConfig.FILA1, mensagem);
//        System.out.println("Mensagem Enviada");
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage2() throws JMSException {
        MessageQueueEntity mensagemEnviada = MessageQueueEntity.builder()
                .id(UUID.randomUUID())
                .titulo("Narnia")
                .mensagem("O leão a feiticeira e o guarda-roupa")
                .build();

        Message mensagemRecebida = jmsTemplate.sendAndReceive(
                JmsConfig.FILA2, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        Message message = null;
                        try{
                            message = session.createTextMessage(
                                    objectMapper.writeValueAsString(mensagemEnviada)
                            );
                            message.setStringProperty(
                                    "_type", "br.senai.appjmsspringartemis.entity.MessageQueueEntity"
                            );
                            System.out.println("Enviando Mensagem");
                            return message;
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        assert mensagemRecebida != null;
        System.out.println("Mensagem Retornada:"+mensagemRecebida.getBody(String.class));
    }
}
