package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModelEntidade.Aluno;

public class DAO {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/pei";
    private static final String USER = "root";
    private static final String PASSWORD = "130544";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver do banco de dados.");
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean autenticar(String matricula, String senha) {
        String sql = "SELECT * FROM pessoa WHERE matricula = ? AND senha = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, matricula);
            stmt.setString(2, senha);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Se houver um resultado, retorna verdadeiro
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar o usuário.");
            e.printStackTrace();
            return false; // Se ocorrer uma exceção, retorna falso
        }
    }
    
    public static boolean inserirAluno(Aluno pessoa) {
        String sql = "INSERT INTO pessoa (nome, matricula, telefone, endereco, habilidade, "
        		+ "condicao, necessidadeEspecifica, dificuldade, conhecimento, area, niveldeusuario) "
        		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getMatricula());
            stmt.setString(3, pessoa.getTelefone());
            stmt.setString(4, pessoa.getEndereco());
            stmt.setString(5, pessoa.getHabilidade());
            stmt.setString(6, pessoa.getCondicao());
            stmt.setString(7, pessoa.getNecessidadeEspecifica());
            stmt.setString(8, pessoa.getDificuldade());
            stmt.setString(9, pessoa.getConhecimento());
            stmt.setString(10, pessoa.getArea());
            stmt.setString(11, pessoa.getNiveldeusuario());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno no banco de dados: " + e.getMessage());
            return false;
        }
    }
    
    public static List<Aluno> buscarAlunosPorNivel(String nivel) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM pessoa WHERE niveldeusuario = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nivel);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Recupere os dados do aluno do ResultSet e crie um objeto Aluno
                String nome = rs.getString("nome");
                String matricula = rs.getString("matricula");
                String telefone = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                String habilidade = rs.getString("habilidade");
                String condicao = rs.getString("condicao");
                String necessidadeEspecifica = rs.getString("necessidadeEspecifica");
                String dificuldade = rs.getString("dificuldade");
                String conhecimento = rs.getString("conhecimento");
                String area = rs.getString("area");
                String niveldeusuario = rs.getString("niveldeusuario");

                Aluno aluno = new Aluno(nome, matricula, telefone, endereco, niveldeusuario,
                        habilidade, condicao, necessidadeEspecifica, dificuldade, conhecimento, area, niveldeusuario);

                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos no banco de dados: " + e.getMessage());
        }

        return alunos;
    }

    


    
    
}
