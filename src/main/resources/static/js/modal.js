// delegação de eventos: abre observação
document.addEventListener('click', function (e) {
    // Observação
    const btnObs = e.target.closest && e.target.closest('.btn-observacao');
    if (btnObs) {
        const id = btnObs.getAttribute('data-id');
        // data-desc vem já com texto, sem quebrar
        const descricao = btnObs.getAttribute('data-desc') || '';
        abrirObservacao(id, descricao);
        return;
    }

    // Tratamento
    const btnTrat = e.target.closest && e.target.closest('.btn-tratamento');
    if (btnTrat) {
        const id = btnTrat.getAttribute('data-id');
        abrirTratamento(id);
        return;
    }
});
/*
function abrirModal(paciente, data, horario, descricao) {
    document.getElementById("mPaciente").value = paciente;
    document.getElementById("mData").value = data;
    document.getElementById("mHorario").value = horario;
    document.getElementById("mDescricao").value = descricao;

    document.getElementById("modal").style.display = "flex";
} 

function fecharModal() {
    document.getElementById("modal").style.display = "none";
}*/

// --- MODAL TRATAMENTO ---
function abrirTratamento(consultaId, paciente) {
    document.getElementById("tConsultaId").value = consultaId;
    document.getElementById("modalTratamento").style.display = "flex";
}

function fecharTratamento() {
    document.getElementById("modalTratamento").style.display = "none";
}
function salvarTratamento() {
    const dados = {
        consultaId: document.getElementById("tConsultaId").value,
        medicamento: document.getElementById("tMedicamento").value,
        dosagem: document.getElementById("tDosagem").value,
        vezesDia: document.getElementById("tVezes").value,
        dias: document.getElementById("tDias").value
    };

    fetch("/tratamento/salvar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dados)
    })
    .then(r => r.text())
    .then(msg => alert(msg));
}
function listarTratamentos() {
    const id = document.getElementById("tConsultaId").value;

    fetch("/tratamento/listar/" + id)
        .then(r => r.json())
        .then(lista => {
            let texto = "Tratamentos desta consulta:\n\n";

            lista.forEach(t => {
                texto +=
                    "- " + t.medicamento +
                    " | " + t.dosagem +
                    " | " + t.vezesDia + "x ao dia" +
                    " | " + t.dias + " dias\n";
            });

            alert(texto);
        });
}


