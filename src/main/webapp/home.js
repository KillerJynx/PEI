document.addEventListener("DOMContentLoaded", function() {
    const cadastrarAlunoLink = document.getElementById('cadastrarAluno');
    const visualizarAlunoLink = document.getElementById('visualizarAluno');

    const modalCadastroAluno = document.getElementById('modalCadastroAluno');
    const closeModalButton = modalCadastroAluno.querySelector('.close');

    cadastrarAlunoLink.addEventListener('click', function() {
        modalCadastroAluno.style.display = 'block';
    });

    visualizarAlunoLink.addEventListener('click', visualizarAluno);


    closeModalButton.addEventListener('click', function() {
        modalCadastroAluno.style.display = 'none';
    });

    function visualizarAluno() {
        fetch('Card', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            const content = document.querySelector('.content');
            content.innerHTML = ''; 
            if (data.hasOwnProperty('locations') && Array.isArray(data.locations)) {
                data.locations.forEach(aluno => {
                    const alunoInfo = document.createElement('div');
                    alunoInfo.classList.add('aluno-info');

                    // Conteúdo do card do aluno 
                    alunoInfo.innerHTML = `
                        <h2>Informações do Aluno</h2>
                        <p><strong>Nome:</strong> ${aluno.Nome}</p>
                        <p><strong>Área de Estudo:</strong> ${aluno.Area}</p>
                        <button class="visualizar-info-btn">Visualizar Informações</button>
                    `;

                    content.appendChild(alunoInfo); // Adiciona as informações do aluno ao conteúdo

                    
                    const visualizarInfoBtn = alunoInfo.querySelector('.visualizar-info-btn');
                    visualizarInfoBtn.addEventListener('click', function() {
                        abrirModal(aluno);
                    });
                });
            } else {
               
                console.error('Os dados retornados não contêm um array de alunos:', data);
            }
        })
        .catch(error => {
            console.error('Erro:', error);
        });
    }
    
function abrirModal(aluno) {
    const modalContent = `
        <div id="modalEstudanteInformacoes" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h2>Informações Completas do Aluno</h2>
                <div id="studentDetailsContainer">
                    <p><strong>Nome:</strong> ${aluno.Nome}</p>
                    <p><strong>Matrícula:</strong> ${aluno.Matricula}</p>
                    <p><strong>Endereço:</strong> ${aluno.Endereco}</p>
                    <p><strong>Telefone:</strong> ${aluno.Telefone}</p>
                    <p><strong>Habilidade:</strong> ${aluno.Habilidade}</p>
                    <p><strong>Condição:</strong> ${aluno.Condicao}</p>
                    <p><strong>Necessidade Específica:</strong> ${aluno.NecessidadeEspecifica}</p>
                    <p><strong>Dificuldade:</strong> ${aluno.Dificuldade}</p>
                    <p><strong>Conhecimento:</strong> ${aluno.Conhecimento}</p>
                    <p><strong>Área:</strong> ${aluno.Area}</p>
                </div>
            </div>
        </div>
    `;

    document.body.insertAdjacentHTML('beforeend', modalContent);

    
    const modal = document.getElementById('modalEstudanteInformacoes');

   
    const closeModalButton = modal.querySelector('.close');

    
    closeModalButton.addEventListener('click', () => {
        modal.remove(); 
    });


    modal.style.display = 'block';
}



});
