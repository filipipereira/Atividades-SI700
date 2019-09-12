package f102312_d169482.ft.unicamp.br.projetosi700_a.alunos;

public class Aluno {

    private String nome;
    private int foto;
    private String descricao;

    public Aluno(String nome, int foto, String descricao) {
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
