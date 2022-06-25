package br.senai.appjmsspringartemis.entity;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageQueueEntity implements Serializable {
    static final long serialVersionUID = 4091898819160202982L;

    private UUID id;
    private String titulo;
    private String mensagem;

    @Override
    public String toString(){
        return "Message:id="+id+",titulo="+titulo+",mensagem"+mensagem;
    }
}
