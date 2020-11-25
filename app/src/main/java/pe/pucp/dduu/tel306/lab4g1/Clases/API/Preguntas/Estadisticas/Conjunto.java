package pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.Estadisticas;

import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.PreguntaYRespuesta;

public class Conjunto extends Cuenta {
    private PreguntaYRespuesta question;
    private Cuenta[] answerstats;

    public PreguntaYRespuesta getQuestion() {
        return question;
    }

    public void setQuestion(PreguntaYRespuesta question) {
        this.question = question;
    }

    public Cuenta[] getAnswerstats() {
        return answerstats;
    }

    public void setAnswerstats(Cuenta[] answerstats) {
        this.answerstats = answerstats;
    }
}
