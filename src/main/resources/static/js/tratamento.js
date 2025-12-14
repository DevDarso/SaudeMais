// Abrir modal de tratamento
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".btn-tratamento").forEach(btn => {
        btn.addEventListener("click", function () {
            const consultaId = this.getAttribute("data-id");
            abrirModalTratamento(consultaId);
        });
    });
});

function abrirModalTratamento(consultaId) {
    document.getElementById("tConsultaId").value = consultaId;
    document.getElementById("modalTratamento").style.display = "block";

    carregarTratamentosNoModal(); 
}

function fecharTratamento() {
    document.getElementById("modalTratamento").style.display = "none";
}

// ---------------- LISTAR TRATAMENTOS NO MODAL ----------------
function carregarTratamentosNoModal() {

    const consultaId = document.getElementById("tConsultaId").value;

    fetch("/api/tratamento/pesquisa/" + consultaId)
        .then(r => r.json())
        .then(lista => {

            let html = `
                <table class="tabela">
                    <thead>
                        <tr>
                            <th>Medicamento</th>
                            <th>Dosagem</th>
                            <th>Vezes</th>
                            <th>Dias</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
            `;

            lista.forEach(t => {
                html += `
                    <tr>
                        <td>${t.nomeMedicamento}</td>
                        <td>${t.dosagem}</td>
                        <td>${t.vezesAoDia}</td>
                        <td>${t.quantidadeDias}</td>
                        <td>
                            <button onclick="deleteTratModal(${t.id})" class="btn btn-danger">Excluir</button>
                        </td>
                    </tr>
                `;
            });

            html += "</tbody></table>";

            document.getElementById("areaTabelaTratamentos").innerHTML = html;
        });
}


// ---------------- DELETE SOMENTE DENTRO DO MODAL ----------------
function deleteTratModal(id) {
    if (!confirm("Deseja realmente excluir este tratamento?")) return;

    fetch("/api/tratamento/delete/" + id, { method: "DELETE" })
        .then(() => carregarTratamentosNoModal());
}



// ---------------- SALVAR TRATAMENTO ----------------
function salvarTratamento() {

    const dto = {
        consultaId: document.getElementById("tConsultaId").value,
        nomeMedicamento: document.getElementById("tMedicamento").value,
        dosagem: document.getElementById("tDosagem").value,
        vezesAoDia: document.getElementById("tVezes").value,
        quantidadeDias: document.getElementById("tDias").value
    };

    fetch("/api/tratamento/salvar", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(dto)
    })
    .then(r => r.json())
    .then(data => {
        alert("Tratamento salvo!");
        carregarTratamentosNoModal();
    });
}


// ---------------- ABRIR LISTA EM OUTRA PÁGINA ----------------
function listarTratamentos() {
    const consultaId = document.getElementById("tConsultaId").value;
    window.location.href = "/tratamento/lista?consultaId=" + consultaId;
}
