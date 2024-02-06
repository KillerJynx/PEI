// Captura os links do menu
const cadastrarAlunoLink = document.getElementById('cadastrarAluno');
const visualizarAlunoLink = document.getElementById('visualizarAluno');

visualizarAlunoLink.addEventListener('click', function() {
	fetch('Card', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		}
	})
		.then(response => response.json())
		.then(data => {
			// Aqui você manipula os dados recebidos do backend
			console.log(data);
		})
		.catch(error => {
			console.error('Erro:', error);
		});
});

// Captura a janela modal e o botão para fechar a modal
const modalCadastroAluno = document.getElementById('modalCadastroAluno');
const closeModalButton = document.querySelector('.close');

// Adiciona eventos de clique aos links
cadastrarAlunoLink.addEventListener('click', function() {
	// Exibe a janela modal ao clicar em "Cadastrar Aluno"
	modalCadastroAluno.style.display = 'block';
});

visualizarAlunoLink.addEventListener('click', visualizarAluno);

// Adiciona evento de clique para fechar a janela modal
closeModalButton.addEventListener('click', function() {
	modalCadastroAluno.style.display = 'none';
});

// Função para exibir a tela de visualização de aluno
function visualizarAluno() {
	const content = document.querySelector('.content');
	content.innerHTML = `
                <h2>Informações do Aluno</h2>
                <p><strong>Nome:</strong> João da Silva</p>
                <p><strong>Endereço:</strong> Rua A, 123</p>
                <p><strong>CPF:</strong> 123.456.789-00</p>
                <p><strong>Área de Estudo:</strong> Matemática</p>
                <button id="criarPei">Criar PEI</button>
            `;

	const criarPeiButton = document.getElementById('criarPei');
	criarPeiButton.addEventListener('click', criarPei);
}

// Função para criar o PEI (Plano Educacional Individualizado)
function criarPei() {
	alert('Funcionalidade em desenvolvimento...');
}

