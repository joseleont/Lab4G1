package pe.pucp.dduu.tel306.lab4g1.Clases.Preguntas;

import java.io.Serializable;

public class Respuesta implements Serializable {

    private int id;
    private String answerText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
