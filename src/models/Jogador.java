package models;

public class Jogador {
    private int id;
    private String nome;
    private int numCamisa;

    public Jogador(int id) {
        setId(id);
    }

    public Jogador(String nome, int numCamisa) {
        setNome(nome);
        setNumCamisa(numCamisa);
    }

    public Jogador(int id, String nome, int numCamisa) {
        setId(id);
        setNome(nome);
        setNumCamisa(numCamisa);
    }

    public int getId() { return id; }

    public String getNome() { return nome; }

    public int getNumCamisa() { return numCamisa; }

    public void setId(int id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setNumCamisa(int numCamisa) { this.numCamisa = numCamisa; }
}
