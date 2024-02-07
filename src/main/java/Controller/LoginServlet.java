package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Model.DAO;
import ModelEntidade.Aluno;
import ModelEntidade.Pessoa;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String matricula = request.getParameter("matricula");
        String senha = request.getParameter("senha");
        System.out.println(matricula);
        System.out.println(senha);
        DAO dao = new DAO();
        
        try {
            boolean autenticado = dao.autenticar(matricula, senha);
            if (autenticado) {
                // Redireciona para a página de sucesso
            	try(PrintWriter out = response.getWriter()){
            		response.sendRedirect("home.html");
        		}
            } else {
                // Exibe uma mensagem de erro
            	try(PrintWriter out = response.getWriter()){
            		 response.sendRedirect("index.html?erro=1");
        		}
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Trate a exceção adequadamente
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String nome = request.getParameter("nome");
		  String endereco = request.getParameter("endereco");
		  String telefone = request.getParameter("telefone");
		  String matricula = request.getParameter("matricula");
		  String habilidade = request.getParameter("habilidade");
		  String condicao = request.getParameter("condicao");
		  String necessidadeEspecifica = request.getParameter("necessidadeEspecifica");
		  String dificuldade = request.getParameter("dificuldade");
		  String conhecimento = request.getParameter("conhecimento");
		  String area = request.getParameter("area");
		  String nivelDeUsuario =  request.getParameter("niveldeusuario");
		  
		  Aluno aluno = new Aluno(nome, matricula, telefone, endereco,   nivelDeUsuario, habilidade, condicao, necessidadeEspecifica, 
				  dificuldade, conhecimento, area, nivelDeUsuario);
		  aluno.inserirAluno(aluno);
		  
		  
		  
	}

}
