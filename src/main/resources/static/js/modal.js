// ======================================================
//  DELEGAÇÃO DE EVENTOS PARA BOTÕES NAS TABELAS
// ======================================================
document.addEventListener("click", function (e) {

    // --- BOTÃO OBSERVAÇÃO ---
    const btnObs = e.target.closest?.(".btn-observacao");
    if (btnObs) {
        const id = btnObs.getAttribute("data-id");
        const descricao = btnObs.getAttribute("data-desc") || "";
        abrirObservacao(id, descricao);
        return;
    }

    // --- BOTÃO TRATAMENTO ---
    const btnTrat = e.target.closest?.(".btn-tratamento");
    if (btnTrat) {
        const consultaId = btnTrat.getAttribute("data-id");
        abrirTratamento(consultaId);
        return;
    }

    // --- BOTÃO EXCLUIR ---
    const btnExcluir = e.target.closest?.(".btn-excluir");
    if (btnExcluir) {
        const id = btnExcluir.getAttribute("data-id");
        abrirModalExcluir(id);
        return;
    }
});

// ======================================================
//  REDIRECIONA PARA A PÁGINA DE LISTAGEM
// ======================================================
function abrirTratamento(consultaId) {
    window.location.href = `/tratamento/lista?consultaId=${consultaId}`;
}