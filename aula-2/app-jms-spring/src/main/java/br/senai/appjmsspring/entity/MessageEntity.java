package br.senai.appjmsspring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity implements Serializable {

    private String titulo;
    private String nota;

    @Override
    public String toString(){
        return "Message{" +
                "titulo = '"+ titulo + '\'' +
                ",nota= '" + nota + '\'' +
                '}';
    }
}
