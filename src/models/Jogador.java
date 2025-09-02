package models;

public class Jogador {
    private int id;
    private String nome;
    private String dataNascimento;
    private int numCamisa;

    public Jogador(String nome) {
        setNome(nome);
    }

    public Jogador(int id) {
        setId(id);
    }

    public Jogador(String nome, String dataNascimento, int numCamisa) {
        setNome(nome);
        setDataNascimento(dataNascimento);
        setNumCamisa(numCamisa);
    }

    public Jogador(int id, String nome, String dataNascimento, int numCamisa) {
        setId(id);
        setNome(nome);
        setDataNascimento(dataNascimento);
        setNumCamisa(numCamisa);
    }

    public int getId() { return id; }

    public String getNome() { return nome; }

    public String getDataNascimento() { return dataNascimento; }

    public int getNumCamisa() { return numCamisa; }

    public void setId(int id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public void setNumCamisa(int numCamisa) { this.numCamisa = numCamisa; }
}
