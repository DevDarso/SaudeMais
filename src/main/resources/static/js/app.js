// =====================================
//  SCRIPT GLOBAL DO SISTEMA SAUDEMAIS
// =====================================

// Apenas para confirmar no console:
console.log("JavaScript do SaudeMais carregado!");

// Executa quando a página termina de carregar
document.addEventListener("DOMContentLoaded", () => {
    console.log("Página carregada com sucesso!");
});

/*
    Aqui você pode colocar qualquer função JS
    - validações
    - eventos de clique
    - animações
    - envio de formulários
*/

// Exemplo simples de interação futura:
function mensagemSistema() {
    alert("Bem-vindo ao sistema SaudeMais!");
}

// Podemos adicionar eventos a botões no futuro:
const btnTeste = document.getElementById("btnTeste");

if (btnTeste) {
    btnTeste.addEventListener("click", () => {
        alert("Botão Teste clicado!");
    });
}
