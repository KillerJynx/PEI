package ModelEntidade;

import java.util.List;

import Model.DAO;

public class Aluno extends Pessoa {
	private String habilidade;
	private String condicao;
	private String necessidadeEspecifica;
	private String dificuldade;
	private String conhecimento;
	private String area;
	
	public Aluno(String nome, String matricula, String telefone, String endereco, String niveldeusuario, String habilidade, String condicao, String necessidadeEspecifica,
			String dificuldade, String conhecimento, String area, String nivelDeUsuario) {
		super(nome, matricula, telefone, endereco, niveldeusuario);
		this.habilidade = habilidade;
		this.condicao = condicao;
		this.necessidadeEspecifica = necessidadeEspecifica;
		this.dificuldade = dificuldade;
		this.conhecimento = conhecimento;
		this.area = area;
	}
	
	public Aluno() {
		
	}
	
	
	public boolean inserirAluno(Aluno a) {
		DAO dao = new DAO();
		if(dao.inserirAluno(a)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	 public static List<Aluno> buscarAlunosPorNivel(String nivel) {
	        return DAO.buscarAlunosPorNivel(nivel);
	    }

	public String getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public String getNecessidadeEspecifica() {
		return necessidadeEspecifica;
	}

	public void setNecessidadeEspecifica(String necessidadeEspecifica) {
		this.necessidadeEspecifica = necessidadeEspecifica;
	}

	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	public String getConhecimento() {
		return conhecimento;
	}

	public void setConhecimento(String conhecimento) {
		this.conhecimento = conhecimento;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	
	
	
	
}
