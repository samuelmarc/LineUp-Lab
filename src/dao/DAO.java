package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    public void inserir(T data) throws SQLException;
    public void excluir(T data) throws SQLException;
    public void atualizar(T data) throws SQLException;
    public List<T> obterTudo() throws SQLException;
    public T obter(T data) throws SQLException;
}
