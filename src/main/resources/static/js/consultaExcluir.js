// ======================================================
//  EXCLUSÃO DE CONSULTA (MODAL)
// ======================================================

function abrirModalExcluir(id) {
    document.getElementById("excluirConsultaId").value = id;
    document.getElementById("modalExcluir").style.display = "flex";
}

function fecharModalExcluir() {
    document.getElementById("modalExcluir").style.display = "none";
}

function confirmarExclusao() {
    const id = document.getElementById("excluirConsultaId").value;

    fetch(`/api/consulta/excluir/${id}`, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            document.getElementById(`linha-${id}`).remove();
            fecharModalExcluir();
        } else {
            console.error("Status:", response.status);
            alert("Não foi possível excluir a consulta.");
        }
    })
    .catch(err => {
        console.error(err);
        alert("Erro de conexão com o servidor.");
    });
}
