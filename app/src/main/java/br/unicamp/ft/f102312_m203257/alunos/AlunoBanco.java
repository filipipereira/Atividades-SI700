package br.unicamp.ft.f102312_m203257.alunos;

public class AlunoBanco {

    private int id;
    private String nome;
    private int tentativaEx;
    private int acerto;
    private int erro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTentativaEx() {
        return tentativaEx;
    }

    public void setTentativaEx(int tentativaEx) {
        this.tentativaEx = tentativaEx;
    }


    public int getAcerto() {
        return acerto;
    }

    public void setAcerto(int acerto) {
        this.acerto = acerto;
    }

    public int getErro() {
        return erro;
    }

    public void setErro(int erro) {
        this.erro = erro;
    }
}
