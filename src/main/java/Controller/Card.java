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
	
	public static String convertLocationsToJson(List<Aluno> alunos) {
	    StringBuilder jsonBuilder = new StringBuilder("[");
	    boolean first = true;

	    for (Aluno aluno : alunos) {
	        if (!first) {
	            jsonBuilder.append(",");
	        } else {
	            first = false;
	        }

	        jsonBuilder.append("{")
	            .append("\"Nome\":\"" + escapeJsonString(aluno.getNome()) + "\",")
	            .append("\"Matricula\":\"" + escapeJsonString(aluno.getMatricula()) + "\",")
	            .append("\"Endereco\":\"" + escapeJsonString(aluno.getEndereco()) + "\",")
	            .append("\"Telefone\":\"" + escapeJsonString(aluno.getTelefone()) + "\",")
	            .append("\"Habilidade\":\"" + escapeJsonString(aluno.getHabilidade()) + "\",")
	            .append("\"Condicao\":\"" + escapeJsonString(aluno.getCondicao()) + "\",")
	            .append("\"NecessidadeEspecifica\":\"" + escapeJsonString(aluno.getNecessidadeEspecifica()) + "\",")
	            .append("\"Dificuldade\":\"" + escapeJsonString(aluno.getDificuldade()) + "\",")
	            .append("\"Conhecimento\":\"" + escapeJsonString(aluno.getConhecimento()) + "\",")
	            .append("\"Area\":\"" + escapeJsonString(aluno.getArea()) + "\"")
	            .append("}");

	    }

	    jsonBuilder.append("]");

	    return jsonBuilder.toString();
	}

}
