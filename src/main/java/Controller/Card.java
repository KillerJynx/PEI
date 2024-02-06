package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ModelEntidade.Aluno;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Card
 */
public class Card extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Card() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String buildJsonResponse(String status, String message, String locationsJson) {
	    String jsonResponse = "{" +
	        "\"status\":\"" + status + "\"," +
	        "\"message\":\"" + message + "\"," +
	        "\"locations\":" + locationsJson +
	        "}";
	    return jsonResponse;
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Aluno aluno = new Aluno();
		
		List<Aluno> alunos = aluno.buscarAlunosPorNivel("A");
		
		String jsonResponse = buildJsonResponse("success", "Usuário possui locais cadastrados", convertLocationsToJson(alunos));
		System.out.println(jsonResponse);
		out.println(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static String escapeJsonString(String input) {
	    if (input == null) {
	        return "";
	    }

	    StringBuilder result = new StringBuilder();
	    for (char c : input.toCharArray()) {
	        switch (c) {
	            case '"':
	                result.append("\\\"");
	                break;
	            case '\\':
	                result.append("\\\\");
	                break;
	            case '\b':
	                result.append("\\b");
	                break;
	            case '\f':
	                result.append("\\f");
	                break;
	            case '\n':
	                result.append("\\n");
	                break;
	            case '\r':
	                result.append("\\r");
	                break;
	            case '\t':
	                result.append("\\t");
	                break;
	            default:
	                result.append(c);
	        }
	    }
	    return result.toString();
	}
	
	public static String convertLocationsToJson(List<Aluno> aluno) {
	    StringBuilder jsonBuilder = new StringBuilder("[");
	    boolean first = true;

	    for (Aluno alunos : aluno) {
	        if (!first) {
	            jsonBuilder.append(",");
	        } else {
	            first = false;
	        }

	        jsonBuilder.append("{")
	            .append("\"Nome\":" + alunos.getNome() + ",")
	            .append("\"Matricula\":\"" + escapeJsonString(alunos.getMatricula()) + "\",")
	            .append("\"Endereco\":\"" + escapeJsonString(alunos.getEndereco()) + "\",")
	            .append("\"Telefone\":\"" + escapeJsonString(alunos.getTelefone()) + "\",")
	            .append("\"Habilidade\":\"" + escapeJsonString(alunos.getHabilidade()) + "\",")
	            .append("\"Condicao\":\"" + escapeJsonString(alunos.getCondicao()) + "\",")
	            .append("\"NecessidadeEspecifica\":\"" + escapeJsonString(alunos.getNecessidadeEspecifica()) + "\",")
	            .append("\"Dificuldade\":\"" + escapeJsonString(alunos.getDificuldade()) + "\",")
	            .append("\"Conhecimento\":\"" + escapeJsonString(alunos.getConhecimento()) + "\",")
	            .append("\"Area\":\"" + escapeJsonString(alunos.getArea()) + "\"");
	            

	        jsonBuilder.append("}");
	    }

	    jsonBuilder.append("]");

	    return jsonBuilder.toString();
	}
}
