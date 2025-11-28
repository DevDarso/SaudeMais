// ================================
// CONTROLE DO MODAL DE OBSERVAÇÃO
// ================================

// Memória temporária das observações (dura enquanto a página está aberta)
let observacoesMemoria = {};

/**
 * Abre o modal de observação
 * @param {string} id - ID da consulta
 * @param {string} textoOriginal - Texto vindo do banco (apenas na primeira vez)
 */
function abrirObservacao(id, textoOriginal) {

    // Se ainda não tiver sido modificado, usa o texto original
    if (!observacoesMemoria[id]) {
        observacoesMemoria[id] = textoOriginal;
    }

    document.getElementById("obsConsultaId").value = id;
    document.getElementById("obsTexto").value = observacoesMemoria[id];

    document.getElementById("modalObservacao").style.display = "flex";
}

/**
 * Salva o texto editado na memória local
 */
function salvarObservacao() {
    let id = document.getElementById("obsConsultaId").value;
    let novoTexto = document.getElementById("obsTexto").value;

    observacoesMemoria[id] = novoTexto;

    alert("Observação salva com sucesso!");

    fecharObservacao();
}

/**
 * Fecha o modal sem salvar alterações
 */
function fecharObservacao() {
    document.getElementById("modalObservacao").style.display = "none";
}
