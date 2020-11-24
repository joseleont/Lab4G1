package pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas;

public class PreguntaYRespuesta {

    private int id;
    private String questionText;
    private String questionDate;
    private Respuesta[] answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public Respuesta[] getAnswers() {
        return answers;
    }

    public void setAnswers(Respuesta[] answers) {
        this.answers = answers;
    }
}
